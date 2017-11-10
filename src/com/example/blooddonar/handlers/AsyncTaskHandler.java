package com.example.blooddonar.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.blooddonar.AddBloodBanks;
import com.example.blooddonar.AddHospitals;
import com.example.blooddonar.EditBloodBank;
import com.example.blooddonar.EditHospital;
import com.example.blooddonar.SignIn;
import com.example.blooddonar.SignUp;
import com.example.blooddonar.ViewBloodBanks;
import com.example.blooddonar.ViewHospitals;
import com.example.blooddonar.ViewUser;
import com.example.blooddonar.utils.AppConstants;
import com.example.blooddonar.utils.CustomProgresssDialog;

public class AsyncTaskHandler extends AsyncTask<Void, Void, Void>{
	Activity activity;

	ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
	StatusHandler jhandler=new StatusHandler();
	String url,message;
	String Result;
	int pageCode;

	public AsyncTaskHandler(Activity activity, ArrayList<NameValuePair> nameValuePair, String url, String message, int pageCode) {
		this.activity=activity;
		this.nameValuePair=nameValuePair;
		this.url=url;
		this.message=message;
		this.pageCode=pageCode;
	}


	CustomProgresssDialog cpd=new CustomProgresssDialog();



	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		cpd.showDialog(activity, message);
	}

	@Override
	protected Void doInBackground(Void... params) {
		ConnectionHandler ch=new ConnectionHandler();
		Result=ch.makeConnection(url, this.nameValuePair);
		Log.e("response", ":"+Result);
		try {
			jhandler.getJsonStatus(Result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		cpd.dismissDialog();

		if(pageCode==AppConstants.PAGE_REGISTER){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				DataHandler.getUserData(Result);
			}
			SignUp signup = (SignUp)activity; 
			signup.onAsynkResult(jhandler.getStatusCode(),jhandler.getStatusMsg());	


		}

		if(pageCode==AppConstants.PAGE_LOGIN){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				DataHandler.getUserData(Result);
			}
			SignIn signin = (SignIn)activity; 
			signin.onAsynkResult(jhandler.getStatusCode(),jhandler.getStatusMsg());	


		}

		if(pageCode==AppConstants.PAGE_HOSPITAL){
			AddHospitals addHospitals = (AddHospitals)activity; 
			addHospitals.onAsynkResult(jhandler.getStatusCode(),jhandler.getStatusMsg());	
		}
		
		if(pageCode==AppConstants.PAGE_BLOODBANK){
			AddBloodBanks addBloodbank = (AddBloodBanks)activity; 
			addBloodbank.onAsynkResult(jhandler.getStatusCode(),jhandler.getStatusMsg());	
		}
		
		if(pageCode==AppConstants.PAGE_GETBLOODBANK){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getBloodBanks(Result);
				ViewBloodBanks viewBloodbank = (ViewBloodBanks)activity; 
				viewBloodbank.onResult(data);
				
			}else{
				ViewBloodBanks viewBloodbank = (ViewBloodBanks)activity; 
				viewBloodbank.onResult(jhandler.getStatusMsg());
			}
		}
		
		
		if(pageCode==AppConstants.PAGE_GETDONOR){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getBloodDonor(Result);
				ViewUser viewUsers = (ViewUser)activity; 
				viewUsers.onResult(data);
				
			}else{
				ViewUser viewUsers = (ViewUser)activity; 
				viewUsers.onResult(jhandler.getStatusMsg());
			}
		}
		
		
		
		
		
		if(pageCode==AppConstants.PAGE_GETHOSPITAL){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getHospitals(Result);
				ViewHospitals viewHospital = (ViewHospitals)activity; 
				viewHospital.onResult(data);
				
			}else{
				ViewHospitals viewHospital = (ViewHospitals)activity; 
				viewHospital.onResult(jhandler.getStatusMsg());
			}
		}
		
		
		if(pageCode==AppConstants.PAGE_EDITHOSPITAL){
			EditHospital editHospitals = (EditHospital)activity; 
			editHospitals.onAsynkResult(jhandler.getStatusCode(),jhandler.getStatusMsg());	
		}
		
		
		if(pageCode==AppConstants.PAGE_EDITBLOODBANK){
			EditBloodBank editBloodbank = (EditBloodBank)activity; 
			editBloodbank.onAsynkResult(jhandler.getStatusCode(),jhandler.getStatusMsg());	
		}
		
		if(pageCode==AppConstants.PAGE_DELETEHOSPITAL){
			ViewHospitals viewHospital = (ViewHospitals)activity; 
			viewHospital.onDeleteResult(jhandler.getStatusMsg());
		}
		
		
		if(pageCode==AppConstants.PAGE_DELETEDONOR){
			
		
			
			
			ViewUser viewUsers = (ViewUser)activity;
			viewUsers.onDeleteResult(jhandler.getStatusMsg());
			//GetUserFragment userfragment=(GetUserFragment)activity;
			//ViewHospitals viewHospital = (ViewHospitals)activity; 
			//viewHospital.onDeleteResult(jhandler.getStatusMsg());
		}
		
		
		if(pageCode==AppConstants.PAGE_DELETEBLOODBANK){
			ViewBloodBanks viewBloodbank = (ViewBloodBanks)activity; 
			viewBloodbank.onDeleteResult(jhandler.getStatusMsg());
		}
	
	}
}
