package com.backjoon.solution.problem_7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][][] map;
    static boolean[][][] isVisit;
    static int N, M, H;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());


        // (x, y, z)
        isVisit = new boolean[M][N][H];

        int blockCnt = 0;

        Queue<Node> queue = new LinkedList<>();
        // input
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(input.readLine());
                for (int k = 0; k < M; k++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == -1 || num == 1) {
                        isVisit[k][j][i] = true;
                        blockCnt++;
                        if (num == 1) {
                            queue.add(new Node(k, j, i, 0));
                        }
                    }
                }
            }
        }

        if (blockCnt == N * M * H) {
            System.out.println(0);
            return;
        }

        int res = bfs(queue, blockCnt);
        System.out.println(res);
    }

    static int[][] direction = {{0, 0, 1},  {0, 1, 0}, {1, 0, 0}, {0, 0, -1}, {0, -1, 0}, {-1, 0, 0}};

    public static int bfs(Queue<Node> queue, int blockCnt) {

        int mr = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int d = 0; d < 6; d++) {
                int movedX = node.x + direction[d][0];
                int movedY = node.y + direction[d][1];
                int movedZ = node.z + direction[d][2];

                if (movedX < 0 || movedX >= M || movedY < 0 || movedY >= N
                        || movedZ < 0 || movedZ >= H || isVisit[movedX][movedY][movedZ]) continue;

                blockCnt++;
                isVisit[movedX][movedY][movedZ] = true;
                queue.add(new Node(movedX, movedY, movedZ, node.round + 1));
                mr = node.round + 1;
            }
        }

        if (blockCnt != N * M * H)
            return -1;
        return mr;
    }
}

class Node {
    int x, y, z, round;

    public Node(int x, int y, int z, int round) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.round = round;
    }
}