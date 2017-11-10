package com.example.blooddonar.fragments;


import com.example.blooddonar.R;
import com.example.blooddonar.ViewUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserFragment extends Fragment implements OnClickListener{
	View rootView;
	Button mView;
	
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView=inflater.inflate(R.layout.fragment_user, container, false);
		initialisation();
		return rootView;
	}
	
	private void initialisation() {

		//mAdd=(Button)rootView.findViewById(R.id.btn_add);
		mView=(Button)rootView.findViewById(R.id.btn_view);
		//mAdd.setOnClickListener(this);
		mView.setOnClickListener(this);



	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//case R.id.btn_add:
			//startActivity(new Intent(getActivity(),AddBloodBanks.class));
			//break;

		case R.id.btn_view:
			startActivity(new Intent(getActivity(),ViewUser.class));
			break;

		default:
			break;
		}

	}

}
