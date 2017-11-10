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
   
	include_once './db_functions.php';
	
	$db = new DB_Functions();
	$responsecheckUser = $db->checkUser($username,$password);
	if(mysql_affected_rows()>0){
		  $response["status"]["code"]=0;
		  $response["status"]["message"]="Account already exists";
		  echo json_encode($response);
		  exit(0);
	}
	else{
	
		$responsestoreUser = $db->registerUser($name,$username,$dob,$group,$phone,$state,$place,$gender,$email,$password);
			if($responsestoreUser){
				
				$responseUser = $db->getUserDetails($username);
				if(mysql_num_rows($responseUser)>0){
					$response["status"]["code"]=1;
				   $response["status"]["message"]="Registration Success";
					while($row=mysql_fetch_array($responseUser)){
						$response["data"]["name"]=$row['name'];
						$response["data"]["username"]=$row['username'];
						$response["data"]["phone"]=$row['phone'];
					}
					echo json_encode($response);
					exit(0);
				}
				else{
					$responseponse["status"]["code"]=0;
	                $responseponse["status"]["message"]="Not a valid user";
					echo json_encode($response);
					exit(0);
				}
				
				
			}
			else{
				$response["status"]["code"]=0;
				$response["status"]["message"]="Failed to register";
				echo json_encode($response);
					exit(0);
			}
		}
	
?>