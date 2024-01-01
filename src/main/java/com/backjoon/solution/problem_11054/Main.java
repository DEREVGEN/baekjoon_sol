package com.backjoon.solution.problem_11054;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0 ; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int[] dp  = getLis(nums);

        int[] reverseNums = new int[N];
        for (int i = 0; i < N; i++) {
            reverseNums[i] = nums[N-1-i];
        }
        int[] reverseDp = getLis(reverseNums);

        int max = 0;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i] + reverseDp[N-i-1] - 1);
        }

        System.out.println(max);
    }

    public static int[] getLis(int[] nums) {
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp;
    }

}
