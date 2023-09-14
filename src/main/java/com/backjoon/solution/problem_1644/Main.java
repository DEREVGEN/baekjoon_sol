package com.backjoon.solution.problem_1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());

        int left = 2, right = 2; // 집합의 왼쪽을 나타내는 idx, 집합의 오른쪽을 나타내는 idx. 1부터 시작
        int sum = 2; // sum은 집합의 합, 소수의 시작 2
        int count = 0; // 합의 개수

        boolean[] primeDP = getPrimeNumber(); // 미리 소수의 유무를 파악하는 dp

        while(left <= right) {

            if (right == N && sum < N)
                break;

            if (sum > N) {
                sum -= left;
                left++;
                while(left <= right && left <= N && !primeDP[left]) left++;
            } else {
                if (sum == N)
                    count++;

                right++;
                while (right + 1 <= N && !primeDP[right]) right++;
                sum += right;
            }
        }

        System.out.println(count);
    }

    public static boolean isPrimeNumber(int num) {
        int to = (int) Math.sqrt(num);

        for (int i = 2; i <= to; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

    public static boolean[] getPrimeNumber() {
        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        int rootN = (int)Math.sqrt(N);

        for(int i=2; i <= rootN; i++) {
            if(isPrime[i]) {
                for(int j = i + i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}