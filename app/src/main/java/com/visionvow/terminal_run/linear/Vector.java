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
