function unit_upload_onclick()
{
	var unit_select = document.getElementById("unitSelect");
	var unit_id = unit_select.options[unit_select.selectedIndex].value;
	var unit_name = unit_select.options[unit_select.selectedIndex].text;
	if(unit_id == "" || unit_id == null || unit_id == "0")
	{
		alert("Please Select An Unit from the dropdown list");
		return false;
	}
	var unit_name = getValue('unitSelect');
	var file = getValue('unitChoose');
	if(file.value!="") {
		$("#unitErrStatus").text("Unit import started ...");		
		launchcourse.UploadUnit(unit_id, unit_name, file,"FS",function(data) {
			$("#unitErrStatus").text(data);
		});
		
	} else {
		alert("Please Select a Zip file to upload");
		return false;
	}
}


function unit_upload_onclick_forDB()
{
	var unit_select = document.getElementById("unitSelect");
	var unit_id = unit_select.options[unit_select.selectedIndex].value;
	var unit_name = unit_select.options[unit_select.selectedIndex].text;
	if(unit_id == "" || unit_id == null || unit_id == "0")
	{
		alert("Please Select An Unit from the dropdown list");
		return false;
	}
	var unit_name = getValue('unitSelect');
	var file = getValue('unitChoose');
	if(file.value!="") {
		$("#unitErrStatus").text("Unit import started ...");		
		launchcourse.UploadUnit(unit_id, unit_name, file,"DB",function(data) {
			$("#unitErrStatus").text(data);
		});
		
	} else {
		alert("Please Select a Zip file to upload");
		return false;
	}
}

/*for facking deliveryjs quit*/
function showUnitGrid()
{
	
}

function unit_download_onclick() {
	
	var unit_select = document.getElementById("unitSelect");
	var unit_id = unit_select.options[unit_select.selectedIndex].value;
	if(unit_id == "" || unit_id == null || unit_id == "0") {
		alert("Please Select An Unit");
		return false;
	}
	$("#unitErrStatus").text("Unit download started ...");		
	launchcourse.UnitExportFile(unit_id, function(data) {
		if(data)
		{
			$("#unitErrStatus").text("Unit download finished");		
			window.open(data);
		}
		else 
		{
			$("#unitErrStatus").text("There is no content inside this unit");		
			alert("There is no content inside this unit");
		}
	});
}

function showUnit(unitData)
{
	setFragment("unitpopup",unitData);
	unitpopupContainer = document.getElementById("unitpopupContainer");
	unitpopupContainer.classList.add("afterPopup");
};

function show_preview_onclick() {
	
	var unit_select = document.getElementById("unitSelect");
	var unit_id = unit_select.options[unit_select.selectedIndex].value;
	if(unit_id == "" || unit_id == null || unit_id == "0") {
		alert("Please Select An Unit");
		return false;
	}
	
	var browserName = navigator.appName;
	var browserVersion = parseInt(navigator.appVersion);
	var browser;
	if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){
		browser='ie4up';
	}
	else {
		browser='mf';
	}
	     		
	launchcourse.lunchCourseAll(browser,browserVersion,unit_id,"0","0",function (data) {
		setValue('',data);
	});
			
	PortalEngine.getInterfaceFragment("LMSPortal","DeliveryEngine",showUnit);
	//window.open('./interfaceenginev2.PortalServlet?IID=DeliveryEngine','new','width=910,height=635,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');
			
}


function closeUnit()
{
	unitpopup=document.getElementById("unitpopup");
	//unitpopup.classList.add("popuphide");
	unitpopupContainer = document.getElementById("unitpopupContainer");
	unitpopupContainer.classList.remove("afterPopup");
};

	
	function createIndex_onclick() {
	
		var unit_select = document.getElementById("unitSelect");
		var unit_id = unit_select.options[unit_select.selectedIndex].value;
		if(unit_id == "" || unit_id == null || unit_id == "0") {
			alert("Please Select an Unit");
			return false;
		}
		$("#unitErrStatus").text("Unit indexing started ...");		
		launchcourse.createIndex(unit_id,function(data){$("#unitErrStatus").text(data);});
	}