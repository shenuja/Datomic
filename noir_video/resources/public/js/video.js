$(function() {
	var video = document.getElementById("Video1");

	video.addEventListener("play", function(event) {
		var v = this;
		alert("play");
		$.ajax({
			url: "/video/play/"+this.attr('id');
		});
	}, false);
	
	video.addEventListener("pause", function(event) {
		alert("pause");
	}, false);
});
