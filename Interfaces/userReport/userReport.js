function show_report_onclick(){
	
	var role_name = getValue("userReportRoleCombo");
	document.getElementById("showreport").src="../frameset?__report=report/user_details_report.rptdesign&Role="+role_name;
	
}
