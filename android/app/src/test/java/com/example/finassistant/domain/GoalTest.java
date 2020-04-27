package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static junit.framework.TestCase.assertEquals;

public class GoalTest {

    @Test
    public void checkGoal() {
        Date date = new Date();
        Goal goal = new Goal("save 100 euros", 100, date);
        Assert.assertEquals(100, goal.getSumGoal());

    }

    @Test
    public void testTitle() {
        Goal goal = new Goal();
        goal.setTitle("New goal");
        Assert.assertEquals("New goal", goal.getTitle());
    }

    @Test
    public void testSumGaol() {
        Goal goal = new Goal();
        goal.setSumGoal(10090);
        Assert.assertEquals(10090, goal.getSumGoal());
    }

    @Test
    public void testInsertAmount(){
        Goal goal = new Goal();
        goal.setAmount(1000.0);
        Assert.assertEquals(1000.0, goal.getAmount(), 0.001);
    }

    @Test
    public void InsertEndDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date(233276400000L);
        Date parsedDate = formatter.parse("27-04-2020");
        assertEquals(myDate.getTime(), parsedDate.getTime());
        Goal goal = new Goal();
        goal.setEndDate(myDate);

    }

    @Test
    public void checkEndDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date(233276400000L);
        Date parsedDate = formatter.parse("27-04-2020");
        assertEquals(myDate.getTime(), parsedDate.getTime());
        Goal goal = new Goal();
        System.out.println("The goal date is: " + goal.getEndDate());
    }

}