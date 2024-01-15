package com.backjoon.solution.problem_10872;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        int sum = 1;

        for (int i = 2; i <= N; i++)
            sum *= i;

        System.out.println(sum);

    }
}