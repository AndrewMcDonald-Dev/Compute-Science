<?php
// Access Database
require 'config.php';
?>

<html>

<head>
	<title>Edit Cell</title>
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css"> -->
	<style type="text/css">
	body {
		font: 14px sans-serif;
		text-align: center;
	}
	</style>
</head>

<body>

	<CENTER>

		<?php
		if (
			isset($_GET['pkname']) &&
			isset($_GET['id']) &&
			isset($_GET['table'])
		) {
			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$table = $_GET['table'];
			$edit_q = mysqli_query($link, 'select * from ' . $table . ' where ' . $pkname . ' = ' . $id . ' LIMIT 1');
			$edit_r = mysqli_fetch_object($edit_q);
		?>
		<h2><?php echo $edit_r->title ?>
		</h2>
		<hr />
		<?php echo $edit_r->content ?>



		<?php
		} else {
			echo "<h2> 404 </h2> <h4>Could not find that</h4> ";
		}
		?>

	</CENTER>

</body>

</html>