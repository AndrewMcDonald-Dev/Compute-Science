<?php
    // Initialize the session
    session_start();

    // Access Database
    require 'config.php';

    //$myask = "SELECT 'id', 'title', 'fulltext' FROM jos_content;";

    $myask = "SELECT `title` FROM jos_content";

    $allitems_q = mysqli_query($link, $myask);
?> 

<html>
<body>

<CENTER>

<?php 

// use SESSION to automatically DETECT username

    if(!empty($_SESSION["username"]))
    {
        echo "<h4>Hello <font color=\"red\">";
        echo htmlspecialchars($_SESSION["username"]); 
        echo "</font>!";
    }
    if(isset($_GET['msg']))
    {
        echo $_GET['msg'];
    }
?>

    <header id="admin-dashboard"> Admin Dashboard </header>

	<div class="search-bar">
		<input type="text" placeholder="Search.."><button id="search-btn">Search</button>
	</div>
        <table border="0" cellpadding ="5" style="border-collapse: collapse; ">
            <tbody id="table-body">
                <tr style="border-bottom: 1px solid">
                    <th>Title</th>
                </tr>

<?php

// DISPLAY cell content of each column and each row
//-------------------------------------------------

        while($users_t_vals = mysqli_fetch_object($allitems_q))
        { 
?>

                <tr style="border-bottom: 1px solid">
                    <td>
			<a href="/p/s23-06/v1/output_html/<?php echo str_replace(" ", "-", strtolower($users_t_vals->title)) ?>">  
                    		<?php echo $users_t_vals->title; ?>
                        </a>
                    </td>
                </tr>
<?php
        }
?>

            </tbody>
        </table>
  
<p> <br>
</CENTER>

</body>
</html>