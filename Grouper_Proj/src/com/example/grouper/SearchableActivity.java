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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		db = new DatabaseHandler(this);
		db.getWritableDatabase();
		List<Group> groupList = db.getAllgroups();
		db.close();
		
		//Listveiw Data
		//String groups[] = {"Group 1", "Mobile App Dev", "Database Mgmt", "Database mgm 2",
							//"iPhone dev", "Java", "C#", "Networking", "Group 3", "LAN Group"};
		
		lv = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		
		//Adding items to ListView
		adapter = new ArrayAdapter<Group>(this, R.layout.searchable_list_item, R.id.searchable_group_name, groupList);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Group group = (Group)adapter.getItem(position);
				Intent intent = new Intent(getApplicationContext(), GroupViewActivity.class);
				intent.putExtra("index", group.getID());
				startActivity(intent);
			}
			
		});
		
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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem_map:
			Intent mapIntent = new Intent(this, MapActivity.class);
			startActivity(mapIntent);
			return true;
		case R.id.menuitem_new_todo:
			Intent todoIntent = new Intent(this, TodoActivity.class);
			startActivity(todoIntent);
			return true;
		}
		return false;
	}
	
//	public void onListItemClick(ListView l, View v, int position, long id){
//		Group group = (Group)getListAdapter().getItem(position);
//		Intent intent = new Intent(getActivity().getApplicationContext(), GroupViewActivity.class);
//		intent.putExtra("index", group.getID());
//		startActivity(intent);
//	}
}
