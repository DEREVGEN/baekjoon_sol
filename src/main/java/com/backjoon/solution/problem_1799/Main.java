package com.backjoon.solution.problem_1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean[][] map;
    static boolean[] isVisitLeftUpper;
    static boolean[] isVisitLeftLower;
    static int MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        isVisitLeftUpper = new boolean[2*N];
        isVisitLeftLower = new boolean[2*N];

        int res = 0;
        // findDFS(0, 0, 0, true);
        findDFS2(0, 0, true);
        res += MAX;

        MAX = 0;
        // findDFS(0, 0, 0, false);
        findDFS2(1, 0, false);
        res += MAX;
        System.out.println(res);
    }


    public static void findDFS(int x, int y, int bCnt, boolean color) {

        for (int mx = x; mx < N; mx++) {
            for (int my = y; my < N; my++) {

                // color : false=흑색 칸, true=백색 칸

                // 백색 일 때, 흑색 칸에 두려는 경우
                if (color && (my + mx) % 2 != 0) continue;
                // 흑색 일 때, 백색 칸에 두려는 경우
                if (!color && (my + mx) % 2 == 0) continue;

                int leftUpper = N + mx - my;
                int leftLower = mx + my;

                if (!map[mx][my] || isVisitLeftUpper[leftUpper] || isVisitLeftLower[leftLower]) continue;

                isVisitLeftUpper[leftUpper] = true;
                isVisitLeftLower[leftLower] = true;
                MAX = Math.max(MAX, bCnt + 1);

                findDFS(mx, my+1, bCnt + 1, color);
                isVisitLeftUpper[leftUpper] = false;
                isVisitLeftLower[leftLower] = false;
            }
        }
    }


    public static void findDFS2(int k, int bCnt, boolean color) {

        for (int i = k; i < N*N; i++) {

            int mx = i / N;
            int my = i % N;

            // color : false=흑색 칸, true=백색 칸

            // 백색 일 때, 흑색 칸에 두려는 경우
            if (color && (my + mx) % 2 != 0) continue;
            // 흑색 일 때, 백색 칸에 두려는 경우
            if (!color && (my + mx) % 2 == 0) continue;

            int leftUpper = N + mx - my;
            int leftLower = mx + my;

            if (!map[mx][my] || isVisitLeftUpper[leftUpper] || isVisitLeftLower[leftLower]) continue;

            isVisitLeftUpper[leftUpper] = true;
            isVisitLeftLower[leftLower] = true;
            MAX = Math.max(MAX, bCnt+1);
            findDFS2(i+1, bCnt+1, color);
            isVisitLeftUpper[leftUpper] = false;
            isVisitLeftLower[leftLower] = false;
        }
    }
}