package com.backjoon.solution.problem_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int N, H, M;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        boolean[][] ladder = new boolean[H][N-1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            ladder[y-1][x-1] = true;
        }

        trace(0, 0, 0, ladder, N, H);

        if (min > 3)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    static void trace(int y, int x, int depth, boolean[][] ladder, int N, int H) {

        if (checkMap(ladder, N, H))
            if (depth < min)
                min = depth;

        if (depth == 3)
            return;

        if (y >= H) {
            x++;
            y = 0;
        }
        while(x < N-1) {
            if (!ladder[y][x]) {
                ladder[y][x] = true;
                trace(y+1, x, depth+1, ladder, N, H);
                ladder[y][x] = false;
            }
            y++;
            if (y >= H) {
                x++;
                y = 0;
            }
        }
    }

    static boolean checkMap(boolean[][] ladder, int N, int H) {

        for (int i = 0; i < N; i++) { // 사다리 타기 i번째 index
            int current = i;

            for (int h = 0; h < H; h++) {
                if (current < N - 1 && ladder[h][current])
                    current++;
                else if (current > 0 && ladder[h][current-1])
                    current--;
            }
            if (i != current)
                return false;
        }
        return true;
    }
}
