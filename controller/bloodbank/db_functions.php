<?php
class DB_Functions {
	private $db;
	function __construct() {
		include_once './db_connect.php';
		// connecting to database
		$this->db = new DB_Connect ();
		$this->db->connect ();
	}
	// destructor
	function __destruct() {
	}
	
	/**
	 * Check new user
	 */
	public function checkUser($username, $password) {
		$result = mysql_query ( "SELECT * FROM login_details WHERE username='$username' " ) or die ( mysql_error () );
		return $result;
	}
	
	/**
	 * Check new login
	 */
	public function checkLogin($username, $password) {
		$result = mysql_query ( "SELECT * FROM login_details WHERE username='$username' AND password='$password' " ) or die ( mysql_error () );
		return $result;
	}
	
	
	
	/**
	 * get user data
	 */
	public function getUserDetails($username) {
		$result = mysql_query ( "SELECT * FROM donor_details WHERE username='$username'" );
			return $result ;
	}
	
	/**
	 * Storing new user
	 */
	public function registerUser($name,$username,$dob,$group,$phone,$state,$place,$gender,$email,$password){
		// insert user into database
		$result1 = mysql_query ( "INSERT INTO donor_details(name,username,dob,blood_group,phone,state,place,gender,email) VALUES('$name','$username','$dob','$group','$phone','$state','$place','$gender','$email')" );
		$result2 = mysql_query ( "INSERT INTO login_details(username,password,type) VALUES('$username','$password','user')" );
		 
		if ($result1 && $result2) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	/**
	 * Search donors detail
	 */
	public function searchByBoth($group, $place) {
		$result = mysql_query ( "SELECT * FROM donor_details WHERE blood_group='$group' AND place like '$place%'" );
		return $result ;
	}
	
	
	/**
	 * Search donors detail
	 */
	public function searchByPlace($place) {
		$result = mysql_query ( "SELECT * FROM donor_details WHERE place like '$place%'" );
		return $result ;
	}
	/**
	 * Search donors detail
	 */
	public function searchByGroup($group) {
		$result = mysql_query ( "SELECT * FROM donor_details WHERE blood_group='$group'" );
		return $result ;
	}
	
	
	/**
	 * Search donors detail
	 */
	public function getDonorId($username){
		$result = mysql_query ( "SELECT * FROM donor_details WHERE username='$username'" );
		return $result ;
	}
	
	/**
	 * Storing feedback
	 */
	public function storeFeedback($donorId,$message){

		$date=date('Y-m-d');
		$result= mysql_query ("INSERT INTO feedbacks(description,date,donor_id) VALUES('$message','$date','$donorId')" );

		if ($result) {
			return true;
		} else {
			return false;
		}
	}	


         /**
	 * Storing hospital data
	 */

	public function storeHospital($name,$place,$phone,$email){

		$result= mysql_query ("INSERT INTO hospital_details(name,place,phone,email) VALUES('$name','$place','$phone','$email')" );

		if ($result) {
			return true;
		} else {
			return false;
		}
	}	


          /**
	 * Storing blood bank data
	 */

	public function storeBloodBank($name,$place,$phone,$email){

		$result= mysql_query ("INSERT INTO bloodbank_details(name,place,phone,email) VALUES('$name','$place','$phone','$email')" );

		if ($result) {
			return true;
		} else {
			return false;
		}
	}	



          /**
	 * get feedbacks
	 */
        public function getFeedbacks(){
		$result = mysql_query ( "SELECT f.date,f.description,d.name,d.email,d.phone FROM donor_details d,feedbacks f" );
		return $result ;
	}
/*


* get feedbacks
	 */
        public function getBDonor(){
		$result = mysql_query ( "SELECT donor_id,place,blood_group,name,phone,email FROM donor_details" );
		return $result ;
	}


/*
 * get feedbacks
	 */
        public function getUserdata(){
		$result = mysql_query ( "SELECT * from donor_details" );
		return $result ;
	}
         /**
	 * getHospitals
	 */
        public function getHospitals(){
		$result = mysql_query ( "SELECT * FROM hospital_details" );
		return $result ;
	}



         /**
	 * getHospitals
	 */
        public function getBloodBanks(){
		$result = mysql_query ( "SELECT * FROM bloodbank_details" );
		return $result ;
	}



         /**
	 * edit Hospitals
	 */
         public function editHospital($id,$name,$place,$phone,$email){

		$result= mysql_query ("UPDATE hospital_details SET name='$name',place='$place',phone='$phone',email='$email' WHERE hospital_id='$id'" );

		if (mysql_affected_rows()>0) {
			return true;
		} else {
			return false;
		}
	}	





        /**
	 * edit Blood bank
	 */
         public function editBloodBank($id,$name,$place,$phone,$email){

		$result= mysql_query ("UPDATE bloodbank_details SET name='$name',place='$place',phone='$phone',email='$email' WHERE bloodbank_id='$id'" );

		if (mysql_affected_rows()>0) {
			return true;
		} else {
			return false;
		}
	}	

   /**
	 * delete bank data
	 */

	public function deleteBloodBank($id){

		$result= mysql_query ("DELETE FROM bloodbank_details WHERE bloodbank_id='$id'" );

		if ($result) {
			return true;
		} else {
			return false;
		}
	}	

         /**
	 * delete hospital data
	 */

	public function deleteHospital($id){

		$result= mysql_query ("DELETE FROM hospital_details WHERE hospital_id='$id'" );

		if ($result) {
			return true;
		} else {
			return false;
		}
	}

	
	public function deleteUser($name){

		$result= mysql_query ("DELETE FROM donor_details WHERE name='$name'" );

		if ($result) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * delete hospital data
	 */

	public function deleteDonor($name){
	

	$result1=mysql_query ("SELECT donor_id FROM donor_details WHERE name='$name'" );
		$result= mysql_query ("DELETE FROM donor_details WHERE donor_id='$result1'" );

		$result=mysql_query ("DELETE FROM donor_details WHERE donor_id='$result1'" );
		if ($result) {
			return true;
		} else {
			return false;
		}
	}


         /**
	
	

         /**
	 * search Hospitals
	 */
        public function searchHospitals($place){
		$result = mysql_query ( "SELECT * FROM hospital_details WHERE place like '$place%'" );
		return $result ;
	}
	
        /**
	 * search bloodbanks
	 */
        public function searchBloodbank($place){
		$result = mysql_query ( "SELECT * FROM bloodbank_details WHERE place like '$place%' " );
		return $result ;
	}
	

	
		 /* edit Hospitals
	 */
         public function updateUser($donorId,$dob,$group,$phone,$state,$place,$gender,$email,$password)
		 {

		$result= mysql_query ("UPDATE donor_details SET dob='$dob',blood_group='$group',place='$place',phone='$phone',email='$email',state='$state',gender='$gender',password='$password' WHERE donor_id='$donorId'" );

		if (mysql_affected_rows()>0) {
			return true;
		} else {
			return false;
		}
	}	

	

}

?>
