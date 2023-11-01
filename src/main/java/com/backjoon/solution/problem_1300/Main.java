package com.backjoon.solution.problem_1300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N, T;

        N = Integer.parseInt(input.readLine());
        T = Integer.parseInt(input.readLine());

        int start = 1;
        int end = T;

        while(start <= end) {
            int mid = (start + end) / 2;

            int m = 0;
            int l = 0;

            for (int i = 1; i <= N; i++) {
                int c = mid / i;

                m += Math.min(c, N);

                if (c <= N) {
                    if (mid % i == 0) {
                        m--;
                        l++;
                    }
                }
            }

            if (m < T && T <= (m+l)) {
                System.out.println(mid);
                return;
            } else if (m >= T) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
}