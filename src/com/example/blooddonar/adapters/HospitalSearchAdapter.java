package com.example.blooddonar.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.blooddonar.R;
import com.example.blooddonar.utils.AppConstants;

public class HospitalSearchAdapter extends BaseAdapter {

	Activity activity;
	ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();

	LayoutInflater inflater;

	public HospitalSearchAdapter(Activity activity,
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
	

	}

	@SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;

		ViewHolder holder;
		if(convertView==null)
		{
			v=inflater.inflate(R.layout.list_item_search_hospitals, parent, false);
			holder =new ViewHolder();
			holder.name=(TextView)v.findViewById(R.id.txt_name);
			holder.email=(TextView)v.findViewById(R.id.txt_email);
			holder.phone=(TextView)v.findViewById(R.id.txt_phone);
			holder.place=(TextView)v.findViewById(R.id.txt_place);

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
