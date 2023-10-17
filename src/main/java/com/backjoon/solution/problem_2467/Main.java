package com.backjoon.solution.problem_2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int left = 0;
        int right = N-1;

        int MIN = Integer.MAX_VALUE;
        int lI = left;
        int rI = right;

        while(left < right) {
            int sum = nums[left] + nums[right];

            if (Math.abs(sum) < MIN) {
                MIN = Math.abs(sum);
                lI = left;
                rI = right;
            }

            if (sum > 0)
                right--;
            else if (sum < 0)
                left++;
            else
                break;
        }

        System.out.println(nums[lI] + " " + nums[rI]);
    }
}