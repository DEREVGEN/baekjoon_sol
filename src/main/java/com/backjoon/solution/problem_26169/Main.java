package com.backjoon.solution.problem_26169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(input.readLine());

        int sy = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());

        boolean[][] isVisited = new boolean[5][5];
        isVisited[sy][sx] = true;
        bfs(sy, sx, isVisited, 1, 0);
        System.out.println(0);
    }

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void bfs(int y, int x, boolean[][] isVisited, int depth, int apple) {

        if (apple >= 2) {
            System.out.println(1);
            System.exit(0);
        }

        if (depth > 3)
            return;

        for (int d = 0; d < 4; d++) {
            int movedY = y + direction[d][0];
            int movedX = x + direction[d][1];

            if (movedY < 0 || movedY >= 5 || movedX < 0 || movedX >= 5 || isVisited[movedY][movedX] || map[movedY][movedX] == -1)
                continue;

            isVisited[movedY][movedX] = true;
            bfs(movedY, movedX, isVisited, depth+1, apple+(map[movedY][movedX] == 1 ? 1 : 0));
            isVisited[movedY][movedX] = false;
        }

    }
}