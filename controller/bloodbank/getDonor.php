<?php 

	include_once './db_functions.php';
	
	$db = new DB_Functions();
	$response["data"]["donor"]=array();

	  $result = $db->getBDonor();
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($result)){
				$donor=array();
				$donor["id"]=$row["donor_id"];
				$donor["place"]=$row["place"];
				$donor["group"]=$row["blood_group"];
				$donor["name"]=$row["name"];
				$donor["phone"]=$row["phone"];
                $donor["email"]=$row["email"];
				array_push($response["data"]["donor"],$donor);
				
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
