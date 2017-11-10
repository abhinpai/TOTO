package com.example.blooddonar.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgresssDialog {
	ProgressDialog pDialog;
	public CustomProgresssDialog(){}

	public void showDialog(Context mContext, String msg) {
		pDialog = new ProgressDialog(mContext);
		pDialog.setMessage(msg);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	public void dismissDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}


}
