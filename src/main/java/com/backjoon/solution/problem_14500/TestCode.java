package com.backjoon.solution.problem_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestCode {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // ㅗ 자 탐색 테스트..
        printMap(map);

        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
        int count;
        int y = 0, x = 1;



        for (int k = 0; k < 4; k++) {
            boolean canGo = true;

            for (int d = 0; d < 4; d++) {
                if (k != d && !checkToGo(y+direction[d][0], x+direction[d][1], N, M)) {
                    canGo = false;
                    break;
                }
            }

            if(canGo) {
                count = map[y][x];
                for (int d = 0; d < 4; d++) {
                    if (k == d)
                        continue;
                    count += map[y + direction[d][0]][x + direction[d][1]];
                }
            }
        }
    }

    static boolean checkToGo(int y, int x, int N, int M) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static void printMap(int[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
