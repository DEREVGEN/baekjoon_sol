package com.backjoon.solution.problem_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int max = 0;
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};


    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];


        int count;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                visit[y][x] = true;
                putBlock(y, x, map[y][x], 1);
                visit[y][x] = false;
            }
        }
        System.out.println(max);
    }


    static void putBlock(int y, int x, int count, int depth) {
        if (depth == 4) {
            if (max < count)
                max = count;

            return;
        }

        // 0 일때, 1을 생략, 1일때, 0을 생략
        for (int d = 0; d < 3; d++) {
            int nowY = y+direction[d][0];
            int nowX = x+direction[d][1];


            if (0 <= nowY && nowY < N && 0 <= nowX && nowX < M && !visit[nowY][nowX]) {
                if(depth == 2) {
                    visit[nowY][nowX] = true;
                    putBlock(y, x, count + map[nowY][nowX], depth+1);
                    visit[nowY][nowX] = false;
                }

                visit[nowY][nowX] = true;
                putBlock(nowY, nowX, count + map[nowY][nowX], depth + 1);
                visit[nowY][nowX] = false;
            }
        }
    }
}
