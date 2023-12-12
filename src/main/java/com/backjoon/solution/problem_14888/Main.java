package com.backjoon.solution.problem_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A;
    static int[] operCnt; // +, -, *, /

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        A = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        operCnt = new int[4];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < 4; i++) {
            operCnt[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(A[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void backTrack(int res, int aIdx) {
        if (aIdx == N) {
            max = Math.max(max, res);
            min = Math.min(min, res);
        }

        for (int i = 0; i < 4; i++) {
            if (operCnt[i] == 0) continue;
            operCnt[i]--;
            switch (i) {
                case 0 -> backTrack(res + A[aIdx], aIdx+1);
                case 1 -> backTrack(res - A[aIdx], aIdx+1);
                case 2 -> backTrack(res * A[aIdx], aIdx+1);
                case 3 -> backTrack(res / A[aIdx], aIdx+1);
            }
            operCnt[i]++;
        }
    }
}