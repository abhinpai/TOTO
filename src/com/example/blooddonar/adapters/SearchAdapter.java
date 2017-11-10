package com.example.blooddonar.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.blooddonar.R;
import com.example.blooddonar.utils.AppConstants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter{


	Activity activity;
	ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();

	LayoutInflater inflater;

	public SearchAdapter(Activity activity,
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
		TextView name,place,group;
		Button call,message;
		
	}

	@SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;

		ViewHolder holder;
		if(convertView==null)
		{
			v=inflater.inflate(R.layout.list_item_search, parent, false);
			holder =new ViewHolder();
			holder.name=(TextView)v.findViewById(R.id.txt_name);
			holder.group=(TextView)v.findViewById(R.id.txt_group);
			holder.place=(TextView)v.findViewById(R.id.txt_place);
			
			
			
			holder.call=(Button)v.findViewById(R.id.btn_call);
			holder.message=(Button)v.findViewById(R.id.btn_message);

			holder.call.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					call(data.get(position).get(AppConstants.KEY_PHONE));
					
				}
			});
			holder.message.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					sms(data.get(position).get(AppConstants.KEY_PHONE));
				}
			});
			
			
			v.setTag(holder);

		}
		else
			holder=(ViewHolder)v.getTag();
		holder.name.setText(data.get(position).get(AppConstants.KEY_NAME));
		holder.group.setText(data.get(position).get(AppConstants.KEY_GROUP));
		holder.place.setText(data.get(position).get(AppConstants.KEY_PLACE));
		return v;

	}

	protected void call(String number) {
		Intent intent = new Intent(Intent.ACTION_CALL);

		intent.setData(Uri.parse("tel:" +number));
		activity.startActivity(intent);

		
	}

	protected void sms(String number) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +number));
		activity.startActivity(intent);
		
	}

}
