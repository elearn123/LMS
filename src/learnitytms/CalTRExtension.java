package learnitytms;

import org.apache.ecs.*;
import java.util.*;
import org.apache.ecs.html.*;

public class CalTRExtension extends TR
{
    public TR addRow(final String c, final Select d) {
        final TR tr = new TR().setBgColor("#4d99e5").addElement((Element)new TD().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement(c)))).addElement((Element)new TD().addElement((Element)new B().addElement((Element)new Font().setFace("verdana").setSize(-2).addElement((Element)d))));
        return tr;
    }
    
    public TR addHeading(final String strtime, final String strMycalendar) {
        final TR tr = new TR().addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(3).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement(strtime)).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(7).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement(strMycalendar));
        return tr;
    }
    
    public TR addDayviewPersonal(final String e_id, final String hm, final String v, final String etime) {
        final TD tdFieldData = new TD().setColSpan(3).addElement((Element)new Font().setFace("verdana").setSize(-1).addElement((Element)new A().setHref("javascript:addCalendar('" + e_id + "','F');").addElement(v)));
        final Input event_id = new Input().setType("hidden").setName("event_no").setValue(e_id);
        final TR tr = new TR().addElement((Element)new TD().setNoWrap(true).setVAlign("top").setWidth("%20").addElement((Element)new Font().setFace("verdana").setSize(-1).addElement(hm).addElement("&nbsp;&nbsp;&nbsp;&nbsp;").addElement(etime).addElement((Element)event_id).addElement("&nbsp;&nbsp;&nbsp;"))).addElement((Element)tdFieldData);
        return tr;
    }
    
    public TR addDayviewPublic(final String e_id, final String hm, final String v, final String etime) {
        final TD tdFieldData = new TD().setColSpan(3).addElement((Element)new Font().setFace("verdana").setSize(-1).addElement((Element)new A().setHref("javascript:sharedCalendar('" + e_id + "','T');").addElement(v)));
        final Input event_id = new Input().setType("hidden").setName("event_no").setValue(e_id);
        final TR tr = new TR().setBgColor("#f1f1fd").addElement((Element)new TD().setNoWrap(true).setVAlign("top").setWidth("%20").addElement((Element)new Font().setFace("verdana").setSize(-1).addElement(hm).addElement("&nbsp;&nbsp;&nbsp;&nbsp;").addElement(etime).addElement((Element)event_id).addElement("&nbsp;&nbsp;&nbsp;"))).addElement((Element)tdFieldData);
        return tr;
    }
    
    public TR addHeadingDays(final String ss, final String m, final String t, final String w, final String th, final String f, final String sa, final int s) {
        final TR tr = new TR().setAlign("top").addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setColor("#FFCC33").addElement(ss))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(m))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(t))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(w))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(th))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(f))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(sa)));
        tr.setClass("calhead");
        return tr;
    }
    
    public TR addHeadingDays1(final String ss, final String m, final String t, final String w, final String th, final String f, final String sa, final int s) {
        final TR tr = new TR().setAlign("top").addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setColor("#FFCC33").addElement(ss))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(m))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(t))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(w))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(th))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(f))).addElement((Element)new TH().setAlign("center").addElement((Element)new Font().setFontClass("calhead").addElement(sa)));
        tr.setClass("calhead");
        return tr;
    }
    
    public TR addWeekviewdays(final Vector<String[]> vWeekdata, final int size, final String programid) {
        final String weekview = "weekview";
        final TR tr = new TR().setAlign("center");
        for (int i = 1; i < vWeekdata.size(); ++i) {
            final String[] strWeek = vWeekdata.elementAt(i);
            if (i == 0) {
                tr.addElement((Element)new TD().setWidth(25).setAlign("left").setNoWrap(true).addElement((Element)new NOBR().addElement((Element)new A().setHref("javascript:calendarMethod('" + weekview + "','" + strWeek[1] + "');").addElement((Element)new Font().setFace("verdana").setSize(size).setColor("#008000").addElement(strWeek[0] + "&nbsp;")))));
            }
            else {
                final Vector strresourceid = DataBaseLayer.getCalendardate(strWeek[1], programid);
                if (strresourceid.size() > 0) {
                    final Table text = new Table();
                    final TD td = new TD();
                    text.addElement((Element)new TR().addElement((Element)td));
                    tr.addElement((Element)new TD().setWidth(50).setHeight(70).setBgColor("#999999").setNoWrap(true).addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(size).setColor("#0000FF").addElement(strWeek[0]).addElement("<br>").addElement((Element)text))));
                    for (int j = 0; j < strresourceid.size(); ++j) {
                        td.addElement((Element)new Font().setFace("verdana").setSize(size).setColor("#FF0000").addElement((String)strresourceid.elementAt(j)).addElement("<BR>"));
                    }
                }
                else {
                    tr.addElement((Element)new TD().setWidth(50).setHeight(70).setAlign("center").setNoWrap(true).addElement((Element)new NOBR().addElement((Element)new Font().setFace("verdana").setSize(size).setColor("#0000FF").addElement(strWeek[0]))));
                }
            }
        }
        return tr;
    }
    
    public TR addWeekviewdate(final Vector<String[]> vWeekdata) {
        final String dayview = "dayview";
        final TR tr = new TR().setAlign("left");
        for (int i = 0; i < vWeekdata.size(); ++i) {
            final String[] strWeek = vWeekdata.elementAt(i);
            tr.addElement((Element)new TD().setWidth(14).setAlign("left").setHeight(10).setNoWrap(true).addElement((Element)new NOBR().addElement((Element)new A().setHref("javascript:calendarMethod('" + dayview + "','" + strWeek[1] + "');").addElement((Element)new Font().setColor("#0000FF").setFace("verdana").addElement(strWeek[0])))));
        }
        return tr;
    }
    
    public TR addWeekviewdata() {
        final String course_id = "str1";
        final String topic_id = "str2";
        final TR tr = new TR().setAlign("center").addElement((Element)new TD().setWidth(14).setNoWrap(true).setAlign("center").setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("Hello")))).addElement((Element)new TD().setNoWrap(true).setWidth(14).setAlign("center").setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("Hihdsfsdg d gfdgfdgfd")))).addElement((Element)new TD().setNoWrap(true).setWidth(14).setAlign("center").setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("fdgfdg thfghfg hfghfg3")))).addElement((Element)new TD().setNoWrap(true).setWidth(14).setAlign("center").setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("dfg fhfgh fhfghfg hfghfg4")))).addElement((Element)new TD().setNoWrap(true).setWidth(14).setAlign("center").setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("5")))).addElement((Element)new TD().setNoWrap(true).setWidth(14).setAlign("center").setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("6dfgfdgdgfdgd dfgfd fdhgfd fghfghfg")))).addElement((Element)new TD().setAlign("center").setWidth(14).setNoWrap(true).setHeight(15).addElement((Element)new A().setHref("javascript:resume2('" + course_id + "','" + topic_id + "');").addElement((Element)new Font().setColor("#0000FF").addElement("7fdgfdgd dfgfdg fd fdhfgh fghfgh fghfgh"))));
        return tr;
    }
    
    public TR addRowUserForNoReminder(final String r_id, final String r_name, final String reminder) {
        final Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        final TR tr = new TR().addElement((Element)new TD().setWidth("8%").setNoWrap(true).setHeight(15).addElement((Element)inputCheck1.setType("radio").setName(r_id).setValue(r_name).setTabindex("1"))).addElement((Element)new TD().setWidth("80%").setNoWrap(true).setHeight(15).addElement((Element)new NOBR().addElement((Element)new Font().setColor("003366").setSize(-2).setFace("verdana").addElement(reminder))));
        return tr;
    }
    
    public TR addRowUserForMReminder(final String r_id, final String r_name, final String reminder) {
        final Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        final TR tr = new TR().addElement((Element)new TD().setWidth("8%").setNoWrap(true).setHeight(15).addElement((Element)inputCheck1.setType("radio").setName(r_id).setValue(r_name).setTabindex("1"))).addElement((Element)new TD().setWidth("80%").setNoWrap(true).setHeight(15).addElement((Element)new NOBR().addElement((Element)new Font().setColor("003366").setFace("verdana").setSize(-2).addElement(reminder))));
        return tr;
    }
    
    public TR addRowUserForReminder(final String r_id, final String r_name, final String reminder, final String bname, final Option[] option, final String before, final String aname, final Option[] option1, final String after) {
        final Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        final TR tr = new TR().addElement((Element)new TD().setWidth("8%").setNoWrap(true).setHeight(15).addElement((Element)inputCheck1.setType("radio").setName(r_id).setValue(r_name).setTabindex("1"))).addElement((Element)new TD().setWidth("80%").setNoWrap(true).setHeight(15).addElement((Element)new NOBR().addElement((Element)new Font().setColor("003366").setFace("verdana").addElement(reminder).setSize(-2).addElement("&nbsp").addElement((Element)new Select().setName(bname).addElement(option)).addElement("&nbsp").addElement(before).addElement("&nbsp").addElement((Element)new Select().setName(aname).addElement(option1)).addElement("&nbsp").addElement(after))));
        return tr;
    }
    
    public TR addRowUserForRepeat(final String r_id, final String r_name, final String reminder, final String bname, final Option[] option, final String mname, final Option[] option1, final String before, final String aname, final Option[] option2, final String after) {
        final Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        final TR tr = new TR().addElement((Element)new TD().setWidth("8%").setNoWrap(true).setHeight(15).addElement((Element)inputCheck1.setType("radio").setName(r_id).setValue(r_name).setTabindex("1"))).addElement((Element)new TD().setWidth("80%").setNoWrap(true).setHeight(15).addElement((Element)new NOBR().addElement((Element)new Font().setColor("003366").setFace("verdana").setSize(-2).addElement(reminder).addElement("&nbsp").addElement((Element)new Select().setName(bname).addElement(option)).addElement((Element)new Select().setName(mname).addElement(option1)).addElement("&nbsp").addElement(before).addElement("&nbsp").addElement((Element)new Select().setName(aname).addElement(option2)).addElement("&nbsp").addElement(after))));
        return tr;
    }
    
    public TR addRowUserForUntil(final String id, final String name, final String title, final String umonth, final Option[] option, final String uday, final Option[] option1, final Input year) {
        final Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        final TR tr = new TR().addElement((Element)new TD().setWidth("8%").setNoWrap(true).setHeight(15).addElement((Element)inputCheck1.setType("radio").setName(id).setValue(name).setTabindex("1"))).addElement((Element)new TD().setWidth("80%").setNoWrap(true).setHeight(15).addElement((Element)new NOBR().addElement((Element)new Font().setColor("003366").setFace("verdana").setSize(-2).addElement(name).addElement("&nbsp").addElement((Element)new Select().setName(umonth).addElement(option)).addElement((Element)new Select().setName(uday).addElement(option1)).addElement((Element)year))));
        return tr;
    }
    
    public TR addSelect(final String txt, final String name, final String tabIndex, final Option[] option) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        return tr;
    }
    
    public TR add() {
        final TR tr = new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("140").addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("100%").addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }
    
    public TR addHR(final int colSpan) {
        final TR tr = new TR().addElement((Element)new TD().setColSpan(colSpan).addElement((Element)new HR().setSize(1)));
        return tr;
    }
    
    public TR addHeader(final String strHeader, final int colSpan) {
        final TR tr = new TR().addElement((Element)new TR().addElement((Element)new TD().setBgColor("#669933").setColSpan(colSpan).setHeight(30).setWidth("100%").addElement((Element)new Font().setColor("#FFFFFF").setFace("verdana").setSize(-2).addElement((Element)new B().addElement("&nbsp;&nbsp;" + strHeader + "&nbsp;")))));
        return tr;
    }
    
    public TR addHeaderNames(final String strSelect, final String strAdminName, final String strCreatedDate, final String strLastMod, final String strStatus) {
        final TR tr = new TR().setBgColor("#669933").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;")))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("sbbd").setColor("003366").addElement("&nbsp;&nbsp;" + strAdminName + "&nbsp;")))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strCreatedDate + "&nbsp;")))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("sbbd").setColor("003366").addElement("&nbsp;&nbsp;" + strLastMod + "&nbsp;")))).addElement((Element)new TD().setWidth("8%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strStatus + "&nbsp;"))));
        return tr;
    }
    
    public TR addRowUser(final String strUserId, final String strName, final String strCreadtedDate, final String strLastModDate, final String strStatus) {
        final Input inputCheck1 = new Input();
        String colour = "";
        if (strStatus.equals("Active")) {
            colour = "3366FF";
        }
        else {
            colour = "FF0000";
        }
        final TR tr = new TR().setBgColor("#FFFFFF").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;").addElement((Element)new Input().setType("hidden").setName("hiddenUnit").setValue(strName)))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreadtedDate + "&nbsp;"))).addElement((Element)new TD().setWidth("18%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLastModDate + "&nbsp;"))).addElement((Element)new TD().setWidth("8%").addElement((Element)new NOBR().addElement((Element)new Font().setColor(colour).addElement("&nbsp;&nbsp;" + strStatus + "&nbsp;").addElement((Element)new Input().setType("hidden").setName("hiddenStatus").setValue(strStatus)))));
        inputCheck1.setOnClick("CCA();checkbox_onclick();");
        return tr;
    }
    
    public TR addRequired(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex).addElement((Element)new Font().setColor("FF0000").addElement("*"))));
        return tr;
    }
    
    public TR addSelect2(final String txt, final String name, final String tabIndex, final Option[] option, final String btn, final String val) {
        final IMG in = new IMG();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option))).addElement((Element)new TD().addElement((Element)in.setName(btn).setWidth(21).setHeight(16).setSrc("../images/btn_go_off.gif")));
        in.setOnClick("document.frm.prmGo.value=" + val + ";Go_onclick()");
        return tr;
    }
    
    public TR addLine() {
        final TR tr = new TR().addElement((Element)new TD().setBgColor("023264").setHeight(1).setWidth(1)).addElement((Element)new TD().setBgColor("023264").setHeight(1).setWidth(1)).addElement((Element)new TD().setBgColor("023264").setHeight(1).setWidth(1));
        return tr;
    }
    
    public TR add2() {
        final TR tr = new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("140").addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }
}
