function activefunc(){

	setReloadGrid("ConfigureMailServerGrid");
	$.unblockUI();
}

function Activebttn_onclick()
{
	var configuration_id=getSelectedRow('ConfigureMailServerGrid','configuration_id');
	if (configuration_id==null){
		alert("Please Select a Row");
	}
	else
	{
		$.blockUI({ message: 'Please Wait...' });
		StAsmtAdmin.updateMailServerStatus(configuration_id,'Active',activefunc);
	}
}

function inactivefunc(){

	setReloadGrid("ConfigureMailServerGrid");
	$.unblockUI();
}

function InActivebttn_onclick()
{
	var configuration_id=getSelectedRow('ConfigureMailServerGrid','configuration_id');
	if (configuration_id==null){
		alert("Please Select a Row");
	}
	else
	{
		$.blockUI({ message: 'Please Wait...' });
		StAsmtAdmin.updateMailServerStatus(configuration_id,'Inactive',inactivefunc);
	}
}

