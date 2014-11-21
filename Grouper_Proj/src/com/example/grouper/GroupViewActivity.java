package com.example.grouper;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Group;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class GroupViewActivity extends Activity {

	public Group _group;
	
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
		ActionBar.Tab mGroupEventTab = actionbar.newTab().setText(
				getString(R.string.ui_groupactivity_tabname_events));
		ActionBar.Tab mGroupTodoTab = actionbar.newTab().setText(
				getString(R.string.ui_groupactivity_tabname_todos));
		
		Fragment mGroupEventFragment = new GroupEventFragment();
		Fragment mGroupTodoFragment = new GroupTodoFragment();
		
		//bind the fragments to the tabs - set up tabListeners for each tab
		mGroupEventTab.setTabListener(new MyTabsListener(mGroupEventFragment,
				getApplicationContext()));
		mGroupTodoTab.setTabListener(new MyTabsListener(mGroupTodoFragment,
				getApplicationContext()));
		
		//add the tabs to the action bar
		actionbar.addTab(mGroupEventTab);
		actionbar.addTab(mGroupTodoTab);
		//connect to db
		DatabaseHandler db = new DatabaseHandler(this);
		
		//the group gets its values here from db.
		Bundle sentGroup = getIntent().getExtras();
		_group = db.getgroup(sentGroup.getInt("index"));
		
	}

}
