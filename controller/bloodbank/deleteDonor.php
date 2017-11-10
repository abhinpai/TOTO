<?php 

     $name = $_REQUEST['name']; 
	//$name='sathish';
   	include_once './db_functions.php';
	
	$db = new DB_Functions();
	
		$result = $db->deleteUser($name);
		if($result){
			$response["status"]["code"]=1;
			$response["status"]["message"]="Donor information Deleted";
			echo json_encode($response);
			exit(0);
			
		}else{
			$response["status"]["code"]=0;
			$response["status"]["message"]="Details Not Deleted";
			echo json_encode($response);
			exit(0);
		}
	
?>
