package com.example.blooddonar.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "bloodbank.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_STATE = "state_details";
	private static final String TABLE_LOCATION = "place_details";
	public static String DB_NAME="bloodbank.db";
	private static String DB_PATH="";
	public static String KEY_EDITION="ed";
	public static final String KEY_TITLE="na";
	private final Context mcontext;
	private SQLiteDatabase database;
	protected static final String TAG= "DataAdapter" ;

	public DbHandler(Context context) {
		super(context,DB_NAME,null,1);
		DB_PATH=context.getApplicationInfo().dataDir+ "/databases/";
		this.mcontext= context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub


	}

	public boolean openDB() throws SQLException {
		DbHandler dbl=new DbHandler(mcontext);
		dbl.openDataBase();
		dbl.close();
		database = getWritableDatabase();
		database=getReadableDatabase();


		if(!database.isReadOnly())
		{
			database.execSQL("PRAGMA foreign_keys= ON;");

		}

		String mPath = DB_PATH + DB_NAME; 
		//Log.v("mPath", mPath); 
		database = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
	
		return database != null; 

	}
	public void createDataBase() throws IOException 
	{ 
		//If database not exists copy it from the assets 

		boolean mDataBaseExist = checkDataBase(); 
		if(!mDataBaseExist) 
		{ 
			this.getReadableDatabase(); 
			this.close(); 
			try  
			{ 
				//Copy the database from assests 
				copyDataBase(); 
				Log.e(TAG, "createDatabase database created"); 
			}  
			catch (IOException mIOException)  
			{ 
				throw new Error("ErrorCopyingDataBase"); 
			} 
		} 
	} 


	//Check that the database exists here: /data/data/your package/databases/Da Name 
	private boolean checkDataBase() 
	{ 
		File dbFile = new File(DB_PATH + DB_NAME); 
		//Log.v("dbFile", dbFile + "   "+ dbFile.exists()); 
		return dbFile.exists(); 
	} 

	//Copy the database from assets 
	private void copyDataBase() throws IOException 
	{ 
		InputStream mInput = mcontext.getAssets().open(DB_NAME); 
		String outFileName = DB_PATH + DB_NAME; 
		OutputStream mOutput = new FileOutputStream(outFileName); 
		byte[] mBuffer = new byte[1024]; 
		int mLength; 
		while ((mLength = mInput.read(mBuffer))>0) 
		{ 
			mOutput.write(mBuffer, 0, mLength); 
		} 
		mOutput.flush(); 
		mOutput.close(); 
		mInput.close(); 
	} 

	//Open the database, so we can query it 
	public boolean openDataBase() throws SQLException 
	{ 
		String mPath = DB_PATH + DB_NAME; 
		//Log.v("mPath", mPath); 
		database = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
		return database != null; 
	} 

	@Override 
	public synchronized void close()  
	{ 
		if(database != null) 
			database.close(); 
		super.close(); 
	}

	public ArrayList<String> getAllStates() {
		ArrayList<String>data=new ArrayList<String>();
		data.add(0, "Select City Name");
		String selectQuery = "SELECT  * FROM "+TABLE_STATE+";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				data.add(cursor.getString( cursor.getColumnIndex("state") ));
			} while (cursor.moveToNext());
		}
		db.close();
		return data;
	}

	public ArrayList<String> getPlaces(String state) {
		ArrayList<String>data=new ArrayList<String>();
		data.add(0, "Select Place Name");
		String selectQuery = "SELECT  p.place FROM "+TABLE_STATE+" s,"+TABLE_LOCATION+" p WHERE p.state_id=s.state_id " +
				"AND s.state like \""+state+"\";";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				data.add(cursor.getString( cursor.getColumnIndex("place") ));
			} while (cursor.moveToNext());
		}
		db.close();
		return data;
	}
}
