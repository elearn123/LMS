function onload_click(){
	
	Portal.returnUserId(function(data){
		setValue('UserIddata',data);	
		changePasswordForm_Select();
	});
	
	document.getElementById('UserIddata').disabled=true;
	
	
}

function submitForm(){
	var OldPassworddata = getValue("ChangePassword_oldPass");
	var NewPassworddata = getValue("ChangePassword_newPass");	
	var RetypePassworddata = getValue("ChangePassword_retypePass");

	
		
	Portal.updatePassword(OldPassworddata,NewPassworddata,RetypePassworddata,function(data){
	if(NewPassworddata==RetypePassworddata){
		if(data=="no")
		{
			alert("Old Password is wrong. Please input correct password");
		}		
		else
		{
			data="Password successfully changed";
			setValue('ChangePassword_mess',data);
		
		}
		
	}
	else{
		data="Password retype field should be same as new password. ";
			setValue('ChangePassword_mess',data);
		
		alert("Password Miss Match");
		return;
	}
		
	
	});	
	//changePasswordForm_Modify();
	
}


function closeChangePass()
{
	popupContainer = document.getElementById("popupContainer");
	popupContainer.classList.remove("afterPopup");
}

