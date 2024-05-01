package com.backjoon.solution.problem_2212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int K = Integer.parseInt(input.readLine());

        int[] nums = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);

        int[] gaps = new int[N];

        for (int i = 0; i < N-1; i++)
            gaps[i] = nums[i+1] - nums[i];

        Arrays.sort(gaps);

        int sum = 0;
        for (int i = 0; i < N-K+1; i++) {
            sum += gaps[i];
        }

        System.out.println(sum);
    }
}