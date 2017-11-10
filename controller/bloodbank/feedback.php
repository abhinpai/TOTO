<?php 

	$username = $_REQUEST['username']; 
	$message = $_REQUEST['message'];
   
	include_once './db_functions.php';
	
	$db = new DB_Functions();
	
	$donorId=0;
	
	$resultDonor = $db->getDonorId($username);
	if(mysql_affected_rows()>0){
		while($row=mysql_fetch_array($resultDonor)){
			$donorId=$row['donor_id'];
			
		}
		$resultFeedback = $db->storeFeedback($donorId,$message);
		if($resultFeedback){
			$response["status"]["code"]=1;
			$response["status"]["message"]="Feedback sent";
			echo json_encode($response);
			exit(0);
			
		}else{
			$response["status"]["code"]=0;
			$response["status"]["message"]="Feedback not sent";
			echo json_encode($response);
			exit(0);
		}
	}
	else{
		$response["status"]["code"]=0;
			$response["status"]["message"]="Error ";
			echo json_encode($response);
			exit(0);
	}
	
?>