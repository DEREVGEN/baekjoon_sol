package com.backjoon.solution.problem_2470;

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

        st = new StringTokenizer(input.readLine());

        int[] nums = new int[N];

        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);

        int left = 0, right = N-1;
        int min = Integer.MAX_VALUE;
        int lIdx = 0, rIdx = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (Math.abs(sum) < min) {
                lIdx = left;
                rIdx = right;
                min = Math.abs(sum);
            }

            if (sum < 0)
                left++;
            else
                right--;
        }

        System.out.println(nums[lIdx] + " " + nums[rIdx]);


    }
}