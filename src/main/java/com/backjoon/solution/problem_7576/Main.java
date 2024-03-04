package com.backjoon.solution.problem_7576;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisit = new boolean[N][M];
        int blockCnt = 0;

        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                // 0이 아닌 블록은 방문 표시
                if (map[i][j] != 0) {
                    isVisit[i][j] = true;
                    blockCnt++;
                }
                if (map[i][j] == 1) {
                    queue.add(new Node(i, j, 0));
                }
            }
        }

        int res = bfs(queue, blockCnt);
        System.out.println(res);
    }

    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int bfs(Queue<Node> queue, int blockCnt) {

        int lastest = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int movedY = node.y + direction[d][0];
                int movedX = node.x + direction[d][1];

                if (movedY < 0 || movedY >= N || movedX < 0 || movedX >= M || isVisit[movedY][movedX]) continue;

                queue.add(new Node(movedY, movedX, node.round + 1));
                lastest = node.round + 1;
                isVisit[movedY][movedX] = true;
                blockCnt++;
            }
        }

        if (blockCnt < N * M)
            return -1;

        return lastest;
    }
}

class Node {
    int y, x, round;

    public Node(int y, int x, int round) {
        this.y = y;
        this.x = x;
        this.round = round;
    }
}