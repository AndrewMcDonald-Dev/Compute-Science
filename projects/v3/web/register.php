<?php
// Include config file
require_once "config.php";
 
// Define variables and initialize with empty values
$username = $password = $message = "";
 
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
 
    // Get username & password 
        $username = trim($_POST["UserName"]);
        $password = trim($_POST["MyPass"]);   
        
    // Prepare an insert statement
    $sql = "INSERT INTO a_users_nick (username, password) VALUES (?, ?)";
        
     // Use DB info in $link from config.php to construct MySQL message/command
        if($stmt = mysqli_prepare($link, $sql)){

            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "ss", $param_username, $param_password);
            
            // Set parameters
            $param_username = $username;
            $param_password = $password; 
            
            // Attempt to EXECUTE the prepared statement
            mysqli_stmt_execute($stmt);            

            // Close statement
            mysqli_stmt_close($stmt);
            $message = "Congratulations! You Have Successfully Register!";

        } else {
                 $message = "Problems with inserting to Database";            
        }

    // Close connection
    mysqli_close($link);
}
?>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<?php echo $message; ?>
 
</body>
</html>
