<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Map of New Paltz Historic Sites</title>
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,100italic,300italic,400italic,500italic,500,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            header{
                background-color: #fff;
                padding: 10px 0;
                box-shadow: 0 0 5px #ccc;
                border-bottom: 1px solid black;
            }
            .navbar{
                width: 100%;
                margin: auto;
                max-width: 90vw;
                display: flex;
                flex-direction: row;
            }
            .navbar-left{
                justify-content: flex-start;
                width: 100%;
		        cursor: pointer;
            }
	        .navbar-left a{
		        text-decoration: none;
		        color: black;
	        }
            .navbar-right{
                justify-content: flex-end;
                display: flex;
                text-align: center;
                width: 100%;
                align-items: center;
                flex-direction: row;
            }
            .navbar-right ul{
                display: flex;
                flex-direction: row;
                list-style: none;
                text-decoration: none;
                padding: 0 10px;
            }
            .navbar-right ul li{
                padding: 0 10px;
            }
            .navbar-right ul li a{
                text-decoration: none;
                color: #000;
                border-radius: 12px;
                border: 2px solid black;
                padding: 5px 10px;
                font-family: 'Roboto';
                font-weight: 700;
            }
            .navbar-right ul li a:hover{
                background-color: #C0C0C0;
            }
            #login-out{
                border: 2px solid #C0C0C0;
                color: #C0C0C0;
            }
            #login-out:hover{
                background-color: white;
            }
        </style>

        <header>
            <div class="navbar">
                <div class="navbar-left">
                    <h3 style="font-family: 'Roboto'; font-weight: 700;"><a href="map.php" target="BOT">New Paltz, NY</a></h3>
                    <a href="map.php" target="BOT" style="font-family: 'Roboto';">Historic Property Information</a>
                </div>
                <div class="navbar-right">
                    <?php 
                        include 'session.php';
                        include 'config.php';

                        // php if statement to check if user is logged in

                        if (isset($_SESSION['username'])) {
                            echo '<div class="links-bar">
                            <div class="links">
                                <ul>
                                    <li><a href="map.php" target="BOT">Map</a></li>
                                    <li><a href="search-page.php" target="BOT">TextSearch</a></li>
                                    <li><a href="search_map.htm" target="BOT">MapSearch</a></li>
                                    <li><a href="index-body.php" target="BOT">About</a></li>
                                    <li><a href="admindash.php" target="BOT">Admin</a></li>
                                    <li><a href="logout.php" id="login-out" target="BOT" onclick="refreshFrame()">Logout</a></li>
                                </ul>
                            </div>
                        </div>';
                    
                        } else {
                            echo '<div class="links-bar">
                            <div class="links">
                                <ul>
                                    <li><a href="map.php" target="BOT">Map</a></li>
                                    <li><a href="search-page.php" target="BOT">TextSearch</a></li>
                                    <li><a href="search_map.htm" target="BOT">MapSearch</a></li>
                                    <li><a href="index-body.php" target="BOT">About</a></li>
                                    <li><a href="login.php" id="login-out"  target="BOT">Login</a></li>
                                </ul>
                        </div>';
                        }
                    ?>
                </div>
            </div>
        </header>
         
        
        </div>
    </head>
</html>

<script>
    // refresh page when user logs in or out
    function refreshPage() {
        window.location.reload();
    }
    // refresh frame when user logs in or out
    function refreshFrame() {
        window.parent.location.reload();
    }
</script>
