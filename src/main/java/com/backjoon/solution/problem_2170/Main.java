package com.backjoon.solution.problem_2170;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        List<Node> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            lines.add(new Node(from, to));
        }

        Collections.sort(lines, (o1, o2) -> {
            if (o1.from == o2.from)
                return o1.to - o2.to;

            return o1.from - o2.from;
        });

        Node prevLine = lines.get(0);

        long sum = prevLine.to - prevLine.from;

        for (int i = 1; i < N; i++) {
            Node line = lines.get(i);

            if (prevLine.from <= line.from && line.to <= prevLine.to)
                continue;

            if (line.from <= prevLine.to) {
                sum += line.to - prevLine.to;
            } else {
                sum += line.to - line.from;
            }
            prevLine = line;
        }

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
