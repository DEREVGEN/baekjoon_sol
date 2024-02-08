package com.backjoon.solution.problem_10816;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        Integer[] cards = new Integer[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            cards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cards);

        int M = Integer.parseInt(input.readLine());

        int[] query = new int[M];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < M; i++) {
            query[i] = Integer.parseInt(st.nextToken());
        }

        LowerUpperBound<Integer> lowerUpperBound = new LowerUpperBound<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int li = lowerUpperBound.lowerBound(cards, query[i]);
            int ui = lowerUpperBound.upperBound(cards, query[i]);

            sb.append(ui - li + 1).append(' ');
        }

        System.out.println(sb);
    }
}

class LowerUpperBound <T extends Number & Comparable<T>> {

    int lowerBound(T[] arr, T key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid].compareTo(key) >= 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    int upperBound(T[] arr, T key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid].compareTo(key) > 0)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return end;
    }
}
