package com.backjoon.solution.problem_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int start, goal;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        M = Integer.parseInt(input.readLine());

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(input.readLine());
        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        findGoal();
    }

    public static void findGoal() {

        Queue<Node> pq = new PriorityQueue<>();

        int[] visitNum = new int[N+1];
        Arrays.fill(visitNum, Integer.MAX_VALUE);

        int[] prevVertex = new int[N+1];

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (visitNum[vertex.to] < vertex.cost) continue;

            for (Node nextVertex : graph[vertex.to]) {
                if (visitNum[nextVertex.to] > nextVertex.cost + vertex.cost) {
                    pq.add(new Node(nextVertex.to, nextVertex.cost + vertex.cost));
                    visitNum[nextVertex.to] = nextVertex.cost + vertex.cost;
                    prevVertex[nextVertex.to] = vertex.to;
                }
            }
        }

        System.out.println(visitNum[goal]);
        int findIdx = goal;
        Stack<Integer> stack = new Stack<>();
        while(findIdx != 0) {
            stack.push(findIdx);
            findIdx = prevVertex[findIdx];
        }

        System.out.println(stack.size());
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }
        System.out.println(sb);
    }
}

class Node implements Comparable<Node>{
    int to, cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}