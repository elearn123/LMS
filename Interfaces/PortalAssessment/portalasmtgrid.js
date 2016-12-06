function check_onclick(){
	var asessmentid=getValue("checkbox");
	Portal.setCounter(asessmentid);
					
}

function loadassessment()
{
	window.open('./interfaceenginev2.PortalServlet?IID=AssessmentDelivery',"assessment","width=800,height=700,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
}	
	
	
function start_onclick(){
	var asessmentid = getSelectedRow('PortalAssessment_AssessmentGrid','assessment_id');
	Portal.setCounter(asessmentid,Availablility_check);
}

function Availablility_check(){
	var asessmentid = getSelectedRow('PortalAssessment_AssessmentGrid','assessment_id');
	Portal.asmtAvailablityCheck(asessmentid,sttest1);
}

function sttest1(data) 
{
	var asessmentid = getSelectedRow('PortalAssessment_AssessmentGrid','assessment_id');
	var timeavailable = "";
	var avail1 = "";
	var valid1 = "";
	var MaxtestTeken = "";
	var nooftimesappeared = "";
	var message = "";
	timeavailable=data[1];
	avail1=data[2];
	valid1=data[3];
	MaxtestTeken=data[4];
	nooftimesappeared=data[5];
	message=data[6];
	if(asessmentid!=null) 
	{
		if((MaxtestTeken!="") && (Number(MaxtestTeken)>Number(nooftimesappeared)))
		{
			if(valid1 == "Available")
			{
				if((avail1 == "Available") && (timeavailable == "Available"))
				{
					loadassessment();		  
			
				}
				else{
					alert("Selected Assessment is not available");
				}
			}
			else {
				alert("Validity finished");
			}
		}
		else {
			alert(message);
		}
	}
	else {
		alert("Please Select an Assessment");
	}  
}