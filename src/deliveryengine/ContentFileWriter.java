package deliveryengine;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ContentFileWriter extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final String file_abs_path = request.getParameter("file_abs_path");
        final String content = request.getParameter("content");
        System.out.println("repository:" + file_abs_path);
        System.out.println("content:" + content);
        this.writeObjects(content, file_abs_path);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public void writeObjects(final String content, final String file_abs_path) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file_abs_path);
            final byte[] c_buf = content.getBytes();
            out.write(c_buf);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (Exception ex) {}
            }
        }
    }
}
