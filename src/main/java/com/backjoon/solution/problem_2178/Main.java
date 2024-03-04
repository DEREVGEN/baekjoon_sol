package com.backjoon.solution.problem_2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String row = input.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j) == '1';
            }
        }

        int res = bfs(0, 0);

        System.out.println(res);
    }

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int bfs(int startY, int startX) {
        boolean[][] isVisit = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 1});
        isVisit[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int movedY = node[0] + direction[d][0];
                int movedX = node[1] + direction[d][1];

                if (0 > movedY || movedY >= N || 0 > movedX || movedX >= M || isVisit[movedY][movedX] || !map[movedY][movedX]) continue;

                isVisit[movedY][movedX] = true;
                queue.add(new int[]{movedY, movedX, node[2] + 1});

                if (movedY == N-1 && movedX == M-1)
                    return node[2] + 1;
            }
        }

        return -1;
    }

}