package com.example.todoapp;

public class TodoItem {
    private String taskDescription;
    private boolean isCompleted;

    public TodoItem(String taskDescription, boolean b) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

