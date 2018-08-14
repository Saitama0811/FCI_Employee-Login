<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");

if(isset($_POST['empid'])){

       $epid = mysqli_real_escape_string($db,$_POST['empid']);

       $nam  = mysqli_real_escape_string($db,$_POST['name']);

       $emai  = mysqli_real_escape_string($db,$_POST['email']);

       $phon   = mysqli_real_escape_string($db,$_POST['phone']);

       $pas  = mysqli_real_escape_string($db,$_POST['pass']);
	   
	   $ques   = mysqli_real_escape_string($db,$_POST['question']);

       $ans  = mysqli_real_escape_string($db,$_POST['answer']);
	   
	   
	   
$sql = "INSERT INTO userlogin(employeeid, name, Email, Password, Phone, question, answer) VALUES('$epid', '$nam', '$emai', '$pas', '$phon', '$ques', '$ans')";

			

        if($db->query($sql) === TRUE){

			

       print "User Registered"; 

		}
		else{

		echo 'Employee Already Registered';

		}

}else{

	echo 'Network Error';

}



?>