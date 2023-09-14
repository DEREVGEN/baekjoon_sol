package com.backjoon.solution.problem_2230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] S = new int[N];

        for (int i = 0; i < N; i++)
            S[i] = Integer.parseInt(input.readLine());

        Arrays.sort(S);

        // N이 1인 경우는 일단 배제

        int l = 0, r = 1; // l은 작은수, r은 큰수를 나타내는 인덱스
        int minD = Integer.MAX_VALUE;

        while (l <= r) {
            int d = S[r] - S[l];
            if (r == N-1 && M > d)
                break;

            if (M <= d) {
                minD = Integer.min(minD, d);
                l++;
            } else {
                if (r + 1 < N) r++;
            }
        }

        System.out.println(minD);
    }
}
