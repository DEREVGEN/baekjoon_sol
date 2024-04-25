package com.backjoon.solution.problem_2109;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.d == o2.d)
                return o2.p - o1.d;
            return o1.d - o2.d;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.offer(new Node(p, d));
        }

        Queue<Integer> pq2 = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Node c = pq.poll();

            if (pq2.size() < c.d) {
                pq2.offer(c.p);
            } else {
                if (pq2.peek() < c.p) {
                    pq2.poll();
                    pq2.offer(c.p);
                }
            }
        }

        int res = 0;
        while (!pq2.isEmpty()) {
            res += pq2.poll();
        }

        System.out.println(res);

    }
}

class Node {
    int p;
    int d;

    public Node(int p, int d) {
        this.p = p;
        this.d = d;
    }
}