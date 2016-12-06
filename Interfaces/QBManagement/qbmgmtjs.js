
function upload_onclick()
{
	var qbid=getSelectedRow('QBManagementGrid','qb_id');
	if(qbid){
	var file = getValue('BrowseFile');
		if(file.value!="") {
		StAsmtAdmin.UploadFile(qbid,file,function(data) {
			setValue('message',data);
			setReloadGrid('QBManagementGrid');
		});
		}
		else
		{
			alert("Please Select a File");
		}
	}
	else{
		alert("Please Select a Row");
	}
}

function download_onclick()
{
	var qbid=getSelectedRow('QBManagementGrid','qb_id');
	var file = getValue('BrowseFile');
	if(qbid){
		
		StAsmtAdmin.DownloadFile(qbid,function(data) {
			window.open(data);
		});
	}
	else{
		alert("Please Select a Row");
	}
}
