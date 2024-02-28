package com.backjoon.solution.problem_14002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int[] lis = new int[N];
        int[] dp = new int[N];
        int lisIdx = 0;

        lis[lisIdx] = nums[0];
        dp[0] = lisIdx;
        lisIdx++;

        for (int i = 1; i < N; i++) {
            if (lis[lisIdx - 1] < nums[i]) {
                lis[lisIdx] = nums[i];
                dp[i] = lisIdx;
                lisIdx++;
                continue;
            }
            int idx = lowerBound(nums[i], lisIdx, lis);
            lis[idx] = nums[i];
            dp[i] = idx;
        }

        System.out.println(lisIdx);

        Stack<Integer> stack = new Stack<>();

        int fIdx = lisIdx-1;
        for (int i = N-1; i >= 0; i--) {
            if (dp[i] == fIdx) {
                fIdx--;
                stack.push(nums[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(' ');

        System.out.println(sb);
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