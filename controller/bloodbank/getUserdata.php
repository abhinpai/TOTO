<?php 

	include_once './db_functions.php';
	
	$db = new DB_Functions();
	$response["data"]["user"]=array();

	  $result = $db->getUserdata();
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($result)){
				$userdata=array();
				$userdata["name"]=$row["name"];
				$userdata["phone"]=$row["phone"];
                $$userdata["email"]=$row["email"];
				$$userdata["place"]=$row["place"];
				array_push($response["data"]["user"],$userdata);
				
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
