<?php

// Include config file
include("session.php");
include("config.php");

if(isset($_POST['submit'])){
    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT * FROM a_users WHERE username = '$username' AND password = '$password'";
    $result = mysqli_query($link, $sql);

    if(mysqli_num_rows($result) == 1){
        $_SESSION['username'] = $username;
        echo '<script>parent.window.location.reload(true);</script>';
    }else{
        echo "Username or Password is incorrect";
    }
}



?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="container">
        <div class="login-card">
            <div class="card-top">
                <h1>Admin Login</h1>
            </div>
            <div class="admin-login">
                <form action="login.php" method="post">
                    <p>Username</p>
                    <input type="text" name="username" placeholder="Username">
                    <p>Password</p>
                    <input type="password" name="password" placeholder="Password">
                    <button type="submit" name="submit">Login</button>
                </form>
            </div>
            
        </div>
    </div>
    

</body>
</html>
<script>
    function refreshFrame() {
        window.parent.location.reload();
    }
   
</script>