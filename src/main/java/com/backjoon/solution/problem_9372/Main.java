package com.backjoon.solution.problem_9372;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(input.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int cnt = 0;

            UnionFind unionFind = new UnionFind(N);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (!unionFind.union(a, b)) {
                    cnt++;
                }
            }
            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
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