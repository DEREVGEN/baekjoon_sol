package com.backjoon.solution.problem_11996;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> barn;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        barn = new ArrayList<>();

        for (int i = 0; i < N; i++)
            barn.add(Integer.parseInt(input.readLine()));

        int minIdx = findStartPoint();

        long res = start((minIdx + 1) % N );
        System.out.println(res);
    }

    public static int findStartPoint() {
        int sum = 0;

        int min = Integer.MAX_VALUE;
        int minIdx = 0;

        for (int i = 0; i < N; i++) {
            sum += barn.get(i) - 1;

            if (sum < min) {
                min = sum;
                minIdx = i;
            }
        }

        return minIdx;
    }

    public static long start(int startIdx) {

        Collections.rotate(barn, N - startIdx);

        boolean[] isVisit = new boolean[N];
        long res = 0;

        for (int i = 0; i < N; i++) {
            int k = barn.get(i);
            for (int j = i; k != 0; j++) {
                if (isVisit[j])
                    continue;
                res += (long) (j - i) * (j - i);
                k--;
                isVisit[j] = true;
            }
        }

        // temp
        return res;
    }
}