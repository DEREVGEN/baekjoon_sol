package com.backjoon.solution.problem_1802;

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = input.readLine();

            paper = new boolean[s.length()];

            for (int j = 0; j < s.length(); j++)
                paper[j] = (s.charAt(j) == '1');

            boolean res = mergeAndCheck(0, s.length() - 1);
            sb.append((res) ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    static boolean mergeAndCheck(int start, int end) {

        if (start >= end) {
            return true;
        }

        int mid = (start + end) / 2;

        for (int i = start; i < mid; i++) {
            if (paper[i] == paper[end - i])
                return false;
        }

        return mergeAndCheck(start, mid - 1) && mergeAndCheck(mid + 1, end);
    }
}