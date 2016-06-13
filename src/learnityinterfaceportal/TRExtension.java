package learnityinterfaceportal;

import org.apache.ecs.html.*;

public class TRExtension extends TR
{

    public TRExtension()
    {
    }

    public TR addRowUserForCoursePortal(String course_id, String course_name, String session)
    {
        Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new A()).setHref((new StringBuilder()).append("javascript:lunchCoursemanagement('").append(course_id).append("','").append(course_name).append("');").toString()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(course_name))))).addElement((new TD()).setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).setColor("#000000").addElement(session))));
        return tr;
    }

    public TR addHeader(String strfid, String strThreadid, String strStart, String strmessage, String strLastPost)
    {
        TR tr = (new TR()).addElement((new TD()).setBgColor("99CC99").setWidth("25%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swbportal").addElement(strfid)))).addElement((new TD()).setBgColor("99CC99").setWidth("25%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("003366").setFontClass("swbportal").addElement((new StringBuilder()).append("&nbsp;").append(strStart).toString())))).addElement((new TD()).setBgColor("99CC99").setWidth("30%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swbportal").addElement((new StringBuilder()).append("&nbsp;").append(strLastPost).toString()))));
        return tr;
    }

    public TR addRowUserForForum(String strfName, String strThread, String strfStart, String strmessage, String strLastPost, String subject, String ID, 
            String MID)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setClassId("tr2").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).addElement((new A()).setHref((new StringBuilder()).append("javascript:forum('").append(ID).append("','").append(strfName).append("');").toString()).addElement(strfName))))).addElement((new TD()).setClassId("tr2").setAlign("center").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(strfStart)))).addElement((new TD()).setClassId("tr2").setAlign("left").setNoWrap(true).addElement((new Font()).setFace("verdana").setSize(-1).addElement((new A()).setHref((new StringBuilder()).append("javascript:lastpost('").append(ID).append("','").append(subject).append("','").append(MID).append("','").append(strfName).append("');").toString()).addElement(strLastPost))));
        return tr;
    }

    public TR addRowUserForNoForum(String strfName, String strThread, String strfStart, String strfmessage, String strLastPost)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setClassId("tr2").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(strfName)))).addElement((new TD()).setClassId("tr2").setAlign("center").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(strfStart)))).addElement((new TD()).setClassId("tr2").setAlign("left").setNoWrap(true).addElement((new Font()).setFace("verdana").setSize(-1).addElement(strLastPost)));
        return tr;
    }

    public TR addHeaderNamesForPortal(String strUnitName, String strmsg)
    {
        TR tr = (new TR()).addElement((new TD()).setBgColor("#99CC99").setWidth("35%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFontClass("swbportal").setColor("#FFFFFF").addElement(strUnitName)))).addElement((new TD()).setBgColor("#99CC99").setWidth("65%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swbportal").addElement(strmsg))));
        return tr;
    }

    public TR addHeaderNamesForPortal(String strSelect, String strUnitName, String strmsg)
    {
        TR tr = (new TR()).addElement((new TD()).setBgColor("99CCCC").setWidth("25%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFontClass("swbportal").setColor("003366").addElement(strUnitName)))).addElement((new TD()).setBgColor("99CCCC").setWidth("25%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swbportal").addElement(strmsg))));
        return tr;
    }

    public TR addHeaderNamesForPortalChat(String strSelect, String strType, String strStatus, String strUnitName, String strmsg)
    {
        TR tr = (new TR()).addElement((new TD()).setBgColor("99CCCC").setWidth("60%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("006699").setFontClass("swbportal").addElement(strSelect)))).addElement((new TD()).setBgColor("99CCCC").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("006699").setFontClass("swbportal").addElement(strType)))).addElement((new TD()).setBgColor("99CCCC").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("006699").setFontClass("swbportal").addElement(strStatus)))).addElement((new TD()).setAlign("center").setBgColor("99CCCC").setWidth("10%").setNoWrap(true).addElement((new Font()).setFontClass("swbportal").setColor("003366").addElement((new StringBuilder()).append("&nbsp;").append(strUnitName).toString()))).addElement((new TD()).setAlign("center").setBgColor("99CCCC").setWidth("10%").setNoWrap(true).addElement((new Font()).setColor("#FFFFFF").setFontClass("swbportal").addElement((new StringBuilder()).append("&nbsp;").append(strmsg).toString())));
        return tr;
    }

    public TR addRowUserForSysnchronous(String id, String sc_name, String strtype, String strStatus, String r_count, String online, String strSessionID)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("60%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new A()).setHref((new StringBuilder()).append("javascript:chatLunch('").append(id).append("','").append(strSessionID).append("');").toString()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(sc_name))))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strtype))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strStatus))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(r_count))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(online)));
        return tr;
    }

    public TR addRowUserForRecordedSysnchronous(String id, String sc_name, String strtype, String strStatus, String r_count, String online, String strRSession)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("60%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new A()).setHref((new StringBuilder()).append("javascript:chatLunchRecorded('").append(id).append("','").append(strRSession).append("');").toString()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(sc_name))))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strtype))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strStatus))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(r_count))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(online)));
        return tr;
    }

    public TR addRowUserForCompletedSysnchronous(String id, String sc_name, String strtype, String strStatus, String r_count, String online, String strSessionID)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("60%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).setColor("FF0000").addElement(sc_name)))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strtype))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strStatus))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(r_count))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(online)));
        return tr;
    }

    public TR addRowUserForNotStartedSysnchronous(String id, String sc_name, String strtype, String strStatus, String r_count, String online, String strSessionID)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("60%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).setColor("3366FF").addElement(sc_name)))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strtype))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(strStatus))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(r_count))).addElement((new TD()).setAlign("center").setWidth("10%").setNoWrap(true).addElement((new NOBR()).addElement(online)));
        return tr;
    }

    public TR addRowUserForNoSysnchronous(String sc_name, String r_count, String online)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setColSpan(5).setWidth("70%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(sc_name))));
        return tr;
    }

    public TR addRowUserForTopicOnlyPortal(String course_id, String course_name, String topic_id, String topicname, String topictypeface)
    {
        Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("20%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new A()).setHref((new StringBuilder()).append("javascript:lunchCourse('").append(course_id).append("');").toString()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(course_name))))).addElement((new TD()).setWidth("40%").setNoWrap(true).setHeight(15).addElement((new NOBR()).addElement((new A()).setHref((new StringBuilder()).append("javascript:resume2('").append(course_id).append("','").append(topic_id).append("');").toString()).addElement((new Font()).setFace(topictypeface).setSize(-1).addElement(topicname)))));
        return tr;
    }

    public TR addRowUserForNonePortal(String course_id, String course_name)
    {
        Input inputCheck1 = new Input();
        inputCheck1.setOnClick("CCA()");
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new A()).setHref((new StringBuilder()).append("javascript:lunchCourse('").append(course_id).append("');").toString()).addElement((new Font()).setFace("verdana").setSize(-1).addElement(course_name))))).addElement((new TD()).setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFace("verdana").setSize(-1).setColor("#000000").addElement("Not Accessed Yet!"))));
        return tr;
    }

    public TR addRowUserForNoInputPortal(String course_id, String course_name, String course_status)
    {
        TR tr = (new TR()).setBgColor("#EEEEEE").addElement((new TD()).setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("003366").setFace("verdana").setSize(-1).addElement(course_name)))).addElement((new TD()).setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FF0000").setFace("verdana").setSize(-1).addElement(course_status))));
        return tr;
    }

    public TR addRowSearchUnitForCourseActiveOnly(String strLearningObject, String course_id, String course_name, String course_stat_desc)
    {
        TR tr = (new TR())
			.setBgColor("FFEEBB")
			.addElement((new TD())
			.setWidth("30%")
			.setNoWrap(true)
			.addElement((new NOBR())
			.addElement(strLearningObject)))
			.addElement((new TD())
			.setWidth("40%")
			.setNoWrap(true)
			.addElement((new NOBR())
			.addElement((new Font())
			.setColor("#0000FF")
			.setFace("verdana")
			.setSize(-1)
			.addElement(course_name))));
        return tr;
    }
    
    
    public TR addRowSearchUnitForCourseActiveOnlywithScript(String strLearningObject, String course_id, String course_name, String course_stat_desc,String jscript)
    {
	    TR tr = new TR()
			    .setBgColor("FFEEBB")
			    .addElement(new TD()
			    .setWidth("30%")
			    .setNoWrap(true)
			    .addElement(new NOBR()
			    .addElement(strLearningObject)))
			    .addElement(new TD()
			    .setWidth("40%")
			    .setNoWrap(true)
			    .addElement(new NOBR()
			    .addElement(new Font()
			    .setColor("#0000FF")
			    .setFace("verdana")
			    .setSize(-1)
			    .addElement(new A()
			    .addElement(course_name).setHref(jscript))))
				   );
	    return tr;
    }

    public TR addRowSearchUnitForCourseOnly(String strLearningObject, String course_id, String course_name, String course_stat_desc)
    {
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("30%").setNoWrap(true).addElement((new NOBR()).addElement(strLearningObject))).addElement((new TD()).setWidth("40%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FF0000").setFace("verdana").setSize(-1).addElement(course_name))));
        return tr;
    }

    public TR addRowSearchForumUnit(String strForumObjects, String forum_d, String forum_name)
    {
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("30%").setNoWrap(true).addElement((new NOBR()).addElement(strForumObjects))).addElement((new TD()).setWidth("40%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#0000FF").setFace("verdana").setSize(-1).addElement(forum_d))));
        return tr;
    }

    public TR addRowSearchForumUnitUnregistered(String strLearningObject, String forum_d, String forum_name)
    {
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("30%").setNoWrap(true).addElement((new NOBR()).addElement(strLearningObject))).addElement((new TD()).setWidth("40%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FF0000").setFace("verdana").setSize(-1).addElement(forum_d))));
        return tr;
    }

    public TR addRowSearchForNoticeOnly(String strNoticeInfo, String notice_id, String notice_name)
    {
        TR tr = new TR()
			.setBgColor("FFEEBB")
			.addElement(new TD()
			.setWidth("30%")
			.setNoWrap(true)
			.addElement(new NOBR()
			.addElement(strNoticeInfo)))
			.addElement(new TD()
			.setWidth("40%")
			.setNoWrap(true)
			.addElement(new NOBR()
			.addElement(new Font()
			.setColor("#0000FF")
			.setFace("verdana")
			.setSize(-1)
			.addElement(notice_name))));
        return tr;
    }
    
    
    public TR addRowSearchForNoticeOnlywithscript(String strNoticeInfo, String notice_id, String notice_name,String jscript)
    {
	    TR tr = new TR()
			    .setBgColor("FFEEBB")
			    .addElement(new TD()
			    .setWidth("30%")
			    .setNoWrap(true)
			    .addElement(new NOBR()
			    .addElement(strNoticeInfo)))
			    .addElement(new TD()
			    .setWidth("40%")
			    .setNoWrap(true)
			    .addElement(new NOBR()
			    .addElement(new Font()
			    .setColor("#0000FF")
			    .setFace("verdana")
			    .setSize(-1)
			    .addElement(new A()
			    .addElement(notice_name)
			    .setHref(jscript))
				       )));
	    return tr;
    }
    

    public TR addHeaderNamesForSearchUnit(String strReference, String strlinks)
    {
        TR tr = (new TR()).addElement((new TD()).setBgColor("990000").setWidth("20%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;").append(strReference).toString())))).addElement((new TD()).setBgColor("660000").setWidth("30%").setNoWrap(true).addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("FFFFFF").addElement((new StringBuilder()).append("&nbsp;").append(strlinks).toString()))));
        return tr;
    }

    public TR add()
    {
        TR tr = (new TR()).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((new TD()).setWidth("160").addElement((new IMG()).setBorder(0).setHeight(5).setWidth(160).setSrc("../images/T.gif"))).addElement((new TD()).setWidth("100%").addElement((new IMG()).setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }

    public TR add1()
    {
        TR tr = (new TR()).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((new TD()).setWidth("160").addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }

    public TR add2()
    {
        TR tr = (new TR()).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((new TD()).setWidth("160").addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setSrc("../images/T.gif"))).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif")));
        return tr;
    }

    public TR addRowImage()
    {
        TR tr = (new TR()).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setWidth(160).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif")));
        return tr;
    }

    public TR add(String txt, String type, String name, String maxlength, String size, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)));
        return tr;
    }

    public TR add3(String txt, String type, String name, String maxlength, String size, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("100").addElement(txt)).addElement((new TD()).setWidth("150").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)));
        return tr;
    }

    public TR add4(String txt, String type, String name, String maxlength, String size, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("75").addElement(txt)).addElement((new TD()).setWidth("100").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)));
        return tr;
    }

    public TR addTime(String txt, String type, String name, String maxlength, String size, String tabIndex, String strFormat)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("75").addElement(txt)).addElement((new TD()).setWidth("100").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement((new Span()).setClassId("PPRSmallLabelText").addElement(strFormat)));
        return tr;
    }

    public TR addCheckBox(String txt, String type, String name, String value, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setName(name).setValue(value).setTabindex(tabIndex)));
        return tr;
    }

    public TR addCheckBox3(String txt, String type, String name, String value, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("100").addElement(txt)).addElement((new TD()).setWidth("150").addElement((new Input()).setType(type).setName(name).setValue(value).setTabindex(tabIndex)));
        return tr;
    }

    public TR addText(String text)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setColSpan(2).addElement(text));
        return tr;
    }

    public TR addText4(String text, String text1)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("75").addElement(text)).addElement((new TD()).setClassId("PPRSmallLabelText").addElement(text1));
        return tr;
    }

    public TR addcheckbox(String txt, String type, String name, String tabIndex, String value, boolean check)
    {
        Input checkButton = new Input();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement(checkButton.setType(type).setName(name).setValue(value).setTabindex(tabIndex)));
        checkButton.setChecked(check);
        return tr;
    }

    public TR addcheckboxSelf(String txt, String type, String name, String tabIndex, String value, boolean check)
    {
        Input checkButton = new Input();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement(checkButton.setType(type).setName(name).setValue(value).setTabindex(tabIndex)));
        checkButton.setChecked(check);
        return tr;
    }

    public TR addRequired(String txt, String type, String name, String maxlength, String size, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex).addElement((new Font()).setColor("FF0000").addElement("*"))));
        return tr;
    }

    public TR addRequiredDisabled(String txt, String type, String name, String maxlength, String size, boolean disabled, String tabIndex)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex).setDisabled(disabled).addElement((new Font()).setColor("FF0000").addElement("*"))));
        return tr;
    }

    public TR addRadio(String gender, String type, String name)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(gender)).addElement((new TD()).setWidth("336").setClassId("PPCheckRadio").addElement((new Input(type, name, "m")).setTabindex("1").setChecked(true).addElement("Male")).addElement((new Input(type, name, "f")).setTabindex("1").setChecked(false).addElement("Female")));
        return tr;
    }

    public TR addRadio1(String gender, String type, String name)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(gender)).addElement((new TD()).setWidth("336").setClassId("PPCheckRadio").addElement((new Input(type, name, "Y")).setTabindex("1").setChecked(true).addElement("Appeared")).addElement((new Input(type, name, "N")).setTabindex("1").setChecked(false).addElement("Did not appeared")));
        return tr;
    }

    public TR addRadio(String gender, String type, String name, String radio1, String radio2)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(gender)).addElement((new TD()).setWidth("336").setClassId("PPCheckRadio").addElement((new Input(type, name, "m")).setTabindex("1").setChecked(false).addElement(radio1)).addElement((new Input(type, name, "f")).setTabindex("1").setChecked(false).addElement(radio2)));
        return tr;
    }

    public TR addEmail(String txt, String type, String name, String maxlength, String size, String tabIndex, String strFormat)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement((new Span()).setClassId("PPRSmallLabelText").addElement((new BR()).addElement(strFormat))));
        return tr;
    }

    public TR addCalender(String txt, String type, String name, String maxlength, String size, String tabIndex, String strFormat)
    {
        A a = new A();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement(a.setHref("javascript:doNothing()").addElement((new Font()).setSize(1).addElement((new IMG()).setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("MIDDLE").addElement("Calendar")))).addElement((new Span()).setClassId("PPRSmallLabelText").addElement((new BR()).addElement(strFormat))));
        a.setOnClick((new StringBuilder()).append("showCal(frm.").append(name).append(", 'Y');").toString());
        return tr;
    }

    public TR addCalender1(String txt, String type, String name, String maxlength, String size, String tabIndex, String strFormat, 
            Option option[], String btn, String val)
    {
        A a = new A();
        IMG in = new IMG();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").addElement(txt)).addElement((new TD()).addElement((new Select()).setName(btn).setClassId("drpdwn").setTabindex(tabIndex).addElement(option))).addElement((new TD()).addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement(a.setHref("javascript:doNothing()").addElement((new Font()).setSize(1).addElement((new IMG()).setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("MIDDLE").addElement("Calendar"))))).addElement((new TD()).addElement(in.setWidth(21).setHeight(16).setSrc("../images/btn_go_off.gif")));
        in.setOnClick((new StringBuilder()).append("document.frm.prmGo.value=").append(val).append(";Go_onclick()").toString());
        a.setOnClick((new StringBuilder()).append("showCal(frm.").append(name).append(", 'Y');").toString());
        return tr;
    }

    public TR addCalender2(String txt, String type, String name, String maxlength, String size, String tabIndex, String strFormat, 
            String obj)
    {
        A a = new A();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement(a.setHref((new StringBuilder()).append("javascript:popup('").append(obj).append("');").toString()).addElement((new Font()).setSize(1).addElement((new IMG()).setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("MIDDLE").addElement("Calendar")))).addElement((new Span()).setClassId("PPRSmallLabelText").addElement((new BR()).addElement(strFormat))));
        return tr;
    }

    public TR addCalender4(String txt, String type, String name, String maxlength, String size, String tabIndex, String strFormat, 
            String obj)
    {
        A a = new A();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("75").addElement(txt)).addElement((new TD()).setWidth("100").addElement((new NOBR()).addElement((new Input()).setType(type).setClassId("PPRField").setMaxlength(maxlength).setName(name).setSize(size).setTabindex(tabIndex)).addElement(a.setHref((new StringBuilder()).append("javascript:popup('").append(obj).append("');").toString()).addElement((new Font()).setSize(1).addElement((new IMG()).setSrc("../images/calendar.gif").setBorder(0).setHeight(16).setWidth(16).setAlign("middle").addElement("Calendar"))))).addElement((new Span()).setClassId("PPRSmallLabelText").addElement(new BR()).addElement((new NOBR()).addElement(strFormat))));
        return tr;
    }

    public TR addSelect(String txt, String name, String tabIndex, Option option[])
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement((new Select()).setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        return tr;
    }

    public TR addSelect2(String txt, String name, String tabIndex, Option option[], String btn, String val)
    {
        IMG in = new IMG();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).addElement((new Select()).setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option))).addElement((new TD()).addElement(in.setName(btn).setWidth(21).setHeight(16).setSrc("../images/btn_go_off.gif")));
        in.setOnClick((new StringBuilder()).append("document.frm.prmGo.value=").append(val).append(";Go_onclick()").toString());
        return tr;
    }

    public TR addSelect(String txt, String name, String tabIndex, Option option[], String select)
    {
        Select sel = new Select();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement(sel.setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        sel.setOnChange(select);
        return tr;
    }

    public TR addSelectRequired(String txt, String name, String tabIndex, Option option[], String select)
    {
        Select sel = new Select();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).setWidth("336").addElement(sel.setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)).addElement((new Font()).setColor("FF0000").addElement("*")));
        sel.setOnChange(select);
        return tr;
    }

    public TR addSelect3(String txt, String name, String tabIndex, Option option[], String select)
    {
        Select sel = new Select();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("100").addElement(txt)).addElement((new TD()).setWidth("150").addElement(sel.setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        sel.setOnChange(select);
        return tr;
    }

    public TR addSelect4(String txt, String name, String tabIndex, Option option[], String select)
    {
        Select sel = new Select();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("75").addElement(txt)).addElement((new TD()).setWidth("100").addElement(sel.setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option)));
        sel.setOnChange(select);
        return tr;
    }

    public TR addSelectButton(String txt, String name, String tabIndex, Option option[], String btnName, String btnValue, String title, 
            String click)
    {
        Input inputButton = new Input();
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(txt)).addElement((new TD()).addElement((new Select()).setClassId("drpdwn").setName(name).setTabindex(tabIndex).addElement(option))).addElement((new TD()).setWidth("5")).addElement((new TD()).addElement((new Table()).setBorder(0).setCellPadding(2).setCellSpacing(0).addElement((new TR()).addElement((new TD()).addElement(inputButton.setClassId("sbttn").setName(btnName).setTabindex(1).setType("button").setTitleValue(title).setValue(btnValue))))));
        inputButton.setOnClick(click);
        return tr;
    }

    public TR addRowImage1()
    {
        TR tr = (new TR()).addElement((new TD()).setRowSpan(3).addElement((new IMG()).setBorder(0).setHeight(8).setWidth(10).setSrc("../images/T.gif"))).addElement((new TD()).setWidth(160).addElement((new IMG()).setBorder(0).setHeight(5).setWidth(160).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif"))).addElement((new TD()).addElement((new IMG()).setBorder(0).setHeight(5).setWidth(1).setSrc("../images/T.gif")));
        return tr;
    }

    public TR addLine()
    {
        TR tr = (new TR()).addElement((new TD()).setBgColor("023264").setHeight(1).setWidth(1)).addElement((new TD()).setBgColor("023264").setHeight(1).setWidth(1)).addElement((new TD()).setBgColor("023264").setHeight(1).setWidth(1));
        return tr;
    }

    public TR addRowUser(String strUserId, String strName, String strCreadtedDate, String strCreatedBy, String strLastModDate, String strStatus)
    {
        Input inputCheck1 = new Input();
        String colour = "";
        if(strStatus.equals("Active"))
        {
            colour = "3366FF";
        } else
        {
            colour = "FF0000";
        }
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("5%").setHeight(23).addElement(inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strName).append("&nbsp;").toString()))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreadtedDate).append("&nbsp;").toString()))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedBy).append("&nbsp;").toString()))).addElement((new TD()).setWidth("18%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLastModDate).append("&nbsp;").toString()))).addElement((new TD()).setWidth("7%").addElement((new NOBR()).addElement((new Font()).setColor(colour).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strStatus).append("&nbsp;").toString()))));
        inputCheck1.setOnClick("CCA();");
        return tr;
    }

    public TR addRowUser(String strUserId, String strName, String strCreadtedDate, String strCreatedBy, String strLastModDate, String modifiedBy, String no, 
            String strStatus)
    {
        Input inputCheck1 = new Input();
        String colour = "";
        if(strStatus.equals("Active"))
        {
            colour = "3366FF";
        } else
        {
            colour = "FF0000";
        }
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("5%").setHeight(23).addElement(inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strName).append("&nbsp;").toString()).addElement((new Input()).setType("hidden").setName("hiddenUnit").setValue(strName)))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreadtedDate).append("&nbsp;").toString()))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedBy).append("&nbsp;").toString()))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLastModDate).append("&nbsp;").toString()))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(modifiedBy).append("&nbsp;").toString()))).addElement((new TD()).setWidth("12%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(no).append("&nbsp;").toString()))).addElement((new TD()).setWidth("8%").addElement((new NOBR()).addElement((new Font()).setColor(colour).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strStatus).append("&nbsp;").toString()).addElement((new Input()).setType("hidden").setName("hiddenStatus").setValue(strStatus)))));
        inputCheck1.setOnClick("CCA();checkbox_onclick();");
        return tr;
    }

    public TR addRowUser(String strUserId, String strName, String strLogin, String strCConnectedFor)
    {
        Input inputCheck1 = new Input();
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("5%").setHeight(23).addElement(inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((new TD()).setWidth("30%").addElement((new NOBR()).addElement("&nbsp;&nbsp;").addElement((new Font()).setColor("#0000FF").addElement((new A()).setHref((new StringBuilder()).append("administration.AdministratorModify?user_id=").append(strUserId).toString()).addElement(strName))).addElement("&nbsp;"))).addElement((new TD()).setWidth("40%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString()))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCConnectedFor).append("&nbsp;").toString())));
        inputCheck1.setOnClick("CCA();");
        return tr;
    }

    public TR addRowUser(String strUserId, String strName, String strLogin, String strFun, String strCConnectedFor)
    {
        Input inputCheck1 = new Input();
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("5%").setHeight(23).addElement(inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strName).append("&nbsp;").toString()))).addElement((new TD()).setWidth("30%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString()))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strFun).append("&nbsp;").toString()))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCConnectedFor).append("&nbsp;").toString())));
        inputCheck1.setOnClick("CCA();");
        return tr;
    }

    public TR addDynamicInformationByUnit(String strUserId, String strName, String strLogin, String strFun, String strCConnectedFor)
    {
        Input inputCheck1 = new Input();
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("5%").setHeight(23).addElement(inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement("&nbsp;&nbsp;").addElement((new Font()).setColor("#0000FF").addElement((new A()).setHref((new StringBuilder()).append("./administration.UnitAdministration?unit_id=").append(strUserId).toString()).addElement(strName))).addElement("&nbsp;"))).addElement((new TD()).setWidth("30%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString()))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strFun).append("&nbsp;").toString()))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCConnectedFor).append("&nbsp;").toString())));
        inputCheck1.setOnClick("CCA();");
        return tr;
    }

    public TR addRowUser1(String strUserId, String strName, String strLogin, String strFun, String strCConnectedFor, String click)
    {
        Input inputCheck1 = new Input();
        TR tr = (new TR()).setBgColor("FFEEBB").addElement((new TD()).setWidth("5%").setHeight(23).addElement(inputCheck1.setType("radio").setName("checkbox").setValue(strUserId).setTabindex("1"))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strName).append("&nbsp;").toString()))).addElement((new TD()).setWidth("30%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString()))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strFun).append("&nbsp;").toString()))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCConnectedFor).append("&nbsp;").toString())));
        inputCheck1.setOnClick((new StringBuilder()).append("CCA();").append(click).append(";").toString());
        return tr;
    }

    public TR addHR()
    {
        TR tr = (new TR()).addElement((new TD()).setColSpan(6).addElement((new HR()).setSize(1)));
        return tr;
    }

    public TR addCurrentUser(String str, int i)
    {
        TR tr = (new TR()).addElement((new TD()).setColSpan(6).addElement((new NOBR()).addElement((new P()).setAlign("left").addElement((new Font()).setSize(2).addElement((new B()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(str).append("&nbsp;").toString()))).addElement((new StringBuilder()).append("").append(i).toString()))));
        return tr;
    }

    public TR addHR(int colSpan)
    {
        TR tr = (new TR()).addElement((new TD()).setColSpan(colSpan).addElement((new HR()).setSize(1)));
        return tr;
    }

    public TR addHR1()
    {
        TR tr = (new TR()).addElement((new TD()).setWidth(160).addElement((new HR()).setSize(1))).addElement((new TD()).setWidth(336).addElement((new HR()).setSize(1)));
        return tr;
    }

    public TR addHeader(String strHeader)
    {
        TR tr = (new TR()).addElement((new TR()).addElement((new TD()).setBgColor("990000").setColSpan(6).setHeight(30).setWidth("100%").addElement((new Font()).setColor("#FFFFFF").addElement((new B()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strHeader).append("&nbsp;").toString())))));
        return tr;
    }

    public TR addHeader(String strHeader, int colSpan)
    {
        TR tr = (new TR()).addElement((new TR()).addElement((new TD()).setBgColor("990000").setColSpan(colSpan).setHeight(30).setWidth("100%").addElement((new Font()).setColor("#FFFFFF").addElement((new B()).addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strHeader).append("&nbsp;").toString())))));
        return tr;
    }

    public TR addHeaderNames(String strSelect, String strAdminName, String strCreatedDate, String strCreatedBy, String strLastMod, String strStatus)
    {
        TR tr = (new TR()).setBgColor("990000").addElement((new TD()).setWidth("5%").setHeight(23).addElement((new NOBR()).addElement((new Font()).setFontClass("swb").setColor("#FFFFFF").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strSelect).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strAdminName).append("&nbsp;").toString())))).addElement((new TD()).setWidth("25%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedDate).append("&nbsp;").toString())))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedBy).append("&nbsp;").toString())))).addElement((new TD()).setWidth("18%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLastMod).append("&nbsp;").toString())))).addElement((new TD()).setWidth("7%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strStatus).append("&nbsp;").toString()))));
        return tr;
    }

    public TR addHeaderNames(String strSelect, String strAdminName, String strCreatedDate, String strCreatedBy, String strLastMod, String modifiedBy, String No, 
            String strStatus)
    {
        TR tr = (new TR()).setBgColor("990000").addElement((new TD()).setWidth("5%").setHeight(23).addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strSelect).append("&nbsp;").toString())))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strAdminName).append("&nbsp;").toString())))).addElement((new TD()).setWidth("15%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedDate).append("&nbsp;").toString())))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedBy).append("&nbsp;").toString())))).addElement((new TD()).setWidth("15%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLastMod).append("&nbsp;").toString())))).addElement((new TD()).setWidth("15%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(modifiedBy).append("&nbsp;").toString())))).addElement((new TD()).setWidth("12%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(No).append("&nbsp;").toString())))).addElement((new TD()).setWidth("8%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strStatus).append("&nbsp;").toString()))));
        return tr;
    }

    public TR addHeaderNames(String strSelect, String strAdmin, String strLogin, String strConnectedFor)
    {
        TR tr = (new TR()).setBgColor("990000").addElement((new TD()).setWidth("5%").setHeight(23).addElement((new NOBR()).addElement((new Font()).setFontClass("swb").setColor("#FFFFFF").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strSelect).append("&nbsp;").toString())))).addElement((new TD()).setWidth("30%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strAdmin).append("&nbsp;").toString())))).addElement((new TD()).setWidth("40%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString())))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strConnectedFor).append("&nbsp;").toString()))));
        return tr;
    }

    public TR addHeaderNames(String strSelect, String strAdmin, String strLogin, String strConnectedFor, String strFunction)
    {
        TR tr = (new TR()).setBgColor("990000").addElement((new TD()).setWidth("5%").setHeight(23).addElement((new NOBR()).addElement((new Font()).setFontClass("swb").setColor("#FFFFFF").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strSelect).append("&nbsp;").toString())))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strAdmin).append("&nbsp;").toString())))).addElement((new TD()).setWidth("30%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strFunction).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strConnectedFor).append("&nbsp;").toString()))));
        return tr;
    }

    public TR addHeaderNamesforUnit(String strSelect, String strAdmin, String strLogin, String strConnectedFor, String strFunction)
    {
        TR tr = (new TR()).setBgColor("990000").addElement((new TD()).setWidth("5%").setHeight(23).addElement((new NOBR()).addElement((new Font()).setFontClass("swb").setColor("#FFFFFF").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strSelect).append("&nbsp;").toString())))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strAdmin).append("&nbsp;").toString())))).addElement((new TD()).setWidth("30%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLogin).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strFunction).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strConnectedFor).append("&nbsp;").toString()))));
        return tr;
    }

    public TR addHeaderNames1(String strSelect, String strName, String strCreatedDate, String strCreatedBy, String strLModDate)
    {
        TR tr = (new TR()).setBgColor("990000").addElement((new TD()).setWidth("5%").setHeight(23).addElement((new NOBR()).addElement((new Font()).setFontClass("swb").setColor("#FFFFFF").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strSelect).append("&nbsp;").toString())))).addElement((new TD()).setWidth("25%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strName).append("&nbsp;").toString())))).addElement((new TD()).setWidth("30%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedDate).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").addElement((new NOBR()).addElement((new Font()).setColor("#FFFFFF").setFontClass("swb").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strCreatedBy).append("&nbsp;").toString())))).addElement((new TD()).setWidth("20%").setBgColor("660000").addElement((new NOBR()).addElement((new Font()).setFontClass("sbbd").setColor("003366").addElement((new StringBuilder()).append("&nbsp;&nbsp;").append(strLModDate).append("&nbsp;").toString()))));
        return tr;
    }

    public TR addRadiocategory(String gender, String type, String name)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("160").addElement(gender)).addElement((new TD()).setWidth("336").setClassId("PPCheckRadio").addElement((new Input(type, name, "General")).setTabindex("1").setChecked(true).addElement("General")).addElement((new Input(type, name, "SC")).setTabindex("1").setChecked(false).addElement("SC")).addElement((new Input(type, name, "ST")).setTabindex("1").setChecked(false).addElement("ST")));
        return tr;
    }

    public TR addview(String txt, String type, String name)
    {
        TR tr = (new TR()).addElement((new TD()).setClassId("PPRLabelText").setWidth("110").addElement((new StringBuilder()).append("<font size=\"2\" color=\"black\"><b>").append(txt).append("</b>").append("</font>").toString())).addElement((new TD()).setWidth("250").addElement((new StringBuilder()).append("<font size=\"2\" color=\"red\"><b>").append(name).append("</b>").append("</font>").toString()));
        return tr;
    }
}
