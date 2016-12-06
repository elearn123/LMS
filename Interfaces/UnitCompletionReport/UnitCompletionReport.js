
function show_report_onclick(){
	
	var report_name = getValue("userReportLabelCombo");
	var unit_id = getValue("unitLabelCombo");
	document.getElementById("showreport").src="../frameset?__report=report/unit_completion_report.rptdesign&Role="+report_name+"&UnitId="+unit_id;
	
}
