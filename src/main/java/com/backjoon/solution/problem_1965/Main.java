package com.backjoon.solution.problem_1965;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        nums = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];
        dp[0] = nums[0];
        int dpIdx = 1;

        for (int i = 1; i < N; i++) {
            if (dp[dpIdx-1] < nums[i]) {
                dp[dpIdx++] = nums[i];
                continue;
            }

            int l = lowerBound(nums[i], dpIdx, dp);

            // target보다 같거나 작을 때만, lis 의 해당 자리(lower bound)에 갱신
            dp[l] = nums[i];
        }

        System.out.println(dpIdx);
    }

    public static int lowerBound(int key, int size, int[] arr) {
        int start = 0, end = size-1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (key <= arr[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }

        return start;
    }
}