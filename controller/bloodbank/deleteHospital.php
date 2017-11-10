<?php 

        $id = $_REQUEST['id']; 
	
   
	include_once './db_functions.php';
	
	$db = new DB_Functions();
	
		$result = $db->deleteHospital($id);
		if($result){
			$response["status"]["code"]=1;
			$response["status"]["message"]="Hospital information Deleted";
			echo json_encode($response);
			exit(0);
			
		}else{
			$response["status"]["code"]=0;
			$response["status"]["message"]="Details Not Deleted";
			echo json_encode($response);
			exit(0);
		}
	
?>
