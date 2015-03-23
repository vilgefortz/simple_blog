
<script type="text/javascript" src="scripts/display-cms.js"></script>
	<!-- Blog Post Content Column -->
	<div style="color:#000000" class="blogfield col-md-8">

		<!-- Blog Post -->
		<button id="save" class="btn btn-primary">Save</button>
		<button id='create' class = 'btn btn-secondary'  style='background:green'>Add</button>
		<button id='delete' class = 'btn btn-secondary' style='background:red;float:right;'>Delete</button>
		<!-- Title -->
		<h1>
			<input class="form-control" id="title"/>
		</h1>

		<hr>

		<!-- Date/Time -->
		<p>
			<span class="glyphicon glyphicon-time"></span><input class="form-control" disabled id="date"></span>
		</p>

		<hr>

		<hr>

		<!-- Post Content -->
		<textarea rows="20" class="form-control" id="content"></textarea>
		<hr>
</div>
<!-- <script type="text/javascript" src="scripts/display.js"></script> -->
<!-- Blog Post Content Column -->
<script type="text/javascript" src="scripts/sidebar.js">

</script>
<div class="col-md-4">
	<!-- Blog Search Well -->
	<div class="well blogfield">
		<h4>Articles</h4>
		<ul id="articles">
		</ul>
		<!-- /.input-group -->
	</div>
</div>