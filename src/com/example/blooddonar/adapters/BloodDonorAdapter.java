package com.example.blooddonar.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.blooddonar.R;
import com.example.blooddonar.utils.AppConstants;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BloodDonorAdapter extends BaseAdapter{
	
	Activity activity;
	ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();

	LayoutInflater inflater;

	public BloodDonorAdapter(Activity activity,ArrayList<HashMap<String, String>> data) {
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
		TextView name,email,phone,bloodgroup,place;
		
	}

	@SuppressWarnings("null")

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		ViewHolder holder;
		if(convertView==null)
		{
			v=inflater.inflate(R.layout.list_item_donorview, parent, false);
			holder =new ViewHolder();
			holder.name=(TextView)v.findViewById(R.id.txt_name);
			holder.email=(TextView)v.findViewById(R.id.txt_email);
			holder.phone=(TextView)v.findViewById(R.id.txt_phone);
			holder.bloodgroup=(TextView)v.findViewById(R.id.txt_bloodgroup);
			holder.place=(TextView)v.findViewById(R.id.txt_place);
			
			v.setTag(holder);

		}
		else
			holder=(ViewHolder)v.getTag();
		holder.name.setText(data.get(position).get(AppConstants.KEY_NAME));
		holder.email.setText(data.get(position).get(AppConstants.KEY_EMAIL));
		holder.phone.setText(data.get(position).get(AppConstants.KEY_PHONE));
		holder.bloodgroup.setText(data.get(position).get(AppConstants.KEY_GROUP));
		holder.place.setText(data.get(position).get(AppConstants.KEY_PLACE));
		return v;

	}

}
