<?php 

	include_once './db_functions.php';

       $place=$_REQUEST["name"];
	
	$db = new DB_Functions();
	$response["data"]["hospital"]=array();

	  $Result = $db->searchHospitals($place);
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($Result)){
				$hospital=array();
                                $hospital["id"]=$row["hospital_id"];
				$hospital["place"]=$row["place"];
				$hospital["name"]=$row["name"];
				$hospital["phone"]=$row["phone"];
                                $hospital["email"]=$row["email"];
				array_push($response["data"]["hospital"],$hospital);
				
			}
			echo json_encode($response);
			exit(0);
	  }
	  else{
		        $response["status"]["code"]=0;
			$response["status"]["message"]="No details"; 
			echo json_encode($response);
			exit(0);
	  }
?>
