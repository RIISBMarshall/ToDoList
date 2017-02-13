package com.example.bmarshall.todolist;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by bmarshall on 1/11/17.
 */

public class ToDoItem {

    private String title;
    private Calendar startDate;
    private Calendar dueDate;
    private Calendar reminderDateTime;

    private boolean startDateIsSet;
    private boolean startTimeIsSet;
    private boolean dueDateIsSet;
    private boolean dueTimeIsSet;

    private boolean isCompleted;
    private boolean reminderIsSet;

    private int reminderHour;


    public static LinkedList<ToDoItem> toDoItems = new LinkedList<ToDoItem>();

    public ToDoItem(String title) {
        this.title = title;

        startDate = new GregorianCalendar();
        dueDate = new GregorianCalendar();

        startDateIsSet = false;
        startTimeIsSet = false;
        dueDateIsSet = false;
        dueTimeIsSet = false;
        isCompleted = false;
        reminderIsSet = false;
    }

    public String getTitle() {
        return title;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public boolean getStartDateIsSet() {
        return startDateIsSet;
    }

    public boolean getDueDateIsSet() {
        return dueDateIsSet;
    }

    public boolean getIsComplete() {
        return isCompleted;
    }

    public int getReminderHour() {
        return reminderHour;
    }

    public boolean getReminderIsSet() {
        return reminderIsSet;
    }

    public Calendar getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(int newReminderTime) {
        reminderHour = newReminderTime;
        Calendar cal = new GregorianCalendar();
        cal.setTime(dueDate.getTime());
        cal.add(Calendar.HOUR, -reminderHour);
        reminderDateTime = cal;
        reminderIsSet = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(int year, int month, int dayOfMonth) {
        startDate.set(year, month, dayOfMonth, startDate.get(Calendar.HOUR_OF_DAY),
                startDate.get(Calendar.MINUTE));

        startDateIsSet = true;
    }

    public void setStartTime(int hourOfDay, int minute) {
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                startDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);

        startTimeIsSet = true;
    }

    public void setDueDate(int year, int month, int dayOfMonth) {
        dueDate.set(year, month, dayOfMonth, dueDate.get(Calendar.HOUR_OF_DAY),
                dueDate.get(Calendar.MINUTE));

        dueDateIsSet = true;
    }

    public void setDueTime(int hourOfDay, int minute) {
        dueDate.set(dueDate.get(Calendar.YEAR), dueDate.get(Calendar.MONTH),
                dueDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);

        dueTimeIsSet = true;
    }

    public void setReminderIsSet(boolean isSet) {
        reminderIsSet = isSet;
    }

    public void changeCompletionStatus() {
        isCompleted = !isCompleted;
    }

    public String toString() {
        if (isCompleted) {
            return "[COMPLETED]  " + title;
        } else {
            return title;
        }
    }
}
