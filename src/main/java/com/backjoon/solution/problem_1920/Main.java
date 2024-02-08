package com.backjoon.solution.problem_1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] A;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        A = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int M = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < M; i++) {
            int q = Integer.parseInt(st.nextToken());
            int res = findNum(0, N-1, q);
            sb.append(res).append('\n');
        }

        System.out.println(sb);
    }

    public static int findNum(int start, int end, int fN) {
        // 찾지 못했을 때..
        if (start > end) {
            return 0;
        }

        int mid = (start + end) / 2;
        if (A[mid] < fN) {
            return findNum(mid + 1, end, fN);
        } else if (A[mid] > fN) {
            return findNum(start, mid - 1, fN);
        }

        return 1;
    }
}