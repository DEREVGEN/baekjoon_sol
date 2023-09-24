package com.backjoon.solution.problem_1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K, N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        K = Integer.parseInt(input.readLine());

        st = new StringTokenizer(input.readLine());


        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = findPath();

        System.out.println(res);
    }

    public static int findPath() {

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] kDirection = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

        Queue<Node> mTracer = new LinkedList<>();

        boolean[][][] isVisit = new boolean[K+1][N][M];

        mTracer.add(new Node(0, 0, K, 0));
        isVisit[0][0][0] = true;

        while(!mTracer.isEmpty()) {
            Node monkey = mTracer.poll();

            if (monkey.y == N-1 && monkey.x == M-1)
                return monkey.cost;

            if (monkey.k > 0) { // 말의 움직임을 이용한 이동
                for (int d = 0; d < 8; d++) {
                    int mY = monkey.y + kDirection[d][0];
                    int mX = monkey.x + kDirection[d][1];

                    if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 0 && !isVisit[monkey.k-1][mY][mX]) {
                        mTracer.add(new Node(mY, mX, monkey.k-1, monkey.cost+1));
                        isVisit[monkey.k - 1][mY][mX] = true;
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int mY = monkey.y + direction[d][0];
                int mX = monkey.x + direction[d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 0 && !isVisit[monkey.k][mY][mX]) {
                    mTracer.add(new Node(mY, mX, monkey.k, monkey.cost+1));
                    isVisit[monkey.k][mY][mX] = true;
                }
            }
        }

        return -1;
    }
}

class Node {
    int y, x, k, cost;

    public Node(int y, int x, int k, int cost) {
        this.y = y;
        this.x = x;
        this.k = k;
        this.cost = cost;
    }
}
