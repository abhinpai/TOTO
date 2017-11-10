package com.example.blooddonar;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.blooddonar.adapters.SearchAdapter;
import com.example.blooddonar.utils.AppConstants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class SearchDetails extends AppCompatActivity {
	ListView mDonors;
	ArrayList<HashMap<String,String>>data=new ArrayList<HashMap<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		mDonors=(ListView)findViewById(R.id.lv);
		
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}

		getIntentdata();
		populateList();
	}

	private void populateList() {
		SearchAdapter adapter=new SearchAdapter(SearchDetails.this, data);
		mDonors.setAdapter(adapter);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getIntentdata() {

		data = ((ArrayList)getIntent().getSerializableExtra(AppConstants.KEY_DATA));
		
	}

}
