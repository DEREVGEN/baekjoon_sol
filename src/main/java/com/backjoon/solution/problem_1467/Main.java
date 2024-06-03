package com.backjoon.solution.problem_1467;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static String inp, e;
    static String ans = "";
    static int len, pos;
    static int[] numCnt = new int[10], eCnt = new int[10], tCnt = new int[10];

    static boolean isPossible() {
        for (int i = 0; i < 10; i++) {
            if (tCnt[i] > eCnt[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        inp = sc.next();
        e = sc.next();

        for (int i = 0; i < inp.length(); i++) numCnt[inp.charAt(i) - '0']++;
        for (int i = 0; i < e.length(); i++) {
            numCnt[e.charAt(i) - '0']--;
            eCnt[e.charAt(i) - '0']++;
        }

        len = inp.length() - e.length();
        for (int i = 0; i < len; i++) {
            for (int j = 9; j >= 0; j--) {
                if (numCnt[j] == 0) continue;

                Arrays.fill(tCnt, 0);
                for (int ii = pos; (inp.charAt(ii) - '0') != j; ii++) tCnt[inp.charAt(ii) - '0']++;

                if (isPossible()) {
                    while ((inp.charAt(pos) - '0') != j) {
                        eCnt[inp.charAt(pos) - '0']--;
                        pos++;
                    }
                    ans += (char) (j + '0');
                    numCnt[j]--;
                    pos++;

                    break;
                }
            }
        }

        System.out.println(ans);
    }
}