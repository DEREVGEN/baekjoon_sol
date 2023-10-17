package com.backjoon.solution.problem_2473;

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

        long min = Long.MAX_VALUE;
        int num1 = nums[0], num2 = nums[1], num3 = nums[2];
        boolean flag = false;

        for (int base = N-1; base >= 2; base--) {
            int baseNum = nums[base];
            int left = 0;
            int right = base-1;

            while (left < right) {
                long sum = (long)nums[left] + nums[right] + baseNum;

                if (Math.abs(sum) < min) {
                    num1 = nums[left];
                    num2 = nums[right];
                    num3 = baseNum;
                    min = Math.abs(sum);
                }

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }

        System.out.println(num1 + " " + num2 + " " + num3);

    }
}