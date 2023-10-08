package com.backjoon.solution.problem_1509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String inputStr;
    static boolean[][] isPal;
    static int N;
    static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        inputStr = input.readLine();
        N = inputStr.length();

        isPal = new boolean[N][N];
        DP = new int[N+1];

        makeTablePalindrome();

/*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((isPal[i][j] ? '1' : '0') + " ");
            }
            System.out.println();
        }
*/

        solution();
        System.out.println(DP[N-1]);
    }

    public static void makeTablePalindrome() {
        for (int i = 0; i < N; i++)
            isPal[i][i] = true;

        for (int i = 0; i < N - 1; i++) {
            if (inputStr.charAt(i) == inputStr.charAt(i + 1)) {
                isPal[i][i+1] = true;
            }
        }

        for (int offset = 2; offset < N; offset++) {
            for (int start = 0; offset+start < N; start++) {
                int end = offset+start;

                if (inputStr.charAt(start) == inputStr.charAt(end) && isPal[start+1][end-1]) {
                    isPal[start][end] = true;
                }
            }
        }
    }

    public static void solution() {
        for (int end = 0; end < N; end++) {
            DP[end] = Integer.MAX_VALUE;

            for (int start = 0; start <= end; start++) {
                if (start == 0) {
                    if (isPal[start][end])
                        DP[end] = 1;
                } else if (isPal[start][end]) {
                    DP[end] = Math.min(DP[end], DP[start - 1] + 1);
                }
            }
        }
    }
}