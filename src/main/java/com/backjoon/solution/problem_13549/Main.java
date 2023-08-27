package com.backjoon.solution.problem_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        int maxTime = Math.abs(N - K);
        time = new int[100001];
        Arrays.fill(time, Integer.MAX_VALUE);

        if (N >= K) {
            System.out.println(maxTime);
        } else {
            System.out.println(find());
        }
    }

    public static int find() {

        Queue<Node> pq = new PriorityQueue<>();

        time[N] = 0;
        pq.add(new Node(0, N)); // 초기 세팅

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.p == K) {
                return node.t;
            }

            int mp = node.p * 2;
            if (0 <= mp && mp <= 100000 && time[mp] > node.t) {
                time[mp] = node.t;
                pq.add(new Node(node.t, mp));
            }
            mp = node.p + 1;
            if (0 <= mp && mp <= 100000 && time[mp] > node.t + 1) {
                time[mp] = node.t + 1;
                pq.add(new Node(node.t + 1, mp));
            }
            mp = node.p - 1;
            if (0 <= mp && mp <= 100000 && time[mp] > node.t + 1) {
                time[mp] = node.t + 1;
                pq.add(new Node(node.t + 1, mp));
            }
        }

        return time[K];
    }
}

class Node implements Comparable<Node> {
    int t, p;

    public Node(int t, int p) {
        this.t = t;
        this.p = p;
    }

    @Override
    public int compareTo(Node o) {
        return t - o.t;
    }
}