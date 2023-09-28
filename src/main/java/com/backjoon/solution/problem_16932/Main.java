package com.backjoon.solution.problem_16932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static Node[][] tracerMap;
    static Queue<int[]> findPlaces;

    public static void main(String[] args ) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tracerMap = new Node[N][M];

        findPlaces = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;

                if (value == 0) // 0인 장소라면, 찾아야 할 노드들 수집
                    findPlaces.add(new int[] {i, j});
                else // 1인 장소라면, map을 갱신하기 위한 노드 생성
                    tracerMap[i][j] = new Node();
            }
        }


        traceMapBfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tracerMap[i][j] == null)
                    System.out.print("(" + 0 + "," + 0 + ")" + " ");
                else
                    System.out.print("(" + tracerMap[i][j].groupNum + "," + tracerMap[i][j].count + ")" + " ");
            }
            System.out.println();
        }

        System.out.println(findMaxWithOneBlock());

    }

    public static void traceMapBfs() {
        /*
        그룹의 번호와, 개수 갱신
         */

        // bfs를 통해, 1로 이루어진 덩어리 갱신..

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1 ,0}};

        boolean[][] visit = new boolean[N][M];

        int groupNum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 || visit[i][j]) continue;

                Queue<int[]> que = new LinkedList<>(); // bfs 를 위한 queue
                Queue<int[]> tracer = new LinkedList<>(); // 그룹 갱신 및 개수를 갱신 하기 위한 queue

                int[] coord = new int[]{i, j};
                visit[i][j] = true;
                que.add(coord);
                tracer.add(coord);

                while(!que.isEmpty()) {
                    coord = que.poll();

                    for (int d = 0;  d < 4; d++) {
                        int mY = direction[d][0] + coord[0];
                        int mX = direction[d][1] + coord[1];

                        if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 1 && !visit[mY][mX]) {
                            visit[mY][mX] = true;

                            int[] nextCoord = new int[]{mY, mX};
                            que.add(nextCoord);
                            tracer.add(nextCoord);
                        }
                    }
                }

                int count = tracer.size();
                groupNum++;

                while(!tracer.isEmpty()) {
                    coord = tracer.poll();

                    Node tracerNode = tracerMap[coord[0]][coord[1]];
                    tracerNode.count = count;
                    tracerNode.groupNum = groupNum;
                }
            }
        }
    }

    public static int findMaxWithOneBlock() {

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1 ,0}};

        int maxSum = 0;

        while(!findPlaces.isEmpty()) {
            Map<Integer, Integer> plusMap = new HashMap<>();
            int[] coord = findPlaces.poll();
            int y = coord[0];
            int x = coord[1];

            for (int d = 0; d < 4; d++) {
                int mY = y + direction[d][0];
                int mX = x + direction[d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX] == 1) {
                    Node tracerNode = tracerMap[mY][mX];
                    plusMap.put(tracerNode.groupNum, tracerNode.count);
                }
            }

            int sum = 0;
            for (int value : plusMap.values())
                sum += value;

            maxSum = Math.max(sum, maxSum);
        }

        return maxSum + 1;
    }
}


class Node {
    int groupNum;
    int count;

    public Node() {}
}