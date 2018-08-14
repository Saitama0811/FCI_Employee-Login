<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");
if($db){
if(isset($_POST['empid'])){

       $employeeid = mysqli_real_escape_string($db,$_POST['empid']);
	   
	   $email = mysqli_real_escape_string($db,$_POST['email']);

       $phone  = mysqli_real_escape_string($db,$_POST['phone']);

$sql = "SELECT question FROM userlogin WHERE  employeeid ='$employeeid' AND Email ='$email' AND Phone='$phone'";

$checkresult=mysqli_query($db,$sql);
if(mysqli_num_rows($checkresult)>0){


if ($resul=mysqli_query($db,$sql))
  {
  // Fetch one and one row
  while ($row=mysqli_fetch_row($resul))
    {
    echo $row[0];
    }
  // Free result set
  mysqli_free_result($resul);
}
    else
        echo "Problem Occured";
}

else{
echo "Wrong credentials";}

mysqli_close($db);

}
}else{
	echo "connection problem";
}


?>