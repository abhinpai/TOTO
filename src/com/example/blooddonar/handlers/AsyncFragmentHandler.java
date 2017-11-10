package com.example.blooddonar.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.blooddonar.interfaces.FragmentCallback;
import com.example.blooddonar.utils.AppConstants;
import com.example.blooddonar.utils.CustomProgresssDialog;

public class AsyncFragmentHandler extends AsyncTask<Void, Void, Void>{
	Activity activity;

	ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
	StatusHandler jhandler=new StatusHandler();
	String url,message;
	String Result;
	int pageCode;
	FragmentCallback fragmentCallback;

	public AsyncFragmentHandler(FragmentActivity activity,
			ArrayList<NameValuePair> nameValuePair, String url,
			String message, int pageCode,
			FragmentCallback fragmentCallback) {
		this.activity=activity;
		this.nameValuePair=nameValuePair;
		this.url=url;
		this.message=message;
		this.pageCode=pageCode;
		this.fragmentCallback=fragmentCallback;
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

		if(pageCode==AppConstants.PAGE_SEARCH){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getSearchData(Result);
				fragmentCallback.onTaskDone(data);
			}else{
				fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
			}
		}

		if(pageCode==AppConstants.PAGE_FEEDBACK){
			fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
		}
		
		if(pageCode==AppConstants.PAGE_GETFEEDBACK){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getFeedback(Result);
				fragmentCallback.onTaskDone(data);
			}else{
				fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
			}
		}
		
		
		
		if(pageCode==AppConstants.PAGE_GETDONOR){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getBloodDonor(Result);
				fragmentCallback.onTaskDone(data);
			}else{
				fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
			}
		}
		
		
		
		
		if(pageCode==AppConstants.PAGE_SEARCHHOSPITAL){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getHospitalSearch(Result);
				fragmentCallback.onTaskDone(data);
			}else{
				fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
			}
		}
		
		if(pageCode==AppConstants.PAGE_SEARCHBLOODBANK){
			if(jhandler.getStatusCode()==AppConstants.ERROR_CODE_SUCCESS){
				ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
				data=DataHandler.getBloodBankSearch(Result);
				fragmentCallback.onTaskDone(data);
			}else{
				fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
			}
		}
		
		
		if(pageCode==AppConstants.PAGE_DELETEDONOR){
			fragmentCallback.onTaskDone(jhandler.getStatusMsg(),jhandler.getStatusCode());
		}



	}
}
