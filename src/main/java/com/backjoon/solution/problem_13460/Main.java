package com.backjoon.solution.problem_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];

        Coord startR = null, startB = null, goal = null;

        for (int i = 0; i < n; i++) {
            String getMap = input.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = getMap.charAt(j);

                if (map[i][j] == 'R')
                    startR = new Coord(i, j);
                else if (map[i][j] == 'B')
                    startB = new Coord(i, j);
                else if (map[i][j] == 'O')
                    goal = new Coord(i, j);
            }
        }

        boolean[][][][] trace = new boolean[n][m][n][m];

        Beads startNode = new Beads(startR, startB, 0);

        Queue<Beads> bq = new LinkedList<>();
        bq.add(startNode);

        trace[startR.y][startR.x][startB.y][startB.x] = true;

        while(!bq.isEmpty()) {
            Beads beads = bq.poll();
            if (beads.count == 10)
                break;

            for (int direction = 0; direction < 4; direction++) {

                int[][] dirUnit = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동 서 남 북

                Coord r = new Coord(beads.R.y, beads.R.x);
                Coord b = new Coord(beads.B.y, beads.B.x);

                trace[r.y][r.x][b.y][b.x] = true;

                boolean isRedGoal = false, isBlueGoal = false;

                while(map[r.y+dirUnit[direction][0]][r.x+dirUnit[direction][1]] != '#') {
                    r.y += dirUnit[direction][0];
                    r.x += dirUnit[direction][1];

                    if (goal.y == r.y && goal.x ==r.x) {
                        isRedGoal = true;
                        break;
                    }
                }
                while(map[b.y+dirUnit[direction][0]][b.x+dirUnit[direction][1]] != '#') {
                    b.y += dirUnit[direction][0];
                    b.x += dirUnit[direction][1];

                    if (goal.y == b.y && goal.x == b.x) {
                        isBlueGoal = true;
                        break;
                    }
                }

                if(r.y == b.y && r.x == b.x) { // R..B or B.. R -> RB or BR 겹치는 상황에 대해 처리
                    if (Math.abs(beads.R.x - r.x + beads.R.y - r.y) > Math.abs(beads.B.x - b.x + beads.B.y - b.y)) {
                        r.y += -dirUnit[direction][0];
                        r.x += -dirUnit[direction][1];
                    } else {
                        b.y += -dirUnit[direction][0];
                        b.x += -dirUnit[direction][1];
                    }
                }

                if (trace[r.y][r.x][b.y][b.x] || isBlueGoal) // 이미 방문 or Blue가 구멍에 들어간 경우
                    continue;

                if (isRedGoal) { // Red 공만 들어갔을시..
                    System.out.println(beads.count+1);
                    return;
                }
                trace[r.y][r.x][b.y][b.x] = true; // BFS를 위한 기록, 끝에 끝으로만 이동하므로, 끝만 방문처리하면 됨..

                bq.add(new Beads(r, b, beads.count+1));
            }
        }
        System.out.println(-1);
    }
}
