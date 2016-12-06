var count1 = 0;		

function onload_click() {
	
	StandAloneAsmt.asmtAvailablityCheck(sttest);

}
		
function sttest(data) {
	var browserName = navigator.appName;
	var browserVersion = parseInt(navigator.appVersion);
	var checkModelstatus = "";
	var timeavailable = "";
	var avail1 = "";
	var valid1 = "";
	var MaxtestTeken = "";
	var nooftimesappeared = "";
	var message = "";
	var sumissionsatatus = "";
	var save_state = "";
	var student_id = "";
	var checkbox1 = 
	checkModelstatus=data[0];
	timeavailable=data[1];
	avail1=data[2];
	valid1=data[3];
	MaxtestTeken=data[4];
	nooftimesappeared=data[5];
	message=data[6];
	sumissionsatatus=data[7];
	save_state=data[8];
	student_id=data[9];
	checkbox1=data[10];
	if(browserName == "Microsoft Internet Explorer" && browserVersion >=4)
	{
		if((sumissionsatatus=="") || (sumissionsatatus == "Submitted"))
		{		
			if(count1!=0) {
				location.href="./learnityasmtserver.assessmentportal.standaloneasmt.CheckAvailabilityOfTest?newtest=start&browserName="+browserName+"&sumissionsatatus="+sumissionsatatus+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"&student_id="+student_id+"";
			}
			else {
				count1 = Number(count1)+1;
				location.href="./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?newtest=start&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"";
			}
		}
		else {
			if(save_state=="") {
				if(count1!=0) {
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.CheckAvailabilityOfTest?newtest=start&save_state=no&browserName="+browserName+"&sumissionsatatus="+sumissionsatatus+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"&student_id="+student_id+"";
				}
				else {
					count1 = Number(count1)+1;
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTestHTML?newtest=start&save_state=no&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"";
				}
			}
			else {
				if(count1!=0) {
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.CheckAvailabilityOfTest?newtest=start&browserName="+browserName+"&sumissionsatatus="+sumissionsatatus+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"&student_id="+student_id+"";
				}
				else {
					count1 = Number(count1)+1;
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestHTML?newtest=start&browserName="+browserName+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"";
				}
			}
		}
	}
	else{
		if((sumissionsatatus=="") || (sumissionsatatus == "Submitted"))
		{		
			if(count1!=0) {
				location.href="./learnityasmtserver.assessmentportal.standaloneasmt.CheckAvailabilityOfTest?newtest=start&browserName="+browserName+"&sumissionsatatus="+sumissionsatatus+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"&student_id="+student_id+"";
			}
			else {
				count1 = Number(count1)+1;
				location.href="./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?newtest=start&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"";
			}
		}
		else {
			if(save_state=="") {
				if(count1!=0) {
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.CheckAvailabilityOfTest?newtest=start&save_state=no&browserName="+browserName+"&sumissionsatatus="+sumissionsatatus+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"&student_id="+student_id+"";
				}
				else {
					count1 = Number(count1)+1;
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.GenerateStAloneTest?newtest=start&save_state=no&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"";
				}
			}
			else {
				if(count1!=0) {
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.CheckAvailabilityOfTest?newtest=start&browserName="+browserName+"&sumissionsatatus="+sumissionsatatus+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"&student_id="+student_id+"";
				}
				else {
					count1 = Number(count1)+1;
					location.href="./learnityasmtserver.assessmentportal.standaloneasmt.GenerateTestXHTML?newtest=start&browserName="+browserName+"&checkModelstatus="+checkModelstatus+"&assessment_id="+checkbox1+"";
				}
			}
		}
	}
}		
		