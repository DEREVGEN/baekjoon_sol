package com.backjoon.solution.problem_2370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // tree set으로, uppder bound, lower bound를 통하여, 품.
        // tree set에서, 갱신하는데, max(왼쪽값, 오른쪽값) + 1이 해당 면의 값이 된다.

        N = Integer.parseInt(input.readLine());

        TreeSet<Integer> ts = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            Integer l = ts.floor(from);

            if (l == null) {
                map.put(from, 1);
            } else {
                map.put(from, map.get(l) + 1);
            }

            Integer u = ts.ceiling(to);
            if (u == null) {
                map.put(to, 1);
            } else {
                map.put(to, map.get(u) + 1);
            }
            ts.add(from);
            ts.add(to);
        }

        System.out.println(map.values());
    }
}