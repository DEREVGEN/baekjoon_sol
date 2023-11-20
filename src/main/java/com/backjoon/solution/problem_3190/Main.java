package com.backjoon.solution.problem_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, D;
    static boolean[][] appleMap;
    static Queue<int[]> direction;

    static final int LEFT = 0, SOUTH = 1, RIGHT = 2, NORTH = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        K = Integer.parseInt(input.readLine());

        appleMap = new boolean[N + 1][N + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(input.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            appleMap[x][y] = true;
        }

        D = Integer.parseInt(input.readLine());

        direction = new LinkedList<>();

        for (int i = 1; i <= D; i++) {
            st = new StringTokenizer(input.readLine());

            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            direction.add(new int[]{t, d.equals("L") ? 1 : -1});
        }

        simulate();
    }

    public static void simulate() {

        int time = 1; // 시간

        Coord head = new Coord(1, 1, RIGHT);
        Coord tail = new Coord(1, 1, RIGHT);

        // 지렁이의 몸통은 map의 방향으로 구분, 빈칸=-1
        int[][] map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++)
            Arrays.fill(map[i], -1);
        map[1][1] = RIGHT;

        // x, y 기준
        int[][] mOffset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (true) {

            // 머리 이동
            head.x += mOffset[head.d][0];
            head.y += mOffset[head.d][1];

            if (head.x <= 0 || head.x > N || head.y <= 0 || head.y > N || map[head.x][head.y] >= 0)  { // 몸통 충돌 or 범위 벗어남
                break;
            }
            boolean hasApple = appleMap[head.x][head.y];

            // 꼬리 이동
            if (!hasApple) {
                // 사과를 먹게 되면, tail이 안줄어듬, 먹지 않으면, 줄어듬

                tail.d = map[tail.x][tail.y]; // 꼬리 방향 확인
                map[tail.x][tail.y] = -1; // 맵에서 해당 위치 삭제

                tail.x += mOffset[tail.d][0];
                tail.y += mOffset[tail.d][1];
            } else {
                // 사과를 먹고, 꼬리가 안줄어듬
                appleMap[head.x][head.y] = false;
            }

            int[] t = direction.peek();

            if (t != null && t[0] == time) { // 해당 시간하고 같다면,
                int md = (t[1] + head.d + 4) % 4; // 방향 움직임
                head.d = md;
                direction.poll();
            }
            map[head.x][head.y] = head.d;

            time++; // 시간 1초 흐름
        }

        System.out.println(time);
    }
}

class Coord {
    int x, y, d; // 좌표, 방향

    public Coord(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}