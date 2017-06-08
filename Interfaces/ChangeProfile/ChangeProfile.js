function onload_click(){
	
	ladminTree.getUserInfo(function(data){
		
		setValue('ChangeProfile_FNamedata',data[9]);
		setValue('ChangeProfile_MNamedata',data[10]);	
		setValue('ChangeProfile_LNamedata',data[1]);	
		setValue('ChangeProfile_Agedata',data[3]);	
		setValue('ChangeProfile_Experiencedata',data[5]);	
		setValue('ChangeProfile_EduQuilidata',data[4]);	
		setValue('ChangeProfile_Emaildata',data[12]);
		setValue('ChangeProfile_Genderdate',data[2]);
// 		setValue('QuestionPreferreddate',data[6]);	
// 		setValue('MediaPreferreddata',data[7]);	
		setValue('ChangeProfile_AccountStatusdata',data[13]);	
		setValue('ChangeProfile_PermitSelfRegistrationdata',data[14]);
		setValue('ChangeProfile_UserIddata',data[0]);	
		document.getElementById('ChangeProfile_UserIddata').disabled=true;
		document.getElementById('ChangeProfile_Emaildata').tabIndex=8;
		document.getElementById('ChangeProfile_Genderdate').tabIndex="7";
// 		document.getElementById('QuestionPreferreddate').tabIndex="9";
// 		document.getElementById('MediaPreferreddata').tabIndex="10";
		document.getElementById('ChangeProfile_AccountStatusdata').tabIndex="11";
		document.getElementById('ChangeProfile_PermitSelfRegistrationdata').tabIndex="12";
			
		
		
	});
	ladminTree.getPortalUserPhoto(function(data){
		setValue('ChangeProfile_userimagelabel',data);
	});	
	

}

function Update_user() {
	var validate = true;
	var FNamedata = getValue("ChangeProfile_FNamedata");
	var MNamedata = getValue("ChangeProfile_MNamedata");
	
	var LNamedata = getValue("ChangeProfile_LNamedata");
	var Agedata = getValue("ChangeProfile_Agedata");
	
	var Experiencedata = getValue("ChangeProfile_Experiencedata");
	var EduQuilidata = getValue("ChangeProfile_EduQuilidata");
	
	var Emaildata = getValue("ChangeProfile_Emaildata");
	var Genderdate = getValue("ChangeProfile_Genderdate");
	
	
	
	var QuestionPreferreddate = 'Y';
	var MediaPreferreddata = 'Video';
	
	var AccountStatusdata = getValue("ChangeProfile_AccountStatusdata");
	var PermitSelfRegistrationdata = getValue("ChangeProfile_PermitSelfRegistrationdata");
	 
	

			ladminTree.ModifyUser(FNamedata,MNamedata,LNamedata,Agedata,Experiencedata,EduQuilidata,Emaildata,Genderdate,QuestionPreferreddate,MediaPreferreddata,AccountStatusdata,PermitSelfRegistrationdata,
			          function(){alert("Successfully Updated User Information");}
			    );
			
} 



function imageupload_onclick(){
	
	
	var file1=document.getElementById("ChangeProfile_userimage").value;
	if(file1)
	{
		var file = getValue('ChangeProfile_userimage');
		ladminTree.PortalUserImageUpload(file,function() {
			ladminTree.getPortalUserPhoto(function(data){
				setValue('ChangeProfile_userimagelabel',data);
				alert("Successfully Uploaded Photo");
			});
		});
	}
	else
	{
		alert('Please browse a file');
	}

}


function closeChangeProfile()
{
	popupContainer = document.getElementById("popupContainer");
	popupContainer.classList.remove("afterPopup");
}

function gravatarsimage_onclick(){
	ladminTree.GravatarsImageUploadChangeProfile(function(data) {
		ladminTree.getPortalUserPhoto(function(data){
				setValue('ChangeProfile_userimagelabel',data);
				alert("Successfully Uploaded Photo");
			});
	});

}



onload_click();