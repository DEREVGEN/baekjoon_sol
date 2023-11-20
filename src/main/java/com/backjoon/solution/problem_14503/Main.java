package com.backjoon.solution.problem_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static Node startR;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(input.readLine());
        int sY = Integer.parseInt(st.nextToken());
        int sX = Integer.parseInt(st.nextToken());
        int sD = Integer.parseInt(st.nextToken());
        startR = new Node(sY, sX, sD);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(sY, sX, sD);
        System.out.println(cnt);
    }

    //static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int cnt = 0;

    public static void dfs(int y, int x, int d) {
        if (map[y][x] == 0) {
            map[y][x] = 2;
            cnt++;
        }

        boolean flag = false;
        int origin = d;
        for (int i = 0; i < 4; i++) {
            int mD = (d + 3) % 4;
            int mY = y + direction[mD][0];
            int mX = x + direction[mD][1];

            if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 0) {
                dfs(mY, mX, mD);
                flag = true;
                break;
            }
            d = (d + 3) % 4;
        }

        if (!flag) { // 청소가 안된 빈칸이 없을 경우
            int rD = (origin + 2) % 4;
            int mY = y + direction[rD][0];
            int mX = x + direction[rD][1];


            if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] != 1) {
                dfs(mY, mX, origin);
            }
        }

    }
}

class Node {
    int y, x, d;

    public Node(int y, int x, int d) {
        this.y = y;
        this.x = x;
        this.d = d;
    }
}