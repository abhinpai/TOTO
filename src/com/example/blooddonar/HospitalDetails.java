package com.example.blooddonar;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.blooddonar.adapters.HospitalSearchAdapter;
import com.example.blooddonar.utils.AppConstants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class HospitalDetails extends AppCompatActivity {
	ListView mLv;
	ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		mLv=(ListView)findViewById(R.id.lv);
		
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}
		getIntentdata();
		populateList();
	}

	private void populateList() {
		HospitalSearchAdapter adapter=new HospitalSearchAdapter(HospitalDetails.this, data);
		mLv.setAdapter(adapter);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getIntentdata() {

		data = ((ArrayList)getIntent().getSerializableExtra(AppConstants.KEY_DATA));
		
	}

}
