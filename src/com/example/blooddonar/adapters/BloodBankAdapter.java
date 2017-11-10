package com.example.blooddonar.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.blooddonar.EditBloodBank;
import com.example.blooddonar.R;
import com.example.blooddonar.handlers.AsyncTaskHandler;
import com.example.blooddonar.utils.AppConstants;

public class BloodBankAdapter extends BaseAdapter {

	Activity activity;
	ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();

	LayoutInflater inflater;

	public BloodBankAdapter(Activity activity,
			ArrayList<HashMap<String, String>> data) {
		this.activity=activity;
		this.data=data;
		inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public static class ViewHolder{
		TextView name,email,phone,place;
		Button edit,delete;

	}

	@SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;

		ViewHolder holder;
		if(convertView==null)
		{
			v=inflater.inflate(R.layout.list_item_bloodbank, parent, false);
			holder =new ViewHolder();
			holder.name=(TextView)v.findViewById(R.id.txt_name);
			holder.email=(TextView)v.findViewById(R.id.txt_email);
			holder.phone=(TextView)v.findViewById(R.id.txt_phone);
			holder.place=(TextView)v.findViewById(R.id.txt_place);

			holder.edit=(Button)v.findViewById(R.id.btn_edit);
			holder.delete=(Button)v.findViewById(R.id.btn_delete);

			holder.edit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent=new Intent(activity,EditBloodBank.class);
					intent.putExtra(AppConstants.KEY_ID, data.get(position).get(AppConstants.KEY_ID));
					intent.putExtra(AppConstants.KEY_NAME, data.get(position).get(AppConstants.KEY_NAME));
					intent.putExtra(AppConstants.KEY_PLACE, data.get(position).get(AppConstants.KEY_PLACE));
					intent.putExtra(AppConstants.KEY_PHONE, data.get(position).get(AppConstants.KEY_PHONE));
					intent.putExtra(AppConstants.KEY_EMAIL, data.get(position).get(AppConstants.KEY_EMAIL));
					activity.startActivity(intent);

				}
			});
			holder.delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
					nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_ID,data.get(position).get(AppConstants.KEY_ID)));

					AsyncTaskHandler ahandler=new AsyncTaskHandler(activity, nameValuePair, AppConstants.URL_DELETEBLOODBANK, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_DELETEBLOODBANK);
					ahandler.execute();
				}
			});
			v.setTag(holder);

		}
		else
			holder=(ViewHolder)v.getTag();
		holder.name.setText(data.get(position).get(AppConstants.KEY_NAME));
		holder.email.setText(data.get(position).get(AppConstants.KEY_EMAIL));
		holder.phone.setText(data.get(position).get(AppConstants.KEY_PHONE));
		holder.place.setText(data.get(position).get(AppConstants.KEY_PLACE));
		return v;

	}



}