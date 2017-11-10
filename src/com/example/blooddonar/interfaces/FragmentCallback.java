package com.example.blooddonar.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface FragmentCallback {
	public void onTaskDone(String msg,int code);
	public void onTaskDone(ArrayList<HashMap<String, String>> data);

}