package com.visionvow.terminal_run.linear;

public interface Matrix {
    /**
     * Returns whether this matrix can perform a Matrix Vector product with a vector
     *
     * @param v The vector which is to be compared
     * @return True if this matrix can, false otherwise
     */
    boolean canVectorProduct(Vector v);

    /**
     * Returns the resulting vector after a matrix vector product on this matrix
     *
     * @param v The initial vector
     * @return The resulting vector
     * @throws ArithmeticException If the vector fails to be multiplied
     */
    Vector getVectorProduct(Vector v);

    /**
     * Performs a left matrix product (This is the right matrix)
     *
     * @param m The matrix which is to be the left matrix
     * @return The matrix after performing a left product with m
     * @throws ArithmeticException If a left matrix product cannot be performed
     */
    Matrix leftMatrixProduct(Matrix m);

    /**
     * Performs a right matrix product (This is the left matrix)
     *
     * @param m The matrix which is to be the right matrix
     * @return The matrix after performing a right product with m
     * @throws ArithmeticException If a left matrix product cannot be performed
     */
    Matrix rightMatrixProduct(Matrix m);

    /**
     * Returns the number of rows of this matrix
     *
     * @return The number of rows
     */
    int getNumRows();

    /**
     * Returns the number of columns of this matrix
     *
     * @return The number of columns
     */
    int getNumColumns();

    /**
     * Returns the resulting matrix after multiplying by a scalar
     *
     * @param scalar The scalar to multiply
     * @return The resulting matrix
     */
    Matrix multiplyByScalar(double scalar);

    /**
     * Returns the determinant of this matrix
     *
     * @return The determinant
     * @throws ArithmeticException If the determinant of this matrix cannot be calculated
     */
    double getDeterminant();

    /**
     * Returns the inverse of this matrix
     *
     * @return The inverse
     * @throws ArithmeticException If this matrix is not invertible
     */
    Matrix getInverse();

    /**
     * Returns a vector corresponding to a column of this matrix
     *
     * @param c The column reference (columns start at 1)
     * @return The vector corresponding to c
     * @throws IndexOutOfBoundsException If the column is not in range
     */
    Vector getColumn(int c);
}
