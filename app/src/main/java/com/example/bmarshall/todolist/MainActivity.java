package com.example.bmarshall.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int currentSelectedItem;
    private SimpleDateFormat dueDateFormat;
    private ListView toDoListView;
    private ListView reminderListView;
    private ArrayAdapter<ToDoItem> adapter;
    private ArrayAdapter<String> remAdapter;
    private ArrayList<String> reminderStringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dueDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm");
        currentSelectedItem = 0;
        reminderStringList = new ArrayList<>();

        //Instantiate Views
        toDoListView = (ListView) findViewById(R.id.toDo_list);
        reminderListView = (ListView) findViewById(R.id.reminderList);

        //Instantiate Adapters
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                ToDoItem.toDoItems);
        remAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                reminderStringList);

        //add items with reminders to reminder list
        for (ToDoItem toDoItem : ToDoItem.toDoItems) {
            if (toDoItem.getReminderIsSet() && !toDoItem.getIsComplete()) {
                String dueDate = dueDateFormat.format(toDoItem.getDueDate().getTime());
                String rem = (toDoItem.getTitle() + " [" + dueDate + "]");
                reminderStringList.add(rem);
            }
        }

        //Set Adapters
        toDoListView.setAdapter(adapter);
        reminderListView.setAdapter(remAdapter);

        //Set click listener for to do list. brings up view of to do item on click
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewItemActivity.class);
                currentSelectedItem = position;
                intent.putExtra(ViewItemActivity.EXTRA_ITEMNO, currentSelectedItem);
                startActivity(intent);
            }
        });
    }

    //Set method to handle clicks on the add item button
    public void onClickAddItem(View view) {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
    }
}
