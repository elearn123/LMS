package learnityasmtserver.assessmentportal.utility;

import org.grlea.log.*;
import org.apache.ecs.*;
import org.apache.ecs.html.*;

public class TRExtension extends TR
{
    public static final SimpleLogger log;
    
    public TR addRowUserForAsmt(final String strUserId, final String strName, final String totatTime, final String count) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("20%").setHeight(23).addElement(strUserId)).addElement((Element)new TD().setWidth("30%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + totatTime + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + count + "&nbsp;")));
        return tr;
    }
    
    public TR addRowUser(final String strUserId, final String strName, final String totatTime, final String count) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("20%").setHeight(25).addElement(strUserId)).addElement((Element)new TD().setWidth("30%").setHeight(25).addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").setHeight(25).addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + totatTime + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").setHeight(25).addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + count + "&nbsp;")));
        return tr;
    }
    
    public TR addHeaderNameForAssessment(final String strUnit, final String strGroup) {
        final TR tr = new TR().addElement((Element)new TD().setColSpan(2).setWidth("50%").setHeight(25).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strUnit + "&nbsp;"))).addElement((Element)new TD().setWidth("50%").setColSpan(2).setHeight(25).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strGroup + "&nbsp;")));
        return tr;
    }
    
    public TR addUserForSection(final String str1, final String str2) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("50%").setHeight(25).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + str1 + "&nbsp;"))).addElement((Element)new TD().setWidth("50%").setHeight(25).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + str2 + "&nbsp;")));
        return tr;
    }
    
    public TR addRowUserForAssessment(final String strUserId, final String strName) {
        final Input inputCheck1 = new Input();
        final Input inputCheck2 = new Input();
        final TR tr = new TR().setBgColor("F0F0F0").addElement((Element)new TD().setWidth("10%").setHeight(23).addElement((Element)inputCheck1.setType("checkbox").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("90%").setColSpan(6).addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;")).addElement((Element)inputCheck2.setType("hidden").setName("header").setValue(strName).setTabindex("1")));
        inputCheck1.setOnClick("CCA();checkbox_onclick();");
        return tr;
    }
    
    public TR addText4(final String text, final String text1) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("200").addElement(text)).addElement((Element)new TD().setClassId("PPRSmallLabelText").addElement(text1));
        return tr;
    }
    
    public TR addSelect3(final String txt, final String name, final String tabIndex, final Option[] option, final String select) {
        final Select sel = new Select();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("100").addElement(txt)).addElement((Element)new TD().setWidth("150").addElement((Element)sel.setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        sel.setOnChange(select);
        return tr;
    }
    
    public TR add() {
        final TR tr = new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("140").addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("100%").addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }
    
    public TR add1() {
        final TR tr = new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("140").addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }
    
    public TR add2() {
        final TR tr = new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth("140").addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }
    
    public TR addRowImage() {
        final TR tr = new TR().addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif")));
        return tr;
    }
    
    public TR add(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)));
        return tr;
    }
    
    public TR addcheckbox(final String txt, final String type, final String name, final String tabIndex, final String value, final boolean check) {
        final Input checkButton = new Input();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)checkButton.setType(type).setName(name).setValue(value).setTabindex(tabIndex)));
        checkButton.setChecked(check);
        return tr;
    }
    
    public TR addRequired(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex).addElement((Element)new Font().setColor("FF0000").addElement("*"))));
        return tr;
    }
    
    public TR addRequiredDisabled(final String txt, final String type, final String name, final String maxlength, final String size, final boolean disabled, final String tabIndex) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex).setDisabled(disabled).addElement((Element)new Font().setColor("FF0000").addElement("*"))));
        return tr;
    }
    
    public TR addRadio(final String gender, final String type, final String name) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(gender)).addElement((Element)new TD().setWidth("336").setClassId("PPCheckRadio").addElement((Element)new Input(type, name, "m").setTabindex("1").setChecked(true).addElement("Male")).addElement((Element)new Input(type, name, "f").setTabindex("1").setChecked(false).addElement("Female")));
        return tr;
    }
    
    public TR addRadio(final String gender, final String type, final String name, final String radio1, final String radio2) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(gender)).addElement((Element)new TD().setWidth("336").setClassId("PPCheckRadio").addElement((Element)new Input(type, name, "m").setTabindex("1").setChecked(false).addElement(radio1)).addElement((Element)new Input(type, name, "f").setTabindex("1").setChecked(false).addElement(radio2)));
        return tr;
    }
    
    public TR addEmail(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement((Element)new Span().setClassId("PPRSmallLabelText").addElement((Element)new BR().addElement(strFormat))));
        return tr;
    }
    
    public TR addCalender(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat) {
        final A a = new A();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement((Element)a.setHref("javascript:doNothing()").addElement((Element)new Font().setSize(1).addElement((Element)new IMG().setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("MIDDLE").addElement("Calendar")))).addElement((Element)new Span().setClassId("PPRSmallLabelText").addElement((Element)new BR().addElement(strFormat))));
        a.setOnClick("showCal(frm." + name + ", 'Y');");
        return tr;
    }
    
    public TR addCalender1(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat, final Option[] option, final String btn, final String val) {
        final A a = new A();
        final IMG in = new IMG();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt)).addElement((Element)new TD().addElement((Element)new Select().setName(btn).setClassId("drpdwn").setTabindex(tabIndex).addElement(option))).addElement((Element)new TD().addElement((Element)new Input().setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement((Element)a.setHref("javascript:doNothing()").addElement((Element)new Font().setSize(1).addElement((Element)new IMG().setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("MIDDLE").addElement("Calendar"))))).addElement((Element)new TD().addElement((Element)in.setWidth(21).setHeight(16).setSrc("../images/btn_go_off.gif")));
        in.setOnClick("document.frm.prmGo.value=" + val + ";Go_onclick()");
        a.setOnClick("showCal(frm." + name + ", 'Y');");
        return tr;
    }
    
    public TR addRowBack(final String txt, final String type, final String name, final String maxLenght, final String size, final String tabIndex, final String txt1, final String type1, final Input btn) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt).addElement("&nbsp;:&nbsp;").addElement((Element)new Input().setType(type).setClassId("PPRLabelText").setMaxlength(maxLenght).setName(name).setSize(size).setTitleValue("enter file name to store result").setTabindex(tabIndex))).addElement((Element)new TD().addElement((Element)btn.setType(type1).setClassId("sbttn").setName(txt1).setValue(txt1).setTitleValue(txt1).setTabindex(tabIndex)));
        return tr;
    }
    
    public TR addCalender2(final String txt, final String type, final String name, final String maxlength, final String size, final String tabIndex, final String strFormat, final String obj) {
        final A a = new A();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt)).addElement((Element)new TD().addElement((Element)new Input().setType(type).setClassId("PPRLabelText").setMaxlength(maxlength).setName(name).setSize(size).setTitleValue(strFormat).setTabindex(tabIndex)).addElement((Element)a.setHref("javascript:popup('" + obj + "');").addElement((Element)new Font().setSize(1).addElement((Element)new IMG().setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("MIDDLE").addElement("Calendar &nbsp;")))));
        return tr;
    }
    
    public TR addSelect(final String txt, final String name, final String tabIndex, final Option[] option) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        return tr;
    }
    
    public TR addSelectGo(final String txt, final String name, final String tabIndex, final Option[] option, final String txt1, final String name1, final Option[] option1) {
        final Input btnGo = new Input().setClassId("sbttn").setName("go").setType("button").setValue("Go");
        btnGo.setOnClick("assessmentUnit_onchange();");
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option).addElement("&nbsp;&nbsp;&nbsp"))).addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt1).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name1).setTabindex(tabIndex).addElement(option1)).addElement("&nbsp;&nbsp;").addElement((Element)btnGo));
        return tr;
    }
    
    public TR addSelectUpdate(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Input btnGo = new Input().setClassId("drpdwn").setName("Update").setType("button").setValue("Update");
        btnGo.setOnClick("assessmentUnit_onchange();");
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)btnGo));
        return tr;
    }
    
    public TR addSelectReport(final String txt, final String name, final String tabIndex, final Option[] option) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        return tr;
    }
    
    public TR addSelectGo(final String txt, final String name, final String tabIndex, final Option[] option) {
        final Input btnGo = new Input().setClassId("sbttn").setName("go").setType("button").setValue("Go");
        btnGo.setOnClick("assessmentUnit_onchange();");
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)).addElement("&nbsp;&nbsp").addElement((Element)btnGo));
        return tr;
    }
    
    public TR addSelectNoGo(final String txt, final String name, final String tabIndex, final Option[] option) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").addElement(txt).addElement("&nbsp;&nbsp;&nbsp")).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        return tr;
    }
    
    public TR addSelect2(final String txt, final String name, final String tabIndex, final Option[] option, final String btn, final String val) {
        final IMG in = new IMG();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option))).addElement((Element)new TD().addElement((Element)in.setName(btn).setWidth(21).setHeight(16).setSrc("../images/btn_go_off.gif")));
        in.setOnClick("document.frm.prmGo.value=" + val + ";Go_onclick()");
        return tr;
    }
    
    public TR addSelect(final String txt, final String name, final String tabIndex, final Option[] option, final String select) {
        final Select sel = new Select();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement((Element)new NOBR().addElement(txt).addElement("&nbsp;&nbsp;"))).addElement((Element)new TD().setWidth("336").addElement((Element)sel.setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        sel.setOnChange(select);
        return tr;
    }
    
    public TR addCheckBox(final String txt, final String type, final String name, final String value, final String tabIndex) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((Element)new TD().setWidth("336").addElement((Element)new Input().setType(type).setName(name).setValue(value).setTabindex(tabIndex)));
        return tr;
    }
    
    public TR addSelectButton(final String txt, final String name, final String tabIndex, final Option[] option, final String btnName, final String btnValue, final String title, final String click) {
        final Input inputButton = new Input();
        final TR tr = new TR().addElement((Element)new TD().setClassId("PPRLabelText").setWidth("140").addElement(txt)).addElement((Element)new TD().addElement((Element)new Select().setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option))).addElement((Element)new TD().setWidth("5")).addElement((Element)new TD().addElement((Element)new Table().setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((Element)new TR().addElement((Element)new TD().addElement((Element)inputButton.setClassId("sbttn").setName(btnName).setTabindex(1).setType("button").setTitleValue(title).setValue(btnValue))))));
        inputButton.setOnClick(click);
        return tr;
    }
    
    public TR addRowImage1() {
        final TR tr = new TR().addElement((Element)new TD().setRowSpan(3).addElement((Element)new IMG().setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((Element)new TD().setWidth(140).addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(140).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((Element)new TD().addElement((Element)new IMG().setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif")));
        return tr;
    }
    
    public TR addLine() {
        final TR tr = new TR().addElement((Element)new TD().setBgColor("023264").setHeight(1).setWidth(1)).addElement((Element)new TD().setBgColor("023264").setHeight(1).setWidth(1)).addElement((Element)new TD().setBgColor("023264").setHeight(1).setWidth(1));
        return tr;
    }
    
    public TR addRowUser(final String strUserId, final String strName, final String strCreadtedDate, final String strCreatedBy, final String strLastModDate, final String strStatus) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)new NOBR().addElement(strUserId))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;"))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreadtedDate + "&nbsp;"))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("18%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLastModDate + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strStatus + "&nbsp;")));
        return tr;
    }
    
    public TR addRowUser(final String strUserId, final String strName, final String strCreadtedDate, final String strCreatedBy, final String strSection, final String strLastModDate, final String modifiedBy, final String no, final String max, final String score, final String percent, final String dateTime) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("10%").setHeight(23).addElement(strUserId)).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreadtedDate + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strSection + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLastModDate + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + modifiedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + no + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + max + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + score + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + percent + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + dateTime + "&nbsp;")));
        return tr;
    }
    
    public TR addHeaderNames(final String strSelect, final String strAdminName, final String strCreatedDate, final String strCreatedBy, final String strSection, final String strLastMod, final String modifiedBy, final String No, final String max, final String score, final String percent, final String strDateTime) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("10%").setHeight(23).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strAdminName + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedDate + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strSection + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLastMod + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + modifiedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + No + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + max + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + score + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + percent + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strDateTime + "&nbsp;")));
        return tr;
    }
    
    public TR addRowCalendarUser(final String strUserId, final String strName, final String strdate, final String strCreadtedDate, final String strCreatedTime, final String strDesc, final String strRepeat, final String StrReminder) {
        final Input inputCheck1 = new Input();
        String strbody = "";
        if (strDesc.length() > 50) {
            strbody = strDesc.substring(0, 50);
        }
        else {
            strbody = strDesc;
        }
        final TR tr = new TR().setBgColor("F0F0F0").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;").addElement((Element)new Input().setType("hidden").setName("hiddenid").setValue(strdate)))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreadtedDate + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedTime + "&nbsp;"))).addElement((Element)new TD().setWidth("55%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strbody + "&nbsp;")));
        inputCheck1.setOnClick("CCA();checkbox_onclick();");
        return tr;
    }
    
    public TR addRowUser(final String name, final String strUserId, final String strName, final String hiddenname, final int a) {
        final Input inputCheck1 = new Input();
        final TR tr = new TR().setBgColor("C0C0C0").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName(name).setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;").addElement((Element)new Input().setType("hidden").setName(hiddenname).setValue(strName))));
        inputCheck1.setOnClick("CCA();checkbox_onclick();");
        return tr;
    }
    
    public TR addRowGroup(final String name, final String strUserId, final String strName, final String hiddenname) {
        final Input inputCheck1 = new Input();
        final TR tr = new TR().setBgColor("C0C0C0").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName(name).setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;").addElement((Element)new Input().setType("hidden").setName(hiddenname).setValue(strName))));
        inputCheck1.setOnClick("CCA();checkbox1_onclick();");
        return tr;
    }
    
    public TR addRowUserList(final String name, final String strUserId, final String strName, final String hiddenname) {
        final Input inputCheck1 = new Input();
        final TR tr = new TR().setBgColor("C0C0C0").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("checkbox").setName(name).setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("15%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;").addElement((Element)new Input().setType("hidden").setName(hiddenname).setValue(strName))));
        inputCheck1.setOnClick("CCA();checkbox_onclick();");
        return tr;
    }
    
    public TR addRowUser(final String strUserId, final String strName, final String strLogin, final String strFun, final String strCConnectedFor) {
        final Input inputCheck1 = new Input();
        final TR tr = new TR().setBgColor("C0C0C0").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLogin + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strFun + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCConnectedFor + "&nbsp;")));
        inputCheck1.setOnClick("CCA();");
        return tr;
    }
    
    public TR addDynamicInformationByUnit(final String strUserId, final String strName, final String strLogin, final String strFun, final String strCConnectedFor) {
        final Input inputCheck1 = new Input();
        final TR tr = new TR().setBgColor("FFEEBB").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;").addElement((Element)new Font().setColor("#0000FF").addElement((Element)new A().setHref("./administration.UnitAdministration?unit_id=" + strUserId).addElement(strName))).addElement("&nbsp;"))).addElement((Element)new TD().setWidth("30%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLogin + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strFun + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCConnectedFor + "&nbsp;")));
        inputCheck1.setOnClick("CCA();");
        return tr;
    }
    
    public TR addRowUser1(final String strUserId, final String strName, final String strLogin, final String strFun, final String strCConnectedFor, final String click) {
        final Input inputCheck1 = new Input();
        final TR tr = new TR().setBgColor("FFEEBB").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strName + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLogin + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strFun + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCConnectedFor + "&nbsp;")));
        inputCheck1.setOnClick("CCA();" + click + ";");
        return tr;
    }
    
    public TR addHR() {
        final TR tr = new TR().addElement((Element)new TD().setColSpan(6).addElement((Element)new HR().setSize(1)));
        return tr;
    }
    
    public TR addCurrentUser(final String str, final int i) {
        final TR tr = new TR().addElement((Element)new TD().setColSpan(6).addElement((Element)new NOBR().addElement((Element)new P().setAlign("left").addElement((Element)new Font().setSize(2).addElement((Element)new B().addElement("&nbsp;&nbsp;" + str + "&nbsp;"))).addElement("" + i))));
        return tr;
    }
    
    public TR addHR(final int colSpan) {
        final TR tr = new TR().addElement((Element)new TD().setColSpan(colSpan).addElement((Element)new HR().setSize(1)));
        return tr;
    }
    
    public TR addHR1() {
        final TR tr = new TR().addElement((Element)new TD().setWidth(140).addElement((Element)new HR().setSize(1))).addElement((Element)new TD().setWidth(336).addElement((Element)new HR().setSize(1)));
        return tr;
    }
    
    public TR addHeader(final String strHeader) {
        final TR tr = new TR().addElement((Element)new TR().addElement((Element)new TD().setBgColor("990000").setColSpan(6).setHeight(30).setWidth("100%").addElement((Element)new Font().setColor("#FFFFFF").addElement((Element)new B().addElement("&nbsp;&nbsp;" + strHeader + "&nbsp;")))));
        return tr;
    }
    
    public TR addHeader(final String strHeader, final int colSpan) {
        final TR tr = new TR().addElement((Element)new TR().addElement((Element)new TD().setColSpan(colSpan).setHeight(20).setWidth("100%").setClassId("swb").addElement((Element)new B().addElement("&nbsp;&nbsp;" + strHeader + "&nbsp;"))));
        return tr;
    }
    
    public TR addHeaderNames(final String strSelect, final String strAdminName, final String strCreatedDate, final String strCreatedBy, final String strLastMod, final String strStatus) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("5%").setHeight(23).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strAdminName + "&nbsp;"))).addElement((Element)new TD().setWidth("25%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedDate + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").setWidth("25%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("18%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLastMod + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strStatus + "&nbsp;")));
        return tr;
    }
    
    public TR addHeaderNames(final String strSelect, final String strAdminName, final String strCreatedDate, final String strCreatedBy, final String strLastMod, final String modifiedBy, final String No, final String strStatus) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("5%").setHeight(23).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strAdminName + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedDate + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCreatedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strLastMod + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + modifiedBy + "&nbsp;"))).addElement((Element)new TD().setWidth("12%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + No + "&nbsp;"))).addElement((Element)new TD().setWidth("8%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strStatus + "&nbsp;")));
        return tr;
    }
    
    public TR addHeaderNames(final String strUserId, final String strUserName, final String strTotalTime, final String strCount) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("40%").setHeight(23).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strUserId + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strUserName + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strTotalTime + "&nbsp;"))).addElement((Element)new TD().setWidth("15%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strCount + "&nbsp;")));
        return tr;
    }
    
    public TR addHeaderNames(final String strUserId, final String strUserName, final String strTotalTime) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("20%").setHeight(23).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strUserId + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strUserName + "&nbsp;"))).addElement((Element)new TD().setWidth("30%").setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + strTotalTime + "&nbsp;")));
        return tr;
    }
    
    public TR addHeaderNames(final String strSelect, final String strAdmin, final String strLogin, final String strConnectedFor, final String strFunction) {
        final TR tr = new TR().setBgColor("990000").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("swb").setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;")))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strAdmin + "&nbsp;")))).addElement((Element)new TD().setWidth("30%").setBgColor("660000").addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("sbbd").setColor("003366").addElement("&nbsp;&nbsp;" + strLogin + "&nbsp;")))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strFunction + "&nbsp;")))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strConnectedFor + "&nbsp;"))));
        return tr;
    }
    
    public TR addHeaderNamesforUnit(final String strSelect, final String strAdmin, final String strLogin, final String strConnectedFor, final String strFunction) {
        final TR tr = new TR().setBgColor("990000").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("swb").setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;")))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strAdmin + "&nbsp;")))).addElement((Element)new TD().setWidth("30%").setBgColor("660000").addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("sbbd").setColor("003366").addElement("&nbsp;&nbsp;" + strLogin + "&nbsp;")))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strFunction + "&nbsp;")))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strConnectedFor + "&nbsp;"))));
        return tr;
    }
    
    public TR addHeaderNames1(final String strSelect, final String strName, final String strCreatedDate, final String strCreatedBy, final String strLModDate) {
        final TR tr = new TR().setBgColor("990000").addElement((Element)new TD().setWidth("5%").setHeight(23).addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("swb").setColor("#FFFFFF").addElement("&nbsp;&nbsp;" + strSelect + "&nbsp;")))).addElement((Element)new TD().setWidth("25%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strName + "&nbsp;")))).addElement((Element)new TD().setWidth("30%").setBgColor("660000").addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("sbbd").setColor("003366").addElement("&nbsp;&nbsp;" + strCreatedDate + "&nbsp;")))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement((Element)new Font().setColor("#FFFFFF").setFontClass("swb").addElement("&nbsp;&nbsp;" + strCreatedBy + "&nbsp;")))).addElement((Element)new TD().setWidth("20%").setBgColor("660000").addElement((Element)new NOBR().addElement((Element)new Font().setFontClass("sbbd").setColor("003366").addElement("&nbsp;&nbsp;" + strLModDate + "&nbsp;"))));
        return tr;
    }
    
    public TR addRowUserForNoReminder(final String r_id, final String r_name, final String reminder) {
        final Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        final TR tr = new TR().addElement((Element)new TD().setWidth("8%").setNoWrap(true).setHeight(15).addElement((Element)inputCheck1.setType("radio").setName(r_id).setValue(r_name).setTabindex("1"))).addElement((Element)new TD().setWidth("80%").setNoWrap(true).setHeight(15).addElement((Element)new NOBR().addElement((Element)new Font().setColor("003366").setSize(-2).setFace("verdana").addElement(reminder))));
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
    
    public TR addRow(final String c, final Select d) {
        final TR tr = new TR().addElement((Element)new TD().setClassId("swb").addElement((Element)new B().addElement(c))).addElement((Element)new TD().setClassId("swb").addElement((Element)new B().addElement((Element)d)));
        return tr;
    }
    
    public TR addReportHeader(final String STR1, final String STR2, final String STR3, final String STR4, final String STR5, final String STR6, final String STR7, final String STR8, final String STR9, final String STR10, final String STR11, final String STR12, final String STR13, final String STR14, final String STR15, final String STR16, final String STR17, final String STR18, final String STR19) {
        final TR tr = new TR().addElement((Element)new TD().setHeight(23).setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR1 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR2 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR3 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR4 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR5 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR6 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR7 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR8 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR9 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR10 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR11 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR12 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR13 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR14 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR15 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR16 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR17 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR18 + "&nbsp;"))).addElement((Element)new TD().setClassId("swb").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR19 + "&nbsp;")));
        return tr;
    }
    
    public TR addReportRowUser(final String STR1, final String STR2, final String STR3, final String STR4, final String STR5, final String STR6, final String STR7, final String STR8, final String STR9, final String STR10, final String STR11, final String STR12, final String STR13, final String STR14, final String STR15, final String STR16, final String STR17, final String STR18, final String STR19) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("10%").setHeight(23).addElement(STR1)).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR2 + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR3 + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR4 + "&nbsp;"))).addElement((Element)new TD().setWidth("10%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR5 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR6 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR7 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR8 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR9 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR10 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR11 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR12 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR13 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR14 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR15 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR16 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR17 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR18 + "&nbsp;"))).addElement((Element)new TD().setWidth("7%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + STR19 + "&nbsp;")));
        return tr;
    }
    
    public TR addReportForAsmt(final String str1, final String str2, final String str3, final String str4) {
        final TR tr = new TR().addElement((Element)new TD().setWidth("20%").setHeight(23).addElement(str1)).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + str2 + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + str3 + "&nbsp;"))).addElement((Element)new TD().setWidth("20%").addElement((Element)new NOBR().addElement("&nbsp;&nbsp;" + str4 + "&nbsp;")));
        return tr;
    }
    
    static {
        log = new SimpleLogger((Class)TRExtension.class, true);
    }
}
