package com.backjoon.solution.problem_5585;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        int change = 1000 - N;

        int cnt = 0;
        int[] nums = new int[]{500, 100, 50, 10, 5, 1};

        for (int num : nums) {
            cnt += change / num;
            change %= num;
        }

        System.out.println(cnt);

    }
}