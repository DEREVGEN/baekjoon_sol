package com.backjoon.solution.problem_1450;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static int[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        items = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        long[] leftComb = new long[1 << N/2];
        long[] rightComb = new long[1 << (N - N/2)];

        makeCombination(0, N/2, leftComb);
        makeCombination(N/2, N-N/2, rightComb);

        // 오른쪽 comb만 정렬
        Arrays.sort(rightComb);

        long cnt = 0;
        for (long leftNum : leftComb) {
            int li = upperBound(rightComb, C - leftNum);
            cnt += li;
        }

        System.out.println(cnt);

    }


    // [from, from+size)
    public static void makeCombination(int from, int size, long[] output) {
        for (int i = 0; i < (1 << size); i++) {
            long sum = 0;
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) >= 1) {
                    sum += items[j + from];
                }
            }
            output[i] = sum;
        }
    }

    public static int upperBound(long[] arr, long key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}