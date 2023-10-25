package com.backjoon.solution.problem_2295;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        Arrays.sort(nums);

        Set<Integer> dc = new HashSet<>();

        // a + b 의 연산
        for (int b = 0; b < N; b++) {
            for (int a = 0; a <= b; a++) {
                dc.add(nums[a] + nums[b]);
            }
        }

        // a + b = d - c 를 찾기 위함.
        for (int d = N - 1; d >= 0; d--) {
            for (int c = 0; c <= d; c++) {
                int sum = nums[d] - nums[c];

                if (dc.contains(sum)) {
                    System.out.println(nums[d]);

                    return;
                }
            }
        }
    }
}