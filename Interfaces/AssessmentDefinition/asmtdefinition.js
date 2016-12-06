
function show_preview_onclick(){
	var browserName = navigator.appName;
	var browserVersion = parseInt(navigator.appVersion);
	var assessmentid=getValue('assessment_id');
	if(assessmentid!=0){
		window.open("./learnityasmtserver.assessmentadmin.standaloneasmt.ShowStAloneAsmtPreview?checkbox="+assessmentid+"&browserName="+browserName+"","new1",'height=700,width=800,status=yes,scrollbars=yes,resizable=yes,manubar=no,toolbar=no');
		
	}
	else{
		alert("Pleare Select an Assessment");
	}
}


function clear_all_previewresult(){
	var assessmentid=getValue('assessment_id');
	if(assessmentid!=0){
		StAsmtAdmin.ClearAllPreviewResult(assessmentid,function(data) {
			setValue('abc1',data);
		});
	}
	else{
		alert("Pleare Select an Assessment");
	}
}
