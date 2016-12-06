function f12( dtnodevar)
{
	NoteBookUtil.getDetailsNote(dtnodevar.data.description,function(data) {
		setValue('showdetails',data)
	});
	setValue('subjectfield',"");
	setValue('notes',"");
	setValue('attachment',"");
}

function onload_onclick()
{
	$("#unit_id").html("<option value='0'>[Choose One]</option>");	
	var fb=document.getElementById("shownotes");
	fb.style.visibility="hidden";	
}		
		
function unitCombo(data){
		$("#unit_id").html(data);
// 		setDropDown(unit_id,data);
}
		
function user_onchange(){
	
	var student_id=getValue("student_id");	
	NoteBookUtil.createDropDownUnitadmin(student_id,unitCombo);
		
}


function go_onclick(){
	var student_id=getValue("student_id");	
	var unit_id=getValue("unit_id");
	NoteBookUtil.setTreeDataadmin(student_id,unit_id);
	NoteBookUtil.getUnitName(unit_id,student_id,function(data) {
		setValue('coursename',data)
	});
	NoteBookUtil.getDetailsNote1(unit_id,student_id,function(data) {
		setValue('showdetails',data)
	});
	
	GenerateTree();
	
}
function ff()
{
	var student_id=getValue("student_id");	
	var unit_id=getValue("unit_id");
	NoteBookUtil.getDetailsNote1(unit_id,student_id,function(data) {
		setValue('showdetails',data)
	});
}	
function shownotes(note_id)
{
	var fb=document.getElementById("shownotes");
	fb.style.visibility="visible";
	NoteBookUtil.getNoteDetails(note_id,function(data) {
// 		setValue('showdetails',data)
		var date="";
		var subjectfield="";
		var notes="";
		var attachment="";
		date=data[0];
		subjectfield=data[1];
		notes=data[2];
		attachment=data[3];
		//document.NotebookMgmtform.subjectfield.value=subjectfield;
		//document.NotebookMgmtform.notes.value=notes;
		setValue('subjectfield',subjectfield);
		setValue('notes',notes);
		setValue('showdate',date);
		setValue('attachment',attachment)
	});
}

function showattachfile()
{
	alert("nnnn");
}
function deletenote_onclick()
{
	NoteBookUtil.deleteNote();
}
function savenote_onclick()
{
	NoteBookUtil.saveNote(function(data) {
		window.open(data);
	});
}
function printnote_onclick()
{
	NoteBookUtil.printNote();
}
	
