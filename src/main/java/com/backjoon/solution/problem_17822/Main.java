package com.backjoon.solution.problem_17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] disk;
    static int[] head; // disk의 12시방향
    static int N, M, T;
    static int[] d, k, x;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        disk = new int[N][M];
        head = new int[N];
        d = new int[T];
        k = new int[T];
        x = new int[T];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++)
                disk[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int c = 0; c < T; c++) { //T번 진행

            boolean[][] visit = new boolean[N][M];
            boolean isChanged = false;

            st = new StringTokenizer(input.readLine());
            x[c] = Integer.parseInt(st.nextToken());
            d[c] = Integer.parseInt(st.nextToken());
            k[c] = Integer.parseInt(st.nextToken());

            for (int n = x[c]; n-1 < N; n+=x[c]) { // n의 배수로 진행
                head[n-1] = move(head[n-1], k[c], d[c]); // 12시방향(헤더)를 가리키는 index이동
            }

            for (int h = 0; h < N; h++) {
                for (int j = 0; j < M; j++) {
                    if (disk[h][circularMove(head[h], j)] == 0)
                        continue;
                    if (traceSameNum(disk, h, circularMove(head[h], j), visit))
                        isChanged = true;
                }
            }

            if (!isChanged) {
                int sum = 0;
                int count = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (disk[i][j] == 0)
                            continue;

                        sum += disk[i][j];
                        count++;
                    }
                }

                double aver = (double)sum/count;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (disk[i][j] == 0)
                            continue;
                        else if (disk[i][j] > aver)
                            disk[i][j]--;
                        else if (disk[i][j] < aver)
                            disk[i][j]++;
                    }
                }


            }


/*            System.out.println("\t round : " + (c+1) );
            for (int n = 0; n < N; n++)
                System.out.println("disk " + n + " header : " + head[n]);

            for (int n = 0; n < N; n++) {
                for (int j = 0; j < M; j++)
                    System.out.print(disk[n][j] + " ");
                System.out.println();
            }*/
        }


        int sum = 0;

        for (int n = 0; n < N; n++) {
            for (int j = 0; j < M; j++) {
                if (disk[n][j] == 0)
                    continue;
                sum += disk[n][j];
            }
        }

        System.out.println(sum);
    }

    static int move(int idx, int k, int d) {
        int m = k % M;

        if (d == 0) { // 시계
            idx -= m;
            if (idx < 0)
                idx = M + idx;
        } else {
            idx += m;
            if (idx >= M)
                idx %= M;
        }

        return idx;
    }

    static int circularMove(int idx, int m) {
        idx += m;
        if (idx < 0)
            return M + idx;
        else if (idx >= M)
            return idx % M;
        else
            return idx;
    }

    static boolean traceSameNum(int[][] disk, int c, int r, boolean[][] visit) {
        int baseNum = disk[c][r];

        int my, mx;

        Queue<Coord> q = new LinkedList<>();

        // 여기서 중요한 점은 디스크를 가리키는 헤더에 따라 달라짐, 즉, y는 디스크의 높이, x는 위치인데, head를 기준으로 비교함
        for (int d = 0; d < 4; d++) {

            my = c + direction[d][0];
            if (my < 0 || my >= N)
                continue;
            mx = circularMove(head[my], circularMove(r, -head[c]) + direction[d][1]);

            if (visit[my][mx] || disk[my][mx] != baseNum)
                continue;

            q.add(new Coord(my, mx));
            visit[my][mx] = true;
            disk[my][mx] = 0;
        }

        if (q.isEmpty())
            return false;

        disk[c][r] = 0;
        visit[c][r] = true;

        while (!q.isEmpty()) {
            Coord num = q.poll();

            for (int d = 0; d < 4; d++) {

                my = num.y + direction[d][0];
                if (my < 0 || my >= N)
                    continue;

                mx = circularMove(head[my], circularMove(num.x, -head[num.y]) + direction[d][1]);


                if (visit[my][mx] || disk[my][mx] != baseNum)
                    continue;

                disk[my][mx] = 0;
                visit[my][mx] = true;
                q.add(new Coord(my, mx));
            }
        }

        return true;
    }
}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
