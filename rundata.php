<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");
if($db){
if(isset($_POST['username'])){

       $usernm = mysqli_real_escape_string($db,$_POST['username']);

       $pas  = mysqli_real_escape_string($db,$_POST['password']);

$sql = "SELECT employeeid FROM userlogin WHERE  employeeid ='$usernm' AND Password ='$pas'";

$resul=mysqli_query($db,$sql);

			

        if(mysqli_num_rows($resul)>0){

			

       print "success"; 

		}else{

		echo'Wrong credentials';

		}

}else{

	echo 'fail';

}
}else{
	echo "connection problem";
}


?>