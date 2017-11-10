<?php 

	$name = $_REQUEST['name']; 
        $id = $_REQUEST['id']; 
	$place = $_REQUEST['place'];
        $phone = $_REQUEST['phone'];
        $email = $_REQUEST['email'];
   
	include_once './db_functions.php';
	
	$db = new DB_Functions();
	
	$donorId=0;
	

		$result = $db->editHospital($id,$name,$place,$phone,$email);
		if($result){
			$response["status"]["code"]=1;
			$response["status"]["message"]="Hospital information Edited";
			echo json_encode($response);
			exit(0);
			
		}else{
			$response["status"]["code"]=0;
			$response["status"]["message"]="Details Not Edited";
			echo json_encode($response);
			exit(0);
		}
	
?>
