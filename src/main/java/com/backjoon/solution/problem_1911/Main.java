package com.backjoon.solution.problem_1911;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        List<int[]> waterList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            waterList.add(new int[]{from, to});
        }

        Collections.sort(waterList, (o1, o2) -> o1[0] - o2[0]);

        // 처음 시작시에, 바로 L 카운팅,
        int cnt = 0;
        int from = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int[] water = waterList.get(i);

            if (from < water[0]) {
                from = water[0];
            }

            int q = (water[1] - from) / L;
            int r  = (water[1] - from) % L;

            cnt += q;
            from += q * L;

            if (r > 0) {
                cnt++;
                from += L;
            }
        }

        System.out.println(cnt);

    }
}