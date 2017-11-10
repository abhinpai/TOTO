package com.example.blooddonar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.blooddonar.db.DbHandler;
import com.example.blooddonar.handlers.AsyncTaskHandler;
import com.example.blooddonar.utils.AppConstants;

public class SignUp extends AppCompatActivity implements OnClickListener,OnItemSelectedListener{
	private Button mRegister;
	EditText mName,mUsername,mPassword,mPhone,mEmail,mDob;
	Spinner mState,mPlace,mGender,mGroup;
	ArrayList<String>stateData=new ArrayList<String>();
	ArrayList<String>placeData=new ArrayList<String>();
	DbHandler dbhandler = null;
	Calendar myCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		initialisation();
		try {
			populateSpinners();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clickListener();

		myCalendar = Calendar.getInstance();

		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateDOB();
			}

		};

		mDob.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(SignUp.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}



	protected void updateDOB() {
		String myFormat = "yyyy-MM-dd"; 
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		mDob.setText(sdf.format(myCalendar.getTime()));

	}

	private void populateSpinners() throws IOException {

		dbhandler = new DbHandler(SignUp.this);

		dbhandler.createDataBase();
		stateData=dbhandler.getAllStates();


		ArrayAdapter<String>stateAdapter=new ArrayAdapter<String>
		(SignUp.this,android.R.layout.simple_dropdown_item_1line,stateData);
		mState.setAdapter(stateAdapter);


		ArrayAdapter<String>genderAdapter=new ArrayAdapter<String>
		(SignUp.this,android.R.layout.simple_dropdown_item_1line,this.getResources().getStringArray(R.array.gender));
		mGender.setAdapter(genderAdapter);
		
		ArrayAdapter<String>groupAdapter=new ArrayAdapter<String>
		(SignUp.this,android.R.layout.simple_dropdown_item_1line,this.getResources().getStringArray(R.array.groups));
		mGroup.setAdapter(groupAdapter);


	}

	private void clickListener() {
		mRegister.setOnClickListener(this);
		mState.setOnItemSelectedListener(this);
	}

	private void initialisation() {
		mRegister=(Button)findViewById(R.id.btn_register);
		mName=(EditText)findViewById(R.id.et_name);
		mUsername=(EditText)findViewById(R.id.et_username);
		mPassword=(EditText)findViewById(R.id.et_password);
		mEmail=(EditText)findViewById(R.id.et_email);
		mDob=(EditText)findViewById(R.id.et_dob);
		mGroup=(Spinner)findViewById(R.id.sp_group);
		mPhone=(EditText)findViewById(R.id.et_phone);
		mState=(Spinner)findViewById(R.id.sp_state);
		mPlace=(Spinner)findViewById(R.id.sp_place);
		mGender=(Spinner)findViewById(R.id.sp_gender);
		mPlace.setVisibility(View.GONE);
		

		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_register:
			if(isFormValid()){
				onRegister();
			}
			break;

		default:
			break;
		}
	}

	private boolean isFormValid() {
		Boolean status=true;

		if(TextUtils.isEmpty(mName.getText().toString())){
			mName.setError("Fill Name");
			status=false;
		}

		if(TextUtils.isEmpty(mUsername.getText().toString())){
			mUsername.setError("Fill Username");
			status=false;
		}

		if(mPassword.getText().length()<6 || mPassword.getText().length()>32 ){
			mPassword.setError("Enter valid Password");
			status=false;
		}

		if(mPhone.length()!=10){
			mPhone.setError("Enter valid phone number");
			status=false;
		}

		if(TextUtils.isEmpty(mEmail.getText().toString())){

			mEmail.setError("Fill Email");
			status=false;
		}

		if(TextUtils.isEmpty(mDob.getText().toString())){
			mDob.setError("Fill DOB");
			status=false;
		}
		
		if(mGroup.getSelectedItemPosition()==0){
			Toast.makeText(SignUp.this, "Select the blood group" , Toast.LENGTH_LONG).show();
		}
		if(mState.getSelectedItemPosition()==0){
			Toast.makeText(SignUp.this, "Select the City" , Toast.LENGTH_LONG).show();
		}
		if(mPlace.getSelectedItemPosition()==0){
			Toast.makeText(SignUp.this, "Select the Place" , Toast.LENGTH_LONG).show();
		}
		if(mGender.getSelectedItemPosition()==0){
			Toast.makeText(SignUp.this, "Select Gender" , Toast.LENGTH_LONG).show();
		}

		

		return status;
	}

	private void onRegister() {
		ArrayList<NameValuePair>nameValuePair=new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_USERNAME,mUsername.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PASSWORD,mPassword.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_NAME,mName.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_EMAIL,mEmail.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_DOB,mDob.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_GROUP,mGroup.getSelectedItem().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_GENDER,mGender.getSelectedItem().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PHONE,mPhone.getText().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_STATE,mState.getSelectedItem().toString()));
		nameValuePair.add(new BasicNameValuePair(AppConstants.KEY_PLACE,mPlace.getSelectedItem().toString()));


		AsyncTaskHandler ahandler=new AsyncTaskHandler(SignUp.this, nameValuePair, AppConstants.URL_REGISTER, AppConstants.MESSAGE_LOADING,AppConstants.PAGE_REGISTER);
		ahandler.execute();


	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		if(mState.getSelectedItemPosition()!=0){
			mPlace.setVisibility(View.VISIBLE);
			placeData=dbhandler.getPlaces(mState.getSelectedItem().toString());

			ArrayAdapter<String>placeAdapter=new ArrayAdapter<String>
			(SignUp.this,android.R.layout.simple_dropdown_item_1line,placeData);
			mPlace.setAdapter(placeAdapter);
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}



	public void onAsynkResult(int statusCode, String statusMsg) {
		Toast.makeText(SignUp.this, statusMsg, Toast.LENGTH_LONG).show();
		if(statusCode==AppConstants.ERROR_CODE_SUCCESS){
			startActivity(new Intent(SignUp.this,Home.class));
		}
	}

}
