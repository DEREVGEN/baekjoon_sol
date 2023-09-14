package com.backjoon.solution.problem_13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        boolean[] visit = new boolean[100001];

        int left = 0, right = 0; // left는 집합의 왼쪽을 나타내는 index, right는 집합의 오른쪽을 나타내는 index
        visit[S[left]] = true; // 집합의 값에 대한 visit 처리

        long count = 0;

        while(right < N - 1) {
            if (!visit[S[right+1]]) {
                right++;
                visit[S[right]] = true;
            } else {
                count += right - left + 1;
                visit[S[left]] = false;
                left++;
            }
        }

        int n = right - left + 1;
        count += (long) (n + 1) * n / 2;

        System.out.println(count);
    }
}
