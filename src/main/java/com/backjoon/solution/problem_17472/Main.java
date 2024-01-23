package com.backjoon.solution.problem_17472;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[][] map;
    static int[][] blockMap;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        blockMap = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }


        int bc = 0; // 섬 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    blockCheck(i, j, ++bc);
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(blockMap[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 섬에서 섬까지의 거리데이터
        int[][] dist = new int[bc+1][bc+1];
        for (int i = 1; i <= bc; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++) {
                if (blockMap[i][j] >= 1) {
                    int bn = blockMap[i][j];

                    for (int d = 1; d < 3; d++) {
                        int my = i + direction[d][0];
                        int mx = j + direction[d][1];
                        int dv = 0;

                        while (0 <= my && my < N && 0 <= mx && mx < M && blockMap[my][mx] != bn) {
                            if (blockMap[my][mx] != 0) {
                                if (dv > 1)
                                    dist[bn][blockMap[my][mx]] = Math.min(dist[bn][blockMap[my][mx]], dv);
                                break;
                            }
                            my += direction[d][0];
                            mx += direction[d][1];
                            dv++;
                        }
                    }
                }
            }
        }

//        System.out.println();
//        System.out.println();
//        for (int i = 1; i <= bc; i++) {
//            for (int j = 1; j <= bc; j++) {
//                System.out.print((dist[i][j] == Integer.MAX_VALUE ? 0 : dist[i][j])  +  " ");
//            }
//            System.out.println();
//        }

        UnionFind unionFind = new UnionFind(bc);

        List<Node> bridges = new ArrayList<>();

        for (int i = 1; i <= bc; i++) {
            for (int j = 1; j <= bc; j++) {
                if (i == j || dist[i][j] == 0) continue;
                bridges.add(new Node(i, j, dist[i][j]));
            }
        }

        bridges.sort((o1, o2) -> o1.weight - o2.weight);
        int sum = 0;
        for (Node bridge : bridges) {
            if (bridge.weight != Integer.MAX_VALUE && !unionFind.union(bridge.from, bridge.to)) {
                sum += bridge.weight;
            }
        }

        for (int bi = 2; bi <= bc; bi++) {
            if (unionFind.findParent(bi) != 1) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(sum == 0 ? -1 : sum);
    }

    static int[][] direction = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public static void blockCheck(int startY, int startX, int bc) {
        Queue<Coord> queue = new LinkedList<>();

        queue.offer(new Coord(startY, startX));
        map[startY][startX] = false;
        blockMap[startY][startX] = bc;

        while (!queue.isEmpty()) {
            Coord coord = queue.poll();

            for (int d = 0; d < 4; d++) {
                int mY = coord.y + direction[d][0];
                int mX = coord.x + direction[d][1];

                if (0 <= mY && mY < N && 0 <= mX && mX < M && map[mY][mX]) {
                    map[mY][mX] = false;
                    blockMap[mY][mX] = bc;
                    queue.offer(new Coord(mY, mX));
                }
            }
        }
    }
}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Node {
    int from, to, weight;

    public Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class UnionFind {
    int N;
    int[] parent;

    public UnionFind(int n) {
        N = n;
        parent = new int[N+1];
        // 각 노드 번호로 초기화

        //  index 시작점 0 또는 1
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }


    public int findParent(int nodeNum) {
        if (parent[nodeNum] != nodeNum)
            parent[nodeNum] = findParent(parent[nodeNum]);

        return parent[nodeNum];
    }


    public boolean union(int v1, int v2) {
        // path compression 코드
        int pV1 = findParent(v1);
        int pV2 = findParent(v2);

        boolean isCycle = false;

        if (pV1 == pV2) {
            isCycle = true;
            return isCycle;
        }

        if (pV1 < pV2)
            parent[pV2] = pV1;
        else
            parent[pV1] = pV2;

        return isCycle;
    }
}
