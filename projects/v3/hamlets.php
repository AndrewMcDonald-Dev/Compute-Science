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
		$edit_q = mysqli_query($link, 'select * from a_all_items where cid = 1677 LIMIT 1');
		$edit_r = mysqli_fetch_object($edit_q);
		?>
		<h2><?php echo $edit_r->title ?>
		</h2>
		<hr />
		<?php echo $edit_r->content ?>



	</CENTER>

</body>

</html>