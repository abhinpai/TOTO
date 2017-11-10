package com.example.blooddonar.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blooddonar.R;
import com.example.blooddonar.SearchDetails;
import com.example.blooddonar.handlers.AsyncFragmentHandler;
import com.example.blooddonar.interfaces.FragmentCallback;
import com.example.blooddonar.utils.AppConstants;

public class SearchFragment extends Fragment implements OnClickListener{
	Button mSearch;
	EditText mPlace;
	RadioGroup mGroup;
	RadioButton mRbPlace,mRbGroup,mRbBoth,mRb;
	Spinner mSpGroup;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.fragment_search, container, false);
		initialisation();

		mGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int position) {

				switch (position) {
				case R.id.rb_place:
					mPlace.setVisibility(View.VISIBLE);
					mSpGroup.setVisibility(View.GONE);
					break;
				case R.id.rb_group:
					mPlace.setVisibility(View.GONE);
					mSpGroup.setVisibility(View.VISIBLE);
					break;
				case R.id.rb_both:
					mPlace.setVisibility(View.VISIBLE);
					mSpGroup.setVisibility(View.VISIBLE);
					break;

				default:
					break;
				}

			}
		});


		return rootView;
	}



	private void initialisation() {
		mGroup=(RadioGroup)rootView.findViewById(R.id.rg_search);
		mRbPlace=(RadioButton)rootView.findViewById(R.id.rb_place);
		mRbGroup=(RadioButton)rootView.findViewById(R.id.rb_group);
		mRbBoth=(RadioButton)rootView.findViewById(R.id.rb_both);
		mPlace=(EditText)rootView.findViewById(R.id.et_place);
		mSearch=(Button)rootView.findViewById(R.id.btn_search);
		mSpGroup=(Spinner)rootView.findViewById(R.id.sp_group);
		mSearch.setOnClickListener(this);
		mPlace.setVisibility(View.GONE);
		mSpGroup.setVisibility(View.GONE);
	}




	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:
			if(isFormValid()){
				asyncSearch();
			}
			break;

		default:
			break;
		}

	}

	private void asyncSearch() {
		ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
		if((mRbBoth.isChecked())){
			nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PLACE,mPlace.getText().toString()));
			nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_GROUP,mSpGroup.getSelectedItem().toString()));
		}
		if(mRbPlace.isChecked()){
			nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PLACE,mPlace.getText().toString()));
		}
		if(mRbGroup.isChecked()){
			nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_GROUP,mSpGroup.getSelectedItem().toString()));
		}

		Log.e("nameV", ":"+nameValuePair);
		AsyncFragmentHandler ahandler=new AsyncFragmentHandler(getActivity(), nameValuePair, AppConstants.URL_SEARCH, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_SEARCH,new FragmentCallback() {

			@Override
			public void onTaskDone(ArrayList<HashMap<String, String>> data) {
				PopulateList(data);

			}

			@Override
			public void onTaskDone(String msg, int code) {
				if(code==AppConstants.ERROR_CODE_FAIL){
					Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
				}

			}
		});
		ahandler.execute();


	}


	protected void PopulateList(ArrayList<HashMap<String, String>> data) {
		
		Intent intent=new Intent(getActivity(),SearchDetails.class);
		intent.putExtra(AppConstants.KEY_DATA, data);
		startActivity(intent);
	}



	private boolean isFormValid() {
		Boolean status=true;
		if(!mRbPlace.isChecked()&&!mRbGroup.isChecked()&&!mRbBoth.isChecked()){
			Toast.makeText(getActivity(), "Select any option to search", Toast.LENGTH_LONG).show();
			status=false;
		}

		if(TextUtils.isEmpty(mPlace.getText().toString())){
			status=false;
		}
		if(mRbGroup.isChecked()){
			if(mSpGroup.getSelectedItemPosition()==0){
				Toast.makeText(getActivity(), "Select Blood Group", Toast.LENGTH_LONG).show();
				status=false;
			}
		}
		return status;
	}
}
