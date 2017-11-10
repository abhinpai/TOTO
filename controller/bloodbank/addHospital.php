<?php 

	$name = $_REQUEST['name']; 
	$place = $_REQUEST['place'];
        $phone = $_REQUEST['phone'];
        $email = $_REQUEST['email'];
   
	include_once './db_functions.php';
	
	$db = new DB_Functions();
	
	$donorId=0;
	

		$result = $db->storeHospital($name,$place,$phone,$email);
		if($result){
			$response["status"]["code"]=1;
			$response["status"]["message"]="Hospital information stored";
			echo json_encode($response);
			exit(0);
			
		}else{
			$response["status"]["code"]=0;
			$response["status"]["message"]="Not sent";
			echo json_encode($response);
			exit(0);
		}
	
?>
