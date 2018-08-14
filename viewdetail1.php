<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");
if($db){


       $usernm = mysqli_real_escape_string($db,$_POST['username']);

       

$sql = "SELECT DESIGNATION_ID,
PAY_STATUS,
PAY_STA_CHG_DT
,INCREMENT_STATUS
,BANK_AC_NO
,HANDICAPPED_FLAG
,USER_ID_CREATED
,CREATED_SITE_ID
,CREATED_TIME_STAMP
,USER_ID_MODIFIED
,MODIFIED_SITE_ID
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