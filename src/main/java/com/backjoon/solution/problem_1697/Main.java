package com.backjoon.solution.problem_1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int MAX = 100_000;

        // X-1, X+1, 2*X
        boolean[] isVisit = new boolean[MAX+1];

        isVisit[N] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int n = node[0];
            int c = node[1];
            if (n == K) {
                System.out.println(c);
                return;
            }

            if (n - 1 >= 0 && !isVisit[n - 1]) {
                isVisit[n-1] = true;
                queue.add(new int[]{n - 1, c + 1});
            }

            if (n + 1 <= MAX && !isVisit[n + 1]) {
                isVisit[n + 1] = true;
                queue.add(new int[]{n + 1, c + 1});
            }

            if (2 * n <= MAX && !isVisit[2 * n]) {
                isVisit[2 * n] = true;
                queue.add(new int[]{2 * n, c + 1});
            }
        }

    }
}