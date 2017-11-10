<?php 

        $id = $_REQUEST['id']; 
	
   
	include_once './db_functions.php';
	
	$db = new DB_Functions();
	
		$result = $db->deleteBloodBank($id);
		if($result){
			$response["status"]["code"]=1;
			$response["status"]["message"]="Blood Bank information Deleted";
			echo json_encode($response);
			exit(0);
			
		}else{
			$response["status"]["code"]=0;
			$response["status"]["message"]="Details Not Deleted";
			echo json_encode($response);
			exit(0);
		}
	
?>
