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
			isset($_GET['column']) &&
			isset($_GET['table']) &&
			!isset($_POST['update'])
		) {
			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$column = $_GET['column'];
			$table = $_GET['table'];
			$edit_q = mysqli_query($link, 'select ' . $column . ' from ' . $table . ' where ' . $pkname . ' = ' . $id . ' LIMIT 1');
			$edit_r = mysqli_fetch_object($edit_q);

			echo '<h2> EDITING ' . $column . ' IN ' . $table . ' FOR ' . $pkname . ' ' . $id . '</h2>';
		?>
			<form method="POST" action="editCelli.php?pkname=<?php echo $pkname; ?>&id=<?php echo $id; ?>&column=<?php echo $column; ?>&table=<?php echo $table; ?>">
				<input type="text" name="modVal" size="30" value="<?php echo $edit_r->$column; ?>" /><br />

				<input type="submit"> <input type="hidden" name="update">

			</form>
		<?php
		}

		if (
			isset($_GET['pkname']) &&
			isset($_GET['id']) &&
			isset($_GET['column']) &&
			isset($_GET['table']) &&
			isset($_POST['modVal']) &&
			isset($_POST['update'])
		) {
			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$column = $_GET['column'];
			$table = $_GET['table'];
			$modVal = $_POST['modVal'];


			if (mysqli_query($link, 'UPDATE ' . $table . ' SET ' . $column . '= "' . $modVal . '" WHERE ' . $pkname . ' = ' . $id . ";")) {
				echo "Record updated!\nRedirecting To Previous Page!";
				header("location: ViewEditItems.php?msg=Record updated successfully");
			} else {
				echo 'UPDATE ' . $table . ' SET ' . $column . '= "' . $modVal . '" WHERE ' . $pkname . ' = ' . $id . ";\n";
				echo "Record FUBAR: " . mysqli_error($link);
			}
		}

		if (
			isset($_GET['ftable']) &&
			isset($_GET['fkname']) &&
			isset($_GET['fid']) &&
			isset($_GET['fcol']) &&
			isset($_GET['pkname']) &&
			isset($_GET['id']) &&
			isset($_GET['column']) &&
			isset($_GET['table']) &&
			!isset($_POST['update2'])
		) {
			$ftable = $_GET['ftable'];
			$fkname = $_GET['fkname'];
			$fid = $_GET['fid'];
			$fcol = $_GET['fcol'];
			$edit_q0 = mysqli_query($link, 'select * from ' . $ftable . ';');
			//$edit_q1 = mysqli_query($link, 'select * from ' . $ftable . ' where ' . $fkname . ' = ' . $fid . ' LIMIT 1;');
			//$edit_r1 = mysqli_fetch_object($edit_q0);

			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$column = $_GET['column'];
			$table = $_GET['table'];

		?>
			<form method="POST" action="editCelli.php?pkname=<?php echo $pkname; ?>&id=<?php echo $id; ?>&column=<?php echo $column; ?>&table=<?php echo $table; ?>">
				<label for="fkvals">Choose a
					new value:</label>
				<select id="fkvals" name="modVal">
					<?php
					while ($fk_t_vals = mysqli_fetch_object($edit_q0)) {

					?>

						<option value=<?php echo $fk_t_vals->$fkname ?>>
							<?php echo $fk_t_vals->$fcol ?>
						</option>

					<?php
					}
					?>
				</select>
				<input type="submit"> <input type="hidden" name="update">
			</form>
		<?php

		}
		?>

	</CENTER>

</body>

</html>