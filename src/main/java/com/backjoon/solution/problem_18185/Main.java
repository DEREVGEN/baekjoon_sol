package com.backjoon.solution.problem_18185;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N+2];

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int cost = 0;

        for (int i = 0; i < N; i++) {
            if (nums[i+1] > nums[i+2]) {
                int c = Math.min(nums[i], nums[i+1] - nums[i+2]);
                nums[i] -= c;
                nums[i+1] -= c;
                cost += c * 5;

                c = Math.min(nums[i], Math.min(nums[i+1], nums[i+2]));
                nums[i] -= c;
                nums[i+1] -= c;
                nums[i+2] -= c;
                cost += c * 7;
            } else {
                int c = Math.min(nums[i], Math.min(nums[i+1], nums[i+2]));
                nums[i] -= c;
                nums[i+1] -= c;
                nums[i+2] -= c;
                cost += c * 7;
            }
            cost += nums[i] * 3;
        }

        System.out.println(cost);
    }
}
