package learnityforum;

import org.grlea.log.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class ShowTree extends HttpServlet
{
    private static final SimpleLogger log;
    public static String strDisplayType;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final HttpSession mysession = request.getSession(true);
        final PrintWriter out = response.getWriter();
        final String user_id = (String)mysession.getAttribute("user_id");
        final String forum_id = (String)mysession.getAttribute("forum_id");
        final String strdisplayforum = ForumDataBaseLayer.returnforumname(forum_id);
        final String SelectedItem = (String)mysession.getAttribute("selectItemId");
        final String threadId = (String)mysession.getAttribute("thread_id");
        System.out.println("SelectedItem       =" + SelectedItem);
        System.out.println("threadId       =" + threadId);
        final String jscript = "function show_thread(threadid,replyid){\n\talert(replyid);\n\talert(threadid);\n\tForumUtil.setParam(threadid,replyid);\n\twindow.parent.document.forumform.action=\"./interfaceenginev2.PortalServlet?IID=forum\";\n\twindow.parent.document.forumform.target=\"forum\";\n\twindow.parent.document.forumform.submit();\n\t}";
        final Div d1 = new Div();
        final Div d2 = new Div();
        d2.setID("d2");
        final Table t = new Table();
        final TR r = new TR();
        final Html html = new Html();
        final Head head = new Head();
        final Body body = new Body();
        final Div treediv = new Div();
        treediv.setClass("divTree");
        head.addElement((Element)new Script().setLanguage("javascript").addElement(jscript));
        head.addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/cooltreepro.js"));
        head.addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/treecoursemgmt.js"));
        head.addElement((Element)new Link().setHref("./forum.discussionforum.cssForum").setRel("stylesheet"));
        head.addElement((Element)new Script().setLanguage("JavaScript").setSrc("../dwr/engine.js"));
        head.addElement((Element)new Script().setLanguage("JavaScript").setSrc("../dwr/util.js"));
        head.addElement((Element)new Script().setLanguage("JavaScript").setSrc("../dwr/interface/ForumUtil.js"));
        final String ajaxcomp = "<script type='text/javascript'>function getValue(variable){ var v1 = dwr.util.getValue(variable); return v1;}function setValue(variable,data){dwr.util.setValue(variable, data, { escapeHtml:false});}</script>";
        head.addElement(ajaxcomp);
        String str2 = this.createTree(strdisplayforum, threadId);
        if (str2 != null) {
            str2 += "var demoname = \"COOLjsTreePRO\";";
            str2 += "var tree = new COOLjsTreePRO (demoname, TREE1_NODES, TREE1_FORMAT);";
        }
        str2 = "\n\t<script type='text/javascript'>" + str2 + "\n\t</script>";
        treediv.addElement(str2);
        treediv.addElement("<script language = \"javascript\">tree.init()</script>");
        d1.addElement((Element)treediv);
        body.addElement((Element)d1);
        body.addElement((Element)new Script().setLanguage("JavaScript").addElement("RedrawAllTrees()"));
        body.setBgColor("#FFFFFF");
        html.addElement((Element)head);
        html.addElement((Element)body);
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    private String createTree(final String strdisplayforum, final String threadId) {
        final Vector<String[]> vPaList = ForumDataBaseLayer.getTree(strdisplayforum, threadId);
        String str1 = "";
        str1 = "var TREE1_NODES = [ ";
        if (vPaList.size() == 0) {
            System.out.println("list is zero");
            return null;
        }
        for (int i = 0; i < vPaList.size(); ++i) {
            str1 += treeprint(vPaList.elementAt(i));
        }
        str1 += " ]; ";
        return str1;
    }
    
    private static String treeprint(final String[] Parent) {
        String str = "";
        str = "['";
        final String thread_title = Parent[0];
        final String created_by = Parent[1];
        final String created_on = Parent[2];
        final String forum_name = Parent[3];
        final String parent_thread = Parent[4];
        final String forum_id = Parent[5];
        final String thread_id = Parent[6];
        str = str + thread_title + " ','javascript:show_thread(\\'" + thread_id + "\\',\\'" + thread_title + "\\');','',";
        final Vector<String[]> vChildList = ForumDataBaseLayer.getTreeReply(forum_name, thread_id);
        for (int i = 0; i < vChildList.size(); ++i) {
            str += treeprint(vChildList.elementAt(i));
        }
        str += "],\n";
        return str;
    }
    
    static {
        log = new SimpleLogger((Class)ShowTree.class);
        ShowTree.strDisplayType = null;
    }
}
