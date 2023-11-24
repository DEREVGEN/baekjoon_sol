package com.backjoon.solution.problem_2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] nums;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());

        nums = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(input.readLine());

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0] + nums[1], nums[1]);
        dp[2] = Math.max(nums[0] + nums[2], nums[1] + nums[2]);

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], nums[i-1] + nums[i] + dp[i-3]);
        }

        System.out.println(dp[N-1]);

    }
}