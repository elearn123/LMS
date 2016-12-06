function unregfunc(data) {
	setValue('message',data);
	fadeOut('message');
	setReloadGrid('AssessmentRegByUserGrid');
}

function unregisteredAllUsers_onclick(){
	setValue('message',"");
	fadeIn('message');
	var assessmentid=getValue('assessment_id');
	var send_mail="";
	if(assessmentid!=0){
		
		StAsmtAdmin.deleteAllAssessmentUserRegistration(assessmentid,send_mail,unregfunc);
		
	}
	else{
		alert("Pleare Select an Assessment");
	}		
}


function upload_onclick(){
	var assessmentid=getValue('assessment_id');
	if(assessmentid!=0){
		var file2=document.getElementById("BrowseFile").value;
		TestFileType(file2, ['ab','xls']);
	
	}
	else{
		alert("Pleare Select an Assessment");
	}
}

function bulkuploadfunc(data) {
	setValue('message',data);
	fadeOut('message');
	setReloadGrid('AssessmentRegByUserGrid');
}

function TestFileType(fileName, fileTypes) {
	var assessmentid=getValue('assessment_id');
	var file = getValue('BrowseFile');
	
	if (!fileName) 
	alert("Please select a file");
	else{
		dots = fileName.split(".");
		fileType = "." + dots[dots.length-1];
		if(fileTypes.join(".").indexOf(fileType) != -1)
		{
			setValue('message',"");
			fadeIn('message');
			StAsmtAdmin.BulkUserRegistration(assessmentid,file,bulkuploadfunc);
			
		}
		else{
			alert("Not valid file type");	
		}
	}	
	
}