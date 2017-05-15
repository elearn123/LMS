function upload_onclick(){
	var unit_id=getValue('unit_id');
	if(unit_id!=0){
		var file = getValue('BrowseFile');
		if(file.value!=""){
			launchcourse.uploadUnitContent(unit_id,file,function(data) {
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
		var file = getSelectedRow('ManageUnitInformationGrid','file_name');
		
		if(file.value!=""){//unable to acess next line /problem start
		launchcourse.downloadUnitContent(unit_id,file,function(data){
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
		});}
	else{
		alert("Pleare Select a file");
		}
		}
	else{
		alert("Please Select an Unit Name");
	}
}

function delete_onclick(){
	
	var unit_id=getValue('unit_id');	
	
	if(unit_id!=0){
		var file = getSelectedRow('ManageUnitInformationGrid','file_name');
		
		if(file.value!=""){//unable to acess next line /problem start
		launchcourse.deleteUnitContent(unit_id,file,function(data){
			$("#unitErrStatus").text("file deleted");	
			if(data){
						alert("File Deleted");
					}
			 
		});}
	else{
		alert("Please Select a file");
		}
		}
	else{
		alert("Please Select an Unit Name");
	}
	
}



function deleteAll_onclick(){
	
	var unit_id=getValue('unit_id');	
	
	if(unit_id!=0){
		//var file = getSelectedRow('ManageUnitInformationGrid','file_name');
		
	//	if(file.value!=""){//unable to acess next line /problem start
		launchcourse.DeleteAction(unit_id,function(data){
			$("#unitErrStatus").text("true");	
			if(data){
						alert("File Deleted");
					}
			 
		});
		}
	else{
		alert("Please Select an Unit Name");
	}
	$('#ManageUnitInformationGrid').trigger( 'reloadGrid' );
}


function showFileInfo(){
	 
	var oFiles = document.getElementById("BrowseFile").files,
		nFileSize = oFiles[0].size,
		sFileName = oFiles[0].name,
		sFileInfo = "File Name: " + sFileName + "   --------- File Size: " + nFileSize + " bytes";

	document.getElementById("fileInfo").innerHTML = sFileInfo;
		}