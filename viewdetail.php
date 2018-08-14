<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");
if($db){


       $usernm = mysqli_real_escape_string($db,$_POST['username']);

       

$sql = "SELECT EMP_NUM,
EMP_FIRST_NAME,
EMP_MIDDLE_NAME
,EMP_LAST_NAME
,EMP_SEX_CODE
,EMP_BIRTH_DATE
,COMP_JOINING_DATE
,PRES_LOCATION_CODE
,EMP_STATUS
,CADRE
,STAFF_CODE
 FROM pay_emp_mast WHERE  EMP_NUM ='$usernm'";



			

        if ($resul=mysqli_query($db,$sql))
  {
  // Fetch one and one row
  while ($row=mysqli_fetch_row($resul))
    {
    echo $row[0].";".$row[1].";".$row[2].";".$row[3].";".$row[4].";".$row[5].";".$row[6].";".$row[7].";".$row[8].";".$row[9].";".$row[10].";";
    }
  // Free result set
  mysqli_free_result($resul);
}
    else
        echo "Problem Occured";
}



else{
	echo "connection problem";
}


?>