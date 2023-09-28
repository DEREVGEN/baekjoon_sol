package com.backjoon.solution.problem_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String inputMapStr = input.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(inputMapStr.charAt(j));
            }
        }

        int res = findGoalBfs();

        System.out.println(res);
    }

    public static int findGoalBfs() {
        boolean[][][] visit = new boolean[2][N][M]; // 3차원 : [0][][] -> 벽을 부수지 않은 상태, [1][][] -> 벽을 부순 상태

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<Node> tracer = new LinkedList<>();
        tracer.add(new Node(0, 0, 1, 0));
        visit[0][0][0] = true;

        while(!tracer.isEmpty()) {
            Node node = tracer.poll();
            if (node.y == N-1 && node.x == M-1)
                return node.cost;

            for (int d = 0; d < 4; d++) {
                int mY = node.y + direction[d][0];
                int mX = node.x + direction[d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && !visit[node.crushNum][mY][mX]) {
                    if (map[mY][mX] == 1 && node.crushNum == 0) {
                        tracer.add(new Node(mY, mX, node.cost+1, 1));
                        visit[1][mY][mX] = true;
                    } else if (map[mY][mX] == 0) {
                        tracer.add(new Node(mY, mX, node.cost+1, node.crushNum));
                        visit[node.crushNum][mY][mX] = true;
                    }
                }
            }
        }

        return -1;
    }
}

class Node {
    int x, y, cost, crushNum;

    public Node(int y, int x, int cost, int crushNum) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.crushNum = crushNum;
    }
}
