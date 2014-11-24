package com.example.grouper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Group;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchableActivity extends Activity {
	
	//ListView
	private ListView lv;
	//ListView Adapter
	ArrayAdapter<Group> adapter;
	//Search EditText
	EditText inputSearch;
	//ArrayList for ListView
	ArrayList<HashMap<String, String>> groupList;
	DatabaseHandler db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		actionbar.setTitle(Html.fromHtml("<font color='#000000'>Search Groups</font>"));
		
		List<Group> groupList = db.getAllgroups();
		
		
		//Listveiw Data
		//String groups[] = {"Group 1", "Mobile App Dev", "Database Mgmt", "Database mgm 2",
							//"iPhone dev", "Java", "C#", "Networking", "Group 3", "LAN Group"};
		
		lv = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		
		//Adding items to ListView
		adapter = new ArrayAdapter<Group>(this, R.layout.searchable_list_item, R.id.searchable_group_name, groupList);
		lv.setAdapter(adapter);
		
		/**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchableActivity.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menuitem_settings) {
			Toast.makeText(this, getString(R.string.ui_menu_settings),
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
//	public void onListItemClick(ListView l, View v, int position, long id){
//		Group group = (Group)getListAdapter().getItem(position);
//		Intent intent = new Intent(getActivity().getApplicationContext(), GroupViewActivity.class);
//		intent.putExtra("index", group.getID());
//		startActivity(intent);
//	}
}
