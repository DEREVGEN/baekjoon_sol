package com.backjoon.solution.problem_14442;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String rowMapStr = input.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = rowMapStr.charAt(j) - '0';
            }
        }

        int res = findPathCost(map, N, M, K);

        System.out.println(res);
    }

   public static int findPathCost(int[][] map, int N, int M, int K) {

        int[][][] cost = new int[K+1][N][M];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        cost[0][0][0] = 1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            Node c = queue.poll();

            for (int d = 0; d < 4; d++) {
                int movedY = c.y + direction[d][0];
                int movedX = c.x + direction[d][1];

                // 범위초과
                if (0 > movedY || movedY >= N || 0 > movedX || movedX >= M) continue;


                if (map[movedY][movedX] == 1) { // 벽일 경우
                    // 벽 뚫 가능?
                    if (c.k >= K) continue;

                    int nextK = c.k + 1;

                    if (cost[nextK][movedY][movedX] > c.c + 1) {
                        cost[nextK][movedY][movedX] = c.c + 1;

                        queue.offer(new Node(movedY, movedX, c.c+1, c.k+1));
                    }
                } else { // 벽이 아닐 경우
                    if (cost[c.k][movedY][movedX] > c.c + 1) {
                        cost[c.k][movedY][movedX] = c.c + 1;
                        queue.offer(new Node(movedY, movedX, c.c+1, c.k));
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for (int k = 0; k <= K; k++) {
            min = Math.min(min, cost[k][N-1][M-1]);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
   }
}

class Node {
    int y, x, c, k;

    public Node(int y, int x, int c, int k) {
        this.y = y;
        this.x = x;
        this.c = c;
        this.k = k;
    }
}