package com.backjoon.solution.problem_1654;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    boj 1654 랜선 자르기
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] cables = new int[N];

        long start = 1, end = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            cables[i] = Integer.parseInt(input.readLine());
        }

        long max = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += (cables[i] / mid);
            }

            if (cnt >= K) {
                max = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(max);

    }
}