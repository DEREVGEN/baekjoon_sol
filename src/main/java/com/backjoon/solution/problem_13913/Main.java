package com.backjoon.solution.problem_13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] time = new int[100_001];
    static int[] visitDp = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        Arrays.fill(time, Integer.MAX_VALUE);


        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
        } else {
            findBrother();
        }
    }

    public static void findBrother() {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        // time: 0, from: N
        pq.offer(new Node(0, N));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.point == K) {
                System.out.println(node.time);

                int fp = node.point;

                Stack<Integer> resStack = new Stack<>();

                resStack.push(fp);

                while (visitDp[fp] != N) {
                    fp = visitDp[fp];
                    resStack.push(fp);
                }

                resStack.push(N);

                while (!resStack.isEmpty())
                    System.out.print(resStack.pop() + " ");

                return;
            }

            int mp = node.point - 1;
            if (0 <= mp && mp <= 100000 && time[mp] > node.time + 1) {
                pq.add(new Node(node.time + 1, mp));
                time[mp] = node.time + 1;
                visitDp[mp] = node.point;
            }
            mp = node.point + 1;
            if (0 <= mp && mp <= 100000 && time[mp] > node.time + 1) {
                pq.add(new Node(node.time + 1, mp));
                time[mp] = node.time + 1;
                visitDp[mp] = node.point;
            }
            mp = node.point * 2;
            if (0 <= mp && mp <= 100000 && time[mp] > node.time + 1) {
                pq.add(new Node(node.time + 1, mp));
                time[mp] = node.time + 1;
                visitDp[mp] = node.point;
            }
        }
    }

}

class Node {
    int time, point;

    public Node(int time, int point) {
        this.time = time;
        this.point = point;
    }
}