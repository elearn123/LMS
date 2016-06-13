package sc.sce;

import org.grlea.log.*;
import java.io.*;
import javax.servlet.*;
import org.htmlparser.*;
import org.htmlparser.visitors.*;
import org.htmlparser.util.*;
import java.net.*;
import java.util.*;
import javax.servlet.http.*;

public class SCClient extends HttpServlet
{
    protected Socket socket;
    protected DataInputStream istream;
    protected DataOutputStream ostream;
    protected boolean stop;
    protected double protocolVersion;
    private static final SimpleLogger log;
    String htmlDoc;
    
    public SCClient() {
        this.stop = false;
        this.protocolVersion = 2.1;
        this.htmlDoc = "";
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        String urlname = request.getParameter("page");
        String mthod = request.getMethod();
        if (urlname != null) {
            SCClient.log.debug("urlname = " + urlname);
            if (!urlname.startsWith("http")) {
                urlname = "http://" + urlname;
            }
            if (mthod.equalsIgnoreCase("GET")) {
                this.htmlDoc = this.linkExtractor(urlname);
            }
            else {
                mthod = mthod.toUpperCase();
                this.htmlDoc = this.linkExtractor(urlname, mthod, request);
            }
            try {
                this.connect();
                this.sendRefreshAction();
            }
            catch (UnknownHostException uhe) {
                SCClient.log.dbe(DebugLevel.L1_FATAL, (Throwable)uhe);
            }
            catch (IOException ioe) {
                SCClient.log.dbe(DebugLevel.L1_FATAL, (Throwable)ioe);
            }
            catch (Exception exp) {
                SCClient.log.dbe(DebugLevel.L1_FATAL, (Throwable)exp);
            }
        }
        out.println(this.htmlDoc);
    }
    
    public String linkExtractor(final String location) {
        try {
            Parser parser = new Parser(location);
            final URLConnection con = parser.getConnection();
            SCClient.log.debug("Location = " + con.getURL());
            if (!location.equalsIgnoreCase(con.getURL().toString())) {
                parser = new Parser(con.getURL().toString());
            }
            final UrlModifyingVisitor visitor = new UrlModifyingVisitor(parser, "./sc.sce.SCClient?page=", "./sc.sce.SCClientForFrame?page=");
            parser.visitAllNodesWith((NodeVisitor)visitor);
            return visitor.getModifiedResult();
        }
        catch (ParserException e) {
            SCClient.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            return "";
        }
    }
    
    public String linkExtractor(final String location, final String mthod, final HttpServletRequest request) {
        try {
            final URL url = new URL(location);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(mthod);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            final Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                final String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                if (headerName.equalsIgnoreCase("HOST")) {
                    headerValue = url.getHost();
                }
                connection.setRequestProperty(headerName, headerValue);
            }
            final Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                if (cookies.length != 0) {
                    connection.setRequestProperty("cookie", this.generateCookieHeader(cookies));
                }
            }
            final StringBuffer buffer = new StringBuffer(1024);
            final Enumeration<String> enum1 = request.getParameterNames();
            while (enum1.hasMoreElements()) {
                final String name = enum1.nextElement();
                if (!name.equals("page")) {
                    if (name.equals("mthod")) {
                        continue;
                    }
                    buffer.append(name + "=");
                    final String value = request.getParameter(name);
                    buffer.append(value);
                    if (!enum1.hasMoreElements()) {
                        continue;
                    }
                    buffer.append("&");
                }
            }
            SCClient.log.debug(buffer.toString());
            final PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(buffer);
            out.close();
            final Parser parser = new Parser((URLConnection)connection);
            final UrlModifyingVisitor visitor = new UrlModifyingVisitor(parser, "./sc.sce.SCClient?page=", "./sc.sce.SCClientForFrame?page=");
            parser.visitAllNodesWith((NodeVisitor)visitor);
            return visitor.getModifiedResult();
        }
        catch (Exception e) {
            SCClient.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            return "";
        }
    }
    
    public void postCookies(final HttpURLConnection urlConn, final Cookie[] cookies) {
    }
    
    private String generateCookieHeader(final Cookie[] cookies) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cookies.length; ++i) {
            buf.append(cookies[i].getName());
            buf.append("=");
            buf.append(cookies[i].getValue());
            if (i + 1 != cookies.length) {
                buf.append("; ");
            }
            else {
                buf.append(" ");
            }
        }
        return buf.toString();
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public void connect() throws UnknownHostException, IOException, Exception {
        final int portnumber = Integer.parseInt((String)this.getServletContext().getAttribute("port"));
        this.socket = new Socket("localhost", portnumber);
        this.ostream = new DataOutputStream(this.socket.getOutputStream());
        synchronized (this.istream = new DataInputStream(this.socket.getInputStream())) {
            synchronized (this.ostream) {
                this.sendProtocol(this.protocolVersion);
            }
        }
    }
    
    protected void sendRefreshAction() throws IOException {
        synchronized (this.ostream) {
            this.ostream.writeShort(40);
            this.ostream.writeBoolean(true);
        }
    }
    
    protected void sendProtocol(final double version) throws IOException {
        synchronized (this.ostream) {
            this.ostream.writeShort(1);
            this.ostream.writeDouble(version);
        }
    }
    
    static {
        log = new SimpleLogger((Class)SCClient.class);
    }
}
