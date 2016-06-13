package sc.sce;

import java.net.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import org.htmlparser.*;
import org.htmlparser.visitors.*;
import org.grlea.log.*;
import org.htmlparser.util.*;

public class SCClientForFrame extends HttpServlet
{
    protected Socket socket;
    protected DataInputStream istream;
    protected DataOutputStream ostream;
    protected boolean stop;
    private static final SimpleLogger log;
    protected double protocolVersion;
    
    public SCClientForFrame() {
        this.stop = false;
        this.protocolVersion = 2.1;
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        String htmlDoc = "";
        String urlname = request.getParameter("page");
        if (urlname != null) {
            if (!urlname.startsWith("http")) {
                urlname = "http://" + urlname;
            }
            htmlDoc = this.linkExtractor(urlname);
        }
        out.println(htmlDoc);
    }
    
    public String linkExtractor(final String location) {
        try {
            final Parser parser = new Parser(location);
            final UrlModifyingVisitor visitor = new UrlModifyingVisitor(parser, "./delivery.SCClient?page=", "./delivery.SCClientForFrame?page=");
            parser.visitAllNodesWith((NodeVisitor)visitor);
            return visitor.getModifiedResult();
        }
        catch (ParserException e) {
            SCClientForFrame.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            return "";
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    static {
        log = new SimpleLogger((Class)SCClientForFrame.class);
    }
}
