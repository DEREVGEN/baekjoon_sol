package com.backjoon.solution.problem_21609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulate();
    }

    public static void simulate() {

        int[][] mark = new int[N][N];

        int count = 1;
        int base = count;

        int point = 0;

        //Queue<Integer> results = new LinkedList<>();
        int[] results = new int[2];

        while(true) {
            int max = 0;
            int maxY = 0, maxX = 0, maxNum = 0, maxWhiteSize = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= 0 || mark[i][j] > base)
                        continue;

                    bfs(new Coord(i, j), ++count, mark, map[i][j], results);
                    int result = results[0];
                    int whiteSize = results[1];

                    if (max < result || (max == result && maxWhiteSize < whiteSize) || (max == result && maxWhiteSize == whiteSize && i > maxY) || (max == result && maxWhiteSize == whiteSize && i == maxY && j > maxX)) {
                        max = result;
                        maxNum = map[i][j];
                        maxY = i;
                        maxX = j;
                        maxWhiteSize = whiteSize;
                    }
                }
            }
            if (max <= 1)
                break;

            point += max * max;

            base = count;

            remove(new Coord(maxY, maxX), maxNum);
            gravity();
            counterClock90();
            gravity();

/*
            System.out.println();
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.printf("%3d", map[i][j]);
                }
                System.out.println();
            }
*/

        }

        System.out.println(point);
    }

    public static void bfs(Coord startNode, int count, int[][] mark, int baseNum, int[] results) {

        int size = 1, whiteSize = 0;
        Queue<Coord> q = new LinkedList<>();

        q.add(startNode);
        mark[startNode.y][startNode.x] = count;

        while(!q.isEmpty()) {
            Coord node = q.poll();

            for (int d = 0; d < 4; d++) {
                int my = node.y + direction[d][0];
                int mx = node.x + direction[d][1];

                if (my < 0 || my >= N || mx < 0 || mx >= N || (map[my][mx] != baseNum && map[my][mx] != 0) || mark[my][mx] == count)
                    continue;

                if (map[my][mx] == 0)
                    whiteSize++;

                q.add(new Coord(my, mx));
                mark[my][mx] = count;
                size++;
            }
        }

        results[0] = size;
        results[1] = whiteSize;
    }

    static void remove(Coord startNode, int removeIdx) {

        Queue<Coord> q = new LinkedList<>();

        q.add(startNode);
        map[startNode.y][startNode.x] = -2;

        while (!q.isEmpty()) {
            Coord node = q.poll();

            for (int d = 0; d < 4; d++) {
                int my = node.y + direction[d][0];
                int mx = node.x + direction[d][1];

                if (my < 0 || my >= N || mx < 0 || mx >= N || (map[my][mx] != removeIdx && map[my][mx] != 0)) {
                    continue;
                }

                q.add(new Coord(my, mx));
                map[my][mx] = -2;
            }
        }
    }

    static void gravity() {

        for (int x = 0; x < N; x++) {
            for (int y = N-2; y >= 0; y--) {
                if (map[y][x] <= -1)
                    continue;

                int my = y;
                while(my+1 < N && map[my+1][x] == -2) my++;

                if (my != y) {
                    map[my][x] = map[y][x];
                    map[y][x] = -2;
                }
            }
        }
    }

    static void counterClock90() {

        for (int i = 0; i < N / 2; i++)
        {
            for (int j = i; j < N - i - 1; j++)
            {
                int temp = map[i][j];

                map[i][j] = map[j][N - i - 1];
                map[j][N - i - 1] = map[N - i - 1][N - j - 1];
                map[N - i - 1][N - j - 1] = map[N - j - 1][i];
                map[N - j - 1][i] = temp;
            }
        }
    }
}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
