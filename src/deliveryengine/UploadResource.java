package deliveryengine;

import javax.servlet.http.*;
import javax.servlet.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.io.*;
import com.oreilly.servlet.*;
import java.util.*;

public class UploadResource extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        final Object obj = mysession.getAttribute("user_id");
        if (obj == null) {
            response.sendRedirect("./interfaceenginev2.PortalServlet?IID=LoginPage");
            return;
        }
        final String strAdminId = obj.toString();
        final String strPrmAddModDel = request.getParameter("prmAddModify");
        if (strPrmAddModDel != null) {
            final int iPrmAddModify = Integer.parseInt(strPrmAddModDel);
            switch (iPrmAddModify) {
                case 0: {
                    this.uploadAssets(request, response, out);
                    break;
                }
            }
        }
        this.getResult(request, response, out, strAdminId);
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String strAdminId) throws IOException, ServletException {
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String strTime = calendar.get(10) + ":" + calendar.get(12);
        final String strDate = months[calendar.get(2)] + " " + calendar.get(5) + ", " + calendar.get(1);
        final String img_url = request.getParameter("src");
        final String dest_dir = request.getParameter("destdir");
        final String javaScript = "\n\tfunction upload_onclick() {\n\t\tdocument.frm.method=\"post\";\n\t\tdocument.frm.action = \"deliveryengine.UploadResource?prmAddModify=0&destdir=\"+document.frm.destdir.value+\"&src=\"+document.frm.src.value;\n\t\tdocument.frm.encoding=\"multipart/form-data\";\n\t\twindow.opener.close();\n\t\tdocument.frm.submit();\n\t}\n\tfunction done_onclick() {\n\t\tself.close();\n\t}";
        final Input inputButton1 = new Input();
        final Input inputButton2 = new Input();
        inputButton1.setOnClick("upload_onclick();");
        inputButton2.setOnClick("done_onclick();");
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("Upload Resource")).addElement((Element)new Link().setHref("../js/stylesheet.css").setRel("stylesheet")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/check.js")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/picker.js")).addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript)));
        final Form form = new Form().setName("frm").setMethod("post");
        final Body body = new Body();
        final Input filebrowse = new Input().setName("file_in").setType("file");
        final Table table = new Table();
        final Table table2 = new Table();
        table2.addElement((Element)new Table(1).setWidth("100%").setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new BR()).addElement((Element)new TR().addElement((Element)new TD().setWidth("160").setClassId("PPRLabelText").addElement("Upload File")).addElement((Element)new TD().setClassId("PPRLabelText").setWidth("500").addElement("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").addElement("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").addElement((Element)filebrowse))).addElement((Element)new TR().addElement((Element)new TD())).addElement((Element)new TR().addElement((Element)new TD())).addElement((Element)new TR().addElement((Element)new TD().setHeight(40)))).addElement((Element)new TR().addElement((Element)new TD().setWidth(47)));
        final Table table3 = new Table();
        table3.addElement((Element)new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new TR().addElement((Element)new TD().setWidth("50")).addElement((Element)new TD().addElement((Element)inputButton1.setClassId("sbttn").setName("upload").setTabindex(2).setTitleValue("Upload Resource").setType("button").setValue("Upload"))).addElement((Element)new TD().setWidth("50")).addElement((Element)new TD().addElement((Element)inputButton2.setClassId("sbttn").setName("done").setTabindex(2).setTitleValue("Done").setType("button").setValue("Done")))));
        table.addElement((Element)new TR().addElement((Element)new TD().addElement((Element)table2))).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)table3)));
        form.addElement((Element)new Input().setType("hidden").setName("destdir").setValue(dest_dir)).addElement((Element)new Input().setType("hidden").setName("src").setValue(img_url));
        form.addElement((Element)table);
        body.addElement((Element)form);
        html.addElement(body.setClass("body"));
        out.print(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public void uploadAssets(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out) throws IOException, ServletException {
        final String img_url = request.getParameter("src");
        final String dest_dir = request.getParameter("destdir");
        String rel_dir = "";
        if (img_url.lastIndexOf(File.separator) != -1) {
            rel_dir = img_url.substring(0, img_url.lastIndexOf(File.separator));
        }
        final File uploaddir = new File(dest_dir + rel_dir);
        if (!uploaddir.exists()) {
            uploaddir.mkdirs();
        }
        boolean success = false;
        try {
            final MultipartRequest multipartrequest = new MultipartRequest(request, dest_dir + rel_dir, 524288000);
            final Enumeration<String> enumeration = multipartrequest.getFileNames();
            while (enumeration.hasMoreElements()) {
                final String s6 = enumeration.nextElement();
                final File uploadedfile = multipartrequest.getFile(s6);
                System.out.println("uploadedfile::" + uploadedfile.toString());
                if (img_url != null && !"".equals(img_url)) {
                    uploadedfile.renameTo(new File(dest_dir + img_url));
                }
            }
            success = true;
        }
        catch (IOException ioexception) {
            success = false;
            System.out.println(ioexception.toString() + " error reading or saving file");
        }
        if (success) {
            out.println("<DIV id=step_1><p><font size=\"4\">The resource successfully uploaded.</font></p></DIV>");
        }
        else {
            out.println("<DIV id=step_1><p><font size=\"4\">Uploading resource failed.</font></p></DIV>");
        }
    }
}
