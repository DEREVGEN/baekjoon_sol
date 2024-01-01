package com.backjoon.solution.problem_2631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N];

        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(input.readLine());

        int res = lis();
        System.out.println(res);
    }

    public static int lis() {
        int[] dp = new int[N];

        dp[0] = nums[0];
        int dpIdx = 1;

        for (int i = 1; i < N; i++) {
            if (dp[dpIdx - 1] < nums[i]) {
                dp[dpIdx] = nums[i];
                dpIdx++;
                continue;
            }

            int idx = lowerBound(nums[i], dpIdx, dp);
            dp[idx] = nums[i];
        }

        return dpIdx;
    }

    static int lowerBound(int target, int size, int[] arr) {
        int start = 0, end = size-1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (arr[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}