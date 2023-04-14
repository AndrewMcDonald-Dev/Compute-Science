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
			!isset($_GET['ftable']) &&
			isset($_GET['pkname']) &&
			isset($_GET['id']) &&
			isset($_GET['table']) &&
			!isset($_POST['update'])
		) {
			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$table = $_GET['table'];
			$edit_q = mysqli_query($link, 'select * from ' . $table . ' where ' . $pkname . ' = ' . $id . ' LIMIT 1');
			$edit_r = mysqli_fetch_object($edit_q);

			echo '<h2> EDITING ' . $table . ' FOR ' . $pkname . ' ' . $id . '</h2>';
		?>
			<form method="POST" action="editCell.php?pkname=<?php echo $pkname; ?>&id=<?php echo $id; ?>&table=<?php echo $table; ?>">
				<input type="text" name="mod_title" size="30" value="<?php echo $edit_r->title; ?>" /><br />
				<textarea name="mod_content" cols="30" rows="10"><?php echo $edit_r->content ?></textarea><br />
				<input type="text" name="mod_lat" size="30" value="<?php echo $edit_r->lat; ?>" /><br />
				<input type="text" name="mod_lng" size="30" value="<?php echo $edit_r->lng; ?>" /><br />
				<input type="submit"> <input type="hidden" name="update">

			</form>
		<?php
		}

		if (
			isset($_GET['pkname']) &&
			isset($_GET['id']) &&
			isset($_GET['table']) &&
			isset($_POST['mod_title']) &&
			isset($_POST['mod_content']) &&
			isset($_POST['mod_lat']) &&
			isset($_POST['mod_lng']) &&
			isset($_POST['update'])
		) {
			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$table = $_GET['table'];
			$mod_title = $_POST['mod_title'];
			$mod_content = $_POST['mod_content'];
			$mod_lat = $_POST['mod_lat'];
			$mod_lng = $_POST['mod_lng'];

			// $stmt = mysqli_prepare($link, $query);

			$query = "UPDATE a_all_items SET title=?, content=?, lat=?, lng=? WHERE cid = ?;";
			$stmt = mysqli_prepare($link, $query);
			if ($stmt) {


				mysqli_stmt_bind_param(
					$stmt,
					"ssddi",
					$mod_title,
					$mod_content,
					$mod_lat,
					$mod_lng,
					$id

				);


				// if (mysqli_query($link, 'UPDATE ' . $table . ' SET title="' . $mod_title . '", content="' . $mod_content . '", lat=' . $mod_lat . ', lng=' . $mod_lng . ' WHERE ' . $pkname . '=' . $id . ";")) {
				if (mysqli_stmt_execute($stmt)) {
					echo "Record updated!\nRedirecting To Previous Page!";
					header("location: dashboard.php?msg=Record updated successfully");
				} else {
					echo "\n" . 'UPDATE ' . $table . ' SET title="' . $mod_title . '", content="' . $mod_content . '", lat=' . $mod_lat . ', lng=' . $mod_lng . ' WHERE ' . $pkname . '=' . $id . ";\n";
					echo "Record FUBAR: " . mysqli_error($link);
				}
			} else {
				echo "Hell hath no fury than a PHP coder scorned. T was here.";
			}
		}

		?>
	</CENTER>

</body>

</html>