package com.backjoon.solution.problem_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, S;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] A = new int[N];

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0; // left 는 집합에서, 왼쪽의 index, right는 오른쪽 index
        int min = Integer.MAX_VALUE; // 합이 S이상, 최소의 길이를 가진 index
        int sum = A[left]; // 집합의 합

        while(left <= right) {
            if (right == N-1 && sum < S) // 종료조건
                break;


            if (sum >= S) {
                min = Integer.min(right - left + 1, min); // 최소의 길이 갱신
                sum -= A[left];
                left++; // left + 1 이동
            } else {
                if (right + 1 <= N - 1) {
                    right++;
                    sum += A[right];
                }
            }
        }

        System.out.println((min == Integer.MAX_VALUE)? 0 : min);
    }
}