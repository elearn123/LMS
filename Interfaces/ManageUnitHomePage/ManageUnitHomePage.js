
function save_homepage_onclick(){
	
	var homePageContent = getValue("unitHomePageText");
	var unitId = getValue("unitSelectionCombo");
	ladminTree.saveUnitHomePageContent(unitId,homePageContent,function(returnMessage) {
		setValue('message',returnMessage);
	});
	
}

function unit_onchange(){
	
	var unitId = getValue("unitSelectionCombo");
	ladminTree.getUnitHomePageContent(unitId,function(homePageContent) {
		setValue('unitHomePageText',homePageContent);
	    setValue('unitHomePageTextPreview',homePageContent);
	});
}

function homepagePreview(){
	
	var homePageContent = getValue('unitHomePageText');
	setValue('unitHomePageTextPreview',homePageContent);
}
