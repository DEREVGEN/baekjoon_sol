package com.backjoon.solution.problem_1577;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
    세로, 가로 반대로..
     */

    static int N, M;
    static long[][] map;
    static boolean[][][] mark;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new long[M+2][N+2];
        mark = new boolean[2][M+2][N+2]; // 3번째 차원에서, 0 은 세로, 1은 가로

        int o = Integer.parseInt(input.readLine());

        for (int i = 1; i <= o; i++) {
            st = new StringTokenizer(input.readLine());
            int oX1 = Integer.parseInt(st.nextToken())+1;
            int oY1 = Integer.parseInt(st.nextToken())+1;
            int oX2 = Integer.parseInt(st.nextToken())+1;
            int oY2 = Integer.parseInt(st.nextToken())+1;

            if (oX1 + 1 == oX2)
                mark[1][oY1][oX1] = true;
            else if (oX1 - 1 == oX2)
                mark[1][oY2][oX2] = true;
            else if (oY1 + 1 == oY2)
                mark[0][oY1][oX1] = true;
            else
                mark[0][oY2][oX2] = true;

        }

        map[1][1] = 1;
        for (int i = 1; i <= M+1; i++) {
            for (int j = 1; j <= N+1; j++) {
                if (i == 1 && j == 1) continue;

                map[i][j] = ((mark[0][i-1][j]) ? 0 : map[i-1][j]) + ((mark[1][i][j-1]) ? 0 : map[i][j-1]);
            }
        }

        System.out.println(map[M+1][N+1]);
    }
}