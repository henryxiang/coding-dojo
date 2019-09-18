package dojo.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayUtilTest {

    @Test
    public void testRotateClockwise() {
        Integer[][] input = {
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3},
                {4, 4, 4}
        };
        Integer[][] output = ArrayUtil.rotateClockwise(input, Integer.class);
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
        assertEquals(input[0].length, output.length);
    }

    @Test
    public void testRotateCounterClockwise() {
        Integer[][] input = {
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3},
                {4, 4, 4}
        };
        Integer[][] output = ArrayUtil.rotateCounterClockwise(input, Integer.class);
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
        assertEquals(input[0].length, output.length);
    }

    @Test
    public void testCloneArray() {
        Integer[][] input = {
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3},
                {4, 4, 4}
        };
        Integer[][] output = ArrayUtil.cloneArray(input, Integer.class);
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
        assertEquals(input.length, output.length);
    }

}