package deliveryengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import learnityInit.Host;

import org.apache.ecs.Element;
import org.apache.ecs.html.Body;
import org.apache.ecs.html.Form;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Option;
import org.apache.ecs.html.Script;
import org.apache.ecs.html.Select;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.html.TextArea;
import org.apache.ecs.html.Title;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HtmlEditor extends HttpServlet
{
    private Document dom;
    private Vector<String[]> contents;
    private String id;
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        final PrintWriter out = response.getWriter();
        final String strPrmAddModDel = request.getParameter("prmAddModify");
        if (strPrmAddModDel != null) {
            final int iPrmAddModify = Integer.parseInt(strPrmAddModDel);
            switch (iPrmAddModify) {
                case 1: {
                    this.writeObjects(request);
                    break;
                }
            }
        }
        final String sdp = Host.getServerDocumentPath();
        final String sdpe = Host.getServerDocumentPathEngine();
        final int server_port = request.getServerPort();
        final HttpSession mysession = request.getSession(false);
        if (mysession == null) {
            response.sendRedirect("./interfaceenginev2.PortalServlet?IID=LoginPage");
            return;
        }
        final String course_id = (String)mysession.getAttribute("unit_id");
        this.id = request.getParameter("identifier");
        String content_file = request.getParameter("content_file");
        System.out.println("content_file:" + content_file);
        System.out.println("course_id:" + course_id);
        System.out.println("identifier:" + this.id);
        this.contents = new Vector<String[]>();
        this.parseManifest(sdp + File.separatorChar + course_id + File.separatorChar + "imsmanifest.xml");
        Option[] option_asset = null;
        option_asset = new Option[this.contents.size() + 1];
        for (int i = 0; i < this.contents.size(); ++i) {
            final String[] strList = this.contents.elementAt(i);
            option_asset[i] = new Option(strList[0]).addElement(strList[1]);
            if (content_file != null && content_file.equals(strList[0])) {
                option_asset[i].setSelected(true);
            }
        }
        if (this.id != null) {
            final String[] sco_selected = this.contents.elementAt(0);
            content_file = ((content_file != null) ? content_file : sco_selected[0]);
        }
        final String file_abs_path = sdp + File.separatorChar + course_id + File.separatorChar + content_file;
        final String destDir = sdp + File.separatorChar + course_id + File.separatorChar;
        System.out.println("file_abs_path:" + file_abs_path);
        final InputStream in = new FileInputStream(file_abs_path);
        final StreamToStringConverter sc = new StreamToStringConverter(in);
        final String content = sc.toString();
        final String content_url = sdpe + course_id + "/";
        String refresh_page = "";
        String refresh_page2 = "";
        String window_id = "0";
        String page_id = "0";
        boolean flag = false;
        if (content_file != null && !content_file.equals("")) {
            final String file_type = content_file.substring(content_file.lastIndexOf(".") + 1);
            if (content_file.startsWith("P")) {
                window_id = content_file.substring(content_file.indexOf("_") + 1, content_file.lastIndexOf("_P"));
                page_id = content_file.substring(content_file.indexOf("_P") + 1, content_file.lastIndexOf("."));
                refresh_page = "window.opener.frames[0].frames[0].document.location=\"http://195.41.102.20:" + server_port + content_url + "P_" + window_id + "_" + page_id + "." + file_type + "\"";
                flag = true;
            }
            else if (content_file.startsWith("S")) {
                window_id = content_file.substring(content_file.indexOf("_") + 1, content_file.lastIndexOf("_P"));
                page_id = content_file.substring(content_file.indexOf("_P") + 1, content_file.lastIndexOf("_root"));
                refresh_page = "window.opener.frames[0].frames[0].frames[0].document.location=\"http://195.41.102.20:" + server_port + content_url + "Section_" + window_id + "_" + page_id + "_root." + file_type + "\"";
                refresh_page2 = "window.opener.frames[0].frames[0].document.location=\"http://195.41.102.20:" + server_port + content_url + "P_" + window_id + "_" + page_id + "." + file_type + "\";";
                flag = true;
            }
            else if (content_file.startsWith("W")) {
                window_id = content_file.substring(content_file.indexOf("_") + 1, content_file.lastIndexOf("."));
                refresh_page = "window.opener.frames[0].document.location=\"http://195.41.102.20:" + server_port + content_url + "W_" + window_id + "." + file_type + "\"";
                flag = true;
            }
            else {
                refresh_page = "window.opener.frames[0].document.location=\"http://195.41.102.20:" + server_port + content_url + content_file + "\"";
                flag = false;
            }
        }
        final String javaScript = "\n\tvar b = new Boolean(" + flag + ");" + "\n function select_onchange() {" + "\n\t\tdocument.frm.method=\"post\";" + "\n\t\tdocument.frm.action = \"deliveryengine.HtmlEditor\";" + "\n\t\tdocument.frm.submit();" + "\n\t}" + "\n\tfunction mysave(){" + "\n\tdocument.frm.method=\"post\";" + "\n\tdocument.frm.action = \"deliveryengine.HtmlEditor?prmAddModify=1\";" + "\n\tdocument.frm.target=\"_self\";" + "\n\tdocument.frm.submit();" + "\n\tif(b == true) {" + "\n\t\tsetTimeout('',12000);" + "\n\t\treload();" + "\n\t\t}" + "\n\t}" + "\n\tfunction reload() {" + "\n\t" + refresh_page + ";" + "\n\t" + refresh_page2 + "" + "\n\tvar pdivid2 = \"W_" + window_id + "_navigation_div\";" + "\n\twindow.opener.frames[0].document.getElementById(pdivid2).style.color= \"white\";" + "\n\tvar pdivid = \"W_" + window_id + "_P_" + page_id + "_iframe\";" + "\n\twindow.opener.frames[0].document.getElementById(pdivid).style.color= \"blue\";" + "\n\t}" + "\n function browseResources(element_id, url, type, window) {" + "\n\t\tdocument.frm.method=\"post\";" + "\n\t\tdocument.frm.src.value = url;" + "\n\t\tdocument.frm.action = \"deliveryengine.UploadResource\";" + "\n\t\twindow.open(\"\",\"asset\",\"width=400,height=400,status=yes,scrollbars=yes,resizable=no,toolbar=no,menubar=no\");" + "\n\t\tdocument.frm.target=\"asset\";" + "\n\t\tdocument.frm.submit();" + "\n\t}";
        final String tinymceinit = "\n tinyMCE.init({\n\t\tmode : \"textareas\",\n\t\ttheme : \"advanced\",\n\t\tremove_linebreaks : false,\n\t\tvalid_elements : \"*[*]\",\n\t\textended_valid_elements : \"*[*]\",\n\t\tconvert_urls : false,\n\t\tdocument_base_url : \"http://195.41.102.20:" + server_port + "/" + content_url + "\"," + "\n\t\tplugins : \"fullpage,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,zoom,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,tinybrowser\"," + "\n\t\ttheme_advanced_buttons1 : \"save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect\"," + "\n\t\ttheme_advanced_buttons2 : \"cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor\"," + "\n\t\ttheme_advanced_buttons3 : \"tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen\"," + "\n\t\ttheme_advanced_buttons4 : \"insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking\"," + "\n\t\ttheme_advanced_toolbar_location : \"top\"," + "\n\t\ttheme_advanced_toolbar_align : \"left\"," + "\n\t\ttheme_advanced_path_location : \"bottom\"," + "\n\t\textended_valid_elements : \"a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]\"," + "\n\t\ttemplate_external_list_url : \"example_template_list.js\"," + "\n\t\tfile_browser_callback : \"browseResources\"," + "\n\t\tsave_onsavecallback : \"mysave\"," + "\n\t\ttheme_advanced_buttons3_add : \"fullpage\"" + "\n\t});";
        final Body body = new Body();
        final Form form = new Form();
        form.addElement((Element)new Input().setType("hidden").setName("identifier").setValue(this.id));
        form.addElement((Element)new Input().setType("hidden").setName("src"));
        form.addElement((Element)new Input().setType("hidden").setName("destdir").setValue(destDir));
        final Select sel = new Select().setName("content_file");
        final Table table_mod = new Table().addElement((Element)new TR().addElement((Element)new TD().setColSpan(3))).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)sel.addElement(option_asset))));
        sel.setOnChange("select_onchange()");
        //final Input inputButton1 = new Input();
        final Html html = new Html().addElement((Element)new Head().addElement((Element)new Title("Html Editor")).addElement((Element)new Script().setLanguage("JavaScript").setSrc("../js/tiny_mce/tiny_mce.js")).addElement((Element)new Script().setLanguage("JavaScript").setType("text/javascript").addElement(tinymceinit)).addElement((Element)new Script().setLanguage("JavaScript").setType("text/javascript").setSrc("../js/abs.js")).addElement((Element)new Script().setLanguage("JavaScript").addElement(javaScript))).addElement((Element)body.addElement((Element)form.addElement((Element)table_mod).setName("frm").addElement((Element)new TextArea().setRows(36).setCols(109).setName("content").addElement(content))));
        form.addElement((Element)new Input().setType("hidden").setName("file_abs_path").setValue(file_abs_path));
        out.println(html.toString());
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    private void parseManifest(final String manifest_dir) {
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder db = dbf.newDocumentBuilder();
            this.dom = db.parse(manifest_dir);
            try {
                this.parseDocument();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (SAXException se) {
            se.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    private void parseDocument() throws Exception {
        final org.w3c.dom.Element docEle = this.dom.getDocumentElement();
        String item_title = "";
        final Hashtable<String, String> ht = new Hashtable<String, String>();
        final NodeList nl = docEle.getElementsByTagName("organization");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); ++i) {
                final org.w3c.dom.Element el = (org.w3c.dom.Element)nl.item(i);
                final NodeList orgNodeList = el.getElementsByTagName("item");
                if (orgNodeList != null && orgNodeList.getLength() > 0) {
                    for (int it = 0; it < orgNodeList.getLength(); ++it) {
                        String identifier = "";
                        String identifierref = "";
                        final Node el2 = orgNodeList.item(it);
                        final NamedNodeMap nnmRes = el2.getAttributes();
                        for (int k = 0; k < nnmRes.getLength(); ++k) {
                            final Node resAttrNode = nnmRes.item(k);
                            if (resAttrNode.getNodeName().equalsIgnoreCase("identifier")) {
                                identifier = resAttrNode.getNodeValue();
                            }
                            if (resAttrNode.getNodeName().equalsIgnoreCase("identifierref")) {
                                identifierref = resAttrNode.getNodeValue();
                            }
                        }
                        if (this.id.equalsIgnoreCase(identifier)) {
                            final org.w3c.dom.Element elIt = (org.w3c.dom.Element)orgNodeList.item(it);
                            item_title = this.getTextValue(elIt, "title");
                            System.out.println("parseDocument item_title:" + item_title);
                        }
                        ht.put(identifier, identifierref);
                    }
                }
            }
        }
        this.getAssets(docEle, ht, item_title);
    }
    
    public String getResourceLocation(final org.w3c.dom.Element docEle, final String strResRef) {
        String strLocation = null;
        final NodeList nl = docEle.getElementsByTagName("resources");
        NodeList nListResource = null;
        NamedNodeMap nnm1 = null;
        for (int i = 0; i < nl.getLength(); ++i) {
            nListResource = ((org.w3c.dom.Element)nl.item(i)).getElementsByTagName("resource");
            for (int j = 0; j < nListResource.getLength(); ++j) {
                nnm1 = nListResource.item(j).getAttributes();
                for (int k = 0; k < nnm1.getLength(); ++k) {
                    if (nnm1.item(k).getNodeName().equalsIgnoreCase("identifier") && nnm1.item(k).getNodeValue().equalsIgnoreCase(strResRef)) {
                        for (int r = 0; r < nnm1.getLength(); ++r) {
                            if (nnm1.item(r).getNodeName().equalsIgnoreCase("href")) {
                                strLocation = nnm1.item(r).getNodeValue();
                            }
                        }
                    }
                }
            }
        }
        return strLocation;
    }
    
    public void getAssets(final org.w3c.dom.Element docEle, final Hashtable<String, String> ht, final String item_title) {
        final NodeList nl = docEle.getElementsByTagName("resources");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); ++i) {
                final org.w3c.dom.Element el = (org.w3c.dom.Element)nl.item(i);
                //final String tag_name = el.getNodeName();
                final NodeList resNodeList = el.getElementsByTagName("resource");
                if (resNodeList != null && resNodeList.getLength() > 0) {
                    for (int res = 0; res < resNodeList.getLength(); ++res) {
                        String identifier = "";
                        final Node el2 = resNodeList.item(res);
                        final NamedNodeMap nnmRes = el2.getAttributes();
                        for (int k = 0; k < nnmRes.getLength(); ++k) {
                            final Node resAttrNode = nnmRes.item(k);
                            if (resAttrNode.getNodeName().equalsIgnoreCase("identifier")) {
                                identifier = resAttrNode.getNodeValue();
                            }
                        }
                        if (identifier.equalsIgnoreCase(ht.get(this.id))) {
                            final String[] cont_aray1 = new String[2];
                            final String strResourceLocation = this.getResourceLocation(docEle, identifier);
                            cont_aray1[0] = strResourceLocation;
                            cont_aray1[1] = item_title;
                            this.contents.add(cont_aray1);
                            final org.w3c.dom.Element elFile = (org.w3c.dom.Element)resNodeList.item(res);
                            final NodeList fileNodeList = elFile.getElementsByTagName("file");
                            if (fileNodeList != null && fileNodeList.getLength() > 0) {
                                for (int j = 0; j < fileNodeList.getLength(); ++j) {
                                    final String[] cont_aray2 = new String[2];
                                    String href = "";
                                    String title = "";
                                    final Node el3 = fileNodeList.item(j);
                                    final NamedNodeMap nnmFile = el3.getAttributes();
                                    for (int l = 0; l < nnmFile.getLength(); ++l) {
                                        final Node hrefAttrNode = nnmFile.item(l);
                                        if (hrefAttrNode.getNodeName().equalsIgnoreCase("href")) {
                                            href = hrefAttrNode.getNodeValue();
                                        }
                                        if (hrefAttrNode.getNodeName().equalsIgnoreCase("title")) {
                                            title = hrefAttrNode.getNodeValue();
                                        }
                                    }
                                    href = ((href == null) ? "" : href);
                                    final String ext = href.substring(href.lastIndexOf(".") + 1);
                                    if (ext.equals("html") || ext.equals("xhtml") || ext.equals("htm") || ext.equals("txt")) {
                                        cont_aray2[0] = href;
                                        if (title != null && !title.equals("")) {
                                            cont_aray2[1] = title;
                                        }
                                        else {
                                            cont_aray2[1] = href;
                                        }
                                        this.contents.add(cont_aray2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private String getTextValue(final org.w3c.dom.Element ele, final String tagName) {
        String textVal = null;
        final NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            final org.w3c.dom.Element el = (org.w3c.dom.Element)nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }
    
    public void writeObjects(final HttpServletRequest request) {
        final String file_abs_path = request.getParameter("file_abs_path");
        final String content = request.getParameter("content");
        System.out.println("repository:" + file_abs_path);
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
