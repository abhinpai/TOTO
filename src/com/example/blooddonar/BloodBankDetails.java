package com.example.blooddonar;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.blooddonar.adapters.BloodBankSearchAdapter;
import com.example.blooddonar.utils.AppConstants;

public class BloodBankDetails extends AppCompatActivity {
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
		BloodBankSearchAdapter adapter=new BloodBankSearchAdapter(BloodBankDetails.this, data);
		mLv.setAdapter(adapter);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getIntentdata() {

		data = ((ArrayList)getIntent().getSerializableExtra(AppConstants.KEY_DATA));
		
	}

}
