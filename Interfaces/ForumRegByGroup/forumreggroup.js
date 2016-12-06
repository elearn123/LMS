function unregisterAll()
{
	var f_id = getValue('forum_id');
	
	if(f_id==null || f_id=="0")
	{
		alert("Please Select A Forum");
	}
	else		
	{
		var cellData = getCellData('ForumRegByGroupAdminGrid', 1, 2);
		
		if (cellData != "No Records Found")
		{
			ForumUtil.unregisterAllForumGroup(f_id, gridReload);
		}
		else
		{
			alert("No groups registered for this forum");
		}
	}
}

function gridReload(status)
{
	if (status==true)
	{	
		setReloadGrid('ForumRegByGroupAdminGrid');
	}
	else
	{
		alert("Unregistering all groups failed");
	}
}