package com.backjoon.solution.problem_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] degree;
    static ArrayList<Integer>[] graph;
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N+1];
        graph = new ArrayList[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to); // 해당 vertex에 연결 vertex 추가
            degree[to]++; // 진입차수 증가
        }

        System.out.println("---BFS---");
        topologicalSortingBFS();
        System.out.println("\n---DFS---");
        topologicalSortingDFS();
    }

    public static void topologicalSortingBFS() {

        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            sb.append(v).append(' ');

            for (int nV : graph[v]) {
                if (degree[nV] == 0) continue;

                degree[nV]--;
                if (degree[nV] == 0)
                    queue.add(nV);
            }
        }

        System.out.println(sb);
    }

    static Stack<Integer> stack = new Stack<>();
    static boolean[] isVisit;

    public static void topologicalSortingDFS() {

        isVisit = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            if (isVisit[i]) continue;

            dfs(i);
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void dfs(int v) {

        isVisit[v] = true; // 방문처리

        for (int nV : graph[v]) {
            if (isVisit[nV]) continue;

            dfs(nV);
        }
        stack.push(v); // 갈데가 없는 vetex를 stack에 삽입
    }
}