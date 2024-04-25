package com.backjoon.solution.problem_3109;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String row = input.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if(dfs(i, 0)) cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean dfs(int y, int x) {
        map[y][x] = 'x';

        if (x == M-1)
            return true;

        int movedY = y-1;
        int movedX = x+1;

        if (movedY >= 0 && movedY < N && movedX >= 0 && movedX < M && map[movedY][movedX] == '.') {
            if (dfs(movedY, movedX))
                return true;
        }

        movedY = y;
        movedX = x+1;

        if (movedY >= 0 && movedY < N && movedX >= 0 && movedX < M && map[movedY][movedX] == '.') {
            if (dfs(movedY, movedX))
                return true;
        }

        movedY = y+1;
        movedX = x+1;

        if (movedY >= 0 && movedY < N && movedX >= 0 && movedX < M && map[movedY][movedX] == '.') {
            if (dfs(movedY, movedX))
                return true;
        }

        return false;
    }
}