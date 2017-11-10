<?php 

	include_once './db_functions.php';
	
	$db = new DB_Functions();
	$response["data"]["feedback"]=array();

	  $result = $db->getFeedbacks();
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($result)){
				$feedback=array();
				$feedback["date"]=$row["date"];
				$feedback["description"]=$row["description"];
				$feedback["name"]=$row["name"];
				$feedback["phone"]=$row["phone"];
                                $feedback["email"]=$row["email"];
				array_push($response["data"]["feedback"],$feedback);
				
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
