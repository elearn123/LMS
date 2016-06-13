package learnitysce.cobrowse;

import java.io.File;
import java.io.FileWriter;

import learnityInit.Host;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.grlea.log.SimpleLogger;
//import java.util.Enumeration;

public class Cobrowse {
	private static final SimpleLogger log = new SimpleLogger(Cobrowse.class,true);
	public static void cobrowse(String url_name, String ip, int serverport,String context)
    {    	
            
	    HttpClient client = new HttpClient();
		HttpMethod method1=new GetMethod(url_name);
		String proxy="http://"+ip+":"+serverport+ context +"/servlet/learnitysce.cobrowse.CobrowseSubMain";
		//System.out.println("referer :" +proxy);
		log.debug("referer :" +proxy);
		
		String theFileIfAny = "/";
		String dotSlash = "/";
		
		if (url_name.length() > 0) {
			String tmpValue = url_name;	
			if (url_name.indexOf("//") != -1) {
				tmpValue = url_name.substring(
					url_name.indexOf("//") + 2);
					
				//System.out.println("tmpValue is " + tmpValue);
				log.debug("tmpValue is " + tmpValue);
				if (tmpValue.indexOf("/") != -1) {
					theFileIfAny = tmpValue.substring(
						tmpValue.indexOf("/") + 1);
						
					//System.out.println("theFileIFAny is " + theFileIfAny);
					log.debug("theFileIFAny is " + theFileIfAny);
					if (theFileIfAny.indexOf("/") != -1) {
					theFileIfAny = theFileIfAny.substring(0,
						theFileIfAny.indexOf("/"));}
					
					//System.out.println("theFileIFAny2 is " + theFileIfAny);																						
					log.debug("theFileIFAny2 is " + theFileIfAny);
					if (url_name.lastIndexOf("/") != -1)
					{
						dotSlash = url_name.substring(0,url_name.lastIndexOf("/"));						
					}
					
					if (dotSlash.lastIndexOf("/") != -1)
					{
						dotSlash = dotSlash.substring(dotSlash.lastIndexOf("/")+1,dotSlash.length());
						//System.out.println("dotSlash1: "+dotSlash);
						log.debug("dotSlash1: "+dotSlash);
					}				
							
					//System.out.println("thisURL is now " + url_name);
					log.debug("thisURL is now " + url_name);
				}
			}
		}
										
		executeMethod(client,method1);
		/*************************************/								
	/*	Enumeration enu = request.getHeaderNames();
		//String proxy=request.getHeader("referer");
		String proxy="http://node15:8080/web/servlet/cobrowse.Cobrowse";
		System.out.println("referer :" +proxy);
	          
		System.out.println("HEADERS");
		
			while (enu.hasMoreElements())
			{
				String name = (String)enu.nextElement();
				Enumeration values =  request.getHeaders(name);
				if(values!=null)
				{
					while (values.hasMoreElements())
					{
						String value = (String)values.nextElement();
						System.out.println(name +" : " +value);
					}
				}
			}*/    	  
	    /*************************************/	
	    
//		byte[] responseBody = method1.getResponseBody();
//		Header[] header_request=method1.getRequestHeaders();
		Header host= method1.getRequestHeader("Host");
		NameValuePair nvpair = (NameValuePair)host;
		String strhost = nvpair.getValue();
		//System.out.println("Host :" +nvpair.getValue());
		log.debug("Host :" +nvpair.getValue());
		/**************** Handle redirection ***************/
		Header redirectionhost= method1.getResponseHeader("Location");
				
		if(redirectionhost!=null)
		{
			NameValuePair locnvpair = (NameValuePair)redirectionhost;
			String strresponsehost = locnvpair.getValue();
			//System.out.println("Location :" +locnvpair.getValue());
			log.debug("Location :" +locnvpair.getValue());
			
			if (strresponsehost.lastIndexOf("/") != -1)
			{
				strresponsehost = strresponsehost.substring(0,strresponsehost.lastIndexOf("/"));
			}
			//System.out.println("Location1 :" +strresponsehost);
			log.debug("Location1 :" +strresponsehost);
			method1=new GetMethod(strresponsehost);			
			
			executeMethod(client,method1);
		}
		/*******************************************/
		
		/*System.out.println("Status code :"+method1.getStatusCode());
		if(method1.getStatusCode()==302)
		{			
			method1.setFollowRedirects(true);
			System.out.println("Status code :"+method1.getStatusCode());	
		}*/
				
		
	/*	Header[] header_response=method1.getResponseHeaders();						
		
		for(int i=0;i<header_request.length-1;i++)
	    {
	    	Header header_rq=header_request[i];
	     	System.out.println("*****header_request in cobrowse*****"+header_rq);
	    }
	    
	    for(int i=0;i<header_response.length;i++)
	    {
	    	Header header_rs=header_response[i];
	     	System.out.println("*****header_response in cobrowse*****"+header_rs);
	    }	    
	    
	    HttpState states =client.getState();
	    org.apache.commons.httpclient.Cookie[] token_array=states.getCookies();
	    */	     	   
	    
	    String session_name=method1.getResponseBodyAsString();
		
		reWriteHtml(session_name,proxy,strhost,theFileIfAny,dotSlash);	    	   
    	method1.releaseConnection();
    	    	
    }
    
    public static void reWriteHtml(String session_name,String proxy,String strhost,String theFileIfAny,String dotSlash) 
	{
		//System.out.println("********in rewrite HTML *********");
		log.debug("********in rewrite HTML *********");
		String regexpForImgSrc = "src=\"";	//SRC caps to be handled later
	    String regexpForImgHttpSrc = "src=\"http";
	    String regexpForDoubleDotImg = "src=\"../";
	    String regexpForSingleDotImg = "src=\"./";
	    String regexpForSrcProxy = "src = "+proxy;	 	    
	    
	    String regexpForHref = "href=\"";
	    String regexpForHrefHttp = "href=\"http";
	    String regexpForDoubleDotHref = "href=\"../";
	    String regexpForHrefProxy = "href = "+proxy;
	    String regexpForSingleDotHref = "href=\"./";
	    String regexpForCssHref = "href=\".*css\"";
	    String regexpForHrefProxyPage = "href = "+proxy+"?page=";
	    	    
	    String regexpForAction = "action=\"";
	    String regexpForSingleDotAction = "action=\"./";
	    String regexpForDoubleDotAction = "action=\"../";
	    
		Pattern patternForImgSrc = regExprInfo("regexpForImgSrc",regexpForImgSrc);
		Pattern patternForImgHttpSrc = regExprInfo("regexpForImgHttpSrc",regexpForImgHttpSrc);
		Pattern patternForHref = regExprInfo("regexpForHref",regexpForHref);
		Pattern patternForHttpHref = regExprInfo("regexpForHrefHttp",regexpForHrefHttp);
		Pattern patternForDoubleDotHref = regExprInfo("regexpForDoubleDotHref",regexpForDoubleDotHref);
		Pattern patternForDoubleDotImg = regExprInfo("regexpForDoubleDotImg",regexpForDoubleDotImg);
		Pattern patternForSrcProxy = regExprInfo("regexpForSrcProxy",regexpForSrcProxy);
		Pattern patternForHrefProxy = regExprInfo("regexpForHrefProxy",regexpForHrefProxy);
		Pattern patternForSingleDotImg = regExprInfo("regexpForSingleDotImg",regexpForSingleDotImg);
		Pattern patternForSingleDotHref = regExprInfo("regexpForSingleDotHref",regexpForSingleDotHref);		
		Pattern patternForCssHref = regExprInfo("regexpForCssHref",regexpForCssHref);	
		Pattern patternForHrefProxyPage = regExprInfo("regexpForHrefProxyPage",regexpForHrefProxyPage);				
		
		Pattern patternForAction = regExprInfo("regexpForAction",regexpForAction);
		Pattern patternForSingleDotAction = regExprInfo("regexpForSingleDotAction",regexpForSingleDotAction);
		Pattern patternForDoubleDotAction = regExprInfo("regexpForDoubleDotAction",regexpForDoubleDotAction );
		
		PatternMatcher matcher=new Perl5Matcher();
		
		/* InputStream in = method1.getResponseBodyAsStream();
		    int c;
		    while((c = in.read()) != -1){
		    	out.write(c);
		    }*/
		/************check for img source***********/
		
		if (!matcher.contains(session_name,patternForImgSrc))
		{					    	  	  			  			       
		   // System.out.println(" No patternForImgSrc");
		   log.debug(" No patternForImgSrc");
		}				
		if (matcher.contains(session_name,patternForImgHttpSrc))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForImgSrc,
                            new Perl5Substitution(
   	                        "src = \""+proxy+"?page=/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
            
            //System.out.println("patternForImgHttpSrc Found");
            log.debug("patternForImgHttpSrc Found");
        }	          
       if(matcher.contains(session_name,patternForSingleDotImg))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForSingleDotImg,
                            new Perl5Substitution(
   	                        "src = \"http://"+strhost+"/"+theFileIfAny+"/"+dotSlash+"/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
            //System.out.println("patternForSingleDotImg Found");						
            log.debug("patternForSingleDotImg Found");
		}  
		/*if(matcher.contains(session_name,patternForSingleDotImg))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForSingleDotImg,
                            new Perl5Substitution(
   	                        "src = \"http://"+strhost+"/"+theFileIfAny+"/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
            System.out.println("patternForSingleDotImg Found");						
		}*/
		if(matcher.contains(session_name,patternForDoubleDotImg))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForDoubleDotImg,
                            new Perl5Substitution(
   	                        "src = \"http://"+strhost+"/"+theFileIfAny+"/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
           // System.out.println("patternForDoubleDotImg Found");
           log.debug("patternForDoubleDotImg Found");
		}
		if (!matcher.contains(session_name,patternForSrcProxy))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
	   	                	patternForImgSrc,
	                            new Perl5Substitution(
	   	                        "src = \"http://"+strhost+"/"),
	           	                session_name,
	                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
	            
	            //System.out.println("patternForImgSrc Only Found");
	            log.debug("patternForImgSrc Only Found");
		}
		
		/************check for href source********/	
		
		if(matcher.contains(session_name,patternForSingleDotHref))
		{			
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForSingleDotHref,
                            new Perl5Substitution(
   	                        "href = \""+proxy+"?page=http://"+strhost+"/"+theFileIfAny+"/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
           // System.out.println("patternForSingleDotHref Found");
           log.debug("patternForSingleDotHref Found");
		}
		if (matcher.contains(session_name,patternForCssHref))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForHrefProxyPage,
                            new Perl5Substitution(
   	                        "href = "),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
           	//System.out.println("CSS Src");
           	log.debug("CSS Src");
		}
		if(matcher.contains(session_name,patternForDoubleDotHref))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForDoubleDotHref,
                            new Perl5Substitution(
   	                        "href = \""+proxy+"?page=http://"+strhost+"/"+theFileIfAny),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
           // System.out.println("patternForDoubleDotHref Found");            
           log.debug("patternForDoubleDotHref Found");
		}	
		
						
		if(matcher.contains(session_name,patternForHttpHref))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
       	                	patternForHttpHref,
                                new Perl5Substitution(
       	                        "href = \""+proxy+"?page=http"),
               	                session_name,
                       	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                        
           // System.out.println("patternForHttpHref Found");
           log.debug("patternForHttpHref Found");
                       	        
		}				
		
		if (!matcher.contains(session_name,patternForHrefProxy))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
       	                	patternForHref,
                                new Perl5Substitution(
       	                        "href = \""+proxy+"?page=http://"+strhost+"/"),
               	                session_name,
                       	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);	                       	        			
												
			//System.out.println("patternForHref Found");
			log.debug("patternForHref Found");
		}

		/************ check for Action ********/
		/******************new****************/
		if(matcher.contains(session_name,patternForSingleDotAction))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForSingleDotAction,
                            new Perl5Substitution(
   	                        "action = \""+proxy+"?page=http://"+strhost+"/"+theFileIfAny+"/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
            //System.out.println("patternForSingleDotAction Found");						
            log.debug("patternForSingleDotAction Found");
		}	

		if(matcher.contains(session_name,patternForDoubleDotAction))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
   	                	patternForDoubleDotAction,
                            new Perl5Substitution(
   	                        "action = \""+proxy+"?page=http://"+strhost+"/"+theFileIfAny+"/"),
           	                session_name,
                   	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);
                   	        
            //System.out.println("patternForDoubleDotAction Found");						
            log.debug("patternForDoubleDotAction Found");
		}
		
		/**************new******************/
		writetoFile(session_name);
		
		if (matcher.contains(session_name,patternForAction))
		{
			session_name=org.apache.oro.text.regex.Util.substitute(matcher,
       	                	patternForAction,
                                new Perl5Substitution(
       	                        "action = \""+proxy+"?page=http://"+strhost+"/"),
               	                session_name,
                       	        org.apache.oro.text.regex.Util.SUBSTITUTE_ALL);	                       	        			
												
			//System.out.println("patternForAction Found");
			log.debug("patternForAction Found");
		}						
	
		//writetoFile(session_name);
			
		//System.out.println("********href session name *********");
		log.debug("********href session name *********");
	}
    
    public static Pattern regExprInfo(String thisName,String thisExpr)
	{

		//String	regExprName;
		Pattern regExprPattern = null;		
		
		PatternCompiler compiler=new Perl5Compiler();
		try {
			//regExprName = thisName;
			regExprPattern = compiler.compile(thisExpr,
				Perl5Compiler.CASE_INSENSITIVE_MASK);
			//System.out.println("compiled" + regExprName);
			return regExprPattern;
			
		} catch (MalformedPatternException mae) {
			//System.out.println("trouble compiling " + thisName +
				//" -> " + mae.getMessage());
			log.fatal("trouble compiling " + thisName+" -> " + mae.getMessage());
		}
		return regExprPattern;
	}
	
	public static void writetoFile(String strpage) 
	{
		
		try
		{
			char buffer[]=new char[strpage.length()];
	        strpage.getChars(0,strpage.length(),buffer,0);  
	        
	        String pathname=Host.getAdminPath();
	        
	        FileWriter f1=new FileWriter(pathname+File.separatorChar+"page.txt");
	        f1.write(buffer);
	        f1.close();
        }
        catch(Exception ex)
        {
        	//System.out.println("IO Exception :" +ex);
        	log.fatal("IO Exception :" +ex);
        }
    }
    
    public static void executeMethod(HttpClient client,HttpMethod method1) 
	{
		int statusCode = -1;
	    int attempt = 0;
		    while (statusCode == -1 && attempt < 3)
		    {   
			   	try 
			   	{        
			            attempt=attempt+1;
			            if(attempt==3)
			            {
			            	//System.out.println("Error in Connecting");
			            	log.debug("Error in Connecting");
			            	//response.sendRedirect( "../html/error.html" );
			            }
			           	statusCode = client.executeMethod(method1); 
			    }
				catch (Exception e) {
			          // System.err.println("A recoverable exception occurred, retrying." +  e.getMessage());
			          log.fatal("A recoverable exception occurred, retrying." +  e.getMessage());
				}               
		    }
	}
	
	public static void postcobrowse(String url_name, String ip, int serverport,String context,HttpClient client,PostMethod method2)
    {    	
    	//System.out.println("working on post");
    	log.debug("working on post");
    	
	String proxy="http://"+ip+":"+serverport+ context +"/servlet/learnitysce.cobrowse.CobrowseSubMain";			
		String theFileIfAny = "/";
		String dotSlash = "/";
    	
    	if (url_name.length() > 0) {
			String tmpValue = url_name;	
			if (url_name.indexOf("//") != -1) {
				tmpValue = url_name.substring(
					url_name.indexOf("//") + 2);
					
				//System.out.println("tmpValue is " + tmpValue);
				log.debug("tmpValue is " + tmpValue);
				if (tmpValue.indexOf("/") != -1) {
					theFileIfAny = tmpValue.substring(
						tmpValue.indexOf("/") + 1);
						
					//System.out.println("theFileIFAny is " + theFileIfAny);
					log.debug("theFileIFAny is " + theFileIfAny);
					if (theFileIfAny.indexOf("/") != -1) {
					theFileIfAny = theFileIfAny.substring(0,
						theFileIfAny.indexOf("/"));}
					
					//System.out.println("theFileIFAny2 is " + theFileIfAny);
					log.debug("theFileIFAny2 is " + theFileIfAny);												
					if (url_name.lastIndexOf("/") != -1)
					{
						dotSlash = url_name.substring(0,url_name.lastIndexOf("/"));						
					}
					
						if (dotSlash.lastIndexOf("/") != -1)
						{
							dotSlash = dotSlash.substring(dotSlash.lastIndexOf("/")+1,dotSlash.length());
							//System.out.println("dotSlash1: "+dotSlash);
							log.debug("dotSlash1: "+dotSlash);
						}
					
					/*if (tmpValue.length() > 1)
						url_name = url_name.
							substring(0,url_name.
							indexOf("/" + theFileIfAny));*/								
							
					//System.out.println("thisURL is now " + url_name);
					log.debug("thisURL is now " + url_name);
				}
			}
		}
    	
//    	byte[] responseBody = method2.getResponseBody();
//		Header[] header_request=method2.getRequestHeaders();
		Header host= method2.getRequestHeader("Host");
		NameValuePair nvpair = (NameValuePair)host;
		String strhost = nvpair.getValue();
		//System.out.println("Host :" +nvpair.getValue());	
    		log.debug("Host :" +nvpair.getValue());
    	String session_name=method2.getResponseBodyAsString();	
		
		reWriteHtml(session_name,proxy,strhost,theFileIfAny,dotSlash);
		/*InputStream in = method2.getResponseBodyAsStream();
		    int c;
		    while((c = in.read()) != -1){
		    	out.write(c);
		    }
		*/
    }
}