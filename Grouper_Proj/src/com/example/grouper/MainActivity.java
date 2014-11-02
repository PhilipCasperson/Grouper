package com.example.grouper;


import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		actionbar.setStackedBackgroundDrawable(new ColorDrawable(Color.WHITE));
		actionbar.setTitle(Html.fromHtml("<font color='#000000'>Grouper</font>"));
		
		//create new tabs and set up the titles of the tabs
		ActionBar.Tab mFeedTab = actionbar.newTab().setText(
				getString(R.string.ui_tabname_feed));
		ActionBar.Tab mDiscoverTab = actionbar.newTab().setText(
				getString(R.string.ui_tabname_discover));
		ActionBar.Tab mToDoTab = actionbar.newTab().setText(
				getString(R.string.ui_tabname_todo));
		
		//create the fragments
		Fragment mFeedFragment = new FeedFragment();
		Fragment mDiscoverFragment = new DiscoverFragment();
		Fragment mToDoFragment = new ToDoFragment();
		
		//bind the fragments to the tabs - set up tabListeners for each tab
		mFeedTab.setTabListener(new MyTabsListener(mFeedFragment,
				getApplicationContext()));
		mDiscoverTab.setTabListener(new MyTabsListener(mDiscoverFragment,
				getApplicationContext()));
		mToDoTab.setTabListener(new MyTabsListener(mToDoFragment,
				getApplicationContext()));
				
		//add the tabs to the action bar
		actionbar.addTab(mFeedTab);
		actionbar.addTab(mDiscoverTab);
		actionbar.addTab(mToDoTab);
		
		//connect to db
		DatabaseHandler db = new DatabaseHandler(this);
		/**
		 * CRUD Operations, hope this works
		 */
		//Insert activities
		Log.d("Insert: ", "Inserting...");
		db.addEvent(new Event("Added to group", "Phil added to group"));
		db.addEvent(new Event("Document added by Jared", "Jared added new document"));
		db.addEvent(new Event("Document added by Kyle", "Kyle added new document"));
		db.addEvent(new Event("Document added by Phil", "Phil added new document"));
		db.addEvent(new Event("Document changed by Kyle ", "Member added new document"));
		db.addEvent(new Event("Document changed by Jared", "Member added new document"));
		db.addEvent(new Event("Added to group", "Phil added to group"));
		db.addEvent(new Event("Document added by Jared", "Jared added new document"));
		db.addEvent(new Event("Document added by Kyle", "Kyle added new document"));
		db.addEvent(new Event("Document added by Phil", "Phil added new document"));
		db.addEvent(new Event("Document changed by Kyle ", "Member added new document"));
		db.addEvent(new Event("Document changed by Jared", "Member added new document"));
		//Read all events
		Log.d("Reading ", "Reading events");
		List<Event> events = db.getAllEvents();
		
		for (Event ev : events){
			String log = "Id: " + ev.getID() + ", Name: " + ev.getName() + " , Description" + ev.getDescription();
			//Write events to log
			Log.d("Name: ", log);
		}
		db.close();
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
			Toast.makeText(this, getString(R.string.ui_menu_search),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_settings:
			Toast.makeText(this, getString(R.string.ui_menu_settings),
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}
	
	class MyTabsListener implements ActionBar.TabListener {
		public Fragment fragment;
		public Context context;
		
		public MyTabsListener(Fragment fragment, Context context) {
			this.fragment = fragment;
			this.context = context;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
		}

	}


