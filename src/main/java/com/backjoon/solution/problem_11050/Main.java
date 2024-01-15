package com.backjoon.solution.problem_11050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int r1 = 1;
        int r2 = 1;

        for (int k = N; k > N-K; k--) {
            r1 *= k;
        }

        for (int i = 2; i <= K; i++) {
            r2 *= i;
        }

        System.out.println(r1 / r2);
    }
}