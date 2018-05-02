package com.visionvow.terminal_run;

import org.junit.Test;

import com.visionvow.terminal_run.linear.*;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void matrices_work() {
        Vector v1 = new Vector(3, 5, 2);
        Vector v2 = new Vector(2, 9, 2);
        Vector v3 = new Vector(1, -2, 1);
        SquareMatrixThree testThree = new SquareMatrixThree(v1, v2, v3);

        assertEquals(testThree.getDeterminant(), 13, 0);
    }
}