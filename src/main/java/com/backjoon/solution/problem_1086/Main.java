package com.backjoon.solution.problem_1086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] tenRemain; // 10^n 에 대한 K를 미리 나누기 위한 전처리 데이터
    static int K; // 나머지 수
    static String[] num; // input num
    static int[] cacheRemain; // num 나머지 caching
    static long[][] dp;
    static int fullVisit;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        long allCase = 1;

        // input
        N = Integer.parseInt(input.readLine());
        num = new String[N];
        cacheRemain = new int[N];

        for (int i = 0; i < N; i++) {
            num[i] = input.readLine();
            allCase = allCase * (i+1);
        }
        K = Integer.parseInt(input.readLine());

        dp = new long[K][1 << N];
        for (int i = 0; i < K; i++)
            Arrays.fill(dp[i], -1);

        fullVisit = (1 << N) - 1;

        // pre processing data
        tenRemain = new int[51];
        tenRemain[0] = 1 % K; // 1을 10에 대한 나머지

        for (int i = 1; i <= 50; i++) // 전처리
            tenRemain[i] = (tenRemain[i-1] * 10) % K;

        for (int i = 0; i < N; i++) {
            int remain = Character.getNumericValue(num[i].charAt(0)) % K; // num[i] 을 첫째자리 수에 대한 나머지
            for (int j = 1; j < num[i].length(); j++) {
                remain = (remain * 10 + Character.getNumericValue(num[i].charAt(j))) % K; // (이전의 나머지 수 * 10 + 현재 index에 대한 숫자) % K
            }
            cacheRemain[i] = remain;
        }

        // process
        long res = findRemain(0, 0);
        long gcd = gcd(allCase, res);

        System.out.println(res/gcd+"/"+allCase/gcd);
    }

    public static long findRemain(int r, int visit) {

        // 종료조건
        if (visit == fullVisit) {
            return (r % K == 0) ? 1 : 0;
        }
        if (dp[r][visit] != -1)
            return dp[r][visit];

        dp[r][visit] = 0;
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) != 0)
                continue;

            int res = (r * tenRemain[num[i].length()] + cacheRemain[i]) % K;

            dp[r][visit] += findRemain(res, visit | (1 << i));
        }

        return dp[r][visit];
    }

    public static long gcd(long A, long B) {

        long tmp;
        if (A < B) {
            tmp = A;
            A = B;
            B = tmp;
        }

        long n;
        while(B != 0) {
            n = A % B;
            A = B;
            B = n;
        }

        return A;
    }
}
