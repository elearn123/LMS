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

function Initilize(){
	
	
	var d=document.createElement("div");
	d.id="NoteBook_attch";
	$("#textlinkNoteBook_attachment").append(d);
	$("#NoteBook_nbmain a").css('color','blue').css('test-decoration','none');
	$("#NoteBook_help a").css('color','blue');
	$("#NoteBook_nbmain ").css('color','green');

	
	//showdate();
	


	document.getElementById('textlinkNoteBook_add').style.visibility="visible";
	document.getElementById('textlinkNoteBook_delete').style.visibility="visible";
	document.getElementById('textlinkNoteBook_modify').style.visibility="visible";
//me	document.getElementById('textlinkNoteBook_publish').style.visibility="visible";
	NoteBookUtil.getUploadFileInfo(showAttachment);
	
	NoteBookUtil.createDropDownCourse(courseCombo);
	
//me	NoteBookUtil.createDropDownUser(user,userCombo);
	
//me	NoteBookUtil.createDropDownUnit(user,unitCombo);
	
	function showAttachment(data){
		setValue('NoteBook_nbattachments',data);
	}
	
	function courseCombo(data){
		setValue('NoteBook_selectcourse',data);
	}
	
/*me
	function userCombo(data){
		setValue('NoteBook_selectuser',data);
	}
	

	function unitCombo(data){
		setValue('NoteBook_selectunit',data);
	}
*/
	$("#NoteBook_attachment").before("NoteBook_Attachments: [").after("]").css('cursor','pointer');
//me	$("#textlinkNoteBook_add,#textlinkNoteBook_delete,#textlinkNoteBook_modify,#textlinkNoteBook_publish,#textlinkNoteBook_add > *,#textlinkNoteBook_delete > *,#textlinkNoteBook_modify > *,#textlinkNoteBook_publish > *").css('font-style','normal').css('color','#000099').css('text-align','center').css('cursor','pointer');
$("#textlinkNoteBook_add,#textlinkNoteBook_delete,#textlinkNoteBook_modify,#textlinkNoteBook_add > *,#textlinkNoteBook_delete > *,#textlinkNoteBook_modify > *").css('font-style','normal').css('color','#000099').css('text-align','center').css('cursor','pointer');

	// 	courseonchange();
	GenerateTree();

	
}
	
function Quit()
{
	popupContainer = document.getElementById("popupContainer");
	popupContainer.classList.remove("afterPopup");

	
}

function Help()
{window.open('../html/notebook.html','notebookhelp','width=600,height=500,menu=no,scrollbars=yes, resizeable=yes');}

function courseonchange()
{
	var course=getValue("course");
//me	var user=getValue("user");
//me	var unit=getValue("unit");
var user="";
var unit="0";
	NoteBookUtil.setTreeData(course,user,unit,'personal');


	//setTimeout('treeshow()', 500);
//me	NoteBookUtil.createDropDownUser(user,userCombo);
	
//me	NoteBookUtil.createDropDownUnit(user,unitCombo);
	
	function userCombo(data){
		setValue('NoteBook_selectuser',data);
	}
	
	function unitCombo(data){
		setValue('NoteBook_selectunit',data);
	}
	document.getElementById('textlinkNoteBook_add').style.visibility="visible";
	document.getElementById('textlinkNoteBook_delete').style.visibility="visible";
	document.getElementById('textlinkNoteBook_modify').style.visibility="visible";
//me	document.getElementById('textlinkNoteBook_publish').style.visibility="visible";
//me	$("#textlinkNoteBook_add").show();$("#textlinkNoteBook_delete").show();$("#textlinkNoteBook_modify").show();$("#textlinkNoteBook_publish").show();
	$("#textlinkNoteBook_add").show();$("#textlinkNoteBook_delete").show();$("#textlinkNoteBook_modify").show();
	$("#NoteBook_attch").html('');
// 	loadnotebook();
	reloadTree();
}


/*me
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
	document.getElementById('textlinkNoteBook_add').style.visibility="hidden";
	document.getElementById('textlinkNoteBook_delete').style.visibility="hidden";
	document.getElementById('textlinkNoteBook_modify').style.visibility="hidden";
	document.getElementById('textlinkNoteBook_publish').style.visibility="hidden";
	function userCombo(data){
		setValue('selectuser',data);
	}
	function courseCombo(data){
		setValue('NoteBook_selectcourse',data);
	}
	
// 	function unitCombo(data){
// 		setValue('selectunit',data);
// 	}
	$("#textlinkNoteBook_add").show();$("#textlinkNoteBook_delete").show();$("#textlinkNoteBook_modify").show();$("#textlinkNoteBook_publish").show();
	$("#NoteBook_attch").html('');
// 	loadnotebook();
	GenerateTree();
    reloadTree();
}
*/

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

/*me
function useronchange()
{
	course=dwr.util.getValue("course");
	user=dwr.util.getValue("user");
	NoteBookUtil.createDropDownUnit(user,unitCombo);
	function unitCombo(data){
		setValue('NoteBook_selectunit',data);
	}
}
*/

function showdate()
{
	var today=new Date();



	day=weekday[today.getDay()];
	 
	curmonth=month[today.getMonth()];
	year=today.getFullYear();
	date=today.getDate();
	 
	cdate="NoteBook_Date : "+day+", "+curmonth+" "+date+", "+year+"";
	$("#NoteBook_notedate").html(cdate);
	
}

/*me
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
*/

/*me
function showSharedNotes()
{
	var course=dwr.util.getValue("course");
	var user=dwr.util.getValue("user");
	var unit=dwr.util.getValue("unit");
	NoteBookUtil.setTreeData(course,user,unit,'NoteBook_published');
	//setTimeout('treeshow()', 500);
	$("#textlinkNoteBook_add").hide();$("#textlinkNoteBook_delete").hide();$("#textlinkNoteBook_modify").hide();$("#textlinkNoteBook_publish").hide();
	NoteBookUtil.createDropDownCourse(courseCombo);
	
	function courseCombo(data){
		setValue('NoteBook_selectcourse',data);
	}
	$("#NoteBook_attch").html('');
	$("#NoteBook_notedate").html('');
	$("#NoteBook_subtxt").val('');
	$("#NoteBook_notes").val('');
	$("#NoteBook_notemain").html('');
// 	loadnotebook();
//	GenerateTree();
}

*/

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
	$("#NoteBook_notedate").html(cdate);
	$("#NoteBook_subtxt").val(notetitle);
	$("#NoteBook_notes").val(notes);
	if(attach=='null'){attach='';$("#NoteBook_attch").html('');}
	if(attach=='undefined'){attach='';$("#NoteBook_attch").html('');}
	
	NoteBookUtil.getAttachmentPath(noteid,attach,showAttachment);
	
	function showAttachment(data)
	{
		link="<a href="+data+">"+attach+"</a>";
		if(attach!=null || attach!='null')
		{$("#NoteBook_attch").html(link);}
	}
// 	path=location.host+location.pathname+'/'+attach;
// 	link="<a href="+path+">"+attach+"</a>";
// 	
// 	if(attach!=null || attach!='null')
// 	{$("#attch").html(link);}


}

function addNote()
{
	var subject=getValue("NoteBook_subtxt");
	var note=getValue("NoteBook_notes");
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
	var subject=getValue("NoteBook_subtxt");
	var note=getValue("NoteBook_notes");
	NoteBookUtil.editNote(subject,note);
	setTimeout('reloadnotedetails()', 300);
}

/*me
function publishNote()
{
	NoteBookUtil.publishNote();
	setTimeout('reloadnotedetails()', 300);
}

*/
function reloadnotedetails()
{
	var course=dwr.util.getValue("course");
	NoteBookUtil.reloadNotes(reloaddetails);
	
	function reloaddetails(data){
		setValue('NoteBook_notemain',data);
	}
}

function f12(dtnode)
{
	NoteBookUtil.getNotes(dtnode.data.description,dtnode.data.key,function(data){setValue('NoteBook_notemain',data);});
	setValue('NoteBook_subtxt',"");
	setValue('NoteBook_notes',"");
	setValue('NoteBook_attch',"");
}



function GenerateTree()
  {
  NoteBookUtil.treeConstruct    (function(data){ 
  setValue('NoteBook_treebox',data);
  $("#NoteBook_treebox").dynatree({
  title: "Sample",
  autoCollapse: true,
	 
  keyboard: true,

  onActivate: function(dtnode) {
  f12(dtnode);
  }
 });}); 
  }
  
  
 function reloadTree(){
  NoteBookUtil.treeConstruct    (function(data){ 
  setValue('NoteBook_treebox',data);
 $("#NoteBook_treebox").dynatree("getTree").reload();
 });}

Initilize();
