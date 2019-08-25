package dojo.uva;

import java.io.InputStream;
import java.util.Scanner;

public class LCDisplay {
    String[] patterns = {
            "0,1,2,4,5,6",
            "2,5",
            "0,2,3,4,6",
            "0,2,3,5,6",
            "1,2,3,5",
            "0,1,3,5,6",
            "0,1,3,4,5,6",
            "0,2,5",
            "0,1,2,3,4,5,6",
            "0,1,2,3,5,6"
    };

    public static void main(String[] args) {
        LCDisplay lcDisplay = new LCDisplay();
        lcDisplay.run(System.in);
    }

    public void run(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int size = scanner.nextInt();
        String digits = scanner.next();
        while (!(size == 0 && digits.equals("0"))) {
            String[][][] blocks = new String[digits.length()][][];
            for (int i = 0; i < digits.length(); i++) {
                blocks[i] = createDisplayBlock(size, Integer.valueOf(digits.substring(i, i+1)));
            }
            display(blocks);
            size = scanner.nextInt();
            digits = scanner.next();
        }
        scanner.close();
    }

    public void display(String[][][] blocks) {
        for (int i = 0; i < blocks[0].length; i++) {
            for (int k = 0; k < blocks.length; k++) {
                for (int j = 0; j < blocks[0][0].length; j++) {
                    System.out.print(blocks[k][i][j]);
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public String[][] createDisplayBlock(int size, int digit) {
        String[][] block = new String[2*size+3][size+2];
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                block[i][j] = " ";
            }
        }
        for (String pos : patterns[digit].split(",")) {
            drawSegment(size, block, pos);
        }
        return block;
    }

    private void drawSegment(int size, String[][] block, String pos) {
        switch (pos) {
            case "0":
                for (int i = 1; i <= size; i++) block[0][i] = "-";
                break;
            case "1":
                for (int i = 1; i <= size; i++) block[i][0] = "|";
                break;
            case "2":
                for (int i = 1; i <= size; i++) block[i][size+1] = "|";
                break;
            case "3":
                for (int i = 1; i <= size; i++) block[size+1][i] = "-";
                break;
            case "4":
                for (int i = size+2; i <= 2*size+1; i++) block[i][0] = "|";
                break;
            case "5":
                for (int i = size+2; i <= 2*size+1; i++) block[i][size+1] = "|";
                break;
            case "6":
                for (int i = 1; i <= size; i++) block[2*size+2][i] = "-";
                break;
        }
    }
}
