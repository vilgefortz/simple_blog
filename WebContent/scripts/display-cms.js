var idmain;

var getArticle = function (id) {
	if (id === undefined) id = 1;
	idmain = id;
	$.post("Article", {
		id : id, action:"getArticle"
	}, function(data) {
		data = $.parseJSON(data);
		if (data.error === "") {
			$("#title").val(data.title);
			$("#content").val(data.content);
			$("#date").val(data.date);
		} else {
			alert(data.error);
			$("#content").val("<h1>404 article not found</h1>");
		}
	});			

};

var getArticles = function () {
	$.post ("Article",{action:"getArticles"}, function (data) {
		data=$.parseJSON(data);
		$("#articles").text("");
		$.each(data, function (key, value) {
			value=$.parseJSON(value);
			$("#articles").append("<li><button class = 'article-button btn btn-secondary' style='padding:2px;margin:2px;background-color:black;' value='" + value.id + "'>" + value.date +  " " + value.title + "</li>");
		});
		$(".article-button").click ( function () {
			getArticle(	$(this).val());
		});
	});

};

$(document).ready(function() {
		getArticle(undefined);
		getArticles();
		$("#save").click (function () {
			var id = idmain;
			var title = $("#title").val();
			var content = $("#content").val();
			var date = $("#date").val();
			$.post ("Article", {
				action:"save",
				id:id,
				title:title,
				content:content,
				date:date
			}, function (data) {
				getArticle (id);
				getArticles ();
			});
		});
		$("#delete").click (function () {
			var id = idmain;
			if (id !== "1") {
			$.post ("Article", {
				action:"delete",
				id:id,
			}, function (data) {
				getArticle (undefined);
				getArticles ();
			});
			}
		});
		$("#create").click (function () {
			$.post ("Article", {
				action:"create"
			}, function (data) {
				getArticle(data);
				getArticles();
			});
		});

});