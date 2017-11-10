package com.example.blooddonar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.ProgressDialog;
public class InfoWeb extends AppCompatActivity {
	
	WebView mWebView;
	ProgressDialog mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_web);
		
		mWebView = (WebView) findViewById(R.id.webView1);
		mWebView.loadUrl("file:///android_asset/mobile/index.html");
		  mWebView.getSettings().setJavaScriptEnabled(true);
	      mWebView.getSettings().setDomStorageEnabled(true);
		
	}

	

	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();            
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
 
	 private class Geninfo extends WebViewClient {
	    	
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        	System.out.println("URL: " + url);
	        	view.loadUrl("javascript:changeLocation('" + url + "')");
	            return true;
	        }
	    	public void onPageFinished(WebView view, String url) {
	    		if(mProgress.isShowing()) {
	    			mProgress.dismiss();
	    		}
	    	}
	    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
