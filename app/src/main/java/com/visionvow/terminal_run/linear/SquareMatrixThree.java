package com.visionvow.terminal_run.linear;

import java.util.ArrayList;

public class SquareMatrixThree implements Matrix {
    private double[][] values;

    /**
     * Create a 3x3 square matrix with 6 double values
     *
     * @param a11 top left value
     * @param a12 top middle value
     * @param a13 top right value
     * @param a21 middle left value
     * @param a22 true neutral value
     * @param a23 middle right value
     * @param a31 bottom left value
     * @param a32 bottom middle value
     * @param a33 bottom right value
     */
    public SquareMatrixThree(double a11, double a12, double a13, double a21, double a22, double a23,
                             double a31, double a32, double a33) {
        values = new double[3][3];
        values[0][0] = a11;
        values[0][1] = a12;
        values[0][2] = a13;
        values[1][0] = a21;
        values[1][1] = a22;
        values[1][2] = a23;
        values[2][0] = a31;
        values[2][1] = a32;
        values[2][2] = a33;
    }


    /**
     * Constructs a 3x3 square matrix with three vectors in R^3
     *
     * @param v1 The first vector in R^3, to make the first column
     * @param v2 The second vector in R^3, to make the second column
     * @param v3 The third vector in R^3, to make the third column
     * @throws ArithmeticException If any of the vectors are not in R^3
     */
    public SquareMatrixThree(Vector v1, Vector v2, Vector v3) {
        if (v1.getNumRows() != 3 || v2.getNumRows() != 3 || v3.getNumRows() != 3) {
            throw new ArithmeticException("Vectors are not of the right dimension");
        }
        values = new double[3][3];
        values[0][0] = v1.getRow(1);
        values[0][1] = v2.getRow(1);
        values[0][2] = v3.getRow(1);
        values[1][0] = v1.getRow(2);
        values[1][1] = v2.getRow(2);
        values[1][2] = v3.getRow(2);
        values[2][0] = v1.getRow(3);
        values[2][1] = v2.getRow(3);
        values[2][2] = v3.getRow(3);
    }

    @Override
    public int getNumRows() {
        return 3;
    }

    @Override
    public int getNumColumns() {
        return 3;
    }

    @Override
    public Vector getColumn(int c) {
        if (c == 1) {
            return new Vector(values[0][0], values[1][0], values[2][0]);
        } else if (c == 2) {
            return new Vector(values[0][1], values[1][1], values[2][1]);
        } else if (c == 3) {
            return new Vector(values[0][2], values[1][2], values[2][2]);
        } else {
            throw new IndexOutOfBoundsException("Invalid column for 3x3");
        }
    }

    /**
     * Returns the co-factor matrix of a coordinate
     *
     * @param r The row reference
     * @param c The column reference
     */
    private SquareMatrixTwo getCofactor(int r, int c) {
        int rowIndexReference = r - 1;
        int columnIndexReference = c - 1;
        ArrayList<Double> newValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != rowIndexReference && j != columnIndexReference) {
                    newValues.add(values[i][j]);
                }
            }
        }
        return new SquareMatrixTwo(newValues.get(0), newValues.get(1),
                newValues.get(2), newValues.get(3));
    }

    @Override
    public double getDeterminant() {
        //Find determinant by co-factor along first column
        SquareMatrixTwo A11 = new SquareMatrixTwo(values[1][1], values[1][2],
                values[2][1], values[2][2]);
        SquareMatrixTwo A21 = new SquareMatrixTwo(values[0][1], values[0][2],
                values[2][1], values[2][2]);
        SquareMatrixTwo A31 = new SquareMatrixTwo(values[0][1], values[0][2],
                values[1][1], values[1][2]);
        return (values[0][0] * A11.getDeterminant() - values[1][0] * A21.getDeterminant() +
                values[2][0] * A31.getDeterminant());
    }

    @Override
    public SquareMatrixThree getInverse() {
        double determinant = getDeterminant();
        if (determinant == 0) {
            throw new ArithmeticException("3x3 not invertible");
        }
        double detReciprocal = 1 / determinant;
        //Get values of adjugate matrix
        double a11 = getCofactor(1, 1).getDeterminant();
        double a12 = -1 * getCofactor(2, 1).getDeterminant();
        double a13 = getCofactor(3, 1).getDeterminant();
        double a21 = -1 * getCofactor(1, 2).getDeterminant();
        double a22 = getCofactor(2, 2).getDeterminant();
        double a23 = -1 * getCofactor(3, 2).getDeterminant();
        double a31 = getCofactor(1, 3).getDeterminant();
        double a32 = -1 * getCofactor(2, 3).getDeterminant();
        double a33 = getCofactor(3, 3).getDeterminant();

        SquareMatrixThree adjugate = new SquareMatrixThree(a11, a12, a13, a21, a22,
                a23, a31, a32, a33);
        return adjugate.multiplyByScalar(detReciprocal);
    }

    @Override
    public SquareMatrixThree multiplyByScalar(double scalar) {
        double a11 = values[0][0] * scalar;
        double a12 = values[0][1] * scalar;
        double a13 = values[0][2] * scalar;
        double a21 = values[1][0] * scalar;
        double a22 = values[1][1] * scalar;
        double a23 = values[1][2] * scalar;
        double a31 = values[2][0] * scalar;
        double a32 = values[2][1] * scalar;
        double a33 = values[2][2] * scalar;
        return new SquareMatrixThree(a11, a12, a13, a21, a22,
                a23, a31, a32, a33);
    }

    @Override
    public boolean canVectorProduct(Vector v) {
        return v.getNumRows() == 3;
    }

    @Override
    public Vector getVectorProduct(Vector v) {
        if (!canVectorProduct(v)) {
            throw new ArithmeticException("Vector Product 3x3 failed");
        }
        // Get new vector values
        double a1 = v.getRow(1) * values[0][0] + v.getRow(2) * values[0][1]
                + v.getRow(3) * values[0][2];
        double a2 = v.getRow(1) * values[1][0] + v.getRow(2) * values[1][1]
                + v.getRow(3) * values[1][2];
        double a3 = v.getRow(1) * values[2][0] + v.getRow(2) * values[2][1]
                + v.getRow(3) * values[2][2];
        return new Vector(a1, a2, a3);
    }

    @Override
    public SquareMatrixThree rightMatrixProduct(Matrix m) {
        if (m.getNumRows() != 3) {
            throw new ArithmeticException("Failed Matrix Product with 3x3 on left");
        }
        Vector v1 = getVectorProduct(m.getColumn(1));
        Vector v2 = getVectorProduct(m.getColumn(2));
        Vector v3 = getVectorProduct(m.getColumn(3));
        return new SquareMatrixThree(v1, v2, v3);
    }

    @Override
    public Matrix leftMatrixProduct(Matrix m) {
        return m.rightMatrixProduct(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SquareMatrixThree)) {
            return false;
        } else {
            SquareMatrixThree m = (SquareMatrixThree) obj;
            return getColumn(1).equals(m.getColumn(1)) && getColumn(2) == m.getColumn(2)
                    && getColumn(3).equals(m.getColumn(3));
        }
    }
}
