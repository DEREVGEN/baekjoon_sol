package com.backjoon.solution.problem_9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int[][] dp;
    static String A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        A = input.readLine();
        B = input.readLine();

        int N = A.length();
        int M = B.length();

        dp = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A.charAt(i-1) == B.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        trace(N, M);

        System.out.println(dp[N][M]);
        while(!stack.isEmpty())
            System.out.print(stack.pop());
    }

    static Stack<Character> stack = new Stack<>();
    public static void trace(int a_i, int b_i) {
        if (dp[a_i][b_i] == 0)
            return;

        if (A.charAt(a_i-1) != B.charAt(b_i-1)) {
            if (dp[a_i-1][b_i] > dp[a_i][b_i-1]) {
                a_i = a_i - 1;
            } else {
                b_i = b_i - 1;
            }
        } else {
            a_i = a_i - 1;
            b_i = b_i - 1;
            stack.push(A.charAt(a_i));
        }

        trace(a_i, b_i);
    }
}