package com.backjoon.solution.problem_3151;

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
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        long zeroCnt = 0;

        for (int i = 2; i < N; i++) {
            int left = 0;
            int right = i - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0)
                    left++;
                else if (sum > 0)
                    right--;
                else {
                    if (nums[left] == nums[right]) {
                        int combN = (right - left + 1);
                        zeroCnt += (long) combN * (combN - 1) / 2;
                        break;
                    }

                    int leftCnt = 1, rightCnt = 1;

                    while(left + 1 < right && nums[left] == nums[left+1]) {
                        left++;
                        leftCnt++;
                    }
                    while (right - 1 > left && nums[right] == nums[right-1]) {
                        right--;
                        rightCnt++;
                    }


                    zeroCnt += (long)leftCnt * rightCnt;
                    left++;
                    right--;
                }
            }
        }

        System.out.println(zeroCnt);

    }
}