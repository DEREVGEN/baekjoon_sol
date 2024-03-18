package com.backjoon.solution.problem_27433;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        if (N == 0) {
           System.out.println(1);
           return;
        }

        long num = N;

        for (int i = N - 1; i >= 2; i--) {
            num = num * i;
        }

        System.out.println(num);
    }
}