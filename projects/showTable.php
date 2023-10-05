<?php
    // Initialize the session
    session_start();

    // Access Database
    require 'config.php';

    $allusers_q = mysqli_query($link, 'select `title` from a_items;');
?> 

<html>
<head>
    <title>Pages</title>
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css"> -->
    <style type="text/css">
        body{ font: 14px sans-serif; text-align: center; }
    </style>
</head>
<body>

<CENTER>

                <tr style="border-bottom: 1px solid">
                    <td>
  			<!--<a href=<?php //echo hrefFix_two($pid, "uid"); ?>>-->
  				 <?php echo $users_t_vals->id; ?>
                        <!--</a>-->
                    </td>
                    <td>
			<a href=<?php echo hrefFix_two($pid, "username"); ?>>  
                    		<?php echo $users_t_vals->username; ?>
                        </a>
                    </td>
                    <td>
                        <a href=<?php echo hrefFix_two($pid, "password"); ?>>
                        	<?php echo $users_t_vals->password; ?>
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