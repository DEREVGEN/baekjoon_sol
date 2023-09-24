package com.backjoon.solution.problem_17836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        /*
        0: 비어있는 곳
        1: 막힌 곳
        2: 검
         */
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findPrincess();
    }

    public static void findPrincess() {

        boolean[][][] visit = new boolean[2][N][M]; // 검을 먹은것과 안먹은것 구분
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 시작 초기화
        visit[0][0][0] = true;

        Queue<Node> tracer = new LinkedList<>();
        tracer.add(new Node(0, 0, 0, false));

        while(!tracer.isEmpty()) {

            Node path = tracer.poll();
            if (path.cost > K) // K 시간 이내에 공주를 발견해야함
                break;
            if (path.y == N-1 && path.x == M-1) {
                System.out.println(path.cost);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int mY = path.y + direction[d][0];
                int mX = path.x + direction[d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M) {
                    if (!path.hasSword) {
                        if (!visit[0][mY][mX] && map[mY][mX] == 0) {
                            tracer.add(new Node(mY, mX, path.cost+1, false));
                            visit[0][mY][mX] = true;
                        } else if (!visit[0][mY][mX] && map[mY][mX] == 2) {
                            tracer.add(new Node(mY, mX, path.cost+1, true));
                            visit[0][mY][mX] = true;
                        }
                    } else {
                        if (!visit[1][mY][mX]) {
                            tracer.add(new Node(mY, mX, path.cost+1, true));
                            visit[1][mY][mX] = true;
                        }
                    }
                }

            }
        }

        System.out.println("Fail");
    }
}

class Node {

    public Node(int y, int x, int cost, boolean hasSword) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.hasSword = hasSword;
    }

    int y, x;
    int cost;
    boolean hasSword;
}