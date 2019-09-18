package dojo.codewars;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  CodeWars 2016 North America Problem 15
 *  http://www.hpcodewars.org/past/cw19/problems/CodeWars2016NAProblemSetFinal.pdf
 */
public class CubeRotation {
    char[][] front = fillColor('G');
    char[][] back = fillColor('B');
    char[][] left = fillColor('O');
    char[][] right = fillColor('R');
    char[][] up = fillColor('W');
    char[][] down = fillColor('Y');

    public static void main(String[] args) {
        CubeRotation cube = new CubeRotation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start");
        cube.printFront();
        while (scanner.hasNextLine()) {
            String rotation = scanner.nextLine();
            if (rotation.equals(".")) break;
            switch (rotation) {
                case "U":
                    cube.faceUp();
                    cube.turn();
                    cube.faceDown();
                    break;
                case "D":
                    cube.faceDown();
                    cube.turn();
                    cube.faceUp();
                    break;
                case "L":
                    cube.faceLeft();
                    cube.turn();
                    cube.faceRight();
                    break;
                case "R":
                    cube.faceRight();
                    cube.turn();
                    cube.faceLeft();
                    break;
                case "F":
                    cube.turn();
                    break;
                case "B":
                    cube.faceBack();
                    cube.turn();
                    cube.faceBack();
                    break;
            }
            System.out.println(rotation);
            cube.printFront();
        }
    }

    public void faceUp() {
        char[][] temp = front;
        front = up;
        up = rotateClockwise(rotateClockwise(back));
        back = rotateClockwise(rotateClockwise(down));
        down = temp;
        left = rotateClockwise(left);
        right = rotateCounterClockwise(right);
    }

    public void faceDown() {
        char[][] temp = front;
        front = down;
        down = rotateClockwise(rotateClockwise(back));
        back = rotateClockwise(rotateClockwise(up));
        up = temp;
        left = rotateCounterClockwise(left);
        right = rotateClockwise(right);
    }

    public void faceLeft() {
        char[][] temp = front;
        front = left;
        left = back;
        back = right;
        right = temp;
        up = rotateCounterClockwise(up);
        down = rotateClockwise(down);
    }

    public void faceRight() {
        char[][] temp = front;
        front = right;
        right = back;
        back = left;
        left = temp;
        up = rotateClockwise(up);
        down = rotateCounterClockwise(down);
    }

    public void faceBack() {
        char[][] temp = front;
        front = back;
        back = temp;
        temp = left;
        left = right;
        right = temp;
        up = rotateClockwise(rotateClockwise(up));
        down = rotateClockwise(rotateClockwise(down));
    }

    public void turn() {
        front = rotateClockwise(front);
        left = rotateClockwise(left);
        right = rotateCounterClockwise(right);
        down = rotateClockwise(rotateClockwise(down));
        char[] temp = down[1];
        down[1] = right[1];
        right[1] = up[1];
        up[1] = left[1];
        left[1] = temp;
        left = rotateCounterClockwise(left);
        right = rotateClockwise(right);
        down = rotateClockwise(rotateClockwise(down));
    }

    public void printFront() {
        for (int i = 0; i < front.length; i++) {
            for (int j = 0; j < front[0].length; j++) {
                System.out.print(front[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] rotateClockwise(char[][] arr) {
        int rows = arr[0].length, cols = arr.length;
        char[][] out = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                out[i][j] = arr[cols-j-1][i];
            }
        }
        return out;
    }

    private static char[][] rotateCounterClockwise(char[][] arr) {
        int rows = arr[0].length, cols = arr.length;
        char[][] out = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                out[i][j] = arr[j][rows-i-1];
            }
        }
        return out;
    }

    private static char[][] fillColor(char c) {
        char[][] out = new char[2][2];
        for (int i = 0; i < out.length; i++) {
            Arrays.fill(out[i], c);
        }
        return out;
    }
}
