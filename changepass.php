<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");

if(isset($_POST['answer'])){

       $epid = mysqli_real_escape_string($db,$_POST['empid']);

       $ans  = mysqli_real_escape_string($db,$_POST['answer']);

       $pas  = mysqli_real_escape_string($db,$_POST['password']);

	   
	   
$sql = "UPDATE userlogin SET Password='$pas' WHERE employeeid='$epid' AND answer='$ans'";

			

        if($db->query($sql) === TRUE){

			

       print "Password Changed"; 

		}
		else{

		echo 'Incorrect security answer';

		}

}else{

	echo 'fail';

}



?>