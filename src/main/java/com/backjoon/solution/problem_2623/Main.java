package com.backjoon.solution.problem_2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        degree = new int[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int pdNum = Integer.parseInt(st.nextToken()); // 그래프의 vertex의 개수
            int from = Integer.parseInt(st.nextToken()); // 미리 from vertex를 받음

            for (int j = 1; j < pdNum; j++) {
                int nextV = Integer.parseInt(st.nextToken());
                graph[from].add(nextV);
                degree[nextV]++;
                from = nextV;
            }
        }

        topologicalSortingBFS();
    }

    public static void topologicalSortingBFS() {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> sortedVertex = new ArrayList<>();

        for (int i = 1; i <= N; i++) { // 차수가 0인 vertex를 큐에 삽입
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            sortedVertex.add(v);

            for (int nV : graph[v]) {
                degree[nV]--;

                if (degree[nV] == 0)
                    queue.add(nV);
            }
        }

        if (sortedVertex.size() != N)
            System.out.println(0);
        else
            sortedVertex.forEach(System.out::println);
    }
}