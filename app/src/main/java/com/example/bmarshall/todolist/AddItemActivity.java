package com.example.bmarshall.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddItemActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText titleText;
    private EditText startDateText;
    private EditText startTimeText;
    private EditText dueDateText;
    private EditText dueTimeText;
    private Spinner reminderSpinner;
    private ToDoItem toDoItem;

    private boolean isStartPicker;
    private boolean isDuePicker;

    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;

    private DialogFragment datePicker;
    private DialogFragment timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dateFormat = new SimpleDateFormat("dd MMM yyyy");
        timeFormat = new SimpleDateFormat("hh:mm a");

        datePicker = new DatePickerFragment();
        timePicker = new TimePickerFragment();

        titleText = (EditText) findViewById(R.id.nameText);
        startDateText = (EditText) findViewById(R.id.startDate);
        startTimeText = (EditText) findViewById(R.id.startTime);
        dueDateText = (EditText) findViewById(R.id.dueDate);
        dueTimeText = (EditText) findViewById(R.id.dueTime);
        reminderSpinner = (Spinner) findViewById(R.id.reminderTime);

        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toDoItem.setReminderIsSet(false);
                } else {
                    toDoItem.setReminderIsSet(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (getIntent().hasExtra("itemToEdit")) {
            toDoItem = ToDoItem.toDoItems.get(getIntent().getIntExtra("itemToEdit", 0));
            titleText.setText(toDoItem.getTitle());

            String startDate = dateFormat.format(toDoItem.getStartDate().getTime());
            startDateText.setText(startDate);

            String startTime = timeFormat.format(toDoItem.getStartDate().getTime());
            startTimeText.setText(startTime);

            String dueDate = dateFormat.format(toDoItem.getDueDate().getTime());
            dueDateText.setText(dueDate);

            String dueTime = timeFormat.format(toDoItem.getDueDate().getTime());
            dueTimeText.setText(dueTime);

            reminderSpinner.setSelection(toDoItem.getReminderHour());

        } else {
            toDoItem = new ToDoItem("titleNotSet");
        }

        isStartPicker = false;
        isDuePicker = false;
    }

    public void showStartDatePickerDialog(View v) {
        datePicker.show(getSupportFragmentManager(), "startDatePicker");

        isStartPicker = true;
    }

    public void showStartTimePickerDialog(View v) {
        timePicker.show(getSupportFragmentManager(), "startTimePicker");

        isStartPicker = true;
    }

    public void showDueDatePickerDialog(View v) {
        datePicker.show(getSupportFragmentManager(), "dueDatePicker");

        isDuePicker = true;
    }

    public void showDueTimePickerDialog(View v) {
        timePicker.show(getSupportFragmentManager(), "dueTimePicker");

        isDuePicker = true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar date = new GregorianCalendar(year, month, day);
        String dateString = dateFormat.format(date.getTime());

        if (isStartPicker) {
            startDateText.setText(dateString);
            toDoItem.setStartDate(year, month, day);
        } else if (isDuePicker) {
            dueDateText.setText(dateString);
            toDoItem.setDueDate(year, month, day);
        }

        isStartPicker = false;
        isDuePicker = false;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar timeCal = new GregorianCalendar();
        timeCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        timeCal.set(Calendar.MINUTE, minute);

        String time = timeFormat.format(timeCal.getTime());

        if (isStartPicker) {
            startTimeText.setText(time);
            toDoItem.setStartTime(hourOfDay, minute);
        } else if (isDuePicker) {
            dueTimeText.setText(time);
            toDoItem.setDueTime(hourOfDay, minute);
        }

        isStartPicker = false;
        isDuePicker = false;
    }

    public void onSubmit(View view) {
        toDoItem.setTitle(titleText.getText().toString());

        if (toDoItem.getDueDateIsSet() && (reminderSpinner.getSelectedItemPosition() != 0)) {
            toDoItem.setReminderDateTime(reminderSpinner.getSelectedItemPosition());
        } else {
            toDoItem.setReminderIsSet(false);
        }

        if (getIntent().hasExtra("itemToEdit")) {
            ToDoItem.toDoItems.set(getIntent().getIntExtra("itemToEdit", 0), toDoItem);
        } else {
            ToDoItem.toDoItems.add(toDoItem);
        }

        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
