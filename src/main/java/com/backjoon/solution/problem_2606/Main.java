package com.backjoon.solution.problem_2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int M = Integer.parseInt(input.readLine());

        graph = new List[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            graph[from].add(to);
            graph[to].add(from);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisit = new boolean[N];
        queue.offer(0);
        int cnt = 0;
        isVisit[0] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            cnt++;

            for (int nextNode : graph[node]) {
                if (isVisit[nextNode]) continue;
                queue.offer(nextNode);
                isVisit[nextNode] = true;
            }
        }

        System.out.println(cnt-1);
    }
}