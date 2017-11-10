package com.example.blooddonar;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.example.blooddonar.handlers.AsyncTaskHandler;
import com.example.blooddonar.utils.AppConstants;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddBloodBanks extends AppCompatActivity implements OnClickListener{
	EditText mName,mPlace,mPhone,mEmail;
	Button mSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_blood_banks);
		initialisation();
	}

	private void initialisation() {
		mName=(EditText)findViewById(R.id.et_name);
		mPhone=(EditText)findViewById(R.id.et_phone);
		mPlace=(EditText)findViewById(R.id.et_place);
		mEmail=(EditText)findViewById(R.id.et_email);
		mSend=(Button)findViewById(R.id.btn_send);
		mSend.setOnClickListener(this);
		

		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:
			if(isFormValid()){
				AsynkBloodBank();
			}
			break;

		default:
			break;
		}

	}

	private void AsynkBloodBank() {
		ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();

		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PLACE,mPlace.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_NAME,mName.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PHONE,mPhone.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_EMAIL,mEmail.getText().toString()));
		AsyncTaskHandler ahandler=new AsyncTaskHandler(AddBloodBanks.this, nameValuePair, AppConstants.URL_BLOODBANK, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_BLOODBANK);
		ahandler.execute();
	}

	private boolean isFormValid() {
		Boolean status=true;

		if(TextUtils.isEmpty(mName.getText().toString())){
			mName.setError("Fill Hospital Name");
			status=false;
		}

		if(TextUtils.isEmpty(mPlace.getText().toString())){
			mPlace.setError("Fill Place");
			status=false;
		}


		if(TextUtils.isEmpty(mPhone.getText().toString())){

			mPhone.setError("Fill Phone Number");
			status=false;
		}

		if(TextUtils.isEmpty(mEmail.getText().toString())){

			mEmail.setError("Fill Email");
			status=false;
		}

		return status;
	}

	public void onAsynkResult(int statusCode, String statusMsg) {
		Snackbar.make(getCurrentFocus(), statusMsg, Snackbar.LENGTH_LONG).show();
		mName.setText("");
		mEmail.setText("");
		mPlace.setText("");
		mPhone.setText("");
		
		mName.setHint("Name");
		mEmail.setHint("Email");
		mPlace.setHint("Place");
		mPhone.setHint("Phone");
		
	}
}
