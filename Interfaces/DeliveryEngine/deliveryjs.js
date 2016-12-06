var toc = false;
var setcontrol = "";
var f_node = true;
var first_node = "";

function afterTreeLoad()
{
	
	var identifier="";
	launchcourse.setSessionSequence('-1',function(data){});
	
//	$("#appletAPIAdapter").css({'z-index' : '0', 'visibility' : 'hidden'});
	$("#deliveryIframe").css({'z-index' : '1', 'width' : '100%', 'left' : '0%'});
	launchcourse.checkCourseControl(function(data) {
		setcontrol=data;
	
//		document.getElementById("deliverytopstrip").style.visibility="hidden";
		
		if (setcontrol!= "") {
			if (setcontrol.charAt(1)=='T') 
			{
				if (setcontrol.charAt(0)=='T') {//TT   Both "Flow and "Choice"
				}
				else{//FT  "Choice"
					document.getElementById("firstitem").parentNode.remove(); 
					document.getElementById("lastitem").parentNode.remove(); 
					document.getElementById("previousitem").parentNode.remove();
					document.getElementById("topitem").parentNode.remove(); 
					document.getElementById("nextitem").parentNode.remove(); 
				}
			}
			else {
				if (setcontrol.charAt(0)=='T') {//TF  "Flow"
		
					$("#showtree").css({'display' : 'none'});
//					$("#showtree").css({'z-index' : '0', 'width' : '0%'});
//					$("#treediv").css({'z-index' : '0', 'width' : '0%'});
//					$("#deliveryIframe").css({'z-index' : '1', 'width' : '75%', 'left' : '0%'});
//					$("#iframediv").css({'z-index' : '1', 'width' : '100%', 'left' : '0%'});
					$("#iframedeliveryIframe").css({'height' : '100%','position' : 'relative'});
					document.getElementById("showhidetoclabel").parentNode.remove(); 
					document.getElementById("showhidetoc").parentNode.remove(); 
		
				}
				else{//FF   "Neither Flow or Choice - this should never happen"
						
				}
						
			}
		}
		
		document.getElementById("deliverytopstrip").style.visibility="visible";

		launchcourse.getIdentifier(function(data) 
		{	
			identifier=data;		
			if(identifier!=0)
			{
				$("#nodeKey").val(identifier);
				$("#treediv").dynatree("getTree").activateKey($("#nodeKey").val());
			}
		});
		
	});
	
		
	setValue('maxresults','10');
//	init();
}

function f12(dtnode){
	//alert(dtnode.data.url);
	var url1=dtnode.data.url;
	// alert(url1);
	var length1=url1.length;
	//alert(length1);
	var s1=url1.substring(length1-4,length1);
	/*
	var filename1=url1.substring(url1.lastindexof('/')+1, length1); 
	alert("filename1==="+filename1);
	var filename2=url1.substring(url1.indexof('//')+1, length1); 
	alert("filename2==="+filename2);*/
	var browserName = navigator.appName;
	var browserVersion = parseInt(navigator.appVersion);
	//var b="";
	if(s1!='asmt'){
		launchcourse.setSessionSequence(dtnode.data.sequence,function(data){});
	// 	alert(dtnode.data.url);
		document.getElementById("deliveryIframe").src = dtnode.data.url;
		$("#nodeKey").val(dtnode.data.key);
		launchcourse.onselectTitle(dtnode.data.key,dtnode.data.tooltip,function(data) {
			var b=data;
			//alert("..."+b);
		
			if(b=='true')
			{
				location.href="./interfaceenginev2.PortalServlet?IID=LoginPage";
			}
		});	
	}
	else{
		launchcourse.setSessionSequence(dtnode.data.sequence,function(data){});
		document.getElementById("deliveryIframe").src = dtnode.data.url;
		$("#nodeKey").val(dtnode.data.key);
		//var filename1="";
		launchcourse.onselectTitle(dtnode.data.key,dtnode.data.tooltip,function(data) {
			var b=data;
			//alert("..."+b);
		
			if(b=='true')
			{
				location.href="./interfaceenginev2.PortalServlet?IID=LoginPage";
			}
		});	
		launchcourse.getEmbeddedAssessment(dtnode.data.url,function(data){
			var filename1=data[0];
			var cid=data[1];
			//alert("filename1===="+filename1);
			//alert("cid============"+cid);
			if(browserName == 'Microsoft Internet Explorer' && browserVersion >=4){
				document.getElementById("deliveryIframe").src =  "./learnityasmtserver.assessmentportal.embeddedasmt.Assessment?s="+filename1+"&cid="+cid+"&identifier="+dtnode.data.key;
			}
			else {
				document.getElementById("deliveryIframe").src =  "./learnityasmtserver.assessmentportal.embeddedasmt.AssessmentXHTML?s="+filename1+"&cid="+cid+"&identifier="+dtnode.data.key;
			}
		});

	}
	f_node = false;
}

function launchNoteBook()
{
	window.open('./interfaceenginev2.PortalServlet?IID=NoteBook','notes','width=800,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no');
}

function doConfirm()
{
	if( confirm("Back to Home?") )
	{
	    window.opener.location.reload();	
            window.parent.close();
	}
	
}
function showreport()
{
	alert("showreport");
}


function launch_editor () {
	var dtnode = getSelectedTreeNode("treediv");
	//launchcourse.setResourceRefToSession(dtnode.data.url, function(data){});
	
	var rel_path = dtnode.data.url;
	var identifier = dtnode.data.key;
	window.open("./deliveryengine.HtmlEditor?repository="+rel_path+"&identifier="+identifier,'editor','width=800,height =600,status=yes,scorllbars=yes,toolbar=no,menubar=no');
}

function show_hide_toc() {
	if(toc == true) {
		$("#showtree").css({'z-index' : '1', 'width' : '25%'});
		$("#treediv").css({'z-index' : '1', 'width' : '180%'});
		$("#deliveryIframe").css({'z-index' : '0', 'width' : '100%', 'left' : '25%'});
		$("#iframediv").css({'z-index' : '0', 'width' : '75%', 'left' : '25%'});
		$("#showhidetoclabel").text('Hide toc');
		toc = false;
	} else {
		$("#showtree").css({'z-index' : '0', 'width' : '0%'});
		$("#treediv").css({'z-index' : '0', 'width' : '0%'});
		$("#deliveryIframe").css({'z-index' : '1', 'width' : '100%', 'left' : '0%'});
		$("#iframediv").css({'z-index' : '1', 'width' : '100%', 'left' : '0%'});
		$("#showhidetoclabel").text('Show toc');
		toc = true;
	}

}
function delivery_search_onclick() {
	if(document.contensearchform.query.value != ""){
		document.contensearchform.method="post";
		document.contensearchform.action="../jsp/delivery/search/results.jsp";
		document.contensearchform.target="deliveryIframe";
		document.contensearchform.submit();
	} else {
		alert("Please enter search parameter.");
	}
}





	/****************************************************************************
	**
	** Function: LMSIsInitialized()
	** Input:   none
	** Output:  boolean
	**
	** Description:  This function returns a boolean that represents where or 
	**               no LMSInitialize() has been called by the SCO.
	**
	***************************************************************************/
	function LMSIsInitialized()
{
	   // Determines if the API (LMS) is in an initialized state.
	   // There is no direct method for determining if the LMS API is initialized
	   // for example an LMSIsInitialized function defined on the API so we'll try
	   // a simple LMSGetValue and trap for the LMS Not Initialized Error
	   
	var value = API.LMSGetValue("cmi.core.student_name");
	var errCode = API.LMSGetLastError().toString();
	if (errCode == 301)
	{
		return false;
	}
	else
	{
		return true;
	}
}
	/****************************************************************************
	**
	** Function: changeSCOContent()
	** Input:   none
	** Output:  none
	**
	** Description:  This function enables the appropriate controls so that
	**               the user can progress to the next item.
	**
	***************************************************************************/
	function changeSCOContent()
	{
	   
	}
	/****************************************************************************
	**
	** Function: closeSCOContent()
	** Input:   none
	** Output:  none
	**
	** Description:  This function exits out of the current lesson and presents
	**               the RTE menu. 
	**
	***************************************************************************/
	function closeSCOContent()
	{
	   
	}
	
	/****************************************************************************
	**
	** Function: init()
	** Input:   none
	** Output:  none
	**
	** Description:  This function sets the API variable and hides the
	**               the navigation buttons
	**
	***************************************************************************/
	function init() { 
		API = this.document.APIAdapter;
	}
	
	
	
	function previousitem1()
	{

		if(f_node) {
			alert("No Item Selected");
			return;
		}
		launchcourse.getPreviousItemNode ("2", function(data) {
			if(data=='First')
			{
				alert('Already In First Topic');
			}
			else
			{
				$("#nodeKey").val(data);
				$("#treediv").dynatree("getTree").activateKey($("#nodeKey").val());
				f_node = false;
			}
		})
		
	}
	
	
	function topitem_onclick()
	{
		document.getElementById("deliveryIframe").src = "./interfaceenginev2.PortalServlet?IID=DeliveryHomePage";
//		$("#nodeKey").val(first_node);
	}

	function nextitem1()
	{
					launchcourse.getNextItemNode ("1", function(data) {
					
						if(data=='End')
						{
							alert('End of Chapter');
						}
						else
						{
							$("#nodeKey").val(data);
							$("#treediv").dynatree("getTree").activateKey($("#nodeKey").val());
							f_node = false;
						}
		
					})
	}
	
	
	function firstitem1()
	{

				launchcourse.getFirstItemNode ("3", function(data) {
				
					$("#nodeKey").val(data);
					$("#treediv").dynatree("getTree").activateKey($("#nodeKey").val());
				
				});		
	}

	function lastitem1()
	{
				launchcourse.getLastItemNode ("4", function(data) {
					$("#nodeKey").val(data);
					$("#treediv").dynatree("getTree").activateKey($("#nodeKey").val());
				});		
	}	