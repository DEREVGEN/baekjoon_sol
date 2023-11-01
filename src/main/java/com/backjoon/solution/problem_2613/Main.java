package com.backjoon.solution.problem_2613;

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

        int[] nums = new int[N];
        st = new StringTokenizer(input.readLine());

        int sum = 0;
        int start = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
            start = Math.max(start, nums[i]);
        }

        int end = sum;

        while(start <= end) {
            int mid = (start + end) / 2;

            int cnt = 1;
            int groupSum = 0;

            for (int i = 0; i < N; i++) {
                groupSum += nums[i];

                if (groupSum > mid) {
                    groupSum = nums[i];
                    cnt++;
                }
            }

            if (cnt <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);

        int groupCnt = 0, groupSum = 0;
        int i;
        for (i = 0; i < N; i++) {
            groupSum += nums[i];

            if (groupSum > start) {
                M--;
                groupCnt = (groupCnt == 0 ? 1 : groupCnt);
                System.out.print(groupCnt + " ");
                groupSum = nums[i];
                groupCnt = 0;
            }
            groupCnt++;

            if (M == N - i) break;
        }

        for (; i < N; i++) {
            System.out.print(groupCnt + " ");
            groupCnt = 1;
        }
    }
}