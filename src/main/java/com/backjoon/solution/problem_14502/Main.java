package com.backjoon.solution.problem_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Coord> virus;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        virus = new ArrayList<>();

        int wallCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                int inputNum = Integer.parseInt(st.nextToken()) ;

                map[i][j] = inputNum;

                if (inputNum == 2)
                    virus.add(new Coord(i, j));

                if (inputNum == 1)
                    wallCnt++;
            }
        }

        wallCnt += 3;

        backTrack(0, 1);

        System.out.println(N * M - minVirus - wallCnt);
    }


    static int minVirus = Integer.MAX_VALUE;

    public static void backTrack(int idxNum, int depth) {

        for (int w = idxNum; w < N * M; w++) {
            int i = w / M;
            int j = w % M;

            if (i == N || j == M) {
                System.out.println("stop");
            }

            if (map[i][j] == 0) {
                map[i][j] = 1;

                if (depth == 3) {
                    minVirus = Math.min(minVirus, bfsVirus());
                }else {
                    backTrack(w + 1, depth+1);
                }
                map[i][j] = 0;
            }
        }
    }

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int bfsVirus() {
        Queue<Coord> queue = new LinkedList<>();
        boolean[][] isVisit = new boolean[N][M];

        int size = virus.size();

        for (Coord v : virus) {
            queue.add(v);
            isVisit[v.y][v.x] = true;
        }

        while (!queue.isEmpty()) {
            Coord v = queue.poll();

            for (int d = 0; d < 4; d++) {
                int mY = v.y + direction[d][0];
                int mX = v.x + direction[d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && !isVisit[mY][mX] && map[mY][mX] == 0) {
                    isVisit[mY][mX] = true;
                    queue.add(new Coord(mY, mX));
                    size++;
                }
            }
        }

        return size;
    }


}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}