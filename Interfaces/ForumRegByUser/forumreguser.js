function unregisterAll()
{
	
	var f_id = getValue('forum_id');
	
	if(f_id==null || f_id=="0")
	{
		alert("Please Select A Forum");
	}
	else
	{
		ForumUtil.unregisterAllForum(f_id, gridReload);
	}
}

function gridReload(status)
{
	if (status==true)
	{	
		setReloadGrid('ForumRegByUserAdminGrid');
	}
	else
	{
		alert("Unregistering all users failed");
	}
}