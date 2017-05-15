function showUnitGrid()
{
	clearGridData( "PortalUnit_UnitGrid" );
//	Portal.unitGrid(setUnitGridData);
	Portal.getUnits(setUnitGridData);
};

function setUnitGridData(unitGridData)
{
	setArrayGridRowData("PortalUnit_UnitGrid",unitGridData);
};


function showUnit(unitData)
{
	setFragment("PortalUnit_unitpopup",unitData);
	unitpopupContainer = document.getElementById("PortalUnit_unitpopupContainer");
	unitpopupContainer.classList.add("afterPopup");
};

function closeUnit()
{
	unitpopup=document.getElementById("PortalUnit_unitpopup");
	//unitpopup.classList.add("popuphide");
	unitpopupContainer = document.getElementById("PortalUnit_unitpopupContainer");
	unitpopupContainer.classList.remove("afterPopup");
};

function lunchCourse(unitId)
{
	var browserName = navigator.appName;
			var browserVersion = parseInt(navigator.appVersion);
			var browser;
			if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){
				browser='ie4up';
			}
			else {
				browser='mf';
			}
	Portal.lunchCourseAll(browser,browserVersion,unitId,"0","0",function (data) {
				setValue('',data);
			});
	
	PortalEngine.getInterfaceFragment("LMSPortal","DeliveryEngine",showUnit);
}

showUnitGrid();