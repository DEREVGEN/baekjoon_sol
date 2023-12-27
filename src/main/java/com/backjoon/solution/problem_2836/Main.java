package com.backjoon.solution.problem_2836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        List<Node> paths = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (from > to)
                paths.add(new Node(to, from));
        }

        Collections.sort(paths, (o1, o2) -> {
            if (o1.from == o2.from)
                return o1.to - o2.to;
            return o1.from - o2.from;
        });

        int left = 0;
        int right = 0;
        long sum = G;

        for (Node path : paths) {
            if (path.from > right) {
                sum += 2L * (right - left);
                left = path.from;
                right = path.to;
            } else {
                right = Math.max(right, path.to);
            }
        }

        sum += (2L * (right - left));

        System.out.println(sum);


    }
}

class Node {
    int from, to;

    public Node(int from, int to) {
        this.from = from;
        this.to = to;
    }
}