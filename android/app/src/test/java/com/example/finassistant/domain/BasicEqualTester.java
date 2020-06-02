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
     * Other object is null.
     */
    public void otherObjectIsNull() {
            Assert.assertFalse(getObjectUnderTest().equals(null));
        }

    /**
     * Both objects have no state.
     *
     * @param other the other
     */
    public void bothObjectsHaveNoState(Object other) {
            Assert.assertEquals(objectUnderTest, other);
            Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
        }

    /**
     * Other objects has no state.
     *
     * @param other the other
     */
    public void otherObjectsHasNoState(Object other) {
            Assert.assertFalse(objectUnderTest.equals(other) );
            Assert.assertFalse(objectUnderTest.hashCode() == other.hashCode());
        }


    /**
     * Other object is of different type.
     *
     * @param other the other
     */
    public void otherObjectIsOfDifferentType(Object other) {
            Assert.assertFalse(objectUnderTest.equals(other));
        }

    /**
     * Same references.
     *
     * @param other the other
     */
    public void sameReferences(Object other) {
            Assert.assertEquals(objectUnderTest, other);
            Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
        }


    /**
     * Both objects have same state.
     *
     * @param other the other
     */
    public void bothObjectsHaveSameState(Object other) {
            Assert.assertEquals(objectUnderTest, other);
            Assert.assertEquals(objectUnderTest.hashCode(), other.hashCode());
        }

    /**
     * Objects have different state.
     *
     * @param other the other
     */
    public void objectsHaveDifferentState(Object other) {
            Assert.assertFalse(objectUnderTest.equals(other));
            Assert.assertFalse(objectUnderTest.hashCode() == other.hashCode());
        }

    /**
     * Sets object under test.
     *
     * @param objectUnderTest the object under test
     */
    public void setObjectUnderTest(T objectUnderTest) {
            this.objectUnderTest = objectUnderTest;
        }

    /**
     * Gets object under test.
     *
     * @return the object under test
     */
    public T getObjectUnderTest() {
            return objectUnderTest;
        }
    }

