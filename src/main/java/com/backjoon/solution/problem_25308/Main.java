package com.backjoon.solution.problem_25308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int cnt = 0;
    public static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nums = new int[8];
        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < 8; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] isVisit = new boolean[8];
        int[] p = new int[8];


        dfs(0, isVisit, p);

        System.out.println(cnt);
    }

    public static void dfs(int depth, boolean[] isVisit, int[] p) {
        if (depth == 8) {
            if (findConvex(p)) cnt++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (isVisit[i]) continue;
            isVisit[i] = true;
            p[depth] = nums[i];
            dfs(depth + 1, isVisit, p);
            isVisit[i] = false;
        }
    }

    public static boolean findConvex(int[] p) {
        for (int i = 0; i < 8; i++) {
            int i1 = i;
            int i2 = (i + 1) % 8;
            int i3 = (i + 2) % 8;

            if (p[i1] * p[i3] * Math.sqrt(2) > p[i2] * (p[i1] + p[i3]))
                return false;
        }
        return true;
    }
}