function upload_onclick(){
	
	var file = getValue('batchuser');
	
	ladminTree.BatchUserCreation(file,function(data) {
		//setValue('message',data);
	});
	setReloadGrid('usercreationgrid');
}


function imageupload_onclick(){
	
	var student_id = getSelectedRow('usercreationgrid','student_id');
	var file = getValue('userimage');
	if(student_id==null || student_id==""){
		alert("Please Select A Row");

	}
	else{
		ladminTree.UserImageUpload(student_id,file,function(data) {
		//setValue('message',data);
		});
		alert("Successfully Uploaded");
	}
}


function CheckEmail(value)
{
	var firstchunk,indx,secondchunk

		/*	if (value == ""){
				alert("Please make sure you have entered a valid " +
						"email before submitting the info.")

						return false
}*/

    //get the zero-based index of the "@" character
			alert(value)
			indx = value.indexOf("@")

    //if the string does not contain an @, then return false
					if (indx == -1 ){

				/*alert("Please make sure you have entered a valid " +
				"email before submitting the info.")*/
						return [false,"Please make sure you have entered a valid email"];
						
					}

    //if the first part of email is < two chars and thye second part is < six chars
    //(arbitrary but workable criteria), reject the input address

					firstchunk = value.substr(0,indx) //up to but not including the "@"

    //start at char following the "@" and include up to end of email addr
							secondchunk = value.substr(indx + 1) 

    //if the part  following the "@" does not include a period "." then
    //also return false

									if ((firstchunk.length < 2 ) || (secondchunk.length < 6) ||
									(secondchunk.indexOf(".") == -1)){ 

								           return [false,"Please make sure you have entered a valid email"];

									}

    //the email was okay; at least it had a @, more than one username chars,
    //more than six chars after the "@", and the substring after the "@"
    // contained a "." char

									return [true,""];

}//CheckEmail



function showprofile_onclick(){
	
	var student_id = getSelectedRow('usercreationgrid','student_id');
	
	ladminTree.setSessionStudentIdtoUpdate(student_id);
	if(student_id==null || student_id==""){
		alert("Please Select A Row");

	}
	else
	{
		window.open('./interfaceenginev2.PortalServlet?IID=ShowUserProfile','showprofile','width=810,height=635,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');
	}
}


function excelimage_onclick(){
	window.open('../html/ExcelFormat.html','Excel Format','width=810,height=635,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');
}