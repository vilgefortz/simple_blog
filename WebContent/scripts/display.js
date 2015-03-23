var getArticle = function (id) {
	if (id === undefined) id = 1;
	$.post("Article", {
		id : id, action:"getArticle"
	}, function(data) {
		data = $.parseJSON(data);
		if (data.error === "") {
			$("#title").text(data.title);
			$("#content").html(data.content);
			$("#date").text(data.date);
		} else {
			alert(data.error);
			$("#content").html("<h1>404 article not found</h1>");
		}
	});			

};
$(document).ready(function() {
		getArticle(undefined);
		$.post ("Article",{action:"getArticles"}, function (data) {
			data=$.parseJSON(data);
			$.each(data, function (key, value) {
				value=$.parseJSON(value);
				$("#articles").append("<li><button class = 'article-button btn btn-secondary' style='padding:2px;margin:2px;background-color:black;' value='" + value.id + "'>" + value.date +  " " + value.title + "</a></li>");
			});
			$(".article-button").click ( function () {
				getArticle(	$(this).val());
			});
		});
	
});