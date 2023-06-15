package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditTodoActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_ITEM = "extra_todo_item";
    public static final int REQUEST_CODE_ADD_TODO = 1;

    private EditText editTextTodo;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);

        editTextTodo = findViewById(R.id.edit_text_task_description);
        buttonSave = findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoText = editTextTodo.getText().toString().trim();
                if (!todoText.isEmpty()) {
                    TodoItem todoItem = new TodoItem(todoText, false);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(EXTRA_TODO_ITEM, (CharSequence) todoItem);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
