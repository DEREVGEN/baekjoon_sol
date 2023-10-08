package com.backjoon.algorithm.disjointsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    union - find 알고리즘 - disjoint set을 찾는 알고리즘
 */
public class Main {
    static int[] parent;
    static boolean cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken()); // vertex 개수
        int M = Integer.parseInt(st.nextToken()); // edge 개수


        parent = new int[N+1]; // 1부터 시작

        for (int i  = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            unionParent(v1, v2);
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(i + "의 parent 노드: " + parent[i]);
        }

        System.out.println("cycle 결과 : " + cycle);
    }

    public static int findParent(int nodeNum) {
        /*if (parent[nodeNum] != nodeNum) {
            return findParent(parent[nodeNum]);
        }
        return nodeNum;*/

        if (parent[nodeNum] != nodeNum)
            parent[nodeNum] = findParent(parent[nodeNum]);

        return parent[nodeNum];
    }

    public static void unionParent(int v1, int v2) {
        // parent[findParent(v2)] = findParent(v1);


        // path compression 코드
        int pV1 = findParent(v1);
        int pV2 = findParent(v2);

        if (pV1 == pV2)
            cycle = true;

        if (pV1 < pV2)
            parent[pV2] = pV1;
        else
            parent[pV1] = pV2;
    }
}