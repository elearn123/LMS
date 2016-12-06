		var user;
		var course;
		var month=new Array(12);
		month[0]="January";
		month[1]="February";
		month[2]="March";
		month[3]="April";
		month[4]="May";
		month[5]="June";
		month[6]="July";
		month[7]="August";
		month[8]="September";
		month[9]="October";
		month[10]="November";
		month[11]="December";

		var weekday=new Array(7);
		weekday[0]="Sunday";
		weekday[1]="Monday";
		weekday[2]="Tuesday";
		weekday[3]="Wednesday";
		weekday[4]="Thursday";
		weekday[5]="Friday";
		weekday[6]="Saturday";
		
$(document).ready(function(){

	var d=document.createElement("div");
	d.id="attch";
	$("#textlinkattachment").append(d);
	$("#nbmain a").css('color','white').css('test-decoration','none');
	$("#help a").css('color','white');
	showdate();
	
// 	$("head").append("<link href='../css/treestyles.css' rel='stylesheet' type='text/css'>")
// 	.append("<link href='../css/deliverytree.css' rel='stylesheet' type='text/css'>")
// 	.append("<link href='../css/cooldevjs.css' rel='stylesheet' type='text/css'>")
// 	.append("<script language='JavaScript' src='../js/deliverytree.js'></script>")
// 	.append("<script language='JavaScript' src='../js/notebooktree_format.js'></script>");
	document.getElementById('textlinkadd').style.visibility="visible";
	document.getElementById('textlinkdelete').style.visibility="visible";
	document.getElementById('textlinkmodify').style.visibility="visible";
	document.getElementById('textlinkpublish').style.visibility="visible";
	NoteBookUtil.getUploadFileInfo(showAttachment);
	
	NoteBookUtil.createDropDownCourse(courseCombo);
	
	NoteBookUtil.createDropDownUser(user,userCombo);
	
	NoteBookUtil.createDropDownUnit(user,unitCombo);
	
	function showAttachment(data){
		setValue('nbattchmets',data);
	}
	
	function courseCombo(data){
		setValue('selectcourse',data);
	}
	
	function userCombo(data){
		setValue('selectuser',data);
	}
	
	function unitCombo(data){
		setValue('selectunit',data);
	}

	$("#attachment").before("Attachments: [").after("]").css('cursor','pointer');
	$("#textlinkadd,#textlinkdelete,#textlinkmodify,#textlinkpublish,#textlinkadd > *,#textlinkdelete > *,#textlinkmodify > *,#textlinkpublish > *").css('font-style','normal').css('color','#000099').css('text-align','center').css('cursor','pointer');
// 	courseonchange();
	GenerateTree();

});
				
function Quit()
{window.parent.close();}

function Help()
{window.open('../html/notebook.html','notebookhelp','width=600,height=500,menu=no,scrollbars=yes, resizeable=yes');}

function courseonchange()
{
	var course=getValue("course");
	var user=getValue("user");
	var unit=getValue("unit");
	NoteBookUtil.setTreeData(course,user,unit,'personal');
	//setTimeout('treeshow()', 500);
	NoteBookUtil.createDropDownUser(user,userCombo);
	
	NoteBookUtil.createDropDownUnit(user,unitCombo);
	
	function userCombo(data){
		setValue('selectuser',data);
	}
	
	function unitCombo(data){
		setValue('selectunit',data);
	}
	document.getElementById('textlinkadd').style.visibility="visible";
	document.getElementById('textlinkdelete').style.visibility="visible";
	document.getElementById('textlinkmodify').style.visibility="visible";
	document.getElementById('textlinkpublish').style.visibility="visible";
	$("#textlinkadd").show();$("#textlinkdelete").show();$("#textlinkmodify").show();$("#textlinkpublish").show();
	$("#attch").html('');
// 	loadnotebook();
	reloadTree();
}


function sharedcourseonchange()
{
	var course=getValue("course");
	var user=getValue("user");
	var unit=getValue("unit");
	NoteBookUtil.setTreeData(course,user,unit,'publish');
	//setTimeout('treeshow()', 500);
	NoteBookUtil.createDropDownUserForSelected(user,userCombo);
	NoteBookUtil.createDropDownCourse(courseCombo);
// 	NoteBookUtil.createDropDownUnit(user,unitCombo);
	document.getElementById('textlinkadd').style.visibility="hidden";
	document.getElementById('textlinkdelete').style.visibility="hidden";
	document.getElementById('textlinkmodify').style.visibility="hidden";
	document.getElementById('textlinkpublish').style.visibility="hidden";
	function userCombo(data){
		setValue('selectuser',data);
	}
	function courseCombo(data){
		setValue('selectcourse',data);
	}
	
// 	function unitCombo(data){
// 		setValue('selectunit',data);
// 	}
	$("#textlinkadd").show();$("#textlinkdelete").show();$("#textlinkmodify").show();$("#textlinkpublish").show();
	$("#attch").html('');
// 	loadnotebook();
//	GenerateTree();
    reloadTree();
}
// function loadnotebook()
// {
// 	document.NoteBookform.method ="post";
// 	document.NoteBookform.action="./interfaceengine.PortalServlet?IID=NoteBook";
// 	window.open("","notebook","width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
// 	document.NoteBookform.target="notebook";
// 	document.NoteBookform.submit();
// }
// function treeshow()
// {
// 	//$("#treebox").load("./ShowTree");
// 	document.NoteBookform.method ="post";
// 	document.NoteBookform.action="./NoteBook.ShowTree";
// 	document.NoteBookform.target="treebox";
// 	document.NoteBookform.submit();
// 		
// }

function useronchange()
{
	course=dwr.util.getValue("course");
	user=dwr.util.getValue("user");
	NoteBookUtil.createDropDownUnit(user,unitCombo);
	function unitCombo(data){
		setValue('selectunit',data);
	}
}

function showdate()
{
	var today=new Date();



	day=weekday[today.getDay()];
	 
	curmonth=month[today.getMonth()];
	year=today.getFullYear();
	date=today.getDate();
	 
	cdate="Date : "+day+", "+curmonth+" "+date+", "+year+"";
	$("#notedate").html(cdate);
	
}


function goonclick(){
	
	var course=dwr.util.getValue("course");
	var user=dwr.util.getValue("user");
	var unit=dwr.util.getValue("unit");
	if(user==0 || unit==0){
		
		alert("Please select User & Unit to view shared notes.");
		
		}else{
		
		showSharedNotes();
		
		}
	
	
	}


function showSharedNotes()
{
	var course=dwr.util.getValue("course");
	var user=dwr.util.getValue("user");
	var unit=dwr.util.getValue("unit");
	NoteBookUtil.setTreeData(course,user,unit,'published');
	//setTimeout('treeshow()', 500);
	$("#textlinkadd").hide();$("#textlinkdelete").hide();$("#textlinkmodify").hide();$("#textlinkpublish").hide();
	NoteBookUtil.createDropDownCourse(courseCombo);
	
	function courseCombo(data){
		setValue('selectcourse',data);
	}
	$("#attch").html('');
	$("#notedate").html('');
	$("#subtxt").val('');
	$("#notes").val('');
	$("#notemain").html('');
// 	loadnotebook();
	GenerateTree();
}

function attach()
{
	//document.NoteBookform.method ="post";
	//document.NoteBookform.action="./interfaceengine.PortalServlet?IID=NotebookAttach";
	window.open("./interfaceenginev2.PortalServlet?IID=NotebookAttach","attach","width=500,height=300,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
	//document.NoteBookform.target="attach";
	//document.NoteBookform.submit();
}

function shownotedetail(courseid,coursetitle,notedate,noteid,notetitle,notes,attach)
{

	NoteBookUtil.setNoteDetails(courseid,coursetitle,notedate,noteid,notetitle);
	
	var y=notedate.substring(0,notedate.indexOf('-'));
	m=notedate.substring(notedate.indexOf('-')+1,notedate.lastIndexOf('-'));
	day=notedate.substring(notedate.lastIndexOf('-')+1);
	
	var myDate=new Date();
	myDate.setFullYear(y,m-1,day);
	
	day=weekday[myDate.getDay()];
	 
	curmonth=month[myDate.getMonth()];
	year=myDate.getFullYear();
	date=myDate.getDate();
	 
	cdate="Date : "+day+", "+curmonth+" "+date+", "+year+"";
	$("#notedate").html(cdate);
	$("#subtxt").val(notetitle);
	$("#notes").val(notes);
	if(attach=='null'){attach='';$("#attch").html('');}
	if(attach=='undefined'){attach='';$("#attch").html('');}
	
	NoteBookUtil.getAttachmentPath(noteid,attach,showAttachment);
	
	function showAttachment(data)
	{
		link="<a href="+data+">"+attach+"</a>";
		if(attach!=null || attach!='null')
		{$("#attch").html(link);}
	}
// 	path=location.host+location.pathname+'/'+attach;
// 	link="<a href="+path+">"+attach+"</a>";
// 	
// 	if(attach!=null || attach!='null')
// 	{$("#attch").html(link);}


}

function addNote()
{
	var subject=getValue("subtxt");
	var note=getValue("notes");
	NoteBookUtil.addNote(subject,note);
	setTimeout('reloadnotedetails()', 300);
}

function deleteNote()
{
	NoteBookUtil.removeNote();
	setTimeout('reloadnotedetails()', 300);
}

function editNote()
{
	var subject=getValue("subtxt");
	var note=getValue("notes");
	NoteBookUtil.editNote(subject,note);
	setTimeout('reloadnotedetails()', 300);
}

function publishNote()
{
	NoteBookUtil.publishNote();
	setTimeout('reloadnotedetails()', 300);
}

function reloadnotedetails()
{
	var course=dwr.util.getValue("course");
	NoteBookUtil.reloadNotes(reloaddetails);
	
	function reloaddetails(data){
		setValue('notemain',data);
	}
}

function f12(dtnode)
{
	NoteBookUtil.getNotes(dtnode.data.description,dtnode.data.key,function(data){setValue('notemain',data);});
	setValue('subtxt',"");
	setValue('notes',"");
	setValue('attch',"");
}


