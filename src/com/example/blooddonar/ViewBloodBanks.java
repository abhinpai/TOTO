package com.example.blooddonar;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.blooddonar.adapters.BloodBankAdapter;
import com.example.blooddonar.handlers.AsyncTaskHandler;
import com.example.blooddonar.utils.AppConstants;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class ViewBloodBanks extends AppCompatActivity {
	ListView mLv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		initialisation();
		viewBloodBanks();
	}

	private void viewBloodBanks() {
		AsyncTaskHandler ahandler=new AsyncTaskHandler(ViewBloodBanks.this, null, AppConstants.URL_GETBLOODBANKS, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_GETBLOODBANK);
		ahandler.execute();
	}

	private void initialisation() {
		mLv=(ListView)findViewById(R.id.lv);

		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}
		
	}

	public void onResult(ArrayList<HashMap<String, String>> data) {
		BloodBankAdapter adapter=new BloodBankAdapter(ViewBloodBanks.this, data);
		mLv.setAdapter(adapter);
		
	}

	public void onResult(String statusMsg) {
		Snackbar.make(getCurrentFocus(), statusMsg, Snackbar.LENGTH_LONG).show();
	}

	public void onDeleteResult(String statusMsg) {
		viewBloodBanks();
		
	}
}
