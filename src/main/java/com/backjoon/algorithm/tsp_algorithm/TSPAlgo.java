package com.backjoon.algorithm.tsp_algorithm;

/*
2023-07-25,
TSP algorithm

https://www.geeksforgeeks.org/travelling-salesman-problem-using-dynamic-programming/
 */

public class TSPAlgo {

    static int n = 4;
    static int MAX = 1000000;

    // dist[i][j] : i to j, dist[j][i] : j to i
    static int[][] dist = {
            { 0, 10, 15, 20 },
            { 10, 0, 25, 25 },
            { 15, 25, 0, 30 },
            { 20, 25, 30, 0 },
    };

    static int[][] memo = new int[n][1 << n];

    public static void main(String[] args) {

        // START 1 to 1, (1 - A - B - C ...- 1 tsp calculating)
        int ans = MAX;

        // start from 0
        for (int i = 1; i < n; i++) {

            ans = Math.min(ans, tsp(i, (1 << n) -1) + dist[i][0]);
        }

        System.out.println(ans);
    }

    public static int tsp(int i, int mask) {

        // only when mask remain ith bit and 0th bit(start node) set,
        if (mask == ((1 << i) | 1))
            return dist[0][i];

        // visited
        if (memo[i][mask] != 0)
            return memo[i][mask];

        int res = MAX;

        for (int j = 0; j < n; j++) {
            if ((mask & (1 << j)) != 0 && j != i && j != 0 && dist[j][i] != 0) {
                res = Math.min(res, tsp(j, mask & (~(1 << i))) + dist[j][i]);
            }
        }

        return memo[i][mask] = res;
    }
}
