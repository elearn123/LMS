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

$(document).ready(function(){
	
	$("#thesearchstring").css("background-color", "#dadbdb");
	if(document.getElementById("thesearchstring").value==''){
		setValue("thesearchstring","Search...");
	}
	
	$("#thesearchstring").blur(function () {

		search_onblur();
	});
	$("#thesearchstring").focus(function () {

		search_onfocus();
	});
	
	
		
		//calenderview();
	
	currdate_onload();
	
	
	
});

	function calenderview(){
				var	day = new Date().getDay();
					week = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday');
				
				document.write(week[day]);
					document.write(new Date().getDate());
			
		setValue("calenderdiv",newDate().getDate);
	}

     function search_onfocus(){
	     $("#thesearchstring").css("background-color", "#ffffff");

	     if(document.getElementById("thesearchstring").value=='Search...')
	     setValue("thesearchstring","");
     }
     
    function search_onblur(){
	     $("#thesearchstring").css("background-color", "#dadbdb");

	     if(document.getElementById("thesearchstring").value=='')
	     setValue("thesearchstring","Search...");
    }
		     
		var vv=1;
		
		function logout() {
			Portal.setUserIDinvalid(function() {
				CallInterface('LoginPage');
			});
		};

		function onload_click() {
			unit_onclick();
			/* linkwebsite()
			dwr.engine.beginBatch(); */
			Portal.setUserInfo(function(data) {
				setValue('usertable',data);
			});
			Portal.Welcome(function(data) {
				setValue('message',data);
			});
			
			Portal.getPhoto(function(data) {
				
				setValue('senderImage',data);
			});
			/* dwr.engine.endBatch(); */
			loginuser();
			/* timeshow(); */
			$('#footer-caption a').css('text-decoration','none').css('color','white');
		};
 
		
 
		function timeshow() {
			var today=new Date();
			var h=today.getHours();
			var m=today.getMinutes();
			var s=today.getSeconds();
			var month=new Array(12);
			month[0]="January";
			month[1]="February";
			month[2]="March";
			month[3]="April";
			month[4]="May";
			month[5]="June";
			month[6]="July";
			month[7]="August";
			month[8]="September";
			month[9]="October";
			month[10]="November";
			month[11]="December";
 	 
			curmonth=month[today.getMonth()];
			year=today.getFullYear();
			day=today.getDate();
 	 
			cdate=curmonth+" "+day+", "+year+" ";
	 
			var str="<br>";
	 
			var time;
	 
			if (h>12)
			{
				h=h-12;
				time=h+ ":" +m+ ":" +s+" p.m";
			}
			else
			{
				time=h+ ":" +m+ ":" +s+" a.m";
			}
 	 
			cdate="<center><font style=\"color:black;font-weight:normal;font-family:verdana,arial;font-size:8pt;\">"
					+cdate+" &nbsp;"+time+"</font></center>";
 
			$('#bottombar3').html(cdate);
			t=setTimeout('timeshow()', 500);
		};
 
 
/*		function lunchCourse(course_id)
		{
			var browserName = navigator.appName;
			var browserVersion = parseInt(navigator.appVersion);
			var browser;
			if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){
				browser='ie4up';
			}
			else {
				browser='mf';
			}
			Portal.lunchCourseAll(browser,browserVersion,course_id,"0","0",function (data) {
				setValue('',data);
			});
			window.open('./interfaceenginev2.PortalServlet?IID=DeliveryEngine','new','fullscreen=yes,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');
		}
	*/	
		function resume2(course_id ,topic_id,strTopic_Title)
		{
			var browserName = navigator.appName;
			var browserVersion = parseInt(navigator.appVersion);
			var browser;
			if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){
				browser='ie4up';
			}
			else {
				browser='mf';
			}
			Portal.lunchCourseAll(browser,browserVersion,course_id,topic_id,strTopic_Title,function (data) {
				setValue('',data);
			});
			window.open('./interfaceenginev2.PortalServlet?IID=DeliveryEngine','new','fullscreen=yes,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');
		}
  
  		function loginuser() {
			Portal.UserLoginNo(function(data) {
				setValue('bottombar1',data);
				   
			});
		}
		
		function basic_search() {
			var n =getValue('thesearchstring');
			window.open('./learnityinterfaceportal.BasicSearchTheUnit?thesearchstring='+n,'basic','width=800,height=600,status=yes,resizeable=yes,toolbar=no,location=no');
		}

	
		function linkwebsite(){
			var t=document.getElementById('bottombar2');
			t.innerHTML='<a href=\'http://www.msmetoolroomkolkata.com\' target=\'new\'>Powered By MSME ToolRoom Kolkata LMS</a>';
		}

//Edited by me
		function loadnotebook(){
				console.log("load notebook called");
				PortalEngine.getInterfaceFragment("LMSPortal","NoteBook",setFragNotebook);
			}

		function setFragNotebook(vstring)
			{
				console.log(vstring);
				setFragment("popup",vstring);
				console.log("load setFragNotebook called");
				unitpopupContainer = document.getElementById("popupContainer");
				
				unitpopupContainer.classList.add("afterPopup");
			}
	
			
			
			
			
		function loadolcalenderpopup(){	
		
				PortalEngine.getInterfaceFragment("LMSPortal","Calender",setFragCalender);

		}
			function setFragCalender(vstring)
			{
				console.log(vstring);
				setFragment("popup",vstring);
				console.log("load setFragCalender called");
				unitpopupContainer = document.getElementById("popupContainer");
				
				unitpopupContainer.classList.add("afterPopup");
			}

//stop here

/*		function loadnotebook()
		{
			window.open('./interfaceenginev2.PortalServlet?IID=NoteBook',"notebook","width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
		}
*/
		
		function assessment(){
			Portal.AssessmentGrid(function(data) {
				setValue('mainsub',data);
			});
		}
	
		function check_onclick(){
			var asessmentid=getValue("checkbox");
			Portal.setCounter(asessmentid);
					
		}
	
	
		function loadassessment()
		{
			window.open('./interfaceenginev2.PortalServlet?IID=LearnityAsmtPortal',"assessment","width=800,height=700,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
		}
	
	
		function start_onclick(){
			var asessmentid=getValue("checkbox");
			Portal.asmtAvailablityCheck(asessmentid,sttest1);
	
	
		}
		function sttest1(data) {
			var asessmentid=getValue("checkbox");
	 
			var timeavailable = "";
			var avail1 = "";
			var valid1 = "";
			var MaxtestTeken = "";
			var nooftimesappeared = "";
			var message = "";
			timeavailable=data[1];
			avail1=data[2];
			valid1=data[3];
			MaxtestTeken=data[4];
			nooftimesappeared=data[5];
			message=data[6];
			if(asessmentid!=false) {
				if((MaxtestTeken!="") && (Number(MaxtestTeken)>Number(nooftimesappeared)))
				{
					if(valid1 == "Available"){
						if((avail1 == "Available") && (timeavailable == "Available"))
						{
							loadassessment();
			
						}
						else{
							alert("Selected Assessment is not available");
						}
					}
					else {
						alert("Validity finished");
					}
				}
				else {
					alert("message");
				}
			}
			else {
				alert("Please Select a Assessment");
			}
		}

		function AddNewNotice()
		{
			window.open('./interfaceenginev2.PortalServlet?IID=LearnityNoticeBoard',"LearnityNoticeBoard","width=600,height=540,status=yes,scrollbars=no,resizable=yes,toolbar=no,menubar=no");
		}
 
//edited by Anupam Samanta
		function loadPasswordChange(){
				
				PortalEngine.getInterfaceFragment("LMSPortal","ChangePassword",setFragPass);
			}

		function setFragPass(vstring)
			{
				
				setFragment("popup",vstring);
				unitpopupContainer = document.getElementById("popupContainer");
				unitpopupContainer.classList.add("afterPopup");
			}
			
		function change_profile()
		{
			PortalEngine.getInterfaceFragment("LMSPortal","ChangeProfile",setFragProfile);
			
		}	
		function setFragProfile(vstring)
		{
				
			setFragment("popup",vstring);
			popupContainer = document.getElementById("popupContainer");
			popupContainer.classList.add("afterPopup");
		}
				

				
		function Quit()
		{
			popupContainer = document.getElementById("popupContainer");
			popupContainer.classList.remove("afterPopup");
		}			
//here i stop
		/*function loadPasswordChange()
		{
 			window.open('./interfaceenginev2.PortalServlet?IID=ChangePassword',"ChangePassword","width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
		}*/

/*		function change_profile()
		{
			window.open('./interfaceenginev2.PortalServlet?IID=ChangeProfile',"ChangeProfile","width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
		}
*/
		function calender_onclick()
		{
			window.open("./calendar.calendar.CalendarMain","Calendar","width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no");
		
		}

		
		function load_help()
		{
			
		PortalEngine.getInterfaceFragment("LMSPortal","HelpPortal",setFragHelp);
			
		}
		function setFragHelp(vstring)
		{
				
			setFragment("popup",vstring);
			popupContainer = document.getElementById("popupContainer");
			popupContainer.classList.add("afterPopup");
		}

		function unit_onclick()
			{
				toggleTabStyle("unitlink", "assessmentlink", "forumlink", "noticelink");
				PortalEngine.getInterfaceFragment("LMSPortal","PortalUnit",setFragUnit);
			}

		function setFragUnit(vstring)
			{
				setFragment("mainsub",vstring);
				showdata();
			}

		function showdata()
			{
				Portal.getUnits(setUnitData);
			}

		function setUnitData(data)
			{
				setArrayGridRowData("PortalUnit_UnitGrid",data);
			}
			
		function assessment_onclick(){
				toggleTabStyle("assessmentlink", "forumlink", "noticelink", "unitlink");
				PortalEngine.getInterfaceFragment("LMSPortal","PortalAssessment",setFragAssessment);
			}

		function setFragAssessment(vstring)
			{
				setFragment("mainsub",vstring);
 				showAssdata();
			}

		function showAssdata()
			{
				Portal.AssessmentGrid(setAssessmentData);

			}

		function setAssessmentData(data)
			{
				setArrayGridRowData("PortalAssessment_AssessmentGrid",data);
			}

			
		function forum_onclick(){
				toggleTabStyle("forumlink", "noticelink", "unitlink",  "assessmentlink");
				PortalEngine.getInterfaceFragment("LMSPortal","PortalForum",setFragForum);
			}

		function setFragForum(vstring)
			{
				setFragment("mainsub",vstring);
			}


		function notice_onclick(){
				toggleTabStyle("noticelink", "unitlink", "forumlink", "assessmentlink");
				PortalEngine.getInterfaceFragment("LMSPortal","PortalNotice",setFragNotice);
			}

		function setFragNotice(vstring)
			{
				setFragment("mainsub",vstring);
			}

		function toggleTabStyle(a,b,c,d)
		{
			 document.getElementById(a).classList.add("clickedClass");
			 document.getElementById(b).classList.remove("clickedClass");
			 document.getElementById(c).classList.remove("clickedClass");
			 document.getElementById(d).classList.remove("clickedClass");
		}
		
		
		
		function currdate_onload() {
	

				var	day = new Date().getDay();
				month= new Array('January', 'February', 'March', 'April', 'May', 'June', 'July','August','September','October','November','December');
				week = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday');
				var m=new Date().getMonth();
				
				
				setValue('lay3',month[m]);
				setValue('lay4',new Date().getDate());
				setValue('lay5',week[day]);
	
	
}

