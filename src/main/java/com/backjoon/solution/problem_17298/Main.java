package com.backjoon.solution.problem_17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int idx = 1;
        while (idx < N) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                nums[stack.pop()]= nums[idx];
            }

            stack.push(idx);
            idx++;
        }

        while (!stack.isEmpty())
            nums[stack.pop()] = -1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(nums[i]).append(' ');
        System.out.println(sb);
    }
}