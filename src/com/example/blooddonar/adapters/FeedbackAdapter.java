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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackAdapter extends BaseAdapter{


	Activity activity;
	ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();

	LayoutInflater inflater;

	public FeedbackAdapter(Activity activity,
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
		TextView name,email,phone,description,date;
		Button message;
	}

	@SuppressWarnings("null")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;

		ViewHolder holder;
		if(convertView==null)
		{
			v=inflater.inflate(R.layout.list_item_feedback, parent, false);
			holder =new ViewHolder();
			holder.name=(TextView)v.findViewById(R.id.txt_name);
			holder.email=(TextView)v.findViewById(R.id.txt_email);
			holder.phone=(TextView)v.findViewById(R.id.txt_phone);
			holder.description=(TextView)v.findViewById(R.id.txt_description);
			holder.date=(TextView)v.findViewById(R.id.txt_date);
			
			holder.message=(Button)v.findViewById(R.id.btn_message);

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
		holder.email.setText(data.get(position).get(AppConstants.KEY_EMAIL));
		holder.phone.setText(data.get(position).get(AppConstants.KEY_PHONE));
		holder.description.setText(data.get(position).get(AppConstants.KEY_DESCRIPTION));
		holder.date.setText(data.get(position).get(AppConstants.KEY_DATE));
		return v;

	}
	
	protected void sms(String number) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +number));
		activity.startActivity(intent);
		
	}

}
