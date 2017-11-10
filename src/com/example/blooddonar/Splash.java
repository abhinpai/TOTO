package com.example.blooddonar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
		Thread timer = new Thread()
		{
			public void run()
			{
				try 
				{
					sleep(3000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				} finally
				{
					startActivity(new Intent(Splash.this,MainActivity.class));
					//startActivity(menu);
					
				}
			}
		};timer.start();
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//ourSong.release();
		//kills the splash activity once it has been displayed at the start.
		finish();
	}
}
