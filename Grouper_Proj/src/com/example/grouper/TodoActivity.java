package com.example.grouper;

import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Group;
import com.example.grouper.database.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TodoActivity extends Activity {
	
	private EditText titleField;
	private EditText descriptionField;
	private Button submitButton;
	private Button cancelButton;
	List<Group> groupList;
	private DatabaseHandler db;
	private Group selectedGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtodo);
		
		titleField = (EditText)findViewById(R.id.newTitle);
		descriptionField = (EditText)findViewById(R.id.newDescription);
		submitButton = (Button)findViewById(R.id.submitButton);
		cancelButton = (Button)findViewById(R.id.cancelButton);
		db = new DatabaseHandler(this);
		db.getWritableDatabase();
		groupList = db.getAllgroups();
		db.close();
		//spinner to select group
		Spinner spinner = (Spinner) findViewById(R.id.group_select_spinner);
		ArrayAdapter<Group> adapter = new ArrayAdapter<Group>(this, 
				R.layout.searchable_list_item, R.id.searchable_group_name, groupList);
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		//get the selected group
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedGroup = (Group)parent.getItemAtPosition(position);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				String title = titleField.getText().toString();
				String description = descriptionField.getText().toString();
				
				Todo newTodo = new Todo(title, description, selectedGroup.getID());
				
				DatabaseHandler dbhandler = new DatabaseHandler(TodoActivity.this);
				dbhandler.addtodo(newTodo);
				dbhandler.close();
				titleField.setText("");
				descriptionField.setText("");
				
				Toast.makeText(TodoActivity.this, getString(R.string.new_todo_toast),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void backToMain(View v) {
        Intent intent = new Intent(TodoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
