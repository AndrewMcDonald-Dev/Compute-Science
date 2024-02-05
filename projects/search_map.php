<?php
  // Include the config file
  require_once('config.php');

  // Get the keyword from the user
  $keyword = $_GET['keyword'];

  // Create the SQL query
  $sql = "SELECT * FROM a_all_items WHERE title LIKE '%$keyword%'";

  // Connect to the database
  $db = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);

  // Check connection
  if ($db->connect_error) {
    die("ERROR: Could not connect. " . $db->connect_error);
  }

  // Execute the query
  $result = $db->query($sql);

  // Check if the query was successful
  if ($result) {
    // Get the results
    $rows = $result->fetch_all(MYSQLI_ASSOC);

    // Loop through the results and print them out
    foreach ($rows as $row) {
      echo $row['cid'] . ' | ';
      echo $row['title'] . '<br>';
    }
  } else {
    echo 'No results found.';
  }

  // Close the database connection
  $db->close();
  ?>