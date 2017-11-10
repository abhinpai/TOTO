package com.example.blooddonar.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blooddonar.R;
import com.example.blooddonar.adapters.FeedbackAdapter;
import com.example.blooddonar.handlers.AsyncFragmentHandler;
import com.example.blooddonar.interfaces.FragmentCallback;
import com.example.blooddonar.utils.AppConstants;

public class GetFeedBackFragment extends Fragment{
	View rootView;
	ListView mLv;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.activity_listview, container, false);
		initialisation();
		
		getFeedbacks();
		return rootView;
	}

	private void getFeedbacks() {
		AsyncFragmentHandler ahandler=new AsyncFragmentHandler(getActivity(), null, AppConstants.URL_GETFEEDBACK, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_GETFEEDBACK,new FragmentCallback() {

			@Override
			public void onTaskDone(ArrayList<HashMap<String, String>> data) {
				populateList(data);

			}

			@Override
			public void onTaskDone(String msg, int code) {
			
					Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
				

			}
		});
		ahandler.execute();


		
	}

	protected void populateList(ArrayList<HashMap<String, String>> data) {
		FeedbackAdapter adapter=new FeedbackAdapter(getActivity(), data);
		mLv.setAdapter(adapter);
	}

	private void initialisation() {
	mLv=(ListView)rootView.findViewById(R.id.lv);
		
	}

}
