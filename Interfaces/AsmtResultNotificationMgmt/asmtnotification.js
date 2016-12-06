function sendindividual_onclick(){
	var assessmentid=getValue('assessment_id');
	var user_id="";
	user_id=getSelectedRow('AsmtResultNotificationMgmtGrid','student_id');
	var message=getValue('message');
	if(assessmentid!=0){
		if(user_id){
			window.open("./learnityasmtserver.assessmentadmin.standaloneasmt.SendMail?student_id="+user_id+"&assessment_id="+assessmentid+"&message="+message+"","new1",'height=200,width=230,top=250,left=350,status=yes,scrollbars=yes,resizable=yes,manubar=no,toolbar=no');
		}
		else{
			alert("Pleare Select an User");
		}
	}
	else{
		alert("Pleare Select an Assessment");
	}	
}
function sendall_onclick(){
	var assessmentid=getValue('assessment_id');
	var message=getValue('message');
	if(assessmentid!=0){
		window.open("./learnityasmtserver.assessmentadmin.standaloneasmt.SendMailAll?assessment_id="+assessmentid+"&message="+message+"","new1",'height=100,width=100,top=250,left=350,status=yes,scrollbars=yes,resizable=yes,manubar=no,toolbar=no');
	}
	else{
		alert("Pleare Select an Assessment");
	}
}