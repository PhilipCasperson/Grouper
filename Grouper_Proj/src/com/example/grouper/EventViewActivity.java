package com.example.grouper;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Event;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class EventViewActivity extends Activity {
	public Event _event;
	TextView eventTitle;
	TextView eventDescription;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventview);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		actionbar.setTitle(Html.fromHtml("<font color='#000000'>Event Details</font>"));
		
		//connect to db
		DatabaseHandler db = new DatabaseHandler(this);
		//the event intent
		Bundle sentEvent = getIntent().getExtras();
		_event = db.getEvent(sentEvent.getInt("index"));
		
		TextView eventTitle = (TextView) findViewById(R.id.eventTitle);
		TextView eventDescription = (TextView) findViewById(R.id.eventDescription);
		
		eventTitle.setText(_event.getName());
		eventDescription.setText(_event.getDescription());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem_search:
			Intent intent = new Intent(this, SearchableActivity.class);
			startActivity(intent);
			return true;
		case R.id.menuitem_new_todo:
			Intent todoIntent = new Intent(this, TodoActivity.class);
			startActivity(todoIntent);
			return true;
		}
		return false;
	}
}
