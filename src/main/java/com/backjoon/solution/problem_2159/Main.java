package com.backjoon.solution.problem_2159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine()) + 1;

        List<Coord> people = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            people.add(new Coord(y, x));
        }

        long[][] dp = new long[N][5];

        // 자기자신 위치, 동, 서, 남, 북
        int[][] direction = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 시작 할 때, 거리계산
        Coord start = people.get(0);

        // 시작점과 첫번째 손님에 대한 거리계산
        for (int d = 0; d < 5; d++) {
            Coord person = people.get(1);

            int my = person.y + direction[d][0];
            int mx = person.x + direction[d][1];

            dp[1][d] = dist(start.y, start.x, my, mx);
        }

        for (int i = 2; i < N; i++) {
            // 다음손님에 대한 좌표정보
            Coord nextPerson = people.get(i);
            // 출발할 손님에 대한 좌표정보
            Coord person = people.get(i-1);

            for (int d = 0; d < 5; d++) {
                // 출발할 손님의 좌표 좌표계산
                int my = person.y + direction[d][0];
                int mx = person.x + direction[d][1];


                for (int nextD = 0; nextD < 5; nextD++) {
                    // 다음손님의 좌표 좌표계산
                    int nextY = nextPerson.y + direction[nextD][0];
                    int nextX = nextPerson.x + direction[nextD][1];

                    // 출발할 손님과 다음손님의 좌표의 모든 경우의 수 계산하고, dp에 거리 최솟값 갱신..
                    if (dp[i][nextD] == 0) {
                        dp[i][nextD] = dp[i-1][d] + dist(my, mx, nextY, nextX);
                    } else {
                        dp[i][nextD] = Math.min(dp[i][nextD], dp[i-1][d] + dist(my, mx, nextY, nextX));
                    }
                }
            }
        }

        long min = dp[N-1][0];
        for (int i = 1; i < 5; i++) {
            min = Math.min(min, dp[N-1][i]);
        }

        System.out.println(min);
    }

    public static int dist(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }
}

class Coord {
    int x, y;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}