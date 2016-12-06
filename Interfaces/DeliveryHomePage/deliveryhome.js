function onload_onclick() {
	launchcourse.showHomePage(function (data)  {
		$("#homePageMain").html(data);
	});
}