package learnitytms;

import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import java.util.*;

public class ResourceCalendar extends HttpServlet
{
    private static final String LOGIN_SESSION_NAME = "ADMIN_LOG_ON";
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final HttpSession mysession = request.getSession(true);
        final Object obj = mysession.getAttribute("user_id");
        if (obj == null) {
            response.sendRedirect("../servlet/interfaceenginev2.PortalServlet?IID=LoginPage");
        }
        else {
            final String strAdminId = obj.toString();
            this.getResult(request, response, out, strAdminId);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String strAdminId) throws IOException, ServletException {
        final Calendar calendar = new GregorianCalendar();
        final Date trialTime = new Date();
        calendar.setTime(trialTime);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String javaScript = "\n\tfunction test() {\n\t\tvar index =0;\n\t\tfor (var i=0;i<document.frm.elements.length;i++){\n\t\t\tvar e = document.frm.elements[i];\n\t\t\tif (e.type=='checkbox'){\n\t\t\t\tindex++;\n\t\t\t}\n\t\t}\n\t\treturn index;\n\t}\n\tfunction ShowDetails_onclick(){\n\t\tvar i=test();\n\t\talert(i);\n\t\tif(i>1){\n\t\t\tvar StrRes=new Array(document.frm.resourceid.length);\n\t\t\tfor(var i=0;i<document.frm.resourceid.length;i++){\n\t\t\t\tif(document.frm.resourceid[i].checked==true){\n\t\t\t\t\tStrRes[i]=document.frm.resourceid[i].value;\n\t\t\t\t\talert(StrRes[i]);\n\t\t\t\t}\n\t\t\t}\n           \t\tfor( var i=0;i<StrRes.length;i++) {\n           \t\t\tdocument.frm.resourceid_hidden[i].value = StrRes[i];\n     \t\t}\n\t\t}\n\t\tif(i==1){\n\t\t\talert(document.frm.resourceid.value);\n\t\t\tdocument.frm.resourceid_hidden.value=document.frm.resourceid.value;\n\t\t}\n\t\tdocument.frm.method=\"post\";\n\t\tdocument.frm.action=\"./learnitytms.ResourceScheduleManagement\";\n\t\twindow.open(\"\",\"new\",\"width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no\");\n\t\tdocument.frm.target=\"new\";\n\t\tdocument.frm.submit();\n\t}\n\tfunction load(){\n\t}";
        final Body body = new Body();
        final Form form = new Form();
        form.setName("frm");
        final Vector<Vector<String>> vResource = DataBaseLayer.getResourcecalendar();
        final Input inputButton1 = new Input();
        inputButton1.setOnClick("ShowDetails_onclick();");
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("Resource Schedule Management")).addElement((Element)new Link().setHref("../js/stylesheet.css").setRel("stylesheet")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/check.js")).addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript)));
        form.addElement((Element)new Table().addElement((Element)new TR().addElement((Element)new TD().setClassId("swb").addElement("<b>Select Resource</b>"))));
        final TR tr = new TR();
        form.addElement((Element)new Table().addElement((Element)tr));
        tr.addElement((Element)new TD().setClassId("swb").setWidth(150).addElement("<b>Select</b>")).addElement((Element)new TD().setClassId("swb").setWidth(150).addElement("<b>Resource Id</b>")).addElement((Element)new TD().setClassId("swb").setWidth(250).addElement("<b>Resource Name</b>"));
        if (vResource.size() > 0) {
            for (int i = 0; i < vResource.size(); ++i) {
                final Vector<String> str22 = vResource.elementAt(i);
                final String strres1 = str22.elementAt(0);
                final String strres2 = str22.elementAt(1);
                tr.addElement((Element)new TR().setBgColor("C0C0C0").addElement((Element)new TD().addElement((Element)new Input().setType("CHECKBOX").setValue(strres1).setName("resourceid"))).addElement((Element)new Input().setType("hidden").setValue(strres1).setName("resourceid_hidden")).addElement((Element)new TD().addElement(strres1)).addElement((Element)new TD().addElement(strres2)));
            }
        }
        form.addElement((Element)new Table().setAlign("center").setWidth("90%").setBorder("0").setCellPadding("0").addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new Table().setBorder("0").setCellPadding("0").addElement((Element)new TR().addElement((Element)new TD().addElement((Element)new BR()).addElement((Element)new BR()).addElement((Element)new BR()).addElement((Element)new BR())).addElement((Element)new TD().addElement((Element)inputButton1.setType("button").setName("add").setValue("Show Calendar Details").setClassId("sbttn"))))))));
        body.setOnLoad("load()");
        body.addElement((Element)form);
        html.addElement((Element)body);
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
