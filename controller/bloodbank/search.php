<?php 


	include_once './db_functions.php';
	
	$db = new DB_Functions();
	$response["data"]["search_details"]=array();

	if((isset($_REQUEST['group']))&& (isset($_REQUEST['place']))){
	$group = $_REQUEST['group'];
	$place = $_REQUEST['place'];
	
	  $searchResult = $db->searchByBoth($group,$place);
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($searchResult)){
				$search=array();
				$search["name"]=$row["name"];
				$search["place"]=$row["place"];
				$search["group"]=$row["blood_group"];
				$search["phone"]=$row["phone"];
				array_push($response["data"]["search_details"],$search);
				
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
	}
	else if(isset($_REQUEST['place'])){
	$place = $_REQUEST['place'];
	
	  $searchResult = $db->searchByPlace($place);
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($searchResult)){
				$search=array();
				$search["name"]=$row["name"];
				$search["place"]=$row["place"];
				$search["group"]=$row["blood_group"];
				$search["phone"]=$row["phone"];
				array_push($response["data"]["search_details"],$search);
				
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
	}
	else if(isset($_REQUEST['group'])){
	$group = $_REQUEST['group'];
	
	 $searchResult = $db->searchByGroup($group);
	  if(mysql_affected_rows()>0){
			$response["status"]["code"]=1;
			$response["status"]["message"]="";
			while($row=mysql_fetch_array($searchResult)){
				$search=array();
				$search["name"]=$row["name"];
				$search["place"]=$row["place"];
				$search["group"]=$row["blood_group"];
				$search["phone"]=$row["phone"];
				array_push($response["data"]["search_details"],$search);
				
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
	  
	}
?>