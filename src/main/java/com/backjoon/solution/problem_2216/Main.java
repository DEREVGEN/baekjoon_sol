package com.backjoon.solution.problem_2216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int A, B, C;
    static String s1, s2;
    static int sl1, sl2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        s1 = input.readLine();
        s2 = input.readLine();

        sl1 = s1.length();
        sl2 = s2.length();

        dp = new int[sl1][sl2];

        for (int i = 0; i < sl1; i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);

        System.out.println(find(0, 0));
    }

    public static int find(int idxI, int idxJ) {

        if (idxI == sl1 && idxJ == sl2)
            return 0;
        else if (idxI == sl1 && idxJ < sl2)
            return B * (sl2 - idxJ);
        else if (idxI < sl1 && idxJ == sl2)
            return B * (sl1 - idxI);

        if (dp[idxI][idxJ] != Integer.MIN_VALUE)
            return dp[idxI][idxJ];

        int max = B + find(idxI+1, idxJ);
        max = Math.max(max, B + find(idxI, idxJ+1));
        max = Math.max(max, ((s1.charAt(idxI) == s2.charAt(idxJ))? A : C) + find(idxI+1, idxJ+1));

        return dp[idxI][idxJ] = max;
    }
}
