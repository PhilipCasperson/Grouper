package com.example.grouper;

import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Discover;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiscoverActivity extends ListActivity {

	private DatabaseHandler dbhandler;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discover);
		dbhandler = new DatabaseHandler(context);
		List<Discover> discoverEvents = dbhandler.getAllDiscoverEvents();
		ArrayAdapter<Discover> adapter = new ArrayAdapter<Discover>(context, android.R.layout.simple_list_item_1, discoverEvents);
		setListAdapter(adapter);
		
		for (Discover di : discoverEvents){
			String log = "Id: " + di.getID() + " , Name: " + di.getName() + " , Description" + di.getDescription();
			Log.d("Name: ", log);
		}
	}
	public void onListItemClick(ListView l, View v, long id){
		Intent intent = new Intent(context, DiscoverActivity.class);
		startActivity(intent);
	}
}
