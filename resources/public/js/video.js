$(function(){
	$("video").bind('play', function(){
		alert('PLAY event to server');
		$.ajax({
			url: "/video/play/"+this.id
		});
		return false;
	});
	$("video").bind('pause', function(){
		alert('PAUSE event to server');
		$.ajax({
			url: "/video/pause/"+this.id
		});
		return false;
	});
	$("img").bind('click', function(){
		alert('CLICK event to server');
		$.ajax({
			url: "/video/play/"+this.id
		});
		return false;
	});
	
});

