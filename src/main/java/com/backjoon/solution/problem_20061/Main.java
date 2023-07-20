package com.backjoon.solution.problem_20061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int green = 0;
    static final int blue = 1;
    static boolean[][][] board;
    static int score = 0;

    public static void main(String[] args) throws IOException {

        board = new boolean[2][6][4];

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t, y, x;
        int N;

        N = Integer.parseInt(input.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            t = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            // t == 1, 1 * 1 블록
            // t == 2, 1 * 2 블록을 일단 전제.
            // t == 3, 2 * 1블록, -> (y, x), (y+1 ,x)블록으로 검증

            int my = down(green, t, x);

            if (t == 2) {
                board[green][my][x] = true;
                board[green][my][x+1] = true;
                t = 3;
            } else if (t == 3) {
                board[green][my][x] = true;
                board[green][my - 1][x] = true;
                t = 2;
            } else {
                board[green][my][x] = true;
            }


            // 90도 회전
            // x -> y, y -> x 일때, transformation 연산
            my = down(blue, t, y);

            if (t == 2) {
                board[blue][my][y] = true;
                board[blue][my][y+1] = true;
            } else if (t == 3) {
                board[blue][my][y] = true;
                board[blue][my - 1][y] = true;
            } else {
                board[blue][my][y] = true;
            }

            blockClear(green);
            blockClear(blue);
        }

        int sum = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 4; k++) {
                    if (board[i][j][k])
                        sum++;
                }
            }
        }

        System.out.println("score: " + score);
        System.out.println("sum: " + sum);
    }

    public static int down(int idx, int t, int x) {
        int my = 0;
        while(my <= 4) {
            if (t == 2) {
                if (board[idx][my + 1][x] || board[idx][my + 1][x + 1]) {
                    break;
                }
                my++;
            } else { // t == 1 or t == 3
                if (board[idx][my + 1][x])
                    break;
                my++;
            }
        }

        return my;
    }

    public static void blockClear(int idx) {
        for (int i = 0; i < 6; i++) {
            boolean check = true;

            for (int j = 0; j < 4; j++)
                if (!board[idx][i][j])
                    check = false;

            if (!check) {
                continue;
            }

            for (int j = i; j >= 1; j--) {
                // j <- j -1
                for (int k = 0; k < 4; k++)
                    board[idx][j][k] = board[idx][j-1][k];
            }
            for (int k = 0; k < 4; k++)
                board[idx][0][k] = false;
            i--; // y index 0 삭제 -> i 는 1로 이동하는데, 또 다시 0을 검사해야함
            score++; // 점수 추가
        }

        // y축 인덱스 1에 블록이 있다면, 밑으로 밀어내야함
        // 우선 y축 idx 0 에 하나라도 존재한다면, +1, y축 idx 1에 블록이 하나라도 존재한다면 +1
        // 둘다 존재할경우 +2가 된다.
        int count = 0;
        for (int ly = 0; ly <= 1; ly++) {
            for (int lx = 0; lx < 4; lx++)
                if (board[idx][ly][lx]) {
                    count++;
                    break;
                }
        }

        if (count == 0)
            return;

        for (int ly = 5; ly >= count; ly--) {
            for (int lx = 0; lx < 4; lx++) {
                board[idx][ly][lx] = board[idx][ly-count][lx];
            }
        }

        for (int ly = 0; ly < count; ly++) {
            for (int lx = 0; lx < 4; lx++)
                board[idx][ly][lx] = false;
        }
    }
}
