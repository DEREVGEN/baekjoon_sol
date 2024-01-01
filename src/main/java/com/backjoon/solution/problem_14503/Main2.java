package com.backjoon.solution.problem_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

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


        int res = simulate();
        System.out.println(res);
    }

    public static int simulate() {

        Node r = startR;
        //int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int cnt = 0;

        while (true) {
            if (map[r.y][r.x] == 0) {
                map[r.y][r.x] = 2;
                cnt++;
            }

            boolean flag = false;

            int mD = r.d;
            for (int d = 0; d < 4; d++) {
                mD = (r.d + 3) % 4;
                int mY = r.y + direction[mD][0];
                int mX = r.x + direction[mD][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 0) {
                    flag = true;
                    r.d = mD;
                    break;
                }
            }

            if (flag) { // 청소되지 않는 빈칸이 있을 경우
                //int d = (r.d + 3) % 4;
                int mY = r.y + direction[r.d][0];
                int mX = r.x + direction[r.d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 0) {
                    r.y = mY;
                    r.x = mX;
                }
            } else { // 청소가 안된 빈칸이 없을 경우

                int mY = r.y - direction[r.d][0];
                int mX = r.x - direction[r.d][1];

                if (!(0 <= mY && mY < N && 0 <= mX && mX < M) || map[mY][mX] == 1) {
                    return cnt;
                }

                r.y = mY;
                r.x = mX;
            }

        }
    }

    static class Node {
        int y, x, d;

        public Node(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}
