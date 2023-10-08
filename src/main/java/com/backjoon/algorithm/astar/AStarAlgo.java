package com.backjoon.algorithm.astar;

import lombok.ToString;

import java.util.PriorityQueue;

public class AStarAlgo {


    // 0 empty, 1 wall, 2 start, 3 goal
    static int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 2, 1, 3, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    static boolean[][] visit;
    final static int movedCost = 1;

    public static void main(String[] args) {

        visit = new boolean[map.length][map[0].length];

        int sY = 0, sX = 0, gY = 0, gX = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 2) {
                    sY = i;
                    sX = j;
                } else if (map[i][j] == 3) {
                    gY = i;
                    gX = j;
                }
            }
        }

        aStar(sY, sX, gY, gX);
    }

    public static void aStar(int sY , int sX, int gY, int gX) {

        int[][] offset = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(sY, sX, 0, 0, 0));
        visit[sY][sX] = true;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.y == gY && node.x == gX) {
                System.out.println(node);
                for (int i = 0; i < visit.length; i++) {
                    for (int j = 0; j < visit[0].length; j++)
                        System.out.print((visit[i][j] ? "1" : "0") + " ");
                    System.out.println();
                }
                break;
            }

            for (int i = 0; i < 4; i++) {
                int my = node.y + offset[i][0];
                int mx = node.x + offset[i][1];

                if (my >= map.length || my < 0 || mx >= map[0].length || mx < 0 || map[my][mx] == 1 || visit[my][mx]) // 범위검사 or 벽검사 or 이미 방문한 노드 검사
                    continue;

                int dist = Math.abs(gY - my) + Math.abs(gX - mx);
                int g = node.g+movedCost;

                // dist(h) + g = f
                pq.offer(new Node(my, mx, dist+g, g, node.cost+1));
                visit[my][mx] = true;
            }
        }
    }
}

@ToString
class Node implements Comparable<Node>{
    int x, y, f, g, cost;

    public Node(int y, int x, int f, int g, int cost) {
        this.y = y;
        this.x = x;
        this.f = f;
        this.g = g;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        if (this.f < o.f)
            return -1;
        else if (this.f > o.f)
            return 1;
        else
            return 0;
    }
}
