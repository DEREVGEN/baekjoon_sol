package com.backjoon.solution.problem_18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        List<Integer> node = new ArrayList<>();
        List<Integer> orginal = new ArrayList<>();

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            node.add(n);
            orginal.add(n);
        }

        Collections.sort(node);

        HashMap<Integer, Integer> map = new HashMap<>();

        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(node.get(i))) {
                map.put(node.get(i), idx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map.get(orginal.get(i))).append(" ");
        }
        System.out.println(sb);

    }
}