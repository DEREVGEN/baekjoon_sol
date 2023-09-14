package com.backjoon.solution.problem_22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken()); // 데이터 개수
        K = Integer.parseInt(st.nextToken()); // 삭제 할 수 있는 최대 개수

        boolean[] isOddNum = new boolean[N]; // 홀수 인지 짝수인지 기록하는 데이터

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++)
            isOddNum[i] = (Integer.parseInt(st.nextToken()) % 2 == 1);

        int left = 0, right = 0, count = 0;
        int max = 0;

        while(right < N) {
            if (isOddNum[right]) {
                right++;
                K--;
                if (K < 0) {
                    while(!isOddNum[left]) {
                        left++;
                        count--;
                    }
                    left++;
                    K++;
                }
            } else {
                count++;
                max = Math.max(max, count);
                right++;
            }
        }

        System.out.println(max);
    }
}
