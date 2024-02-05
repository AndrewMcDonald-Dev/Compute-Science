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
			$item_query = mysqli_query($link, 'select * from ' . $table . ' where ' . $pkname . ' = ' . $id . ' LIMIT 1');
			$item = mysqli_fetch_object($item_query);
		?>
			<h2><?php echo $item->title ?>
			</h2>
			<hr>
			<?php
			if (strlen($item->main_content) > 0) {
				echo $item->main_content;
				echo "<hr>";
			}
			if (strlen($item->report) > 0) {
				echo $item->report;
				echo "<hr>";
			}
			if ($item->data != "{}") {
				$data = json_decode($item->data, true);
			?>
				<table>
					<tr>
						<td>Tax parcel Number
						</td>
						<td><?php echo $data["Tax parcel Number"] ?>
						</td>
					</tr>
					<tr>
						<td>Street number</td>
						<td><?php echo $data["Street number"] ?>
						</td>
					</tr>
					<tr>
						<td>Street name</td>
						<td><?php echo $data["Street Name"] ?>
						</td>
					</tr>
					<tr>
						<td>Present Use</td>
						<td><?php echo $data["Present Use"] ?>
						</td>
					</tr>
					<tr>
						<td>Historic Function
						</td>
						<td><?php echo $data["Historic Function"] ?>
						</td>
					</tr>
					<tr>
						<td>Historic Use</td>
						<td><?php echo $data["Historic Use"] ?>
						</td>
					</tr>
					<tr>
						<td>Date of Construction
						</td>
						<td><?php echo $data["Date of Construction"] ?>
						</td>
					</tr>
					<tr>
						<td>Dates of alteration
						</td>
						<td><?php echo $data["Dates of alteration"] ?>
						</td>
					</tr>
					<tr>
						<td>Style</td>
						<td><?php echo $data["Style"] ?>
						</td>
					</tr>
					<tr>
						<td>No. of Stories</td>
						<td><?php echo $data["No. of Stories"] ?>
						</td>
					</tr>
					<tr>
						<td>Construction Method
						</td>
						<td><?php echo $data["Construction method"] ?>
						</td>
					</tr>
					<tr>
						<td>Exterior material:
							historic 1</td>
						<td><?php echo $data["Exterior material: historic 1"] ?>
						</td>
					</tr>
					<tr>
						<td>Exterior material:
							historic 2</td>
						<td><?php echo $data["Exterior material: historic 2"] ?>
						</td>
					</tr>
					<tr>
						<td>Exterior material:
							present</td>
						<td><?php echo $data["Exterior material: present"] ?>
						</td>
					</tr>
					<tr>
						<td>Roof Type</td>
						<td><?php echo $data["Roof Type"] ?>
						</td>
					</tr>
					<tr>
						<td>Foundation Material
						</td>
						<td><?php echo $data["Foundation Material"] ?>
						</td>
					</tr>
					<tr>
						<td>Additions</td>
						<td><?php echo $data["Additions"] ?>
						</td>
					</tr>
					<tr>
						<td>Porch location 1
						</td>
						<td><?php echo $data["Porch location 1"] ?>
						</td>
					</tr>
					<tr>
						<td>Porch condition</td>
						<td><?php echo $data["Porch condition"] ?>
						</td>
					</tr>
					<tr>
						<td>Window condition
						</td>
						<td><?php echo $data["Window condition"] ?>
						</td>
					</tr>
					<tr>
						<td>Overall integrity
						</td>
						<td><?php echo $data["Overall integrity"] ?>
						</td>
					</tr>
					<tr>
						<td>Contributing</td>
						<td><?php echo $data["Contributing"] ?>
						</td>
					</tr>
					<tr>
						<td>Endangered</td>
						<td><?php echo $data["Endangered"] ?>
						</td>
					</tr>
					<tr>
						<td>Distinctive features
						</td>
						<td><?php echo $data["Distinctive features"] ?>
						</td>
					</tr>
					<tr>
						<td>Notes</td>
						<td><?php echo $data["Notes"] ?>
						</td>
					</tr>
					<tr>
						<td>Outbldg 1 type</td>
						<td><?php echo $data["Outbldg 1 type"] ?>
						</td>
					</tr>
					<tr>
						<td>Outbldg 1 date</td>
						<td><?php echo $data["Outbldg 1 date"] ?>
						</td>
					</tr>
					<tr>
						<td>Outbldg 1
							construction method
						</td>
						<td><?php
							if (array_key_exists("Outbldg 1 construction method", $data)) {
								echo $data["Outbldg 1 construction method"];
							} ?>
						</td>
					</tr>
					<tr>
						<td>Outbldg 1
							distinctive features
						</td>
						<td><?php echo $data["Outbldg 1 distinctive features"] ?>
						</td>
					</tr>
					<tr>
						<td>Outbldg 2 type</td>
						<td><?php echo $data["Outbldg 2 type"] ?>
						</td>
					</tr>
					<tr>
						<td>Outbldg 2 date</td>
						<td><?php echo $data["Outbldg 2 date"] ?>
						</td>
					</tr>
					<tr>
						<td>Landscape features
						</td>
						<td><?php echo $data["Landscape features"] ?>
						</td>
					</tr>
				</table>
				<hr>
			<?php
			}
			if (strlen($item->document_links) > 0) {
				echo $item->document_links;
				echo "<hr>";
			}
			if (strlen($item->picture_links) > 0) {
				echo $item->picture_links;
				echo "<hr>";
			}
			?>



		<?php
			if ($item->lat != 0 && $item->lng != 0) {
				require 'Simple_Map.php';
			}
		} else {
			echo "<h2> 404 </h2> <h4>Could not find that</h4> ";
		}
		?>

	</CENTER>

</body>

</html>