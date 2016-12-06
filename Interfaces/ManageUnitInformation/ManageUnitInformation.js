function upload_onclick(){
	var unit_id=getValue('unit_id');
	if(unit_id!=0){
		var file = getValue('BrowseFile');
		if(file.value!=""){
			launchcourse.uploadManifest(unit_id,file,function(data) {
				setReloadGrid('ManageUnitInformationGrid');
				$.blockUI({message: data});
				setTimeout($.unblockUI, 1000);
			});
		}
		else{
		alert("Pleare Select a file");
		}
	}
	else{
		alert("Pleare Select an Unit Name");
	}
	
}

function download_onclick(){
	var unit_id=getValue('unit_id');
	if(unit_id!=0){
		launchcourse.downloadManifest(unit_id,function(data){
			window.open(data);	
		});
	}
	else{
		alert("Please Select an Unit Name");
	}
}

function showFileInfo(){
	 
	var oFiles = document.getElementById("BrowseFile").files,
		nFileSize = oFiles[0].size,
		sFileName = oFiles[0].name,
		sFileInfo = "File Name: " + sFileName + "   --------- File Size: " + nFileSize + " bytes";

	document.getElementById("fileInfo").innerHTML = sFileInfo;
		}