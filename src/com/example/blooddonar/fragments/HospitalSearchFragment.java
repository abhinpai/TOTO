package com.example.blooddonar.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blooddonar.HospitalDetails;
import com.example.blooddonar.R;
import com.example.blooddonar.handlers.AsyncFragmentHandler;
import com.example.blooddonar.interfaces.FragmentCallback;
import com.example.blooddonar.utils.AppConstants;

public class HospitalSearchFragment extends Fragment implements OnClickListener{
	View rootView;
	EditText mName;
	Button mSearch;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.fragment_search_hospital, container,false);

		initialisation();
		return rootView;
	}

	private void initialisation() {
		mName=(EditText)rootView.findViewById(R.id.et_search);
		mSearch=(Button)rootView.findViewById(R.id.btn_search);
		mSearch.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		if(isFormValid()){
			Search();
		}
	}

	private void Search() {
		ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_NAME,mName.getText().toString()));
		AsyncFragmentHandler ahandler=new AsyncFragmentHandler(getActivity(), nameValuePair, AppConstants.URL_SEARCHHOSPITAL, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_SEARCHHOSPITAL,new FragmentCallback() {

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

		Intent intent=new Intent(getActivity(),HospitalDetails.class);
		intent.putExtra(AppConstants.KEY_DATA, data);
		startActivity(intent);
		
	}

	private boolean isFormValid() {
		Boolean status=true;
		if(TextUtils.isEmpty(mName.getText().toString())){
			mName.setError("Fill Name");
			status=false;
		}


		return status;
	}
}
