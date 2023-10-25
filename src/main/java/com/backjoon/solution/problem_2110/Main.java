package com.backjoon.solution.problem_2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, C;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] routers = new int[N];
        for (int i = 0; i < N; i++) {
            routers[i] = Integer.parseInt(input.readLine());
        }

        Arrays.sort(routers);

        int maxGap = getMaxGap(routers, N, C);

        System.out.println(maxGap);

    }

    private static int getMaxGap(int[] routers, int N, int C) {
        int maxGap = Integer.MIN_VALUE;

        int start = 1;
        int end = routers[N - 1] - routers[0];

        while (start <= end) {
            int mid = (end + start) / 2;
            int cnt = 1;

            int compRouter = routers[0];

            for (int i = 1; i < N; i++) {
                if (routers[i] - compRouter >= mid) {
                    cnt++;
                    compRouter = routers[i];
                }
            }

            if (cnt >= C) {
                maxGap = Math.max(maxGap, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return maxGap;
    }
}