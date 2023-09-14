package com.backjoon.solution.problem_2283;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] a = new int[N];
        int[] b = new int[N];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());

            min = Math.min(min, a[i]);
            max = Math.max(max, b[i]);
        }

        int l = max - min + 1;
        int[] S = new int[l];

        for (int i = 0; i < N; i++) {
            for (int j = a[i] - min + 1; j <= b[i] - min; j++)
                S[j]++;
        }

        int left = 0, right = 0; // 집합의 왼쪽 index, 오른쪽 index
        long sum = S[left];

        while(left <= right) {
            if (sum < K) {
                if (right+1 == l) {
                    System.out.println("0 0");
                    return;
                }

                right++;
                sum += S[right];
            } else if (sum > K) {
                sum -= S[left+1];
                left++;
            } else {
                System.out.println(((left == 0) ? left : (left+min)) + " " + (right+min));
                return;
            }
        }
    }
}