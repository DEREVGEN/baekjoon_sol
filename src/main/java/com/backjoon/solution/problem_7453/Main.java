package com.backjoon.solution.problem_7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(input.readLine());

        int[][] num = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < 4; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        HashMap<Integer, Integer> cdIdx = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cdIdx.merge( -(num[i][2] + num[j][3]), 1, Integer::sum);
            }
        }

        //cdIdx.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += cdIdx.getOrDefault(num[i][0] + num[j][1], 0);
            }
        }

        System.out.println(count);
    }
}
