package com.backjoon.solution.problem_4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(input.readLine());
            int cnt = 0;
            UnionFind unionFind = new UnionFind(N * 2);
            Map<String, Integer> idxMapper = new HashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                idxMapper.putIfAbsent(name1, cnt++);
                idxMapper.putIfAbsent(name2, cnt++);
                unionFind.union(idxMapper.get(name1), idxMapper.get(name2));
                sb.append(unionFind.getSize(idxMapper.get(name1))).append('\n');
            }
        }

        System.out.println(sb);
    }
}


class UnionFind {
    int N;
    int[] parent;
    int[] size;

    public UnionFind(int n) {
        N = n;
        parent = new int[N+1];
        size = new int[N+1];
        // 각 노드 번호로 초기화

        //  index 시작점 0 또는 1
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
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

        if (pV1 < pV2) {
            parent[pV2] = pV1;
            size[pV1] += size[pV2];
        } else {
            parent[pV1] = pV2;
            size[pV2] += size[pV1];
        }

        return isCycle;
    }

    public boolean isCycle(int v1, int v2) {
        return findParent(v1) == findParent(v2);
    }

    public int getSize(int v) {
        return size[findParent(v)];
    }
}