package com.backjoon.solution.problem_5373;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        char[][][] cube = new char[6][3][3];

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(input.readLine());
        int n;

        char[] colors = {'w', 'r', 'y', 'o', 'g', 'b'};

        for (int m = 0; m < testNum; m++) {

            for (int idx = 0; idx < colors.length; idx++) {
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        cube[idx][i][j] = colors[idx];
            }


            n = Integer.parseInt(input.readLine());
            st = new StringTokenizer(input.readLine());

            for (int i = 0; i < n; i++) {
                String direction = st.nextToken();

                switch (direction.charAt(0)) {
                    case 'L':
                        switch (direction.charAt(1)) {
                            case '-':
                                Left(cube);
                                Left(cube);
                                Left(cube);
                                break;
                            case '+':
                                Left(cube);
                                break;
                        }
                        break;
                    case 'R':
                        switch (direction.charAt(1)) {
                            case '-':
                                Right(cube);
                                Right(cube);
                                Right(cube);
                                break;
                            case '+':
                                Right(cube);
                                break;
                        }
                        break;
                    case 'F':
                        switch (direction.charAt(1)) {
                            case '-':
                                Front(cube);
                                Front(cube);
                                Front(cube);
                                break;
                            case '+':
                                Front(cube);
                                break;
                        }
                        break;
                    case 'B':
                        switch (direction.charAt(1)) {
                            case '-':
                                Back(cube);
                                Back(cube);
                                Back(cube);
                                break;
                            case '+':
                                Back(cube);
                                break;
                        }
                        break;
                    case 'U':
                        switch (direction.charAt(1)) {
                            case '-':
                                Up(cube);
                                Up(cube);
                                Up(cube);
                                break;
                            case '+':
                                Up(cube);

                                break;
                        }
                        break;
                    case 'D':
                        switch (direction.charAt(1)) {
                            case '-':
                                Down(cube);
                                Down(cube);
                                Down(cube);
                                break;
                            case '+':
                                Down(cube);
                                break;
                        }
                        break;
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(cube[0][i][j]);
                }
                System.out.println();
            }
        }

    }

    static void Left(char[][][] cube) {
        // 윗면 0, 앞면 1, 밑면 2, 뒷면3

        char[] temp = new char[3];

        temp[0] = cube[3][0][0];
        temp[1] = cube[3][1][0];
        temp[2] = cube[3][2][0];

        cube[3][0][0] = cube[2][0][0];
        cube[3][1][0] = cube[2][1][0];
        cube[3][2][0] = cube[2][2][0];

        cube[2][0][0] = cube[1][0][0];
        cube[2][1][0] = cube[1][1][0];
        cube[2][2][0] = cube[1][2][0];

        cube[1][0][0] = cube[0][0][0];
        cube[1][1][0] = cube[0][1][0];
        cube[1][2][0] = cube[0][2][0];

        cube[0][0][0] = temp[0];
        cube[0][1][0] = temp[1];
        cube[0][2][0] = temp[2];

        rotate_90(cube, 4);
    }

    static void Right(char[][][] cube) {
        // 윗면 0, 앞면 1, 밑면 2, 뒷면3

        char[] temp = new char[3];

        temp[0] = cube[1][0][2];
        temp[1] = cube[1][1][2];
        temp[2] = cube[1][2][2];

        cube[1][0][2] = cube[2][0][2];
        cube[1][1][2] = cube[2][1][2];
        cube[1][2][2] = cube[2][2][2];

        cube[2][0][2] = cube[3][0][2];
        cube[2][1][2] = cube[3][1][2];
        cube[2][2][2] = cube[3][2][2];

        cube[3][0][2] = cube[0][0][2];
        cube[3][1][2] = cube[0][1][2];
        cube[3][2][2] = cube[0][2][2];

        cube[0][0][2] = temp[0];
        cube[0][1][2] = temp[1];
        cube[0][2][2] = temp[2];

        rotate_90(cube, 5);
    }

    static void Front(char[][][] cube) {

        char[] temp = new char[3];

        temp[0] = cube[4][2][2];
        temp[1] = cube[4][1][2];
        temp[2] = cube[4][0][2];

        cube[4][2][2] = cube[2][0][2];
        cube[4][1][2] = cube[2][0][1];
        cube[4][0][2] = cube[2][0][0];

        cube[2][0][2] = cube[5][0][0];
        cube[2][0][1] = cube[5][1][0];
        cube[2][0][0] = cube[5][2][0];

        cube[5][0][0] = cube[0][2][0];
        cube[5][1][0] = cube[0][2][1];
        cube[5][2][0] = cube[0][2][2];

        cube[0][2][0] = temp[0];
        cube[0][2][1] = temp[1];
        cube[0][2][2] = temp[2];

        rotate_90(cube, 1);
    }

    static void Back(char[][][] cube) {
        // 윗면, 오른쪽면, 밑면, 왼쪽면 순

        char[] temp = new char[3];

        temp[0] = cube[5][0][2];
        temp[1] = cube[5][1][2];
        temp[2] = cube[5][2][2];

        cube[5][0][2] = cube[2][2][2];
        cube[5][1][2] = cube[2][2][1];
        cube[5][2][2] = cube[2][2][0];

        cube[2][2][2] = cube[4][2][0];
        cube[2][2][1] = cube[4][1][0];
        cube[2][2][0] = cube[4][0][0];

        cube[4][2][0] = cube[0][0][0];
        cube[4][1][0] = cube[0][0][1];
        cube[4][0][0] = cube[0][0][2];

        cube[0][0][0] = temp[0];
        cube[0][0][1] = temp[1];
        cube[0][0][2] = temp[2];

        rotate_90(cube, 3);
    }

    static void Up(char[][][] cube) {

        char[] temp = new char[3];

        temp[0] = cube[5][0][0];
        temp[1] = cube[5][0][1];
        temp[2] = cube[5][0][2];

        cube[5][0][0] = cube[3][2][2];
        cube[5][0][1] = cube[3][2][1];
        cube[5][0][2] = cube[3][2][0];

        cube[3][2][2] = cube[4][0][0];
        cube[3][2][1] = cube[4][0][1];
        cube[3][2][0] = cube[4][0][2];

        cube[4][0][0] = cube[1][0][0];
        cube[4][0][1] = cube[1][0][1];
        cube[4][0][2] = cube[1][0][2];

        cube[1][0][0] = temp[0];
        cube[1][0][1] = temp[1];
        cube[1][0][2] = temp[2];

        rotate_90(cube, 0);
    }

    static void Down(char[][][] cube) {

        char[] temp = new char[3];

        temp[0] = cube[4][2][0];
        temp[1] = cube[4][2][1];
        temp[2] = cube[4][2][2];

        cube[4][2][0] = cube[3][0][2];
        cube[4][2][1] = cube[3][0][1];
        cube[4][2][2] = cube[3][0][0];

        cube[3][0][2] = cube[5][2][0];
        cube[3][0][1] = cube[5][2][1];
        cube[3][0][0] = cube[5][2][2];

        cube[5][2][0] = cube[1][2][0];
        cube[5][2][1] = cube[1][2][1];
        cube[5][2][2] = cube[1][2][2];

        cube[1][2][0] = temp[0];
        cube[1][2][1] = temp[1];
        cube[1][2][2] = temp[2];

        rotate_90(cube, 2);
    }

    static void rotate_90(char[][][] cube, int idx) {

        for (int i = 0; i < 2; i++) {
            char temp = cube[idx][2-i][0];
            cube[idx][2-i][0] = cube[idx][2][2-i];
            cube[idx][2][2-i] = cube[idx][i][2];
            cube[idx][i][2] = cube[idx][0][i];
            cube[idx][0][i] = temp;
        }
    }
}
