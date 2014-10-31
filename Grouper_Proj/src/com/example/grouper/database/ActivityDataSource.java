package com.example.grouper.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ActivityDataSource {
	//Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_ACTIVITYNAME, MySQLiteHelper.COLUMN_ACTIVITYDESCRIPTION };
	
	private static final String TAG = "ActivityDB";
	
	public ActivityDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	public void close(){
		dbHelper.close();
	}
	public Activity createActivity(String activityName, String activityDescription){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ACTIVITYNAME, activityName);
		values.put(MySQLiteHelper.COLUMN_ACTIVITYDESCRIPTION, activityDescription);
		long insertId = database.insert(MySQLiteHelper.TABLE_ACTIVITIES, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_ACTIVITIES, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Activity newActivity = cursorToActivity(cursor);
		cursor.close();
		return newActivity;
	}
	
	private Activity cursorToActivity(Cursor cursor) {
		Activity activity = new Activity();
		
		activity.setId(cursor.getLong(0));
		activity.setActivityName(cursor.getString(1));
		activity.setActivityDescription(cursor.getString(2));
		return activity;
	}
	public List<Activity> getAllActivites() {
		List<Activity> activities = new ArrayList<Activity>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_ACTIVITIES, 
				allColumns, null, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Activity activity = cursorToActivity(cursor);
			Log.d(TAG, "get comment = " + cursorToActivity(cursor).toString());
			activities.add(activity);
			cursor.moveToNext();
		}
		//Close cursor
		cursor.close();
		return activities;
	}
}
