package learnitysce;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.grlea.log.DebugLevel;
import org.grlea.log.SimpleLogger;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

import sc.sce.UrlModifyingVisitor;

/**
 * Title:        LearnITy
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Aunwesha
 * @author Asish Kumar Samanta
 * @version 3.0
 */

public class SCClientForFrame extends HttpServlet {
    protected Socket socket;
    protected DataInputStream istream;
    protected DataOutputStream ostream;
    protected boolean stop = false;
    private static final SimpleLogger log = new SimpleLogger(SCClientForFrame.class);
    protected double protocolVersion = 2.1; // by default
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	                       throws ServletException, IOException {
	    
	    response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String htmlDoc = "";
    	String urlname =request.getParameter("page");    	
    	if (urlname != null) {
	    	if(!urlname.startsWith("http")) {
	    		urlname= "http://"+urlname;
	    	}
	    	htmlDoc = linkExtractor(urlname);
	    	
	    }
	    out.println(htmlDoc);
	}
	
	public String linkExtractor(String location) {
        try {
            Parser parser   = new Parser(location); // Create the parser object            
			UrlModifyingVisitor visitor = new UrlModifyingVisitor(parser, "./delivery.SCClient?page=", "./delivery.SCClientForFrame?page=");
			parser.visitAllNodesWith(visitor);			
	        return visitor.getModifiedResult(); 			
        }
        catch (ParserException e) {
           // e.printStackTrace();
            log.dbe(DebugLevel.L1_FATAL, e);
            return "";
        }
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws IOException, ServletException {
        doGet(request, response);
    }
}
