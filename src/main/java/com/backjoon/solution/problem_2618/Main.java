package com.backjoon.solution.problem_2618;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Coord> incidents;
    static int[][] dp;
    static int[][] tracer;
    static int N, W;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        W = Integer.parseInt(input.readLine());

        incidents = new ArrayList<>();

        incidents.add(new Coord(1, 1));
        incidents.add(new Coord(N, N));

        W += 2;
        dp = new int[W][W];
        tracer = new int[W][W];

        for (int i = 3; i <= W; i++) {
            st = new StringTokenizer(input.readLine());

            incidents.add(new Coord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int res = dealWith(0, 1);

        System.out.println(res);
        trace();
    }

    public static int dealWith(int A, int B) {

        if (dp[A][B] != 0) {
            return dp[A][B];
        }

        int nextIdx = Math.max(A, B) + 1;
        if (nextIdx >= W)
            return 0;

        int resA = dealWith(nextIdx, B) + dist(A, nextIdx);
        int resB = dealWith(A, nextIdx) + dist(nextIdx, B);

        if (resA <= resB)
            tracer[A][B] = 1;
        else
            tracer[A][B] = 2;

        return dp[A][B] = Math.min(resA, resB);
    }

    public static int dist(int p1, int p2) {
        return Math.abs(incidents.get(p1).y - incidents.get(p2).y) + Math.abs(incidents.get(p1).x - incidents.get(p2).x);
    }

    public static void trace() {
        int a = 0, b = 1;

        for (int i = 2; i < W; i++) {
            if (tracer[a][b] == 1) {
                System.out.println(1);
                a = i;
            } else {
                System.out.println(2);
                b = i;
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