package com.example.blooddonar.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blooddonar.R;
import com.example.blooddonar.handlers.AsyncFragmentHandler;
import com.example.blooddonar.interfaces.FragmentCallback;
import com.example.blooddonar.utils.AppConstants;

public class FeedbackFragment extends Fragment implements OnClickListener{
	View rootView;
	TextView mName;
	EditText mDescription;
	Button mSubmit;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.fragment_feedback, container, false);

		initialisation();
		return rootView;
	}
	private void initialisation() {
		mName=(TextView)rootView.findViewById(R.id.txt_name);
		mDescription=(EditText)rootView.findViewById(R.id.et_description);
		mSubmit=(Button)rootView.findViewById(R.id.btn_submit);
		mSubmit.setOnClickListener(this);
		mName.setText("Hi "+AppConstants.PARAM_NAME);

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit:
			if(isFormValid())
				asyncFeedback();
			break;

		default:
			break;
		}

	}
	private boolean isFormValid() {
		Boolean status=true;
		if(TextUtils.isEmpty(mDescription.getText().toString())){
			status=false;
			mDescription.setError("Fill Description");
		}
		return status;
	}
	private void asyncFeedback() {
		ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();

		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_MESSAGE,mDescription.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_USERNAME,AppConstants.PARAM_USERNAME));

		AsyncFragmentHandler ahandler=new AsyncFragmentHandler(getActivity(), nameValuePair, AppConstants.URL_FEEDBACK, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_FEEDBACK,new FragmentCallback() {

			@Override
			public void onTaskDone(ArrayList<HashMap<String, String>> data) {
				
			}

			@Override
			public void onTaskDone(String msg, int code) {
			
					Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
				

			}
		});
		ahandler.execute();


	}

}
