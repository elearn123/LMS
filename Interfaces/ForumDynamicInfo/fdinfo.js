function refreshinfo()
{
	ForumUtil.refreshUserInfo(showInfo);
}

function showInfo(data)
{
	setValue('userinfo',data);
}
