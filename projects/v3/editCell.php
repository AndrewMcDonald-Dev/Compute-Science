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

			$data = json_decode($edit_r->data, true);

			echo '<h2> EDITING ' . $table . ' FOR ' . $pkname . ' ' . $id . '</h2>';
		?>
		<table>
			<form method="POST"
				action="editCell.php?pkname=<?php echo $pkname; ?>&id=<?php echo $id; ?>&table=<?php echo $table; ?>">
				<tr>
					<td>Title</td>
					<td>
						<input
							type="text"
							name="mod_title"
							size="30"
							value="<?php echo $edit_r->title; ?>" />
					</td>
				</tr>
				<tr>
					<td>Description</td>
					<td>
						<textarea
							name="mod_content"
							cols="30"
							rows="10"><?php echo $edit_r->main_content ?></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Report
					</td>
					<td>
						<textarea
							name="mod_report"
							cols="30"
							rows="10"><?php echo $edit_r->report ?></textarea>
					</td>
				</tr>
				<tr>
					<td>Document Links
					</td>
					<td>
						<textarea
							name="mod_document"
							cols="30"
							rows="10"><?php echo $edit_r->document_links ?></textarea>
					</td>
				</tr>
				<tr>
					<td>Picture Links
					</td>
					<td>
						<textarea
							name="mod_picture"
							cols="30"
							rows="10"><?php echo $edit_r->picture_links ?></textarea>
					</td>
				</tr>
				<tr>
					<td>Latitude</td>
					<td>
						<input
							type="text"
							name="mod_lat"
							size="30"
							value="<?php echo $edit_r->lat; ?>" />
					</td>
				</tr>
				<tr>
					<td>Longitude</td>
					<td>
						<input
							type="text"
							name="mod_lng"
							size="30"
							value="<?php echo $edit_r->lng; ?>" />
					</td>
				</tr>
				<tr>
					<td>Tax parcel
						Number
					</td>
					<td>
						<input
							type="text"
							name="mod_tax"
							size="30"
							value="<?php 
							if (array_key_exists("Tax parcel Number", $data)){
								echo str_replace('"',"&quot;",$data["Tax parcel Number"]);  
							} 
							?>"/>
					</td>
				</tr>
				<tr>
					<td>Street number
					</td>
					<td>
						<input
							type="text"
							name="mod_street_num"
							size="30"
							value="<?php
								if (array_key_exists("Street number", $data)) {
									echo str_replace('"',"&quot;",$data["Street number"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Street name</td>
					<td>
						<input
							type="text"
							name="mod_street_name"
							size="30"
							value="<?php
								if (array_key_exists("Street Name", $data)) {
									echo str_replace('"',"&quot;",$data["Street Name"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Present Use</td>
					<td>
						<input
							type="text"
							name="mod_present_use"
							size="30"
							value="<?php
								if (array_key_exists("Present Use", $data)) {
									echo str_replace('"',"&quot;",$data["Present Use"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Historic
						Function
					</td>
					<td>
						<input
							type="text"
							name="mod_historic_func"
							size="30"
							value="<?php
								if (array_key_exists("Historic Function", $data)) {
									echo str_replace('"',"&quot;",$data["Historic Function"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Historic Use
					</td>
					<td>
						<input
							type="text"
							name="mod_historic_use"
							size="30"
							value="<?php
								if (array_key_exists("Historic Use", $data)) {
									echo str_replace('"',"&quot;",$data["Historic Use"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Date of
						Construction
					</td>
					<td>
						<input
							type="text"
							name="mod_construct_date"
							size="30"
							value="<?php
								if (array_key_exists("Date of Construction", $data)) {
									echo str_replace('"',"&quot;",$data["Date of Construction"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Dates of
						alteration
					</td>
					<td>
						<input
							type="text"
							name="mod_alteration_date"
							size="30"
							value="<?php
								if (array_key_exists("Dates of alteration", $data)) {
									echo str_replace('"',"&quot;",$data["Dates of alteration"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Style</td>
					<td>
						<input
							type="text"
							name="mod_style"
							size="30"
							value="<?php
								if (array_key_exists("Style", $data)) {
									echo str_replace('"',"&quot;",$data["Style"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>No. of Stories
					</td>
					<td>
						<input
							type="text"
							name="mod_stories"
							size="30"
							value="<?php
								if (array_key_exists("No. of Stories", $data)) {
									echo str_replace('"',"&quot;",$data["No. of Stories"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Construction
						Method
					</td>
					<td>
						<input
							type="text"
							name="mod_construct_method"
							size="30"
							value="<?php
								if (array_key_exists("Construction method", $data)) {
									echo str_replace('"',"&quot;",$data["Construction method"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Exterior
						material:
						historic 1</td>
					<td>
						<input
							type="text"
							name="mod_exterior_historic_1"
							size="30"
							value="<?php
								if (array_key_exists("Exterior material: historic 1", $data)) {
									echo str_replace('"',"&quot;",$data["Exterior material: historic 1"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Exterior
						material:
						historic 2</td>
					<td>
						<input
							type="text"
							name="mod_exterior_historic_2"
							size="30"
							value="<?php
								if (array_key_exists("Exterior material: historic 2", $data)) {
									echo str_replace('"',"&quot;",$data["Exterior material: historic 2"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Exterior
						material:
						present</td>
					<td>
						<input
							type="text"
							name="mod_exterior_present"
							size="30"
							value="<?php
								if (array_key_exists("Exterior material: present", $data)) {
									echo str_replace('"',"&quot;",$data["Exterior material: present"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Roof Type</td>
					<td>
						<input
							type="text"
							name="mod_roof_type"
							size="30"
							value="<?php
								if (array_key_exists("Roof Type", $data)) {
									echo str_replace('"',"&quot;",$data["Roof Type"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Foundation
						Material
					</td>
					<td>
						<input
							type="text"
							name="mod_foundation_material"
							size="30"
							value="<?php
								if (array_key_exists("Foundation Material", $data)) {
									echo str_replace('"',"&quot;",$data["Foundation Material"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Additions</td>
					<td>
						<input
							type="text"
							name="mod_additions"
							size="30"
							value="<?php
								if (array_key_exists("Additions", $data)) {
									echo str_replace('"',"&quot;",$data["Additions"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Porch location 1
					</td>
					<td>
						<input
							type="text"
							name="mod_porch_location"
							size="30"
							value="<?php
								if (array_key_exists("Porch location 1", $data)) {
									echo str_replace('"',"&quot;",$data["Porch location 1"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Porch condition
					</td>
					<td>
						<input
							type="text"
							name="mod_porch_condition"
							size="30"
							value="<?php
								if (array_key_exists("Porch condition", $data)) {
									echo str_replace('"',"&quot;",$data["Porch condition"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Window condition
					</td>
					<td>
						<input
							type="text"
							name="mod_window_condition"
							size="30"
							value="<?php
								if (array_key_exists("Window condition", $data)) {
									echo str_replace('"',"&quot;",$data["Window condition"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Overall
						integrity
					</td>
					<td>
						<input
							type="text"
							name="mod_integrity"
							size="30"
							value="<?php
								if (array_key_exists("Overall integrity", $data)) {
									echo str_replace('"',"&quot;",$data["Overall integrity"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Contributing
					</td>
					<td>
						<input
							type="text"
							name="mod_contributing"
							size="30"
							value="<?php
								if (array_key_exists("Contributing", $data)) {
									echo str_replace('"',"&quot;",$data["Contributing"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Endangered</td>
					<td>
						<input
							type="text"
							name="mod_endangered"
							size="30"
							value="<?php
								if (array_key_exists("Endangered", $data)) {
									echo str_replace('"',"&quot;",$data["Endangered"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Distinctive
						features
					</td>
					<td>
						<input
							type="text"
							name="mod_distinctive_features"
							size="30"
							value="<?php
								if (array_key_exists("Distinctive features", $data)) {
									echo str_replace('"',"&quot;",$data["Distinctive features"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Notes</td>
					<td>
						<input
							type="text"
							name="mod_notes"
							size="30"
							value="<?php
								if (array_key_exists("Notes", $data)) {
									echo str_replace('"',"&quot;",$data["Notes"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Outbldg 1 type
					</td>
					<td>
						<input
							type="text"
							name="mod_outbldg_type_1"
							size="30"
							value="<?php
								if (array_key_exists("Outbldg 1 type", $data)) {
									echo str_replace('"',"&quot;",$data["Outbldg 1 type"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Outbldg 1 date
					</td>
					<td>
						<input
							type="text"
							name="mod_outbldg_date_1"
							size="30"
							value="<?php
								if (array_key_exists("Outbldg 1 date", $data)) {
									echo str_replace('"',"&quot;",$data["Outbldg 1 date"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Outbldg 1
						construction
						method
					</td>
					<td>
						<input
							type="text"
							name="mod_outbldg_construction_1"
							size="30"
							value="<?php
								if (array_key_exists("Outbldg 1 construction method", $data)) {
									echo str_replace('"',"&quot;",$data["Outbldg 1 construction method"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Outbldg 1
						distinctive
						features
					</td>
					<td>
						<input
							type="text"
							name="mod_outbldg_distinctive_1"
							size="30"
							value="<?php
								if (array_key_exists("Outbldg 1 distinctive features", $data)) {
									echo str_replace('"',"&quot;",$data["Outbldg 1 distinctive features"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Outbldg 2 type
					</td>
					<td>
						<input
							type="text"
							name="mod_outbldg_type_2"
							size="30"
							value="<?php
								if (array_key_exists("Outbldg 2 type", $data)) {
									echo str_replace('"',"&quot;",$data["Outbldg 2 type"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Outbldg 2 date
					</td>
					<td>
						<input
							type="text"
							name="mod_outbldg_date_2"
							size="30"
							value="<?php
								if (array_key_exists("Outbldg 2 date", $data)) {
									echo str_replace('"',"&quot;",$data["Outbldg 2 date"]);
								}?>" />
					</td>
				</tr>
				<tr>
					<td>Landscape
						features
					</td>
					<td>
						<input
							type="text"
							name="mod_landscape_features"
							size="30"
							value="<?php
								if (array_key_exists("Landscape features", $data)) {
									echo str_replace('"',"&quot;",$data["Landscape features"]);
								}?>" />
					</td>
				</tr>
				<input type="submit">
				<input type="hidden"
					name="update">

			</form>
		</table>
		<?php
		}
		if (
			// For grabbing item and table
			isset($_GET['pkname']) &&
			isset($_GET['id']) &&
			isset($_GET['table']) &&
			// For fields for the item
			isset($_POST['mod_title']) &&
			isset($_POST['mod_content']) &&
			isset($_POST['mod_report']) &&
			isset($_POST['mod_lat']) &&
			isset($_POST['mod_lng']) &&
			// For the data field
			isset($_POST['mod_street_num']) &&
			isset($_POST['mod_street_name']) &&
			isset($_POST['mod_present_use']) &&
			isset($_POST['mod_historic_func']) &&
			isset($_POST['mod_historic_use']) &&
			isset($_POST['mod_construct_date']) &&
			isset($_POST['mod_alteration_date']) &&
			isset($_POST['mod_style']) &&
			isset($_POST['mod_stories']) &&
			isset($_POST['mod_construct_method']) &&
			isset($_POST['mod_exterior_historic_1']) &&
			isset($_POST['mod_exterior_historic_2']) &&
			isset($_POST['mod_exterior_present']) &&
			isset($_POST['mod_roof_type']) &&
			isset($_POST['mod_foundation_material']) &&
			isset($_POST['mod_additions']) &&
			isset($_POST['mod_porch_location']) &&
			isset($_POST['mod_porch_condition']) &&
			isset($_POST['mod_window_condition']) &&
			isset($_POST['mod_integrity']) &&
			isset($_POST['mod_contributing']) &&
			isset($_POST['mod_endangered']) &&
			isset($_POST['mod_distinctive_features']) &&
			isset($_POST['mod_notes']) &&
			isset($_POST['mod_outbldg_type_1']) &&
			isset($_POST['mod_outbldg_date_1']) &&
			isset($_POST['mod_outbldg_construction_1']) &&
			isset($_POST['mod_outbldg_distinctive_1']) &&
			isset($_POST['mod_outbldg_type_2']) &&
			isset($_POST['mod_outbldg_date_2']) &&
			isset($_POST['mod_landscape_features']) &&
			isset($_POST['update'])
		) {
			// For grabbing item and table
			$pkname = $_GET['pkname'];
			$id = $_GET['id'];
			$table = $_GET['table'];
			// For fields for the item
			$mod_title = $_POST['mod_title'];
			$mod_content = $_POST['mod_content'];
			$mod_report = $_POST['mod_report'];
			$mod_lat = $_POST['mod_lat'];
			$mod_lng = $_POST['mod_lng'];
			// For the data field
			$mod_street_num = addslashes($_POST['mod_street_num']);
			$mod_street_name = addslashes($_POST['mod_street_name']);
			$mod_present_use = addslashes($_POST['mod_present_use']);
			$mod_historic_func = addslashes($_POST['mod_historic_func']);
			$mod_historic_use = addslashes($_POST['mod_historic_use']);
			$mod_construct_date = addslashes($_POST['mod_construct_date']);
			$mod_alteration_date = addslashes($_POST['mod_alteration_date']);
			$mod_style = addslashes($_POST['mod_style']);
			$mod_stories = addslashes($_POST['mod_stories']);
			$mod_construct_method = addslashes($_POST['mod_construct_method']);
			$mod_exterior_historic_1 = addslashes($_POST['mod_exterior_historic_1']);
			$mod_exterior_historic_2 = addslashes($_POST['mod_exterior_historic_2']);
			$mod_exterior_present = addslashes($_POST['mod_exterior_present']);
			$mod_roof_type = addslashes($_POST['mod_roof_type']);
			$mod_foundation_material = addslashes($_POST['mod_foundation_material']);
			$mod_additions = addslashes($_POST['mod_additions']);
			$mod_porch_locations = addslashes($_POST['mod_porch_location']);
			$mod_porch_condition = addslashes($_POST['mod_porch_condition']);
			$mod_window_condition = addslashes($_POST['mod_window_condition']);
			$mod_integrity = addslashes($_POST['mod_integrity']);
			$mod_contributing = addslashes($_POST['mod_contributing']);
			$mod_endangered = addslashes($_POST['mod_endangered']);
			$mod_distinctive_features = addslashes($_POST['mod_distinctive_features']);
			$mod_notes = addslashes($_POST['mod_notes']);
			$mod_outbldg_type_1 = addslashes($_POST['mod_outbldg_type_1']);
			$mod_outbldg_date_1 = addslashes($_POST['mod_outbldg_date_1']);
			$mod_outbldg_construction_1 = addslashes($_POST['mod_outbldg_construction_1']);
			$mod_outbldg_distinctive_1 = addslashes($_POST['mod_outbldg_distinctive_1']);
			$mod_outbldg_type_2 = addslashes($_POST['mod_outbldg_type_2']);
			$mod_outbldg_date_2 = addslashes($_POST['mod_outbldg_date_2']);
			$mod_outbldg_landscape_features = addslashes($_POST['mod_landscape_features']);

			$mod_data = "";

			if (
				$mod_street_num == "" && $mod_street_name == "" && $mod_present_use == "" && $mod_historic_func == "" &&
				$mod_historic_use == "" && $mod_construct_date == "" && $mod_alteration_date == "" && $mod_style == "" &&
				$mod_stories == "" && $mod_construct_method == "" && $mod_exterior_historic_1 == "" && $mod_exterior_historic_2 == "" &&
				$mod_exterior_present == "" && $mod_roof_type == "" && $mod_foundation_material == "" &&
				$mod_additions == "" && $mod_porch_locations == "" && $mod_porch_condition == "" && $mod_window_condition == "" &&
				$mod_integrity == "" && $mod_contributing == "" && $mod_endangered == "" && $mod_distinctive_features == "" &&
				$mod_notes == "" && $mod_outbldg_type_1 == "" && $mod_outbldg_date_1 == "" && $mod_outbldg_construction_1 == "" &&
				$mod_outbldg_distinctive_1 == "" && $mod_outbldg_type_2 == "" && $mod_outbldg_date_2 == "" && $mod_outbldg_landscape_features == ""
			) {
				$mod_data = "{}";
			} else {
				$mod_data = '{"Street number":"' . $mod_street_num . '","Street Name":"' . $mod_street_name . '","Present Use":"' . $mod_present_use . '","Historic Function":"' . $mod_historic_func . '","Historic Use":"' . $mod_historic_use . '","Date of Construction":"' . $mod_construct_date . '","Dates of alteration":"' . $mod_alteration_date . '","Style":"' . $mod_style . '","No. of Stories":"' . $mod_stories . '","Construction method":"' . $mod_construct_method . '","Exterior material: historic 1":"' . $mod_exterior_historic_1 . '","Exterior material: historic 2":"' . $mod_exterior_historic_2 . '","Exterior material: present":"' . $mod_exterior_present . '","Roof Type":"' . $mod_roof_type . '","Foundation Material":"' . $mod_foundation_material . '","Additions":"' . $mod_additions . '","Porch location 1":"' . $mod_porch_locations . '","Porch condition":"' . $mod_porch_condition . '","Window condition":"' . $mod_window_condition . '","Overall integrity":"' . $mod_integrity . '","Contributing":"' . $mod_contributing . '","Endangered":"' . $mod_endangered . '","Distinctive features":"' . $mod_distinctive_features . '","Notes":"' . $mod_notes . '","Outbldg 1 type":"' . $mod_outbldg_type_1 . '","Outbldg 1 date":"' . $mod_outbldg_date_1 . '","Outbldg 1 construction method":"' . $mod_outbldg_construction_1 . '","Outbldg 1 distinctive features":"' . $mod_outbldg_distinctive_1 . '","Outbldg 2 type":"' . $mod_outbldg_type_2 . '","Outbldg 2 date":"' . $mod_outbldg_date_2 . '","Landscape features":"' . $mod_outbldg_landscape_features . '"}';
			}



			$query = "UPDATE a_all_items SET title=?, main_content=?, report=?, data=?, lat=?, lng=? WHERE cid = ?;";
			$stmt = mysqli_prepare($link, $query);
			if ($stmt) {

				mysqli_stmt_bind_param(
					$stmt,
					"ssssddi",
					$mod_title,
					$mod_content,
					$mod_report,
					$mod_data,
					$mod_lat,
					$mod_lng,
					$id

				);


				if (mysqli_stmt_execute($stmt)) {
					echo "Record updated!\nRedirecting To Previous Page!";
					header("location: dashboard.php?msg=Record updated successfully");
				} else {
					echo "\n" . 'UPDATE ' . $table . ' SET title="' . $mod_title . '", main_content="' . $mod_content . '", lat=' . $mod_lat . ', lng=' . $mod_lng . ',  report="' . $mod_report . '", data="' . $mod_data . '" WHERE ' . $pkname . '=' . $id . ";\n";
					echo "Record FUBAR: " . mysqli_error($link);
				}
			} else {
				echo "Hell hath no fury than a PHP coder scorned.";
			}
		}

		?>
	</CENTER>

</body>

</html>