package learnityasmtserver.assessmentportal.utility;

import org.grlea.log.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;

public class TableExtension extends Table
{
    public static final SimpleLogger log;
    
    public Table add() {
        final Table tbl = new Table().addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TD().setWidth(140).addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth(140).addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif")))));
        return tbl;
    }
    
    public Table add(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().add(txt, type, name, maxlength, size, tabIndex)));
        return tbl;
    }
    
    public Table addRadio(final String txt, final String type, final String name, final String radio1, final String radio2) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addRadio(txt, type, name, radio1, radio2)));
        return tbl;
    }
    
    public Table addHR1() {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addHR1()));
        return tbl;
    }
    
    public Table addCalender(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addCalender(txt, type, name, maxlength, size, tabIndex, strFormat)));
        return tbl;
    }
    
    public Table addCalender1(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat, final Option[] option, final String btn, final String val) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add1()).addElement((Element)new TRExtension().addCalender1(txt, type, name, maxlength, size, tabIndex, strFormat, option, btn, val)));
        return tbl;
    }
    
    public Table addCalender2(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat, final String obj) {
        final Table tbl = new Table().setBorder(0).setCellPadding(0).setCellSpacing(0).addElement((Element)new TRExtension().addCalender2(txt, type, name, maxlength, size, tabIndex, strFormat, obj));
        return tbl;
    }
    
    public Table addRequired(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addRequired(txt, type, name, maxlength, size, tabIndex)));
        return tbl;
    }
    
    public Table addSelect3(final String txt, final String name, final String tabIndex, final Option[] option, final String select) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addSelect3(txt, name, tabIndex, option, select)));
        return tbl;
    }
    
    public Table addText4(final String txt, final String txt1) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addText4(txt, txt1)));
        return tbl;
    }
    
    public Table addSelect(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addSelect(txt, name, tabIndex, option)));
        return tbl;
    }
    
    public Table addSelectGo(final String txt, final String name, final String tabIndex, final Option[] option, final String txt1, final String name1, final Option[] option1) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addSelectGo(txt, name, tabIndex, option, txt1, name1, option1)));
        return tbl;
    }
    
    public Table addSelectUpdate(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addSelectUpdate(txt, name, tabIndex, option)));
        return tbl;
    }
    
    public Table addSelectGo(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addSelectGo(txt, name, tabIndex, option)));
        return tbl;
    }
    
    public Table addSelectNoGo(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addSelectNoGo(txt, name, tabIndex, option)));
        return tbl;
    }
    
    public Table addSelectReport(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().addSelectReport(txt, name, tabIndex, option)));
        return tbl;
    }
    
    public Table addSelect1(final String txt, final String name, final String tabIndex, final Option[] option, final String btn, final String val) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add2()).addElement((Element)new TRExtension().addSelect2(txt, name, tabIndex, option, btn, val)));
        return tbl;
    }
    
    public Table addSelect(final String txt, final String name, final String tabIndex, final Option[] option, final String select) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addSelect(txt, name, tabIndex, option, select)));
        return tbl;
    }
    
    public Table addCheckBox(final String txt, final String type, final String name, final String value, final String tabIndex) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addCheckBox(txt, type, name, value, tabIndex)));
        return tbl;
    }
    
    public Table addHR() {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TRExtension().add()).addElement((Element)new TRExtension().addHR(8)));
        return tbl;
    }
    
    public Table addSelectButton(final String txt, final String name, final String tabIndex, final Option[] option, final String btnName, final String btnValue, final String title, final String click) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("140").addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")))).addElement((Element)new TRExtension().addSelectButton(txt, name, tabIndex, option, btnName, btnValue, title, click)));
        return tbl;
    }
    
    public Table headerTable(final String strAdmin, final String strDate, final String strTime, final String strFnc) {
        final Table tbl = new Table().setCellPadding(2).setCellSpacing(1).setWidth("75%").addElement((Element)new TR().addElement((Element)new TD().setHeight(23).setWidth("50%").setClassId("swn").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement("&nbsp;&nbsp;" + strAdmin + "&nbsp;")))).addElement((Element)new TD().setWidth("25%").setClassId("swn").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement("&nbsp;&nbsp;<b>Date : </b>" + strDate + "&nbsp;")))).addElement((Element)new TD().setWidth("25%").setClassId("swn").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement("&nbsp;&nbsp;<b>Time : </b>" + strTime + "&nbsp;"))))).addElement((Element)new TR().addElement((Element)new TD().setHeight(23).setWidth("50%").setClassId("swn").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement("&nbsp;&nbsp;" + strFnc + "&nbsp;")))).addElement((Element)new TD().setWidth("25%")).addElement((Element)new TD().setWidth("25%")));
        return tbl;
    }
    
    public Table headerTable(final String str, final String strAdmin, final String strDate, final String strTime, final String strFnc, final String administration, final String no) {
        final Table tbl = new Table().setCellPadding(2).setCellSpacing(1).setWidth("75%").addElement((Element)new TR().setBgColor("990000").addElement((Element)new TD().setHeight(23).setWidth("50%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strAdmin + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;<b>Date : </b>" + strDate + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;<b>Time : </b>" + strTime + "&nbsp;")))))).addElement((Element)new TR().addElement((Element)new TD().setBgColor("990000").setHeight(23).setWidth("50%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strFnc + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%")).addElement((Element)new TD().setWidth("25%"))).addElement((Element)new TR().addElement((Element)new TD().setWidth("50%").addElement((Element)new IMG().setWidth("140").setHeight("5").setBorder(0).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("25%")).addElement((Element)new TD().setWidth("25%"))).addElement((Element)new TR().addElement((Element)new TD().setWidth("50%").setBgColor("990000").setHeight(23).addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + str + "&nbsp;"))).addElement((Element)new TD().setWidth("25%").setBgColor("990000").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + administration + "&nbsp;"))).addElement((Element)new TD().setWidth("25%"))).addElement((Element)new TR().addElement((Element)new TD().setWidth("50%").setBgColor("990000").setHeight(23).addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;Average login time (In minutes)&nbsp;"))).addElement((Element)new TD().setBgColor("990000").setWidth("25%").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + no + "&nbsp;"))).addElement((Element)new TD().setWidth("25%")));
        return tbl;
    }
    
    public Table headerTable(final String str, final String strAdmin, final String strDate, final String strTime, final String strFnc, final String administration, final String no, final String misc) {
        final Table tbl = new Table().setCellPadding(2).setCellSpacing(1).setWidth("75%").addElement((Element)new TR().setBgColor("990000").addElement((Element)new TD().setHeight(23).setWidth("50%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strAdmin + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;<b>Date : </b>" + strDate + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement((Element)new Font().setColor("#FFFFFF").addElement("<b>&nbsp;&nbsp;Time : </b>" + strTime + "&nbsp;")))))).addElement((Element)new TR().addElement((Element)new TD().setBgColor("990000").setHeight(23).setWidth("50%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strFnc + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%")).addElement((Element)new TD().setWidth("25%"))).addElement((Element)new TR().addElement((Element)new TD().setWidth("50%").addElement((Element)new IMG().setWidth("140").setHeight("5").setBorder(0).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("25%")).addElement((Element)new TD().setWidth("25%"))).addElement((Element)new TR().addElement((Element)new TD().setWidth("50%").setBgColor("990000").setHeight(23).addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + str + "&nbsp;"))).addElement((Element)new TD().setWidth("25%").setBgColor("990000").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + administration + "&nbsp;"))).addElement((Element)new TD().setWidth("25%"))).addElement((Element)new TR().addElement((Element)new TD().setWidth("50%").setBgColor("990000").setHeight(23).addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + misc + "&nbsp;"))).addElement((Element)new TD().setBgColor("990000").setWidth("25%").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + no + "&nbsp;"))).addElement((Element)new TD().setWidth("25%")));
        return tbl;
    }
    
    public Table addTextArea(final String ct) {
        final Table tbl = new Table();
        tbl.addElement((Element)new TR().addElement((Element)new TD().setAlign("center").addElement((Element)new B().addElement((Element)new Font().setColor("#000000").setSize(-2).setFace("verdana").addElement("&nbsp;"))))).addElement((Element)new TR().addElement((Element)new TD().setAlign("center").setWidth("100%").addElement((Element)new Font().setColor("#EEEEEE").addElement((Element)new TextArea().setRows(4).setCols(30).setName("calendartext").addElement(ct).setDisabled(false)))));
        return tbl;
    }
    
    static {
        log = new SimpleLogger((Class)TableExtension.class, true);
    }
}
