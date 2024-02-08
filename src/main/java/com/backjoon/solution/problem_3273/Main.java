package com.backjoon.solution.problem_3273;

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

        int[] nums = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        int M = Integer.parseInt(input.readLine());

        int left = 0, right = N-1;
        long cnt = 0;
        while (left < right) {

            int c = M - nums[right];
            if (nums[left] == c) {
                cnt++;
                left++;
                right--;
            } else if (nums[left] < c) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(cnt);
    }
}