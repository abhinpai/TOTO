package com.example.blooddonar.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.blooddonar.utils.AppConstants;

public class DataHandler {

	public static void getUserData(String result) {

		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jdata=jo.getJSONObject(AppConstants.KEY_DATA);
			AppConstants.PARAM_NAME=jdata.getString(AppConstants.KEY_NAME);
			AppConstants.PARAM_USERNAME=jdata.getString(AppConstants.KEY_USERNAME);
			AppConstants.PARAM_PHONE=jdata.getString(AppConstants.KEY_PHONE);
			AppConstants.PARAM_TYPE=jdata.getString(AppConstants.KEY_TYPE);

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<HashMap<String, String>> getSearchData(String result) {
		ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
			JSONArray ja=jd.getJSONArray(AppConstants.KEY_SEARCH_DETAILS);
			for(int i=0;i<ja.length();i++){
				JSONObject jdata=ja.getJSONObject(i);
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
				map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
				map.put(AppConstants.KEY_GROUP, jdata.getString(AppConstants.KEY_GROUP));
				map.put(AppConstants.KEY_PLACE, jdata.getString(AppConstants.KEY_PLACE));


				data.add(map);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return data;
	}

	public static ArrayList<HashMap<String, String>> getFeedback(String result) {
		ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
			JSONArray ja=jd.getJSONArray(AppConstants.KEY_FEEDBACK);
			for(int i=0;i<ja.length();i++){
				JSONObject jdata=ja.getJSONObject(i);
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
				
				map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
				map.put(AppConstants.KEY_EMAIL, jdata.getString(AppConstants.KEY_EMAIL));
				map.put(AppConstants.KEY_DESCRIPTION, jdata.getString(AppConstants.KEY_DESCRIPTION));
				map.put(AppConstants.KEY_DATE, jdata.getString(AppConstants.KEY_DATE));
				data.add(map);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return data;
	}

	
	
	public static ArrayList<HashMap<String, String>> getBloodDonor(String result) {
		ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
			JSONArray ja=jd.getJSONArray(AppConstants.KEY_DONOR);
			for(int i=0;i<ja.length();i++){
				JSONObject jdata=ja.getJSONObject(i);
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
				map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
				map.put(AppConstants.KEY_EMAIL, jdata.getString(AppConstants.KEY_EMAIL));
				map.put(AppConstants.KEY_GROUP, jdata.getString(AppConstants.KEY_GROUP));
				map.put(AppConstants.KEY_PLACE, jdata.getString(AppConstants.KEY_PLACE));
				data.add(map);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return data;
	}

	
	
	
	
	
	
	public static ArrayList<HashMap<String, String>> getBloodBanks(String result) {
			ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
			try {
				JSONObject jo = new JSONObject(result);
				JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
				JSONArray ja=jd.getJSONArray(AppConstants.KEY_BLOODBANK);
				for(int i=0;i<ja.length();i++){
					JSONObject jdata=ja.getJSONObject(i);
					HashMap<String, String>map=new HashMap<String, String>();
					map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
					map.put(AppConstants.KEY_ID, jdata.getString(AppConstants.KEY_ID));
					map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
					map.put(AppConstants.KEY_EMAIL, jdata.getString(AppConstants.KEY_EMAIL));
					map.put(AppConstants.KEY_PLACE, jdata.getString(AppConstants.KEY_PLACE));
					
					data.add(map);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return data;
	}

	public static ArrayList<HashMap<String, String>> getHospitals(String result) {
		ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
			JSONArray ja=jd.getJSONArray(AppConstants.KEY_HOSPITAL);
			for(int i=0;i<ja.length();i++){
				JSONObject jdata=ja.getJSONObject(i);
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
				map.put(AppConstants.KEY_ID, jdata.getString(AppConstants.KEY_ID));
				Log.d("id", ":"+jdata.getString(AppConstants.KEY_ID));
				map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
				map.put(AppConstants.KEY_EMAIL, jdata.getString(AppConstants.KEY_EMAIL));
				map.put(AppConstants.KEY_PLACE, jdata.getString(AppConstants.KEY_PLACE));
				
				data.add(map);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return data;
	}

	public static ArrayList<HashMap<String, String>> getHospitalSearch(
			String result) {
		ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
			JSONArray ja=jd.getJSONArray(AppConstants.KEY_HOSPITAL);
			for(int i=0;i<ja.length();i++){
				JSONObject jdata=ja.getJSONObject(i);
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
				map.put(AppConstants.KEY_ID, jdata.getString(AppConstants.KEY_ID));
				map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
				map.put(AppConstants.KEY_EMAIL, jdata.getString(AppConstants.KEY_EMAIL));
				map.put(AppConstants.KEY_PLACE, jdata.getString(AppConstants.KEY_PLACE));
				
				data.add(map);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return data;
	}	
	
	public static ArrayList<HashMap<String, String>> getBloodBankSearch(
			String result) {
		ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();
		try {
			JSONObject jo = new JSONObject(result);
			JSONObject jd=jo.getJSONObject(AppConstants.KEY_DATA);
			JSONArray ja=jd.getJSONArray(AppConstants.KEY_BLOODBANK);
			for(int i=0;i<ja.length();i++){
				JSONObject jdata=ja.getJSONObject(i);
				HashMap<String, String>map=new HashMap<String, String>();
				map.put(AppConstants.KEY_NAME, jdata.getString(AppConstants.KEY_NAME));
				map.put(AppConstants.KEY_ID, jdata.getString(AppConstants.KEY_ID));
				map.put(AppConstants.KEY_PHONE, jdata.getString(AppConstants.KEY_PHONE));
				map.put(AppConstants.KEY_EMAIL, jdata.getString(AppConstants.KEY_EMAIL));
				map.put(AppConstants.KEY_PLACE, jdata.getString(AppConstants.KEY_PLACE));
				
				data.add(map);
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return data;
	}	

}
