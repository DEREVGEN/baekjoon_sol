package com.backjoon.solution.problem_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int M = Integer.parseInt(input.readLine());

        UnionFind unionFind = new UnionFind(N);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                int m = Integer.parseInt(st.nextToken());
                if (m == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        st = new StringTokenizer(input.readLine());
        int fn = unionFind.findParent(Integer.parseInt(st.nextToken()) - 1);

        for (int i = 1; i < M; i++) {
            int city = Integer.parseInt(st.nextToken()) - 1;
            if (fn != unionFind.findParent(city)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
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

    public boolean isCycle(int v1, int v2) {
        return findParent(v1) == findParent(v2);
    }
}