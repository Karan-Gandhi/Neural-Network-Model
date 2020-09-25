import java.util.Arrays;
import java.util.ArrayList;

public class Matrix
{
    int rows;
    int cols;
    double[][] matrix;
    
    public Matrix(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                this.matrix[i][j] = 0.0;
            }
        }
    }
    
    public void randomSize() {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                this.matrix[i][j] = Math.random() * 2.0 - 1.0;
            }
        }
    }
    
    public static Matrix fromArray(final double[][] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++i) {
                matrix.matrix[i][j] = array[i][j];
            }
        }
        return matrix;
    }
    
    public static Matrix fromArray(final int[][] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++i) {
                matrix.matrix[i][j] = array[i][j];
            }
        }
        return matrix;
    }
    
    public static Matrix fromArray(final long[][] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++i) {
                matrix.matrix[i][j] = (double)array[i][j];
            }
        }
        return matrix;
    }
    
    public static Matrix fromArray(final short[][] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++i) {
                matrix.matrix[i][j] = array[i][j];
            }
        }
        return matrix;
    }
    
    public static Matrix fromArray(final float[][] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++i) {
                matrix.matrix[i][j] = array[i][j];
            }
        }
        return matrix;
    }
    
    public static Matrix fromArray(final byte[][] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++i) {
                matrix.matrix[i][j] = array[i][j];
            }
        }
        return matrix;
    }
    
    public static Matrix fromArray(final double[] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            matrix.matrix[i][0] = array[i];
        }
        return matrix;
    }
    
    public static Matrix fromArray(final int[] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            matrix.matrix[i][0] = array[i];
        }
        return matrix;
    }
    
    public static Matrix fromArray(final long[] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            matrix.matrix[i][0] = (double)array[i];
        }
        return matrix;
    }
    
    public static Matrix fromArray(final short[] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            matrix.matrix[i][0] = array[i];
        }
        return matrix;
    }
    
    public static Matrix fromArray(final float[] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            matrix.matrix[i][0] = array[i];
        }
        return matrix;
    }
    
    public static Matrix fromArray(final byte[] array) {
        final Matrix matrix = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; ++i) {
            matrix.matrix[i][0] = array[i];
        }
        return matrix;
    }
    
    public double[] toArray() {
        final double[] array = new double[this.rows * this.cols];
        final ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                list.add(this.matrix[i][j]);
            }
        }
        for (int k = 0; k < list.size(); ++k) {
            array[k] = list.get(k);
        }
        return array;
    }
    
    static Matrix multiply(final Matrix matrix, final Matrix matrix2) {
        if (!(matrix instanceof Matrix) || !(matrix2 instanceof Matrix)) {
            return null;
        }
        if (matrix.cols != matrix2.rows) {
            System.out.println("Columns of A must match columns of B");
            return null;
        }
        final Matrix matrix3 = new Matrix(matrix.rows, matrix2.cols);
        for (int i = 0; i < matrix3.rows; ++i) {
            for (int j = 0; j < matrix3.cols; ++j) {
                double n = 0.0;
                for (int k = 0; k < matrix.cols; ++k) {
                    n += matrix.matrix[i][k] * matrix2.matrix[k][j];
                }
                matrix3.matrix[i][j] = n;
            }
        }
        return matrix3;
    }
    
    public void multiply(final Matrix matrix) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n = j;
                array[n] *= matrix.matrix[i][j];
            }
        }
    }
    
    public void multiply(final double n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] *= n;
            }
        }
    }
    
    public void add(final int n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] += n;
            }
        }
    }
    
    public void add(final short n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] += n;
            }
        }
    }
    
    public void add(final byte b) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n = j;
                array[n] += b;
            }
        }
    }
    
    public void add(final long n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] += n;
            }
        }
    }
    
    public void add(final float n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] += n;
            }
        }
    }
    
    public void add(final double n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] += n;
            }
        }
    }
    
    public void add(final int[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] += array[i][j];
            }
        }
    }
    
    public void add(final byte[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] += array[i][j];
            }
        }
    }
    
    public void add(final short[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] += array[i][j];
            }
        }
    }
    
    public void add(final long[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] += array[i][j];
            }
        }
    }
    
    public void add(final float[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] += array[i][j];
            }
        }
    }
    
    public void add(final double[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] += array[i][j];
            }
        }
    }
    
    public void add(final Matrix matrix) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n = j;
                array[n] += matrix.matrix[i][j];
            }
        }
    }
    
    public void subtract(final int n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] -= n;
            }
        }
    }
    
    public void subtract(final short n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] -= n;
            }
        }
    }
    
    public void subtract(final byte b) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n = j;
                array[n] -= b;
            }
        }
    }
    
    public void subtract(final long n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] -= n;
            }
        }
    }
    
    public void subtract(final float n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] -= n;
            }
        }
    }
    
    public void subtract(final double n) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n2 = j;
                array[n2] -= n;
            }
        }
    }
    
    public void subtract(final int[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] -= array[i][j];
            }
        }
    }
    
    public void subtract(final byte[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] -= array[i][j];
            }
        }
    }
    
    public void subtract(final short[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] -= array[i][j];
            }
        }
    }
    
    public void subtract(final long[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] -= array[i][j];
            }
        }
    }
    
    public void subtract(final float[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] -= array[i][j];
            }
        }
    }
    
    public void subtract(final double[][] array) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array2 = this.matrix[i];
                final int n = j;
                array2[n] -= array[i][j];
            }
        }
    }
    
    public void subtract(final Matrix matrix) {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                final double[] array = this.matrix[i];
                final int n = j;
                array[n] -= matrix.matrix[i][j];
            }
        }
    }
    
    static Matrix transpose(final Matrix matrix) {
        final double[][] array = new double[matrix.cols][matrix.rows];
        for (int i = 0; i < matrix.rows; ++i) {
            for (int j = 0; j < matrix.cols; ++j) {
                array[j][i] = matrix.matrix[i][j];
            }
        }
        final Matrix matrix2 = new Matrix(matrix.cols, matrix.rows);
        matrix2.add(array);
        return matrix2;
    }
    
    static Matrix subtract(final Matrix matrix, final Matrix matrix2) {
        final Matrix matrix3 = new Matrix(matrix.rows, matrix.cols);
        for (int i = 0; i < matrix.rows; ++i) {
            for (int j = 0; j < matrix.cols; ++j) {
                matrix3.matrix[i][j] = matrix.matrix[i][j] - matrix2.matrix[i][j];
            }
        }
        return matrix3;
    }
    
    public void sigmoid() {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                this.matrix[i][j] = 1.0 / (1.0 + Math.exp(-this.matrix[i][j]));
            }
        }
    }
    
    public static Matrix dsigmoid(final Matrix matrix) {
        final Matrix matrix2 = new Matrix(matrix.rows, matrix.cols);
        for (int i = 0; i < matrix2.rows; ++i) {
            for (int j = 0; j < matrix2.cols; ++j) {
                matrix2.matrix[i][j] *= 1.0 - matrix2.matrix[i][j];
            }
        }
        return matrix2;
    }
    
    public void print() {
        System.out.print("[");
        final double[][] matrix = this.matrix;
        for (int length = matrix.length, i = 0; i < length; ++i) {
            System.out.print(Arrays.toString(matrix[i]) + ", ");
        }
        System.out.println("\b\b]");
    }
    
    public String length() {
        return " Rows : " + this.rows + " Cols : " + this.cols;
    }
}