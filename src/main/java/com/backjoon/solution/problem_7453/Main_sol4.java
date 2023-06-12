package com.backjoon.solution.problem_7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_sol4 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N], B = new int[N], C = new int[N], D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] CD = new int[N*N];
        int[] AB = new int[N*N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        int left = 0, right = N*N-1;
        long count = 0;

        while (left < N*N && right >= 0) {

            if (AB[left] + CD[right] < 0) {
                left++;
            } else if (AB[left] + CD[right] > 0) {
                right--;
            } else {
                int leftCnt = 1, rightCnt = 1;

                while (left+1 < N*N && AB[left] == AB[left+1]) {
                    left++;
                    leftCnt++;
                }
                left++;
                while (right > 0 && CD[right] == CD[right-1]) {
                    right--;
                    rightCnt++;
                }
                right--;
                count += leftCnt * rightCnt;
            }

        }

        System.out.println(count);

    }
}
