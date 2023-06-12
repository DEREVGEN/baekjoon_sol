package com.backjoon.solution.problem_7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_sol2 {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(input.readLine());

        int[][] nums = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < 4; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(zeroOfSum(nums, n));
    }

    static int zeroOfSum(int[][] nums, int n) {

        int[] CD = new int[n*n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CD[n * i + j] = -(nums[i][2] + nums[j][3]);
            }
        }

        Arrays.sort(CD);

        int count = 0;

        int ab, li, ui;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab = nums[i][0] + nums[j][1];

                li = lowerBound(ab, CD);
                ui = upperBound(ab, CD);

                count += ui - li + 1;
            }
        }

        return count;
    }


    static int lowerBound(int key, int[] arr) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid] >= key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    static int upperBound(int key, int[] arr) {

        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] > key)
                end = mid - 1;
            else
                start = mid + 1;
        }

        return end;
    }
}
