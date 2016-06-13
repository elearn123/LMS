package learnitysce;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.grlea.log.DebugLevel;
import org.grlea.log.SimpleLogger;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

import sc.sce.UrlModifyingVisitor;
//import org.htmlparser.scanners.*;

/**
 * Title:        LearnITy
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Aunwesha
 * @author Asish Kumar Samanta
 * @version 3.0
 */

public class SCClient extends HttpServlet {
    protected Socket socket;
    protected DataInputStream istream;
    protected DataOutputStream ostream;
    protected boolean stop = false;
    protected double protocolVersion = 2.1; // by default
    private static final SimpleLogger log = new SimpleLogger(SCClient.class);
	String htmlDoc = "";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	                       throws ServletException, IOException {
	    
	    response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	String urlname = request.getParameter("page");    
    	String mthod = 	request.getMethod();
    	if (urlname != null) {
    		
	    	//System.out.println("urlname = "+urlname);
	    	log.debug("urlname = "+urlname);
	    	if(!urlname.startsWith("http")) {
	    		urlname= "http://"+urlname;
	    	}
	    	if (mthod.equalsIgnoreCase("GET")) {
	    		htmlDoc = linkExtractor(urlname);
	    	}
	    	else {
	    		mthod = mthod.toUpperCase();
	    		htmlDoc = linkExtractor(urlname, mthod, request);
	    	}
	    	try {                   	
				connect(); 
				sendRefreshAction(); 
			}
			catch(UnknownHostException uhe) {
				//uhe.printStackTrace();
				 log.dbe(DebugLevel.L1_FATAL,uhe);
			}
			catch(IOException ioe) {
				//ioe.printStackTrace();
				 log.dbe(DebugLevel.L1_FATAL,ioe);
			} 
			catch(Exception exp) {
				//exp.printStackTrace();
				 log.dbe(DebugLevel.L1_FATAL,exp);
			}   
	    }
	    out.println(htmlDoc);
	}
	
	public String linkExtractor(String location) {
        try {
            Parser parser   = new Parser(location); // Create the parser object 
            java.net.URLConnection con = parser.getConnection();
			//System.out.println("Location = "+con.getURL());    
			log.debug("Location = "+con.getURL());
			if (!location.equalsIgnoreCase(con.getURL().toString())) {
				parser   = new Parser(con.getURL().toString());
			}       
			UrlModifyingVisitor visitor = new UrlModifyingVisitor(parser, "./learnitysce.SCClient?page=", "./learnitysce.SCClientForFrame?page=");
			parser.visitAllNodesWith(visitor);			
	        return visitor.getModifiedResult(); 			
        }
        catch (ParserException e) {
           // e.printStackTrace();
           log.dbe(DebugLevel.L1_FATAL,e);
            return "";
        }
	}
	
	public String linkExtractor (String location, String mthod, HttpServletRequest request) {
		URL url;
        HttpURLConnection connection;
        StringBuffer buffer;
        PrintWriter out;

        try  {
            // from the 'action' (relative to the refering page)
            
            
            
            url = new URL (location);
            connection = (HttpURLConnection)url.openConnection ();
            connection.setRequestMethod (mthod);

            connection.setDoOutput (true);
            connection.setDoInput (true);
            connection.setUseCaches (false);

            // more or less of these may be required
            // see Request Header Definitions: http://www.ietf.org/rfc/rfc2616.txt
            Enumeration headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String headerName = (String)headerNames.nextElement();
				String headerValue = (String)request.getHeader(headerName);
				if (headerName.equalsIgnoreCase("HOST")) {
					headerValue = url.getHost();
				}
				connection.setRequestProperty (headerName, headerValue);
			}
            Cookie[] cookies = request.getCookies();
            
			if ((cookies == null) || (cookies.length ==0)) {
			}
			else {
				//String[] cookieHeaders = new String[cookies.length];
				connection.setRequestProperty("cookie",generateCookieHeader(cookies));
			}
            //connection.setRequestProperty ("Referer", "http://www.usps.com/zip4/citytown.htm");
            //connection.setRequestProperty ("User-Agent", "Zip.java/1.0");

            buffer = new StringBuffer (1024);
            // 'input' fields separated by ampersands (&)
            //buffer.append ("zipcode=");
            //buffer.append (code);
            // buffer.append ("&");
            // etc.
			
			Enumeration enum1 = request.getParameterNames();
			while (enum1.hasMoreElements()) {
				String name = (String)enum1.nextElement();
				if (name.equals("page") || name.equals("mthod")) continue; // Skip to/from
				buffer.append (name+"=");
				String value = request.getParameter(name);
				buffer.append (value);
				if(enum1.hasMoreElements()) {
					buffer.append ("&");
				}
			}
			//System.out.println(buffer.toString());
			log.debug(buffer.toString());
            out = new PrintWriter (connection.getOutputStream ());
            out.print (buffer);
            out.close ();            
            
			Parser parser = new Parser(connection); // Create the parser object            
			UrlModifyingVisitor visitor = new UrlModifyingVisitor(parser, "./learnitysce.SCClient?page=", "./learnitysce.SCClientForFrame?page=");
			parser.visitAllNodesWith(visitor);			
	        return visitor.getModifiedResult(); 	
            
        }
        catch (Exception e) {
          // e.printStackTrace();
          log.dbe(DebugLevel.L1_FATAL,e);
           return "";
        }
	}
	
	/**
     * set cookies to send in a HttpURLConnection<br>
     * This method should only be called before any
     * parameters are posted
     * and before the connection is made.
     * @param urlConn the HttpURLConnection to send
     * the cookies through
     * @param cookies the cookies to send
     */
    public void postCookies(HttpURLConnection urlConn, Cookie[] cookies) {
        
    }

    /**
     * generate a HTTP cookie header value string
     * from an array of cookies
     * @param cookies the cookies which should be set
     * in the header value
     * @return A string containing the HTTP Cookie
     * Header value
     */
    private String generateCookieHeader(Cookie[] cookies) {
        StringBuffer buf = new StringBuffer();

        for (int i=0; i < cookies.length;i++) {
            buf.append(cookies[i].getName());
            buf.append("=");
            buf.append(cookies[i].getValue());
            if (i+1 != cookies.length) {
                buf.append("; ");
            }
            else buf.append(" ");
        }
        return buf.toString();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws IOException, ServletException {
        doGet(request, response);
    }
	
    public void connect() throws UnknownHostException, IOException, Exception {
    	
		int portnumber = Integer.parseInt((String)getServletContext().getAttribute("port"));
		// set up the client socket
		socket = new Socket("localhost", portnumber);
	
		// Get the output stream
		ostream = new DataOutputStream(socket.getOutputStream());
	
		// Get an input stream to correspond to the client socket
		istream = new DataInputStream(socket.getInputStream());
		
		synchronized (istream) {
		    synchronized (ostream) {
				sendProtocol(protocolVersion);
			}
		}
		
    }

    protected void sendRefreshAction() throws IOException  {
	// Send a message to the server about our desired protocol
		synchronized (ostream) {
			ostream.writeShort(40);
			ostream.writeBoolean(true);
		}
    }
    protected void sendProtocol(double version)
	throws IOException
    {
	// Send a message to the server about our desired protocol
	synchronized (ostream)
	    {
		ostream.writeShort(SCCommand.SETPROTO);
		ostream.writeDouble(version);
	    }
    }
}
