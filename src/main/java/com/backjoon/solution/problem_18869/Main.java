package com.backjoon.solution.problem_18869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] numArr = new int[N][M];
        int[] size = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            Map<Integer, Integer> numsIdxMapper = new HashMap<>();

            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                numsIdxMapper.put(num, j); // key : num, value : index
            }

            List<Integer> numList = new ArrayList<>(numsIdxMapper.keySet()); // key 는 num 이므로, num을 정렬시켜야함.
            Collections.sort(numList);
            for (int j = 0; j < numList.size(); j++)
                numArr[i][j] = numsIdxMapper.get(numList.get(j));
            size[i] = numList.size();
        }

        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (size[i] != size[j]) continue;

                if (Arrays.equals(numArr[i], numArr[j]))
                    sum++;
            }
        }

        System.out.println(sum);
    }
}