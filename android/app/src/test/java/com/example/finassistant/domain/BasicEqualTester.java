package com.example.finassistant.domain;

import org.junit.Assert;

/**
 * The type Basic equal tester.
 *
 * @param <T> the type parameter
 */
public class BasicEqualTester<T> {

    private T objectUnderTest;

    /**
     * Tests if objectUnderTest
     * equals null
     */
    public void otherObjectIsNull() {
        Assert.assertFalse(getObjectUnderTest().equals(null));

    }

    /**
     * Tests if objectUnderTest
     * and other Object don't have a state
     *
     * @param other is the other object we want to compare
     */
    public void bothObjectsHaveNoState(Object other) {
        Assert.assertEquals(objectUnderTest, other);
        Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }

    /**
     * Tests if objectUnderTest
     * and other Object don't have the same state
     *
     * @param other is the other object we want to compare
     */
    public void otherObjectsHasNoState(Object other) {
        Assert.assertFalse(objectUnderTest.equals(other));
        Assert.assertFalse(objectUnderTest.hashCode() == other.hashCode());
    }

    /**
     * Asserts if objectUnderTest
     * and other Object are of the same type
     *
     * @param other
     */
    public void otherObjectIsOfDifferentType(Object other) {
        Assert.assertFalse(objectUnderTest.equals(other));
    }

    /**
     * Asserts if objectUnderTest
     * and other Object refer to the same Object
     *
     * @param other
     */
    public void sameReferences(Object other) {
        Assert.assertEquals(objectUnderTest, other);
        Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }

    /**
     * Asserts if objectUnderTest
     * and other Object haven the same state
     *
     * @param other
     */
    public void bothObjectsHaveSameState(Object other) {
        Assert.assertEquals(objectUnderTest, other);
        Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
    }

    /**
     * Asserts if objectUnderTest
     * and other Object haven different states
     *
     * @param other
     */
    public void objectsHaveDifferentState(Object other) {
        Assert.assertFalse(objectUnderTest.equals(other));
        Assert.assertFalse(objectUnderTest.hashCode() == other.hashCode());
    }

    /**
     * sets the private field objectUnderTest
     *
     * @param objectUnderTest
     */
    public void setObjectUnderTest(T objectUnderTest) {
        this.objectUnderTest = objectUnderTest;
    }

    /**
     * gets the private field objectUnderTest
     *
     * @return objectUnderTest
     */
    public T getObjectUnderTest() {
        return objectUnderTest;
    }
}

