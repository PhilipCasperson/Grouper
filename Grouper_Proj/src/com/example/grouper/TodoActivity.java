package com.example.grouper;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Todo;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TodoActivity extends Activity {
	
	private EditText titleField;
	private EditText descriptionField;
	private Button submitButton;
	private Button cancelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtodo);
		
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		actionbar.setTitle(Html.fromHtml("<font color='#000000'>Add Todo</font>"));
		
		titleField = (EditText)findViewById(R.id.newTitle);
		descriptionField = (EditText)findViewById(R.id.newDescription);
		submitButton = (Button)findViewById(R.id.submitButton);
		cancelButton = (Button)findViewById(R.id.cancelButton);
		
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				String title = titleField.getText().toString();
				String description = descriptionField.getText().toString();
				
				Todo newTodo = new Todo(title, description);
				
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
