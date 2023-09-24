package com.backjoon.solution.problem_16973;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int H, W;
    static int sY, sX, gY, gX;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());


        // 맵 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(input.readLine());

        H = Integer.parseInt(st.nextToken()); // 세로 길이
        W = Integer.parseInt(st.nextToken()); // 가로 길이
        sY = Integer.parseInt(st.nextToken()) - 1; // 시작좌표 Y
        sX = Integer.parseInt(st.nextToken()) - 1; // 시작좌표 X
        gY = Integer.parseInt(st.nextToken()) - 1; // 목표좌표 Y
        gX = Integer.parseInt(st.nextToken()) - 1; // 목표좌표 X

        findPosition();
    }

    public static void findPosition() {

        boolean[][] visit = new boolean[N][M];

        // 동, 서, 남, 북 구별해야함..

        // 처음 queue 에 집어넣음
        Queue<Node> tracer = new LinkedList<>();

        tracer.add(new Node(sY, sX, 0));
        visit[sY][sX] = true;

        boolean canGo;

        // 동, 서, 남, 북 index
        int[][] square = {{0, W}, {0, -1}, {H, 0}, {-1, 0}};
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // bfs 방식
        while(!tracer.isEmpty()) {
            Node position = tracer.poll();

            if (position.y == gY && position.x == gX) {
                System.out.println(position.cost);
                return;
            }

            // 0 : 동, 1 : 서, 2 : 남, 3 : 북, 한칸씩 이동
            for (int d = 0; d < 4; d++) {
                canGo = true;

                int mY = position.y + direction[d][0];
                int mX = position.x + direction[d][1];

                int squareY = position.y + square[d][0]; // 방향에 따른 검사할 직사각형 모서리 인덱스
                int squareX = position.x + square[d][1];

                if (0 <= squareY && squareY < N && 0 <= squareX && squareX < M && !visit[mY][mX]) { // 범위검사 및 방문검사
                    if (d == 0 || d == 1) { // 동, 서 일 경우..
                        for (int h = 0; h < H; h++) {
                            if (map[squareY+h][squareX] == 1) {
                                canGo = false;
                                break;
                            }
                        }
                    } else { // 남, 북 일 경우..
                        for (int w = 0; w < W; w++) {
                            if (map[squareY][squareX+w] == 1) {
                                canGo = false;
                                break;
                            }
                        }
                    }
                    if (canGo) {
                        visit[mY][mX] = true;
                        tracer.add(new Node(mY, mX, position.cost+1));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

class Node {
    int y, x, cost;

    public Node(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }
}
