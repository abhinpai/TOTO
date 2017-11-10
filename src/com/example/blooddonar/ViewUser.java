package com.example.blooddonar;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.blooddonar.adapters.UserAdapter;
import com.example.blooddonar.handlers.AsyncTaskHandler;
import com.example.blooddonar.utils.AppConstants;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class ViewUser extends AppCompatActivity {
	ListView mLv;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		initialisation();
		viewUsers();
	}
	
	
	private void viewUsers() {
		AsyncTaskHandler ahandler=new AsyncTaskHandler(ViewUser.this, null, AppConstants.URL_GETDONOR, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_GETDONOR);
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
		UserAdapter adapter=new UserAdapter(ViewUser.this, data);
		mLv.setAdapter(adapter);
		
	}
	
	public void onResult(String statusMsg) {
		Snackbar.make(getCurrentFocus(), statusMsg, Snackbar.LENGTH_LONG).show();
	}

	public void onDeleteResult(String statusMsg) {
		viewUsers();
		
	}

}
