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
        Assert.assertEquals(100, goal.getAmount(),0.0001);

    }

    @Test
    public void testTitle() {
        Goal goal = new Goal();
        goal.setTitle("New goal");
        Assert.assertEquals("New goal", goal.getTitle());
    }

    @Test
    public void testAmount() {
        Goal goal = new Goal();
        goal.setAmount(1000.00);
        Assert.assertEquals(1000.00, goal.getAmount(),0.0001);
    }

    @Test
    public void testInsertAmount(){
        Goal goal = new Goal();
        goal.setAmount(-1000.0);
        Assert.assertEquals(0.0, goal.getAmount(), 0.001);
    }

    @Test
    public void checkEndDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = formatter.parse("27/04/2020");
        Goal goal = new Goal();
        goal.setEndDate(parsedDate);
        Assert.assertEquals(parsedDate,goal.getEndDate());
    }

    @Test
    public void testCurrentAmount() {
        Goal goal = new Goal();
        Assert.assertEquals(0.0, goal.getCurrentAmount(), 0.0001);
    }

    @Test
    public void testCompletion() {
        Goal goal = new Goal();
        goal.setAmount(89.51);
        goal.GoalCompletion(76.43);
        Assert.assertEquals(76.43, goal.getCurrentAmount(), 0.001);
    }

    @Test
    public void testCompletion2() {
        Goal goal = new Goal();
        goal.setAmount(32.53);
        goal.GoalCompletion(43.7);
        Assert.assertEquals(32.53, goal.getCurrentAmount(), 0.001);
    }
}