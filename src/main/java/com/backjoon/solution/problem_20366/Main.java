package com.backjoon.solution.problem_20366;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        int[] S = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            S[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(S);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N-3; i++) {
            for (int k = i+3; k < N; k++) {
                int j = i + 1;
                int m = k - 1;

                while(j < m) {
                    int gap = S[i] + S[k] - S[j] - S[m];

                    min = Math.min(min, Math.abs(gap));

                    if (gap > 0)
                        j++;
                    else
                        m--;
                }
            }
        }

        System.out.println(min);
    }
}
