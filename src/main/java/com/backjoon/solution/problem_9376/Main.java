package com.backjoon.solution.problem_9376;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(input.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // padding 추가
            char[][] map = new char[h+2][w+2];

            Coord s1 = null;
            Coord s2 = null;

            for (int i = 1; i <= h; i++) {
                String rowMapStr = input.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = rowMapStr.charAt(j-1);
                    if (map[i][j] == '$') {
                        if (s1 == null) s1 = new Coord(i, j);
                        else s2 = new Coord(i, j);
                    }
                }
            }

            int[][] firstRes = find(s1, map, h, w);
            int[][] secondRes = find(s2, map, h, w);
            int[][] thirdRes = find(new Coord(0, 0, 0), map, h, w);

            int min = Integer.MAX_VALUE;

            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    int m = firstRes[i][j] + secondRes[i][j] + thirdRes[i][j];
                    if (map[i][j] == '#') {
                        m -= 2;
                    }
                    min = Math.min(min, m);
                }
            }

            System.out.println(min);
        }
    }

    static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int[][] find(Coord start, char[][] map, int h, int w) {

        int[][] cost = new int[h+2][w+2];
        for (int[] c : cost) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }

        Queue<Coord> q = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        q.offer(new Coord(start.y, start.x, 0));
        cost[start.y][start.x] = 0;

        while (!q.isEmpty()) {
            Coord c = q.poll();

            if (cost[c.y][c.x] < c.val) continue;

            for (int d = 0; d < 4; d++) {
                int movedY = c.y + direction[d][0];
                int movedX = c.x + direction[d][1];

                if (movedY < 0 || movedY > h + 1 || movedX < 0 || movedX > w + 1 || map[movedY][movedX] == '*') continue;

                int v = c.val;

                if(map[movedY][movedX] == '#') v++;

                if (cost[movedY][movedX] > v) {
                    q.offer(new Coord(movedY, movedX, v));
                    cost[movedY][movedX] = v;
                }
            }
        }

        return cost;
    }

}

class Coord {
    int y, x, val;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Coord(int y, int x, int val) {
        this.y = y;
        this.x = x;
        this.val = val;
    }
}