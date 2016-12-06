function unreggroupfnc(data) {
	setValue('message',data);
	fadeOut('message');
	setReloadGrid('AssessmentRegByGroupGrid');
}

function unregisteredAllGroups_onclick(){
	setValue('message',"");
	fadeIn('message');
	var assessmentid=getValue('assessment_id');
	var send_mail="";
	if(assessmentid!=0){
		StAsmtAdmin.deleteAllAssessmentGroupRegistration(assessmentid,send_mail,unreggroupfnc);
	}
	else{
		alert("Pleare Select an Assessment");
	}		
}

function uploadfnc(data) {
	setValue('message',data);
	fadeOut('message');
	setReloadGrid('AssessmentRegByGroupGrid');
}

function upload_onclick(){
	var assessmentid=getValue('assessment_id');
	
	if(assessmentid!=0){
		var file = getValue('BrowseFile');
		if (!file) {
			alert("Please select a file");
		}
		else{
			setValue('message',"");
			fadeIn('message');
			StAsmtAdmin.BulkGroupRegistration(assessmentid,file,uploadfnc);
		}
	}
	else{
		alert("Pleare Select an Assessment");
	}
}
