package com.example.grouper;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
}
