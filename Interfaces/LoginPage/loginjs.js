
NS4 = (document.layers) ? true : false;
  function checkEnter(event)	
{ 
	var code = 0;
	if (NS4)  		
	code = event.which; 	
	else  		
		code = event.keyCode;	
	if (code==13) 		
	login_onclick();
}	;


var inProgress = false; 

function animate()
{
	if( inProgress ) return false;
	inProgress = true;
	animElement = document.getElementById("animationDiv");
	animElement.classList.add("la-animate");
	setTimeout( function() {
						animElement.classList.remove("la-animate");
						inProgress = false;
					}, 6000 );
}

function currdate_onclick() {
	
	
	
	/*var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();

if(dd<10) {
    dd='0'+dd
} 

if(mm<10) {
    mm='0'+mm
} 
*/
//today = mm+'/'+dd+'/'+yyyy;
//setValue('currdate',today);

Portal.getCurrentDate(F22);


	
}
function F22( date){
setValue('currdate',date);	
	
}


function login_onclick() {

	setValue('incorrectuser','');
	animate();
	var uid=getValue("accuseridinput");
	var password=getValue("accpasswordinput");
 	Portal.verifyUser(uid,password,function(data) {

		if(data=='Invalid')
		setValue('incorrectuser','<blink><font style=\"font:10px tahoma, verdana, sans-serif;color:#990000\">Login Failed! </font><font style=\"font:10px  tahoma, verdana, sans-serif;color:#000000\">Invalid user.</font></blink>');
		if(data=='Inactive')
		setValue('incorrectuser','<blink><font style=\"font:10px tahoma, verdana, sans-serif;color:#990000\">Login Failed! </font><font style=\"font:10px  tahoma, verdana, sans-serif;color:#000000\">Inactive user.</font></blink>');
		if(data=='Norole')
		setValue('incorrectuser','<blink><font style=\"font:10px tahoma, verdana, sans-serif;color:#990000\">Login Failed! </font><font style=\"font:10px  tahoma, verdana, sans-serif;color:#000000\">Role is not defined for this user.</font></blink>');
		if(data=='undefined')
		setValue('incorrectuser','<blink><font style=\"font:10px tahoma, verdana, sans-serif;color:#990000\">Login Failed! </font><font style=\"font:10px  tahoma, verdana, sans-serif;color:#000000\">Role is not defined in properties file.</font></blink>');
		if(data=='Admin')
		{
			CallInterface('LMSAdmin');
			
		}
		if(data=='Portal')
		{  Portal.CheckTimeCompleted(function(data1) {
			if(data1=='Your Time Completed For Today')
			{
				setValue('incorrectuser',data1);
			}	
			else
			{
				Portal.getFirstLogin(function(data2) {
					if(data2=='first')
					{
						CallInterface('PasswordChange')
					}
					else
					{
						CallInterface('LMSPortal');
					}
				});
			}
			});
		}
			
 	});
 };
 
 
