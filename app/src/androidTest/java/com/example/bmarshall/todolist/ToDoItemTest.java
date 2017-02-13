package com.example.bmarshall.todolist;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ToDoItemTest {
    private ToDoItem toDoItem;

    @Before
    public void setUp() {
        toDoItem = new ToDoItem("test");
    }

    @Test
    public void getTitle() throws Exception {
        String title = toDoItem.getTitle();
        assertEquals("test", title);
    }

    @Test
    public void getStartDate() throws Exception {

        toDoItem.setStartDate(2017, 3, 19);
        toDoItem.setStartTime(5, 20);

        Calendar startDate = toDoItem.getStartDate();

        assertEquals(2017, startDate.get(Calendar.YEAR));
        assertEquals(3, startDate.get(Calendar.MONTH));
        assertEquals(19, startDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(5, startDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(20, startDate.get(Calendar.MINUTE));


    }

    @Test
    public void getDueDate() throws Exception {
        toDoItem.setDueDate(2018, 4, 20);
        toDoItem.setDueTime(9, 40);

        Calendar dueDate = toDoItem.getDueDate();

        assertEquals(2018, dueDate.get(Calendar.YEAR));
        assertEquals(4, dueDate.get(Calendar.MONTH));
        assertEquals(20, dueDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(9, dueDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(40, dueDate.get(Calendar.MINUTE));
    }

    @Test
    public void getStartDateIsSet() throws Exception {
        Boolean startDateIsSet = toDoItem.getStartDateIsSet();

        assertEquals(false, startDateIsSet);

        toDoItem.setStartDate(2010, 5, 6);

        startDateIsSet = toDoItem.getStartDateIsSet();

        assertEquals(true, startDateIsSet);
    }

    @Test
    public void getDueDateIsSet() throws Exception {
        Boolean dueDateIsSet = toDoItem.getDueDateIsSet();

        assertEquals(false, dueDateIsSet);

        toDoItem.setDueDate(2010, 5, 6);

        dueDateIsSet = toDoItem.getDueDateIsSet();

        assertEquals(true, dueDateIsSet);
    }

    @Test
    public void getIsComplete() throws Exception {
        Boolean isComplete = toDoItem.getIsComplete();

        assertEquals(false, toDoItem.getIsComplete());

        toDoItem.changeCompletionStatus();

        isComplete = toDoItem.getIsComplete();

        assertEquals(true, isComplete);
    }

    @Test
    public void getReminderHour() throws Exception {
        toDoItem.setReminderDateTime(5);

        assertEquals(5, toDoItem.getReminderHour());
    }

    @Test
    public void getReminderIsSet() throws Exception {
        assertEquals(false, toDoItem.getReminderIsSet());

        toDoItem.setReminderDateTime(5);

        assertEquals(true, toDoItem.getReminderIsSet());
    }

    @Test
    public void getReminderDateTime() throws Exception {
        toDoItem.setDueDate(2018, 4, 20);
        toDoItem.setDueTime(9, 40);

        toDoItem.setReminderDateTime(5);

        Calendar reminderDate = toDoItem.getReminderDateTime();

        assertEquals(2018, reminderDate.get(Calendar.YEAR));
        assertEquals(4, reminderDate.get(Calendar.MONTH));
        assertEquals(20, reminderDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(4, reminderDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(40, reminderDate.get(Calendar.MINUTE));
    }

    @Test
    public void setReminderDateTime() throws Exception {
        toDoItem.setDueDate(2018, 4, 20);
        toDoItem.setDueTime(9, 40);

        toDoItem.setReminderDateTime(5);

        Calendar reminderDate = toDoItem.getReminderDateTime();

        assertEquals(2018, reminderDate.get(Calendar.YEAR));
        assertEquals(4, reminderDate.get(Calendar.MONTH));
        assertEquals(20, reminderDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(4, reminderDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(40, reminderDate.get(Calendar.MINUTE));
    }

    @Test
    public void setTitle() throws Exception {
        toDoItem.setTitle("testingSetTitle");
        assertEquals("testingSetTitle", toDoItem.getTitle());
    }

    @Test
    public void setStartDate() throws Exception {
        toDoItem.setStartDate(2017, 3, 19);
        toDoItem.setStartTime(5, 20);

        Calendar startDate = toDoItem.getStartDate();

        assertEquals(2017, startDate.get(Calendar.YEAR));
        assertEquals(3, startDate.get(Calendar.MONTH));
        assertEquals(19, startDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(5, startDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(20, startDate.get(Calendar.MINUTE));
    }

    @Test
    public void setStartTime() throws Exception {
        toDoItem.setStartDate(2017, 3, 19);
        toDoItem.setStartTime(5, 20);

        Calendar startDate = toDoItem.getStartDate();

        assertEquals(2017, startDate.get(Calendar.YEAR));
        assertEquals(3, startDate.get(Calendar.MONTH));
        assertEquals(19, startDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(5, startDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(20, startDate.get(Calendar.MINUTE));
    }

    @Test
    public void setDueDate() throws Exception {
        toDoItem.setDueDate(2018, 4, 20);
        toDoItem.setDueTime(9, 40);

        Calendar dueDate = toDoItem.getDueDate();

        assertEquals(2018, dueDate.get(Calendar.YEAR));
        assertEquals(4, dueDate.get(Calendar.MONTH));
        assertEquals(20, dueDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(9, dueDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(40, dueDate.get(Calendar.MINUTE));
    }

    @Test
    public void setDueTime() throws Exception {
        toDoItem.setDueDate(2018, 4, 20);
        toDoItem.setDueTime(9, 40);

        Calendar dueDate = toDoItem.getDueDate();

        assertEquals(2018, dueDate.get(Calendar.YEAR));
        assertEquals(4, dueDate.get(Calendar.MONTH));
        assertEquals(20, dueDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(9, dueDate.get(Calendar.HOUR_OF_DAY));
        assertEquals(40, dueDate.get(Calendar.MINUTE));
    }

    @Test
    public void setReminderIsSet() throws Exception {
        assertEquals(false, toDoItem.getReminderIsSet());

        toDoItem.setReminderIsSet(true);

        assertEquals(true, toDoItem.getReminderIsSet());
    }

    @Test
    public void changeCompletionStatus() throws Exception {
        assertEquals(false, toDoItem.getIsComplete());

        toDoItem.changeCompletionStatus();

        assertEquals(true, toDoItem.getIsComplete());

        toDoItem.changeCompletionStatus();

        assertEquals(false, toDoItem.getIsComplete());
    }

}