package com.example.grouper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.ActionBar.Tab;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//set action bar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//create Tabs
		ActionBar.Tab feedTab = actionbar.newTab().setText(
				getString(R.string.ui_tabs_feed));
		ActionBar.Tab discoverTab = actionbar.newTab().setText(
				getString(R.string.ui_tabs_discover));
		ActionBar.Tab todoTab = actionbar.newTab().setText(
				getString(R.string.ui_tabs_todo));
		
		
		//Fragments
		Fragment feedFrag = new Fragment();
		Fragment discoverFrag = new Fragment();
		Fragment todoFrag = new Fragment();
		
		//bind fragment
		
		// add the tabs to the action bar
		actionbar.addTab(feedTab);
		actionbar.addTab(discoverTab);
		actionbar.addTab(todoTab);
	}
}
