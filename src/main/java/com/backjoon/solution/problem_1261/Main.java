package com.backjoon.solution.problem_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[C][R];

        for (int i = 0; i < C; i++) {
            String inputRowStr = input.readLine();
            for (int j = 0; j < R; j++) {
                map[i][j] = inputRowStr.charAt(j) - '0';
            }
        }

        int res = findGoalMinDest();
        System.out.println(res);
    }

    public static int findGoalMinDest() {
        Queue<Node> pq = new PriorityQueue<>();

        int[][] visitNum = new int[C][R];
        for (int i = 0; i < C; i++)
            Arrays.fill(visitNum[i], Integer.MAX_VALUE);

        pq.offer(new Node(0, 0, 0));
        visitNum[0][0] = 0;

        int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.x == C - 1 && node.y == R - 1)
                return node.cost;

            if (visitNum[node.x][node.y] < node.cost) continue;

            for (int d = 0; d < 4; d++) {
                int mx = node.x + dist[d][0];
                int my = node.y + dist[d][1];

                if (0 > mx || mx >= C || 0 > my || my >= R) continue;

                if (visitNum[mx][my] > node.cost + map[mx][my]) {
                    visitNum[mx][my] = node.cost + map[mx][my];
                    pq.offer(new Node(mx, my, node.cost + map[mx][my]));
                }
            }
        }

        return visitNum[C-1][R-1];
    }
}

class Node implements Comparable<Node>{
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}