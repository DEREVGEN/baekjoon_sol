package com.backjoon.solution.problem_1459;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());
        long S = Long.parseLong(st.nextToken());

        long res = X * W + Y * W;
        long res2 = (((X - Y) % 2 == 0) ? Math.max(X, Y) * S : (Math.max(X, Y) - 1) * S + W);
        long res3 = Math.min(X, Y) * S + Math.abs(X - Y) * W;

        System.out.println(Math.min(res, Math.min(res2, res3)));
    }
}