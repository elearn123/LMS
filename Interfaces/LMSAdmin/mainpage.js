function onload_click() {
			$("#ladminTree").dynatree({
				title: "Sample",
				autoCollapse: 'true',
				keyboard: 'true',
				onActivate: function(dtnode) {
					$("#ladminTitle").text(dtnode.data.title);
					f12(dtnode);
					},
					children: [
					{title: "User",isFolder: true,url:'',
						children: [
						{title: "User Administration",isFolder: true,url:'',
							children: [
								{title:'User Account Administration',url:'./interfaceenginev2.PortalServlet?IID=UserCreation' ,isFolder: false},
							        {title:'User Role Association',url:'./interfaceenginev2.PortalServlet?IID=UserRoleAssociation' ,isFolder: false},
							        {title:'Change Profile',url:'./interfaceenginev2.PortalServlet?IID=ChangeProfile' ,isFolder: false},
								{title:'Change Password',url:'./interfaceenginev2.PortalServlet?IID=ChangePassword' ,isFolder: false},
								{title:'User Chart',url:'./interfaceenginev2.PortalServlet?IID=userChart' ,isFolder: false}

							]
						},
						{title: "Group Administration",isFolder: true,url:'',
							children: [
								{title:'Create Group',url:'./interfaceenginev2.PortalServlet?IID=UserGroupAdmin' ,isFolder: false},
								{title:'Group User Registration',url:'./interfaceenginev2.PortalServlet?IID=GroupUserReg' ,isFolder: false}

							]
						},
						{title: "Access Administration",isFolder: true,url:'',
							children: [
								{title:'User Access Management',url:'./interfaceenginev2.PortalServlet?IID=UserAccessMgmt' ,isFolder: false},
								{title:'Group Access Management',url:'./interfaceenginev2.PortalServlet?IID=GroupAccessMgmt' ,isFolder: false}
							]
						},
						{title: "View Dynamic Information",isFolder: true,url:'',
							children: [
								{title:'View Dynamic Information',url:'./interfaceenginev2.PortalServlet?IID=DynaInfoBy' ,isFolder: false}
							]
						},
						{title: "View Report",isFolder: true,url:'',
							children: [
								{title:'View User Report',url:'./interfaceenginev2.PortalServlet?IID=userReport' ,isFolder: false},
								{title:'View User Chart',url:'./interfaceenginev2.PortalServlet?IID=UserChart' ,isFolder: false}
							]
						}
							]
					},
					{title:'Content Management',isFolder: true,url:'',
						children: [
						{title: "Unit Administration",isFolder: true,url:'',
							children: [
								{title:'Manage Units',url:'./interfaceenginev2.PortalServlet?IID=ManageUnitAdmin' ,isFolder: false},
								{title:'Manage Unit Registration By User',url:'./interfaceenginev2.PortalServlet?IID=UnitRegByUser' ,isFolder: false},
								{title:'Manage Unit Registration By Group',url:'./interfaceenginev2.PortalServlet?IID=UnitRegByGroup' ,isFolder: false},
							]
							},
							{title:'Repository Administration',isFolder: true,url:'',
							children: [
								{title:'Manage Unit Information',url:'./interfaceenginev2.PortalServlet?IID=ManageUnitInformation'},
								{title:'Manage Unit Home Page',url:'./interfaceenginev2.PortalServlet?IID=ManageUnitHomePage'},
								{title:'Unit Import and Export',url:'./interfaceenginev2.PortalServlet?IID=unitImportExport'}		
							]
							},
							{title: "View Report",isFolder: true,url:'',
							children: [
							{title:'Unit completion Report',url:'./interfaceenginev2.PortalServlet?IID=UnitCompletionReport' ,isFolder: false}
							
							]
							}
						]
						},
						{title: "Assessment Management",isFolder: true,url:'',
						children: [
						{title: "Q B Administration",isFolder: true,url:'',
							children: [
								{title:'QB Management',url:'./interfaceenginev2.PortalServlet?IID=QBManagement' ,isFolder: false},
								{title:'QB Item Management',url:'./interfaceenginev2.PortalServlet?IID=QBItemManagement' ,isFolder: false},
							]
							},
							{title: "Assessment Administration",isFolder: true,url:'',	
							children: [
								{title:'Topic Management',url:'./interfaceenginev2.PortalServlet?IID=TopicManagement' ,isFolder: false},
								{title:'Question Bank Topic Association',url:'./interfaceenginev2.PortalServlet?IID=QBTopicAssociation' ,isFolder: false},
								{title:'Assessment Management',url:'./interfaceenginev2.PortalServlet?IID=AssessmentManagement' ,isFolder: false},
								{title:'Assessment Definition',url:'./interfaceenginev2.PortalServlet?IID=AssessmentDefinition' ,isFolder: false},
								{title:'Assessment Registration By User',url:'./interfaceenginev2.PortalServlet?IID=AssessmentRegByUser' ,isFolder: false},
								{title:'Assessment Registration By Group',url:'./interfaceenginev2.PortalServlet?IID=AssessmentRegByGroup' ,isFolder: false},
								{title:'Assessment Result Notification Mgmt.',url:'./interfaceenginev2.PortalServlet?IID=AsmtResultNotificationMgmt' ,isFolder: false},
								{title:'Mail Server Configuration',url:'./interfaceenginev2.PortalServlet?IID=ConfigureMailServer' ,isFolder: false}
							]
							},
							{title: "Assessment Reports",isFolder: true,url:'',
							children: [
								{title:'Assessments Result',url:'./interfaceenginev2.PortalServlet?IID=AssessmentsResult' ,isFolder: false},
								{title:'Assessment Registration Report',url:'./interfaceenginev2.PortalServlet?IID=AssessmentRegisReport' ,isFolder: false},
								{title:'Assessment Report Generation',url:'./interfaceenginev2.PortalServlet?IID=AsmtReportGeneration' ,isFolder: false},
								{title:'Assessment Details Report',url:'./interfaceenginev2.PortalServlet?IID=AsmtDetailsReport' ,isFolder: false},
								{title:'Assessments Report Summary',url:'./interfaceenginev2.PortalServlet?IID=AsmtReportSummary' ,isFolder: false},
								{title:'Top Five Toppers',url:'./interfaceenginev2.PortalServlet?IID=TopFiveToppers' ,isFolder: false}	
							]
							}
						]
						},
						{title: "Forum Administration",isFolder: true,url:'',
						children: [
							{title:'Forum Management',url:'./interfaceenginev2.PortalServlet?IID=ForumAdmin' ,isFolder: false},
							{title:'Message Management',url:'./interfaceenginev2.PortalServlet?IID=ForumMsgMgmt' ,isFolder: false},
							{title:'Forum Registration by User',url:'./interfaceenginev2.PortalServlet?IID=ForumRegByUser' ,isFolder: false},
							{title:'Forum Registration by Group',url:'./interfaceenginev2.PortalServlet?IID=ForumRegByGroup' ,isFolder: false},
							{title:'Forum Dynamic Information',url:'./interfaceenginev2.PortalServlet?IID=ForumDynamicInfo' ,isFolder: false},
							{title:'View Forum Report',url:'./interfaceenginev2.PortalServlet?IID=forumReport' ,isFolder: false},
						]
						},
						{title: "NoteBook Administration",isFolder: true,url:'',
							children: [
								{title:'NoteBook Management',url:'./interfaceenginev2.PortalServlet?IID=NotebookMgmt' ,isFolder: false}
							]
						},
						{title: "Notice Administration",isFolder: true,url:'',
							children: [
								{title:'Notice Board',url:'./interfaceenginev2.PortalServlet?IID=NoticeBoardAdmin' ,isFolder: false}
							]
						}
				]//main
		});
}

   function f12(dtnode) {
	   document.getElementById('ladminFrame').src = dtnode.data.url;
   }

   function logout_onclick() {
	   ladminTree.sessionInvalidate(function (){
			CallInterface("LoginPage");
	   });
   }
   
   
