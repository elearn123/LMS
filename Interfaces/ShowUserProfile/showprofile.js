function onload_click(){
	
	
		
		ladminTree.getStudentDetails(function(data){
			setValue('userdetailslabel',data);
		});
		ladminTree.getPhoto(function(data){
			setValue('userimagelabel',data);
		});
	
}

function imageupload_onclick(){
	
	var file1=document.getElementById('userimage').value;
		if(file1)
		{
			var file = getValue('userimage');
			ladminTree.UserImageUpload(file,function() {
				ladminTree.getPhoto(function(data){
					setValue('userimagelabel',data);
				});
				alert("Successfully Uploaded Photo");
			});
		}
		else
		{
			alert('Please browse a file');
		}
}

