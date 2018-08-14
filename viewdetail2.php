<?php

$db = mysqli_connect("localhost", "database_name", "database_password", "table_name");
if($db){


       $usernm = mysqli_real_escape_string($db,$_POST['username']);

       

$sql = "SELECT  MODIFIED_TIME_STAMP,
	MARITAL_STATUS,
	PAY_MODE,
	EMP_CATEGORY,
	WASHING_ALLOW_FLAG,
	EMP_PAN_NO,
	BANK_CODE,
	BRANCH_CODE,
	RETIREMENT_DATE,
	CONV_ALLOW_FLAG,
	EMP_TYPE
 
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