package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoItemClickListener {

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private List<TodoItem> todoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoItems = new ArrayList<>();
        todoAdapter = new TodoAdapter(todoItems, this);
        recyclerView.setAdapter(todoAdapter);

        // Add sample data
        todoItems.add(new TodoItem("Task 1", false));
        todoItems.add(new TodoItem("Task 2", true));
        todoItems.add(new TodoItem("Task 3", false));
        todoAdapter.notifyDataSetChanged();

        // Enable swipe-to-delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                todoItems.remove(position);
                todoAdapter.notifyItemRemoved(position);
                Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditTodoActivity.class);
                startActivityForResult(intent, AddEditTodoActivity.REQUEST_CODE_ADD_TODO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddEditTodoActivity.REQUEST_CODE_ADD_TODO && resultCode == RESULT_OK) {
            if (data != null) {
                TodoItem todoItem = (TodoItem) data.getSerializableExtra(AddEditTodoActivity.EXTRA_TODO_ITEM);
                if (todoItem != null) {
                    todoItems.add(todoItem);
                    todoAdapter.notifyItemInserted(todoItems.size() - 1);
                    Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onTodoItemClick(int position) {
        // Handle item click event
    }
}
