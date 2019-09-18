package dojo.util;

import java.lang.reflect.Array;

public class ArrayUtil {
    public static <E> E[][] rotateClockwise(E[][] input, Class<? extends  E> cls) {
        int rows = input[0].length;
        int cols = input.length;
        E[][] output = (E[][]) Array.newInstance(cls, rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[i][j] = input[cols-j-1][i];
            }
        }
        return output;
    }

    public static <E> E[][] rotateCounterClockwise(E[][] input, Class<? extends  E> cls) {
        int rows = input[0].length;
        int cols = input.length;
        E[][] output = (E[][]) Array.newInstance(cls, rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[i][j] = input[j][rows-i-1];
            }
        }
        return output;
    }

    public static <E> E[][] cloneArray(E[][] input, Class<? extends  E> cls) {
        int rows = input.length;
        int cols = input[0].length;
        E[][] output = (E[][]) Array.newInstance(cls, rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[i][j] = input[i][j];
            }
        }
        return output;
    }

    public static <E> E[] getRow(E[][] input, int row, Class<? extends E> cls) {
        E[] result = (E[]) Array.newInstance(cls, input[row].length);
        for (int i = 0; i < input[row].length; i++) {
            result[i] = input[row][i];
        }
        return result;
    }

    public static <E> E[] getCol(E[][] input, int col, Class<? extends E> cls) {
        E[] result = (E[]) Array.newInstance(cls, input.length);
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i][col];
        }
        return result;
    }
 }
