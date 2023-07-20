package com.backjoon.solution.problem_16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Tree> trees;
    static int[][] nutrient;
    static int[][] land;
    static int N, M, K;
    static int[][] deadTrees;
    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken()); // 맵크기( N by N )
        M = Integer.parseInt(st.nextToken()); // 나무개수
        K = Integer.parseInt(st.nextToken()); // K 년 뒤

        nutrient = new int[N][N]; // 양분을 위한 배열
        land = new int[N][N]; // 땅(양분 데이터를 저장하는)
        deadTrees = new int[N][N]; // 죽은 나무

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                land[i][j] = 5;
            }
        }

        trees = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            int x, y, z;

            st = new StringTokenizer(input.readLine());
            x = Integer.parseInt(st.nextToken()); // x좌표
            y = Integer.parseInt(st.nextToken()); // y좌표
            z = Integer.parseInt(st.nextToken()); // 나무의 크기

            trees.add(new Tree(x-1, y-1, z));
        }

        simulate();

        System.out.println(trees.size());
    }

    public static void simulate() {

        for (int year = 1; year <= K; year++) {
            Iterator<Tree> treeIter = trees.iterator();

            int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
            List<Tree> addedTrees = new LinkedList<>();

            while (treeIter.hasNext()) {
                Tree tree = treeIter.next();
                int ty = tree.y;
                int tx = tree.x;

                // 봄, 봄에는 나무가 양분을 먹고 큰다, 나무가 양분을 먹으면 해당자리의 양분이 마이너스가 된다.
                if (tree.z <= land[ty][tx]) {
                    land[ty][tx] -= tree.z;
                    tree.z++;
                    if (tree.z % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int my = ty + direction[d][0];
                            int mx = tx + direction[d][1];

                            if (my < 0 || my >= N || mx < 0 || mx >= N)
                                continue;

                            addedTrees.add(new Tree(my, mx, 1));
                        }
                    }
                } else {
                    // 양분보다 나이가 크면, 나무는 죽는다.
                    deadTrees[ty][tx] += tree.z >> 1;
                    treeIter.remove();
                }
            }

            trees.addAll(0, addedTrees); // 번식된 나무(크기 1) 을 가지고 있는 나무의 0번째 index에 추가(오름차순 유지)

            // 겨울, 양분을 기존 땅에 추가한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (deadTrees[i][j] > 0) {
                        land[i][j] += nutrient[i][j] + deadTrees[i][j];
                        deadTrees[i][j] = 0;
                    } else {
                        land[i][j] += nutrient[i][j];
                    }
                }
            }

        }
    }
}

class Tree {
    int x, y, z;

    public Tree(int y, int x, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}