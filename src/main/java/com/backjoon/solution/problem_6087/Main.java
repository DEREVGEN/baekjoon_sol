package com.backjoon.solution.problem_6087;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static int[][][] costs; // 한 차원 더 추가..

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        Coord start = null;
        Coord end = null;

        for (int i = 0; i < N; i++) {
            String rowMap = input.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = rowMap.charAt(j);

                if (map[i][j] == 'C') {
                    if (start == null)
                        start = new Coord(i, j, 0, -1);
                    else
                        end = new Coord(i, j, 0, -1);
                }
            }
        }

        // cost를 max로 초기화
        costs = new int[4][N][M];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(costs[i][j], Integer.MAX_VALUE);
            }
        }

        int res = findEnd(start, end);

        System.out.println(res);
    }

    public static int findEnd(Coord start, Coord end) {
        Queue<Coord> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        for (int i = 0; i < 4; i++)
            pq.offer(new Coord(start.y, start.x, 0, i));

        while (!pq.isEmpty()) {
            Coord c = pq.poll();

            if (costs[c.direction][c.y][c.x] <= c.cost) continue;

            costs[c.direction][c.y][c.x] = c.cost;

            for (int d = 0; d < 4; d++) {
                int movedY = c.y + direction[d][0];
                int movedX = c.x + direction[d][1];

                if (movedY >= N || movedY < 0 || movedX >= M || movedX < 0 || map[movedY][movedX] == '*') continue;

                int cost = c.cost;
                if (d != c.direction) {
                    cost++;
                }

                if (costs[d][movedY][movedX] > cost) {
                    pq.offer(new Coord(movedY, movedX, cost, d));
                }
            }
        }


        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++)
            min = Math.min(min, costs[i][end.y][end.x]);

        return min;
    }

}

class Coord {
    int y, x, cost, direction;

    public Coord(int y, int x, int cost, int direction) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.direction = direction;
    }
}