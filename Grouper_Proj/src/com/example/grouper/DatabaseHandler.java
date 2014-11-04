package com.example.grouper;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	//Static vars
	
	//database version
	private static final int DATABASE_VERSION = 1;
	//Database name
	private static final String DATABASE_NAME = "grouperManager";
	//Table Name
	private static final String TABLE_EVENTS = "events";
	//events table column names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_DESCRIPTION = "description";
	
	public DatabaseHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//Create Tables
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_DESCRIPTION + " TEXT" + ")";
		db.execSQL(CREATE_EVENTS_TABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//Drop old table if exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		//Create table again
		onCreate(db);
	}
	
	
	
	
	
	
	
	//CRUD operations
	//Add new event
	public void addEvent(Event event){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, event.getName()); //event Name
		values.put(KEY_DESCRIPTION, event.getDescription()); //event Description
		
		//Insert row
		db.insert(TABLE_EVENTS, null, values);
		db.close();
	}
	//Get single event
	Event getEvent(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_EVENTS, new String[]{ KEY_ID, KEY_NAME, KEY_DESCRIPTION }, KEY_ID + "=?", 
		new String[] {String.valueOf(id)}, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		Event event = new Event(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		
		return event;
	}
	//Get all events
	public List<Event> getAllEvents(){
		List<Event> eventList = new ArrayList<Event>();
		//Select all database query
		String selectQuery = "SELECT * FROM " + TABLE_EVENTS;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		//loop through all rows and add to the event list
		if (cursor.moveToFirst()) {
			do {
				Event event = new Event();
				event.setID(Integer.parseInt(cursor.getString(0)));
				event.setName(cursor.getString(1));
				event.setDescription(cursor.getString(2));
				//Add event to list
				eventList.add(event);
			} while (cursor.moveToNext());
		}
		cursor.close();
		//return event list
		return eventList;
	}
	//Updating single event
	public int updateEvent(Event event){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, event.getName());
		values.put(KEY_DESCRIPTION, event.getDescription());
		
		//update row
		return db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(event.getID())});
	}
	//Deleting single event
	public void deleteEvent(Event event){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_EVENTS, KEY_ID + " = ?", 
				new String[] { String.valueOf(event.getID())});
		db.close();
	}
	//Get event count
	public int getEventsCount(){
		String countQuery = "SELECT * FROM " + TABLE_EVENTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		
		//return count
		return cursor.getCount();
	}
}
