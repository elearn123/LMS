package calendar.calendar;

import org.grlea.log.*;
import org.apache.ecs.*;
import java.util.*;

import org.apache.ecs.html.*;

public class CalTableExtension extends Table
{
    private static final SimpleLogger log;
    
    public Table add() {
        final Table tbl = new Table().addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TD().setWidth(140).addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../coreadmin/images/T.gif"))).addElement((Element)new TD().setWidth(140).addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../coreadmin/images/T.gif")))));
        return tbl;
    }
    
    public Table addHR() {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new CalTRExtension().add()).addElement((Element)new CalTRExtension().addHR(8)));
        return tbl;
    }
    
    public Table addHeading(final String strtime, final String strMycalendar) {
        final Table tbl = new Table().setBorder(0).setWidth("100%").setCellPadding(2).setCellSpacing(0).setBgColor("#eeeeee").addElement((Element)new TBody().addElement((Element)new CalTRExtension().addHeading(strtime, strMycalendar)));
        return tbl;
    }
    
    public Table addHeadingDays(final String s, final String m, final String t, final String w, final String th, final String f, final String sa) {
        final Table tbl = new Table().setBorder(1).setWidth("100%").setCellPadding(2).setCellSpacing(0).setBgColor("#CCCC66").addElement((Element)new TBody().addElement((Element)new TR().addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(s))).addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(m))).addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(t))).addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(w))).addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(th))).addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(f))).addElement((Element)new TH().setWidth("14%").addElement((Element)new Font().setColor("#FFFFFF").addElement(sa)))));
        return tbl;
    }
    
    public Table addCalendarMonth(final Vector vMonth, final String monthname, final int s) {
        final Table tbl = new Table().addElement((Element)new Caption().setAlign("top").addElement((Element)new B().addElement((Element)new Font().setFontClass("cap").addElement(monthname))));
        tbl.addElement((Element)new CalTRExtension().addHeadingDays(" S ", " M ", " T ", " W ", " T ", " F ", " S ", s));
        final String[] strMonth = (String[]) vMonth.elementAt(0);
        for (int count = 1; count < vMonth.size(); ++count) {
            final Vector vWeekdays = (Vector)vMonth.elementAt(count);
            CalTableExtension.log.entry("addCalendarMonth()");
            CalTableExtension.log.info("size =" + vWeekdays.size());
            final String[] str11 = (String[]) vMonth.elementAt(0);
            CalTableExtension.log.info("******(String[])vMonth.elementAt(0)*********" + str11[0]);
            tbl.addElement((Element)new CalTRExtension().addWeekviewdays(vWeekdays, s));
        }
        return tbl;
    }
    
    public Table addTextArea(final String strbody) {
        final Table tbl = new Table();
        tbl.addElement((Element)new TR().addElement((Element)new TD().setAlign("center").addElement((Element)new B().addElement((Element)new Font().setColor("#000000").setSize(-2).setFace("verdana").addElement("&nbsp;"))))).addElement((Element)new TR().addElement((Element)new TD().setAlign("center").setWidth("100%").addElement((Element)new Font().setColor("#EEEEEE").addElement((Element)new TextArea().setRows(4).setCols(30).setName("calendartext").addElement(strbody).setDisabled(false)))));
        return tbl;
    }
    
    public Table headerTable(final String strAdmin, final String strDate, final String strTime) {
        final Table tbl = new Table().setCellPadding(2).setCellSpacing(1).setWidth("75%").addElement((Element)new TR().setBgColor("#669933").addElement((Element)new TD().setHeight(23).setWidth("50%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strAdmin + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;<b>Date : </b>" + strDate + "&nbsp;"))))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new P().setAlign("center").addElement((Element)new Font().setColor("#FFFFFF").addElement("&nbsp;&nbsp;<b>Time : </b>" + strTime + "&nbsp;"))))));
        return tbl;
    }
    
    public Table addRequired(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new CalTRExtension().add()).addElement((Element)new CalTRExtension().addRequired(txt, type, name, maxlength, size, tabIndex)));
        return tbl;
    }
    
    public Table addSelect(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Table tbl = new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TBody().addElement((Element)new CalTRExtension().add()).addElement((Element)new CalTRExtension().addSelect(txt, name, tabIndex, option)));
        return tbl;
    }
    
    static {
        log = new SimpleLogger((Class)CalTableExtension.class);
    }
}
