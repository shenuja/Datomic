$(function() {
	var video = document.getElementById("Video1");

	video.addEventListener("play", function(event) {
		alert("play");
	}, false);
	
	video.addEventListener("pause", function(event) {
		alert("pause");
	}, false);
});
