package com.backjoon.solution.problem_4779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String inputNum = input.readLine();
            if (inputNum == null)
                break;

            int N = Integer.parseInt(inputNum);

            N = (int) Math.pow(3, N);

            lines = new boolean[N + 1];
            Arrays.fill(lines, true);

            divideLines(1, N);

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= N; i++) {
                if (lines[i])
                    sb.append('-');
                else
                    sb.append(' ');
            }

            System.out.println(sb);
        }
    }

    public static void divideLines(int a, int b) {
        if (a >= b)
            return;

        int sn = (b - a + 1) / 3;

        for (int i = a + sn; i <= b - sn; i++) {
            lines[i] = false;
        }

        divideLines(a, a + sn - 1);
        divideLines(b - sn + 1, b);
    }
}