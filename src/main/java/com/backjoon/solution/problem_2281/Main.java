package com.backjoon.solution.problem_2281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dp;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N]; // index 1부터 시작
        dp = new int[N]; // index 1부터 시작
        dp[N-1] = 0; // 마지막 dp는 항상 0을 가짐.

        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
           nums[i] = Integer.parseInt(input.readLine());
        }

        int res = writeDeathNote(0);

        System.out.println(res);

    }

    public static int writeDeathNote(int idx) {

        if (dp[idx] != Integer.MAX_VALUE)
            return dp[idx];

        int remain = M - nums[idx];

        for (int i = idx+1; i <= N && remain >= 0; i++) {

            // 한 줄에 모든 단어를 채울때.
            if (i == N) {
                return dp[idx] = 0;
            }

            dp[idx] = Math.min(dp[idx], remain * remain + writeDeathNote(i));
            remain -= (nums[i] + 1);
        }

        return dp[idx];
    }
}
