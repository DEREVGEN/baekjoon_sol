package com.backjoon.solution.problem_14921;

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

        int[] liquid = new int[N];
        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(liquid);

        // 투 포인터 설정
        int left = 0;
        int right = N-1;

        // 0에 가까운 최소값 갱신하기 위한 변수
        int min = Integer.MAX_VALUE;

        while(left < right) {
            int sum = liquid[left] + liquid[right];

            if (Math.abs(sum) < min) {
                min = sum;
            }

            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                System.out.println(min);
                break;
            }
        }

        System.out.println(min);
    }
}