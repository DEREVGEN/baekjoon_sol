package com.backjoon.solution.problem_3197;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static Queue<Coord> swans;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        Coord start = null;
        Coord end = null;

        Queue<Coord> landQueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String rowMap = input.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = rowMap.charAt(j);

                if (map[i][j] == 'L') {
                    if (start == null)
                        start = new Coord(i, j);
                    else
                        end = new Coord(i, j);
                    map[i][j] = '.';
                }

                if (map[i][j] == '.') {
                    landQueue.offer(new Coord(i, j));
                }
            }
        }

        /*
            땅에서, 얼음을 녹임..
            처음 시작 -> 다음 녹일 땅만 큐에 담김

            백조 -> bfs 로 동작, 만약 얼음에 있는 곳이라면, 다음 bfs로 돌려야함..

            우선, 백조가 갈 수 있는가? 처음 검증 -> 0번째날
         */

        int round = 0;

        swans = new LinkedList<>();
        swans.offer(start);

        boolean[][] isVisit = new boolean[N][M];
        isVisit[start.y][start.x] = true;

        while (true) {
            if (findEnd(end, isVisit)) {
                System.out.println(round);
                return;
            }

            int size = landQueue.size();
            for (int i = 0; i < size; i++) {
                Coord land = landQueue.poll();

                for (int d = 0; d < 4; d++) {
                    int movedY = land.y + direction[d][0];
                    int movedX = land.x + direction[d][1];

                    if (movedY < 0 || movedY >= N || movedX < 0 || movedX >= M)
                        continue;
                    else if (map[movedY][movedX] == 'X') {
                        landQueue.offer(new Coord(movedY, movedX));
                        map[movedY][movedX] = '.';
                    }
                }
            }
            round++;
        }
    }

    public static boolean findEnd(Coord end, boolean[][] isVisit) {

        Queue<Coord> next = new LinkedList<>();

        while (!swans.isEmpty()) {
            Coord c = swans.poll();

            for (int d = 0; d < 4; d++) {
                int movedY = c.y + direction[d][0];
                int movedX = c.x + direction[d][1];

                if (movedY < 0 || movedY >= N || movedX < 0 || movedX >= M || isVisit[movedY][movedX]) continue;

                if (movedY == end.y && movedX == end.x)
                    return true;

                isVisit[movedY][movedX] = true;
                if (map[movedY][movedX] == 'X') {
                    next.add(new Coord(movedY, movedX));
                    continue;
                }

                swans.add(new Coord(movedY, movedX));
            }
        }

        swans = next;

        return false;
    }
}

class Coord {
    int x, y;

    public Coord(int y, int x) {
        this.x = x;
        this.y = y;
    }
}