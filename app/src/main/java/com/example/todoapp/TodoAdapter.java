package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<TodoItem> todoItems;
    private OnTodoItemClickListener itemClickListener;

    public TodoAdapter(List<TodoItem> todoItems, OnTodoItemClickListener itemClickListener) {
        this.todoItems = todoItems;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        TodoItem todoItem = todoItems.get(position);
        holder.bind(todoItem);
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public interface OnTodoItemClickListener {
        void onTodoItemClick(int position);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CheckBox checkBox;
        private TextView textView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        public void bind(TodoItem todoItem) {
            checkBox.setChecked(todoItem.isCompleted());
            textView.setText(todoItem.getTaskDescription());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.onTodoItemClick(position);
            }
        }
    }
}

