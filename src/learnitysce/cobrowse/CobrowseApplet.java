package learnitysce.cobrowse;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.Applet;
import java.net.*;
import org.grlea.log.*;

public class CobrowseApplet extends Applet  {
  private static final SimpleLogger log = new SimpleLogger(CobrowseApplet.class,true);
  Button cobrowsebttn, studentcobrowse,syncpresbttn;
  
 // PlayThread playThread ;
  
  public void init(){
   
	//System.out.println("************************init***************");
	log.debug("************************init***************");
	cobrowsebttn=new Button("CoBrowse");
	add(cobrowsebttn);
	studentcobrowse=new Button("Student CoBrowse");
	add(studentcobrowse);
	
	syncpresbttn=new Button("Synch. Presentation");
	add(syncpresbttn);
	
	cobrowsebttn.setEnabled(true);
	studentcobrowse.setEnabled(true);
   
	studentcobrowse.addActionListener(new java.awt.event.ActionListener(){
	  public void actionPerformed(ActionEvent es) {
	    studentcobrowse_actionPerformed(es);
	  }});           
      
	cobrowsebttn.addActionListener(new java.awt.event.ActionListener(){
	public void actionPerformed(ActionEvent e) {
	cobrowsebttn_actionPerformed(e);
	}});
      
	syncpresbttn.addActionListener(new java.awt.event.ActionListener(){
	public void actionPerformed(ActionEvent eps) {
	syncpresbttn_actionPerformed(eps);
	}});
  
  }

  	void cobrowsebttn_actionPerformed(ActionEvent e) {
  	      
		//System.out.println("**************Master CoBrowse*************");
		log.debug("**************Master CoBrowse*************");
		String strContext = getCodeBase().getPath();
		strContext = strContext.substring(0,strContext.indexOf('/',1));
		//System.out.println("strContext1 = "+strContext);
		log.debug("strContext1 = "+strContext);
		String location = strContext+"/servlet/learnitysce.cobrowse.LaunchCobrowse?cobrowse=master";
		//String location = strContext+"/servlets/InitCobrowse.html";
		
		openpage(location);    
  	}
  	  	
  	void studentcobrowse_actionPerformed(ActionEvent es) {
  	      
		//System.out.println("************Student CoBrowse*************");
		log.debug("************Student CoBrowse*************");
		String strContext = getCodeBase().getPath();          
		strContext = strContext.substring(0,strContext.indexOf('/',1));
		//System.out.println("strContext = "+strContext);
		log.debug("strContext = "+strContext);
		//String location = strContext+"/servlet/cobrowse.CobrowseRefresh";
		String location = strContext+"/servlet/learnitysce.cobrowse.LaunchCobrowse?cobrowse=student";
		
		openpage(location);        
	 }
  	
  	void syncpresbttn_actionPerformed(ActionEvent eps) {
  		
  		//System.out.println("************Synch. Presentation*************");
  		log.debug("************Synch. Presentation*************");
  		String strContext = getCodeBase().getPath();
		strContext = strContext.substring(0,strContext.indexOf('/',1));
		//System.out.println("strContext1 = "+strContext);
		log.debug("strContext1 = "+strContext);
		String location = strContext+"/servlet/synchopres.LaunchPresentation";		
		
		openpage(location);
	}
  	
  	void openpage(String location) {
  		
		try
	      {   
		  //	  System.out.println("*********Open Page ***********");
		  log.debug("*********Open Page ***********");
	      	  URL codeBase = getCodeBase();
	          String strProtocol = codeBase.getProtocol();
	          //System.out.println(strProtocol);
	      	  String strHost = codeBase.getHost();
	      	  //System.out.println(strHost);
	      	  int iPort = codeBase.getPort();
	      	  //System.out.println(iPort);
	          URL ur = new URL(strProtocol, strHost, iPort, location);
	          
	          getAppletContext().showDocument(ur, "_blank");                             
	       }
	       catch (Exception ex)
	       {
			//  ex.printStackTrace();            
			log.dbe(DebugLevel.L1_FATAL, ex);
		   }
  	}
}