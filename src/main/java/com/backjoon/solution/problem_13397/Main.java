package com.backjoon.solution.problem_13397;

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

        st = new StringTokenizer(input.readLine());

        int[] nums = new int[N];

        int max = 0, min = 10000;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);

        }

        int start = 0;
        int end = max - min;
        int gapMin = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;

            int cnt = 1;

            max = nums[0];
            min = nums[0];

            for (int idx = 0; idx < N; idx++) {

                max = Math.max(max, nums[idx]);
                min = Math.min(min, nums[idx]);

                int gap = max - min;

                if (gap > mid) {
                    cnt++;
                    min = nums[idx];
                    max = nums[idx];
                }
            }

            if (cnt > M) {
                start = mid + 1;
            } else {
                gapMin = Math.min(gapMin, mid);
                end = mid - 1;
            }
        }

        System.out.println(gapMin);
    }
}