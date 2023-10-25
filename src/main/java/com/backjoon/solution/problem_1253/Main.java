package com.backjoon.solution.problem_1253;

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

        int zeroCnt = 0;

        for (int i = 0; i < N; i++) {
            int baseNum = nums[i];

            int left = 0;
            int right = N - 1;

            while(left < right) {
                int sum = nums[left] + nums[right];

                if (sum < baseNum) { // a + b < c
                    left++;
                } else if (sum > baseNum) { // a + b > c
                    right--;
                } else { // 같을 때

                    if (left != i && right != i) {
                        zeroCnt++;
                        break;
                    } else {
                        if (left == i)
                            left++;
                        if (right == i)
                            right--;
                    }

                }
            }

        }

        System.out.println(zeroCnt);
    }
}