package com.backjoon.solution.problem_2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findSudokuBackTract(0);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    static boolean flag;

    public static void findSudokuBackTract(int K) {

        if (K == 81) {
            flag = true;
            return;
        }

        int startCol = K / 9;
        int startRow = K % 9;

        if (map[startCol][startRow] != 0) {
            findSudokuBackTract(K+1);
        } else {
            for (int num = 1; num <= 9; num++) {
                if (!check(startCol, startRow, num)) continue;

                map[startCol][startRow] = num;

                findSudokuBackTract(K + 1);

                if (flag)
                    return;

                map[startCol][startRow] = 0;
            }
        }
    }

    public static boolean check(int col, int row, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[col][i] == num || map[i][row] == num) return false;
        }

        int sCol = col / 3 * 3;
        int sRow = row / 3 * 3;

        for (int i = sCol; i < sCol + 3; i++) {
            for (int j = sRow; j < sRow + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }

        return true;
    }
}