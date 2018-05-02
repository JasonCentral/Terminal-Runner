package com.visionvow.terminal_run.linear;

import java.util.ArrayList;

public class Vector {
    private int numRows;
    private ArrayList<Double> values;

    /**
     * Create a vector in whichever dimension you want
     *
     * @param values Each value corresponds to the next row
     */
    public Vector(double... values) {
        this.values = new ArrayList<>();
        for (double value : values) {
            this.values.add(value);
        }
        numRows = values.length;
    }

    /**
     * Returns n such that this vector is in R^n
     *
     * @return Such an n
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the r'th row of this vector
     *
     * @param r The row value to get (starts indexing at 1)
     * @return The value
     */
    public double getRow(int r) {
        return values.get(r - 1);
    }

    /**
     * Mutates this vector by adding another vector onto this one
     *
     * @param v The vector to add
     * @throws ArithmeticException If the vector cannot be added (wrong dimension)
     */
    public void addVector(Vector v) {
        if (!(numRows == v.getNumRows())) {
            throw new ArithmeticException("Vectors cannot be added");
        }
        for (int i = 0; i < numRows; i++) {
            values.set(i, values.get(i) + v.getRow(i + 1));
        }
    }

    /**
     * Mutates this vector by subtracting another vector from this one
     *
     * @param v The vector to subtract
     * @throws ArithmeticException If the vector cannot be subtracted (wrong dimension)
     */
    public void subtractVector(Vector v) {
        if (!(numRows == v.getNumRows())) {
            throw new ArithmeticException("Vectors cannot be subtracted");
        }
        for (int i = 0; i < numRows; i++) {
            values.set(i, values.get(i) - v.getRow(i + 1));
        }
    }

    /**
     * Mutates this vector by multiplying by a scalar
     *
     * @param s A scalar to multiply by
     */
    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < numRows; i++) {
            values.set(i, i * scalar);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        } else {
            Vector v = (Vector) obj;
            for (int i = 1; i <= v.getNumRows(); i++) {
                if (getRow(i) != v.getRow(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
