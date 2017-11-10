<?php 
	$username = $_REQUEST["username"]; 
	$password = $_REQUEST["password"];  
	
	include_once './db_functions.php';
        $type='';
	
	$db = new DB_Functions();
	$rescheckUser = $db->checkLogin($username,$password);
	if(mysql_affected_rows()>0){
          while($row=mysql_fetch_array($rescheckUser)){
           $type=$row['type'];					
           }
             if($type=="user"){
		
				$resUser = $db->getUserDetails($username);
				if(mysql_num_rows($resUser)>0){
					$response["status"]["code"]=1;
				        $response["status"]["message"]="Login Success";
					while($row=mysql_fetch_array($resUser)){
						$response["data"]["name"]=$row['name'];
						$response["data"]["username"]=$row['username'];
						$response["data"]["phone"]=$row['phone'];
                                                $response["data"]["type"]="user";
					}
					echo json_encode($response);
					exit(0);
				}
				else{
					$response["status"]["code"]=0;
	                $response["status"]["message"]="Not a valid user";
					echo json_encode($response);
					exit(0);
				}
               }
               else{
                   $response["status"]["code"]=1;
		   $response["status"]["message"]="Login Success";
                   $response["data"]["name"]="Admin";
                   $response["data"]["username"]=$username;
		   $response["data"]["phone"]="";
                   $response["data"]["type"]="admin";
                   echo json_encode($response);
		   exit(0);
               }
	}
	else{
	$response["status"]["code"]=0;
	$response["status"]["message"]="Not a valid user";
	echo json_encode($response);
	exit(0);
	}
	
?>
