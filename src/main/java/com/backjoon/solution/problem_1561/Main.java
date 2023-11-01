package com.backjoon.solution.problem_1561;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] ride = new int[M];

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            ride[i] = num;
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long start = 0;
        long end = 60000000000L; // 최대 놀이기구 cost에 모든 사람을 태우는 시간
        long totalCost = 0;

        while(start <= end) {
            long mid = (start + end) / 2;
            long sum = M;

            for (int i = 0; i < M; i++) {
                sum += mid / ride[i];
            }

            if (sum >= N) {
                totalCost = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        long sum = M;
        for (int i = 0; i < M; i++)
            sum += (totalCost - 1) / ride[i];

        for (int i = 0; i < M; i++) {
            if (totalCost % ride[i] == 0)
                sum++;

            if (sum == N) {
                System.out.println(i+1);
                return;
            }
        }

    }
}