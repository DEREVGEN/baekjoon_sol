package com.backjoon.solution.problem_22868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] graphs;
    static int start, dest;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graphs = new ArrayList[N+1]; // 1부터 시작

        for (int i = 1 ; i <= N; i++)
            graphs[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) { // 연결된 도로 입력(vertex)
            st = new StringTokenizer(input.readLine());

            // a - b 간 경로 입력
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graphs[a].add(b);
            graphs[b].add(a);
        }

        for (int i = 1; i <= N; i++) // 사전순으로 접근해야 함
            Collections.sort(graphs[i]);

        st = new StringTokenizer(input.readLine());

        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        // 경로 구했음

        boolean[] isVisit = new boolean[N+1];
        List<Integer> startToDestPath = findPath(start, dest, isVisit);

        isVisit = new boolean[N+1];
        for (var iter = startToDestPath.iterator(); iter.hasNext();) // start to dest 경로 vertex visit 처리
            isVisit[iter.next()] = true;

        isVisit[start] = false;
        isVisit[dest] = false;

        List<Integer> destToStartPath = findPath(dest, start, isVisit);

        int size = startToDestPath.size() + destToStartPath.size() - 2;
        System.out.println(size);
    }

    public static List<Integer> findPath(int start, int dest, boolean[] isVisit) {

        isVisit[start] = true;

        Queue<Node> que = new LinkedList<>();

        que.add(new Node(start, new ArrayList<Integer>()));


        while(!que.isEmpty()) {
            Node node = que.poll();

            if (node.v == dest) {

                return node.tracer;
            }

            for (var iter = graphs[node.v].iterator(); iter.hasNext();) {
                int nextVertex = iter.next();

                if (isVisit[nextVertex]) continue;

                que.add(new Node(nextVertex, node.tracer));
                isVisit[nextVertex] = true;
            }
        }

        return null;
    }
}

class Node {
    int v;
    List<Integer> tracer;

    public Node(int v, List<Integer> tracer) {
        this.v = v;
        this.tracer = new ArrayList<>();
        this.tracer.addAll(tracer);
        this.tracer.add(v);
    }
}