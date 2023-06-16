package com.backjoon.solution.problem_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] direction = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    static int[][] map;
    static int N, L, R;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        for (int day = 0; day < 2000; day++) {

            boolean[][] visit = new boolean[N][N];
            boolean flag = false;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visit[y][x])
                        continue;

                    visit[y][x] = true;

                    List<Coord> countries = new ArrayList<>();
                    countries.add(new Coord(y, x));
                    trace(y, x, countries, visit);

                    if (countries.size() == 1)
                        continue;

                    flag = true;

                    int sum = 0;
                    for (Coord country : countries) {
                        sum += map[country.y][country.x];
                    }
                    int avg = sum / countries.size();

                    for (Coord country : countries) {
                        if (avg != map[country.y][country.x])
                            map[country.y][country.x] = avg;

                    }
                }
            }

            if (!flag) {
                System.out.println(day);
                return;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < N; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }

    static void trace(int y, int x, List<Coord> countries, boolean[][] visit) {

        for (int d = 0; d < 4; d++) {
            int my = y+direction[d][0];
            int mx = x+direction[d][1];

            if  (0 <= my && my < N && 0 <= mx && mx < N && !visit[my][mx]) {
                int gap = Math.abs(map[y][x] - map[my][mx]);
                if (L <= gap && gap <= R) {
                    countries.add(new Coord(my,mx));
                    visit[my][mx] = true;
                    trace(my, mx, countries, visit);
                }
            }
        }
    }
}

class Coord {
    int x, y;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}