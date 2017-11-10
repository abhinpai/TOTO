package com.example.blooddonar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.blooddonar.AddHospitals;
import com.example.blooddonar.R;
import com.example.blooddonar.ViewHospitals;

public class HospitalFragments extends Fragment implements OnClickListener{
	View rootView;
	Button mView,mAdd;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.fragment_hospitals, container, false);
		initialisation();
		return rootView;
	}

	private void initialisation() {

		mAdd=(Button)rootView.findViewById(R.id.btn_add);
		mView=(Button)rootView.findViewById(R.id.btn_view);
		mAdd.setOnClickListener(this);
		mView.setOnClickListener(this);



	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			startActivity(new Intent(getActivity(),AddHospitals.class));
			break;

		case R.id.btn_view:
			startActivity(new Intent(getActivity(),ViewHospitals.class));
			break;

		default:
			break;
		}

	}

}
