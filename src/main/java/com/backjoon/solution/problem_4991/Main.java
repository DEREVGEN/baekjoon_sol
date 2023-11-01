package com.backjoon.solution.problem_4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static List<Coord> goal;
    static int[][] dist;
    static int fullVisited;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {

            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)
                return;

            map = new char[M][N];

            goal = new ArrayList<>();
            Coord start = null;

            for (int i = 0; i < M; i++) {
                String inputMapRow = input.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = inputMapRow.charAt(j);

                    if (map[i][j] == 'o') {
                        start = new Coord(i, j);
                    } else if (map[i][j] == '*') {
                        goal.add(new Coord(i, j));
                    }
                }
            }

            goal.add(start);
            dist = new int[goal.size()][goal.size()];

            boolean isNotValid = false;

            for (int i = 0; i < goal.size(); i++) {

                // visit 으로 해당 거리 계산
                int[][] visitNum = new int[M][N];
                for (int k = 0; k < M; k++) {
                    Arrays.fill(visitNum[k], -1);
                }

                bfs(goal.get(i), visitNum);

                for (int j = 0; j < goal.size(); j++) {
                    Coord g = goal.get(j);
                    // 목표의 거리 dist에 갱신
                    dist[i][j] = visitNum[g.x][g.y];
                    if (dist[i][j] == -1) {
                        // 목표에 닿지 못할때, (=막혀 있을 때,)
                        isNotValid = true;
                        break;
                    }
                }
            }

            if (isNotValid) { // 막혀있을 때 예외 처리
                System.out.println(-1);
                continue;
            }

            memo = new int[goal.size()][1 << goal.size() - 1]; // 시작 지점을 제외 하고, masking 계산해야함
            fullVisited = (1 << goal.size() - 1) - 1; // 시작 지점 제외

            int res = tsp(goal.size() - 1, 0); // 시작지점, index=goal.size()-1
            System.out.println(res);
        }
    }

    public static void bfs(Coord c, int[][] visitNum) {
        Queue<Coord> q = new LinkedList<>();

        visitNum[c.x][c.y] = 0;
        q.add(c);

        int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!q.isEmpty()) {
            Coord n = q.poll();

            for (int i = 0; i < 4; i++) {
                int mx = n.x + dist[i][0];
                int my = n.y + dist[i][1];

                if (0 <= mx && mx < M && 0 <= my && my < N && visitNum[mx][my] < 0 && map[mx][my] != 'x') {
                    q.add(new Coord(mx, my));
                    visitNum[mx][my] = visitNum[n.x][n.y] + 1;
                }
            }
        }
    }

    public static int tsp(int i, int mask) {

        // 마지막 방문시,
        if (mask == fullVisited) {
            return 0;
        }

        // visited
        if (memo[i][mask] != 0)
            return memo[i][mask];

        memo[i][mask] = 1000000;

        for (int j = 0; j < goal.size()-1; j++) {
            if ((mask & (1 << j)) == 0 && dist[i][j] != 0) {
                memo[i][mask] = Math.min(memo[i][mask], tsp(j, mask | (1 << j)) + dist[i][j]);
            }
        }

        return memo[i][mask];
    }

}

class Coord {
    int x, y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}