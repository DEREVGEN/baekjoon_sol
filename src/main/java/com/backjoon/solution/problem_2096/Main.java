package com.backjoon.solution.problem_2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        int[][] nums = new int[N+1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int[][] maxDp = new int[N+1][3];
        int[][] minDp = new int[N+1][3];

        for (int h = 1; h <= N; h++) {
            maxCalculate(maxDp, nums, h);
            minCalculate(minDp, nums, h);
        }

        System.out.print(compareMaxThreeNums(maxDp[N][0], maxDp[N][1], maxDp[N][2]) + " ");
        System.out.println(compareMinThreeNums(minDp[N][0], minDp[N][1], minDp[N][2]));
    }

    public static int compareMaxThreeNums(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static int compareMinThreeNums(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void maxCalculate(int[][] maxDp, int[][] nums,  int h) {
        maxDp[h][0] = compareMaxThreeNums(maxDp[h-1][0], maxDp[h-1][1], Integer.MIN_VALUE) + nums[h][0];
        maxDp[h][1] = compareMaxThreeNums(maxDp[h-1][0], maxDp[h-1][1], maxDp[h-1][2]) + nums[h][1];
        maxDp[h][2] = compareMaxThreeNums(maxDp[h-1][1], maxDp[h-1][2], Integer.MIN_VALUE) + nums[h][2];
    }

    public static void minCalculate(int[][] minDp, int[][] nums,  int h) {
        minDp[h][0] = compareMinThreeNums(minDp[h-1][0], minDp[h-1][1], Integer.MAX_VALUE) + nums[h][0];
        minDp[h][1] = compareMinThreeNums(minDp[h-1][0], minDp[h-1][1], minDp[h-1][2]) + nums[h][1];
        minDp[h][2] = compareMinThreeNums(minDp[h-1][1], minDp[h-1][2], Integer.MAX_VALUE) + nums[h][2];
    }
}
