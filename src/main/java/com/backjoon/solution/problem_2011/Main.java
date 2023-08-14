package com.backjoon.solution.problem_2011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] memorize;
    static int[] encodedNums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String encodedStr;

        encodedStr = input.readLine();

        int N = encodedStr.length();
        encodedNums = new int[N];
        memorize = new int[N+1];


        for (int i = 0; i < N; i++)
            encodedNums[i] = encodedStr.charAt(i) - '0';

        memorize[0] = 1;
        memorize[1] = 1;

        if (encodedNums[0] == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 2; i <= N; i++) {

            if (encodedNums[i-1] != 0) {
                memorize[i] += memorize[i - 1];
                memorize[i] %= 1000000;
            }

            int subNum2 = encodedNums[i-2] * 10 + encodedNums[i-1];
            if (10 <= subNum2 && subNum2 <= 26) {
                memorize[i] += memorize[i - 2];
                memorize[i] %= 1000000;
            }
        }

        System.out.println(memorize[N]);
    }
}
