
function clear_forum()
{
	var rowid= getIdOfSelectedRow('fagrid');
	
	ForumUtil.clearForum(rowid, function (){setReloadGrid('fagrid')});
}


