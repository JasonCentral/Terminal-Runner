package com.visionvow.terminal_run.linear;

/**
 * A 2 by 2 square matrix
 */
public class SquareMatrixTwo implements Matrix {
    private double a, b, c, d; //Top left, top right, bottom left, bottom right

    /**
     * Constructs a 2 by 2 square matrix with four values
     *
     * @param a top-left value
     * @param b top-right value
     * @param c bottom-left value
     * @param d bottom-right value
     */
    public SquareMatrixTwo(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Constructs a 2 by 2 square matrix with two vectors in R^2
     *
     * @param v1 The first vector in R^22, to make the first column
     * @param v2 The second vector in R^2, to make the second column
     * @throws ArithmeticException If either of the vectors are not in R^2
     */
    public SquareMatrixTwo(Vector v1, Vector v2) {
        if (v1.getNumRows() != 2 || v2.getNumRows() != 2){
            throw new ArithmeticException();
        }
        this.a = v1.getRow(1);
        this.c = v1.getRow(2);
        this.b = v2.getRow(1);
        this.d = v2.getRow(2);
    }

    @Override
    public double getDeterminant() {
        return a * d - b * c;
    }

    @Override
    public SquareMatrixTwo multiplyByScalar(double scalar) {
        return new SquareMatrixTwo(scalar * a, scalar * b, scalar * c, scalar * d);
    }

    @Override
    public SquareMatrixTwo getInverse() {
        double determinant = getDeterminant();
        if (determinant == 0) {
            throw new ArithmeticException("Not Invertible!");
        }
        double detReciprocal = 1 / determinant;
        return multiplyByScalar(detReciprocal);
    }

    @Override
    public boolean canVectorProduct(Vector v) {
        return v.getNumRows() == 2;
    }

    @Override
    public Vector getVectorProduct(Vector v) {
        if (!canVectorProduct(v)) {
            throw new ArithmeticException("Failed Vector Product!");
        }
        double a1 = a * v.getRow(1) + b * v.getRow(2);
        double a2 = c * v.getRow(1) + b * v.getRow(2);
        return new Vector(a1, a2);
    }

    @Override
    public int getNumColumns() {
        return 2;
    }

    @Override
    public int getNumRows() {
        return 2;
    }

    @Override
    public Vector getColumn(int c) {
        if (c == 1){
            return  new Vector(this.a, this.c);
        }
        else if (c == 2){
            return new Vector(this.b, this.c);
        }
        else{
            throw new IndexOutOfBoundsException("Column " + c + " does not exist.");
        }
    }

    @Override
    public SquareMatrixTwo rightMatrixProduct(Matrix m) {
        if (m.getNumColumns() != 2) {
            throw new ArithmeticException("Failed Matrix Product with 2x2 on left");
        }
        //Gets vectors needed to create new matrix
        Vector v1 = getVectorProduct(m.getColumn(1));
        Vector v2 = getVectorProduct(m.getColumn(2));
        return new SquareMatrixTwo(v1, v2);
    }

    @Override
    public Matrix leftMatrixProduct(Matrix m) {
        return m.rightMatrixProduct(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SquareMatrixTwo)){
            return false;
        }
        else{
            SquareMatrixTwo m = (SquareMatrixTwo)obj;
            return getColumn(1).equals(m.getColumn(1)) && getColumn(2) == m.getColumn(2);
        }
    }
}
