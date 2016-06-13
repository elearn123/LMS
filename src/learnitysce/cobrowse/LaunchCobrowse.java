package learnitysce.cobrowse;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.ecs.html.*;
import org.grlea.log.*;

public class LaunchCobrowse extends HttpServlet
{
	private static final SimpleLogger log = new SimpleLogger(LaunchCobrowse.class,true);
    public LaunchCobrowse()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = null;
        String s1 = httpservletrequest.getParameter("cobrowse");
       // System.out.println("cobrowse:" + s1);
       	log.debug("cobrowse:" + s1);
        Html html = new Html();
        Head head = new Head(new Title("LearnITy Eksathe: Cobrowsing"));
        Body body = new Body();
        if(s1.equals("student"))
        {
            //System.out.println("cobrowse student");
            log.debug("cobrowse student");
	    s = "\n   function bodyonload(){\n\t\twindow.open(\"./learnitysce.cobrowse.CobrowseRefresh\",\"_blank\",\"width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no\");\n\t\twindow.parent.close();\t\n   }";
        } else
        if(s1.equals("master"))
		s = "\n   function bodyonload(){\n\t\twindow.open(\"../coreadmin/servlets/InitCobrowse.html\",\"_blank\",\"width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no\");\n\t\twindow.parent.close();\t\n   }";
        html.addElement(head);
	html.addElement("<frameset rows=\"90,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\" cols=\"*\"> "+
			"<frame name=\"topFrame\" scrolling=\"NO\" noresize src=\"../coreadmin/servlets/TopCobrowse.html\" > "+
			"<frame src=\"./sc.sce.SCClient\"></frameset><noframes>");
        html.addElement(body);
        html.addElement("</noframes>");
        printwriter.print(html.toString());
        printwriter.close();
    }
}