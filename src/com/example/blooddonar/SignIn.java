package com.example.blooddonar;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.example.blooddonar.handlers.AsyncTaskHandler;
import com.example.blooddonar.utils.AppConstants;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity implements OnClickListener{
	Button mLogin;
	EditText mUsername,mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		initialisation();
	}

	private void initialisation() {
		mLogin=(Button)findViewById(R.id.btn_login);
		mUsername=(EditText)findViewById(R.id.et_username);
		mPassword=(EditText)findViewById(R.id.et_password);
		mLogin.setOnClickListener(this);
		
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}

	}

	@Override
	public void onClick(View v) {
		if(isFormValid()){
			asynkLogin();
		}
	}

	private void asynkLogin() {
		ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_USERNAME,mUsername.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PASSWORD,mPassword.getText().toString()));

		AsyncTaskHandler ahandler=new AsyncTaskHandler(SignIn.this, nameValuePair, AppConstants.URL_LOGIN, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_LOGIN);
		ahandler.execute();
	}

	private boolean isFormValid() {
		Boolean status=true;

		if(TextUtils.isEmpty(mUsername.getText().toString())){
			mUsername.setError("Fill Username");
			status=false;
		}

		if(TextUtils.isEmpty(mPassword.getText().toString())){
			mPassword.setError("Fill Password");
			status=false;
		}
		return status;
	}

	public void onAsynkResult(int statusCode, String statusMsg) {
		Toast.makeText(SignIn.this, statusMsg, Toast.LENGTH_LONG).show();
		if(statusCode==AppConstants.ERROR_CODE_SUCCESS){
			if(AppConstants.PARAM_TYPE.equals("user"))
				startActivity(new Intent(SignIn.this,Home.class));
			else
				startActivity(new Intent(SignIn.this,HomeAdmin.class));

		}

	}
}
