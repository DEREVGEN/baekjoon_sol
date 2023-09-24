package com.backjoon.solution.problem_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 1; // count 시작 연도

        while (true) {
            boolean canGo = melt();
            if (!canGo) {
                year = 0;
                break;
            }
            boolean canStop = findIceberg();

            if (canStop)
                break;
            year++;
        }

        System.out.println(year);
    }

    static int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 메모리 많이 먹을라나..?

    public static boolean melt() {
        // 빙하 녹음

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    continue;

                for (int d = 0; d < 4; d++) {
                    int mY = i + direction[d][0];
                    int mX = j + direction[d][1];

                    if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 0) { // 범위검사
                        if (--map[i][j] == 0) {
                            map[i][j] = -1;
                            break;
                        }
                    }
                }
            }
        }

        int iceCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1)
                    map[i][j] = 0;
                else if (map[i][j] > 0) // 현재 남아있는 빙산의 개수 1 이하면 종료하기 위한 로직
                    iceCnt++;
            }
        }

        return iceCnt > 1;
    }

    public static boolean findIceberg() {

        boolean[][] isVisit = new boolean[N][M];

        Queue<Node> tracer = new LinkedList<>();

        int iceCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 || isVisit[i][j])
                    continue;

                iceCnt++;
                if (iceCnt == 2)
                    return true;

                tracer.add(new Node(i, j));
                isVisit[i][j] = true;

                while(!tracer.isEmpty()) {
                    Node ice = tracer.poll();

                    for (int d = 0; d < 4; d++) {
                        int mY = ice.y + direction[d][0];
                        int mX = ice.x + direction[d][1];

                        if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] != 0 && !isVisit[mY][mX]) {
                            tracer.add(new Node(mY, mX));
                            isVisit[mY][mX] = true;
                        }
                    }
                }
            }
        }

        return false;
    }
}

class Node {
    public Node (int y, int x) {
        this.y = y;
        this.x = x;
    }

    int x, y;
}