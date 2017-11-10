package com.example.blooddonar;

import com.example.blooddonar.fragments.HospitalSearchFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener{

	Button mSignIn,mSearch,mHospital,mBlood,mDonor,mGeneral,mFeedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialisation();

		mSignIn.setOnClickListener(this);
		mSearch.setOnClickListener(this);
		mHospital.setOnClickListener(this);
		mBlood.setOnClickListener(this);
		mDonor.setOnClickListener(this);
		mGeneral.setOnClickListener(this);
		mFeedback.setOnClickListener(this);

	}

	private void initialisation() {
		mSignIn=(Button)findViewById(R.id.btn_signin);
		mSearch=(Button)findViewById(R.id.btn_search);
		mHospital=(Button)findViewById(R.id.btn_hospital);
		mBlood=(Button)findViewById(R.id.btn_blood);
		mDonor=(Button)findViewById(R.id.btn_donor);
		mGeneral=(Button)findViewById(R.id.btn_general);
		mFeedback=(Button)findViewById(R.id.btn_feedback);
		
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_signin:
			startActivity(new Intent(MainActivity.this,SignIn.class));
			break;
		case R.id.btn_search:
			startActivity(new Intent(MainActivity.this,Home.class));
			break;
		case R.id.btn_hospital:
			startActivity(new Intent(MainActivity.this,HospitalSearchFragment.class));
			//fragmentClass = HospitalSearchFragment.class;
			break;
		case R.id.btn_blood:
			startActivity(new Intent(MainActivity.this,Home.class));
			break;
		case R.id.btn_donor:
			startActivity(new Intent(MainActivity.this,SignUp.class));
			break;
		case R.id.btn_general:
			startActivity(new Intent(MainActivity.this,InfoWeb.class));
			break;
		case R.id.btn_feedback:
			startActivity(new Intent(MainActivity.this,SignIn.class));
			break;
			


			
		default:
			break;
		}
	}
}
