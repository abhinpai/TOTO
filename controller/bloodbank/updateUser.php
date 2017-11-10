<?php 

	$name = $_REQUEST['name']; 
	$username = $_REQUEST['username']; 
	$password = $_REQUEST['password']; 
	$dob = $_REQUEST['dob']; 
	$group = $_REQUEST['group'];
	$phone = $_REQUEST['phone'];
	$state = $_REQUEST['state'];
	$place = $_REQUEST['place'];
	$gender = $_REQUEST['gender'];
	$email = $_REQUEST['email'];
	
	/*
		$name = "sathish";
	$username =  "sathish";
	$password = "guru";
	$dob = "2016-03-10";
	$group = "m+";
	$phone = "123456789";
	$state = "guru";
	$place = "guru";
	$gender = "guru";
	$email = "guru";
		*/
		
		include_once './db_functions.php';
	
	$db = new DB_Functions();
	
	$donorId=0;
	
	$resultDonor = $db->getDonorId($username);
	if(mysql_affected_rows()>0){
		while($row=mysql_fetch_array($resultDonor)){
			$donorId=$row['donor_id'];
			
		}
		
		$resultUpdateUser = $db->updateUser($donorId,$dob,$group,$phone,$state,$place,$gender,$email,$password);
		if($resultUpdateUser){
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
   