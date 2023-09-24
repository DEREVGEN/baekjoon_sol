package com.backjoon.solution.problem_4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[][] map;
    static Queue<Node> pTracer;
    static Queue<Node> fTracer;
    static boolean[][] fVisit;
    static boolean[][] pVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        pTracer = new LinkedList<>();
        fTracer = new LinkedList<>();

        fVisit = new boolean[N][M];
        pVisit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String inputLine = input.readLine();

            for (int j = 0; j < M; j++) {
                int ch = inputLine.charAt(j);

                switch (ch) {
                    case '#' -> map[i][j] = 1; // 벽
                    case '.' -> map[i][j] = 0; // 빈 곳
                    case 'F' -> {
                        map[i][j] = 2; // 불
                        fTracer.add(new Node(i, j));
                        fVisit[i][j] = true;
                    }
                    case 'J' -> {
                        map[i][j] = 3; // 사람
                        pTracer.add(new Node(i, j));
                        pVisit[i][j] = true;
                    }
                }
            }
        }

        int time = escapeFromRoom();
        if (time == -1)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(time);
    }

    public static int escapeFromRoom() {

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int time = 0;

        while (!pTracer.isEmpty()) {
            time++;

            int size = fTracer.size();
            for (int i = 0; i < size; i++) {
                Node fire = fTracer.poll();

                for (int d = 0; d < 4; d++) {
                    int mY = fire.y + direction[d][0];
                    int mX = fire.x + direction[d][1];

                    if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] != 1 && !fVisit[mY][mX]) {
                        fTracer.add(new Node(mY, mX));
                        fVisit[mY][mX] = true;
                    }
                }
            }
            size = pTracer.size();
            for (int i = 0; i < size; i++) {
                Node jihoon = pTracer.poll();

                for (int d = 0; d < 4; d++) {
                    int mY = jihoon.y + direction[d][0];
                    int mX = jihoon.x + direction[d][1];

                    if (0 <= mY && mY < N && 0 <= mX && mX < M) {
                        if (map[mY][mX] != 1 && !fVisit[mY][mX] && !pVisit[mY][mX]) {
                            pTracer.add(new Node(mY, mX));
                            pVisit[mY][mX] = true;
                        }
                    } else {
                        return time; // 탈출조건
                    }
                }
            }
        }

        return -1;
    }

}

class Node {
    int x, y;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}