package com.example.finassistant.domain;

import org.junit.Assert;
import org.junit.Test;

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


}