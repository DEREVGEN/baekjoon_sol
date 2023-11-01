package com.backjoon.solution.problem_2585;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, T;
    static Coord[] plane;

    static final int MAX = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        plane = new Coord[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            plane[i] = new Coord(x, y);
        }
        plane[N] = new Coord(MAX, MAX);

        int start = 0;
        int end = getOil(new Coord(0, 0), new Coord(MAX, MAX));

        while(start <= end) {
            int mid = (end + start) / 2;

            if (bfs(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    static boolean bfs(int mid) {
        Queue<Coord> queue = new LinkedList<>();
        boolean[] isVisit = new boolean[N+1];

        Coord start = new Coord(0, 0);
        start.setC(0);
        queue.add(start);

        while(!queue.isEmpty()) {
            Coord p = queue.poll();

            if (p.x == MAX && p.y == MAX)
                return true;
            if (p.c > T)
                continue;

            for (int i = 0; i <= N; i++) {
                if (isVisit[i] || getOil(p, plane[i]) > mid)
                    continue;
                isVisit[i] = true;
                plane[i].setC(p.c+1);
                queue.add(plane[i]);
            }
        }

        return false;
    }

    static int getOil(Coord c1, Coord c2) {
        return (int) Math.ceil(Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2)) / 10);
    }
}

class Coord {
    int x, y, c;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setC(int c) {
        this.c = c;
    }
}