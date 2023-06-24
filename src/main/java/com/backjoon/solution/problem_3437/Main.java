package com.backjoon.solution.problem_3437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int max;
    static double[][] dist;
    static int N;
    static double R;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;



        while(true) {
            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            R = Double.parseDouble(st.nextToken());
            if (N == 0 && R == 0)
                return;

            R += 0.001;
            max = 0;

            int[] Ya = new int[N];
            int[] Xa = new int[N];

            dist = new double[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                Ya[i] = Integer.parseInt(st.nextToken());
                Xa[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        continue;
                    }
                    dist[i][j] = Math.sqrt(Math.pow(Xa[i] - Xa[j], 2) + Math.pow(Ya[i] - Ya[j], 2)) / 2;
                }
            }


            List<Integer> sets = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                sets.add(i);
                backtrack(sets, i);
                sets.remove(sets.size() - 1);
            }

            System.out.println("It is possible to cover " + max + " points.");
            input.readLine();
        }
    }

    static void backtrack(List<Integer> sets, int current) {

        if (max < sets.size())
            max = sets.size();

        for (int i = current+1; i < N; i++) {
            boolean isPossible = true;

            for (int idx : sets) {
                if (dist[i][idx] > R) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                sets.add(i);
                backtrack(sets, i);
                sets.remove(sets.size()-1);
            }
        }
    }
}
