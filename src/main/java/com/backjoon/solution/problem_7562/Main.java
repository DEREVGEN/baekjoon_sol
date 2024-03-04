package com.backjoon.solution.problem_7562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            L = Integer.parseInt(input.readLine());

            st = new StringTokenizer(input.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(input.readLine());
            int destY = Integer.parseInt(st.nextToken());
            int destX = Integer.parseInt(st.nextToken());

            int res = bfs(startY, startX, destY, destX);

            System.out.println(res);
        }

    }

    public static int[][] direction = {{-1, -2}, {-2, -1}, {1, 2}, {2, 1},
            {-1, 2}, {-2, 1}, {1, -2}, {2, -1}};

    public static int bfs(int startY, int startX, int destY, int destX) {

        if (startY == destY && startX == destX)
            return 0;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isVisit = new boolean[L][L];
        isVisit[startY][startX] = true;
        queue.add(new int[]{startY, startX, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 8; d++) {
                int movedY = node[0] + direction[d][0];
                int movedX = node[1] + direction[d][1];

                if (movedY == destY && movedX == destX)
                    return node[2] + 1;

                if (0 > movedY || movedY >= L || 0 > movedX || movedX >= L || isVisit[movedY][movedX]) continue;

                queue.add(new int[]{movedY, movedX, node[2] + 1});
                isVisit[movedY][movedX] = true;
            }
        }

        return -1;
    }
}