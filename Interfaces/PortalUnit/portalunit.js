function showUnitGrid()
{
	clearGridData( "PortalUnit_UnitGrid" );
	
	Portal.getUnits(setUnitData);
};

function setUnitData(UnitData)
{
	setArrayGridRowData("PortalUnit_UnitGrid",UnitData);
};


function lunchCourse(course_id)
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
	Portal.lunchCourseAll(browser,browserVersion,course_id,"0","0",function (data) {
				setValue('',data);
			});
	
	PortalEngine.getInterfaceFragment("LMSPortal","DeliveryEngine",showUnit);
}


function showUnit(unitData)
{
	setFragment("PortalUnit_popup",unitData);
	unitpopupContainer = document.getElementById("PortalUnit_popupContainer");
	unitpopupContainer.classList.add("afterPopup");
};

function closeUnit()
{
	unitpopupContainer = document.getElementById("PortalUnit_popupContainer");
	unitpopupContainer.classList.remove("afterPopup");
};

showUnitGrid();