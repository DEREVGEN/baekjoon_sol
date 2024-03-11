package com.backjoon.solution.problem_1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int n = Integer.parseInt(st.nextToken());
        // 거꾸로 입력받음
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        int N = (int) Math.pow(2, n);

        dfs(0,  N - 1, 0, N - 1);
    }

    static int cnt = 0;

    public static void dfs(int fromRow, int toRow, int fromCol, int toCol) {
        if (fromRow == toRow && fromCol == toCol) {
            if (r == fromRow && c == fromCol) {
                System.out.println(cnt);
            }
            return;
        }

        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;

        int blockCnt = (midRow + 1 - fromRow) * (midCol + 1 - fromCol);

        if (fromRow <= r && r <= midRow && fromCol <= c && c <= midCol)
            dfs(fromRow, midRow, fromCol, midCol);
        else
            cnt += blockCnt;
            //cnt += (midRow + 1 - fromRow) * (midCol + 1 - fromCol);

        if (midRow < r && r <= toRow && fromCol <= c && c <= midCol)
            dfs(midRow + 1, toRow, fromCol, midCol);
        else
            cnt += blockCnt;

        if (fromRow <= r && r <= midRow && midCol < c && c <= toCol)
            dfs(fromRow, midRow, midCol + 1, toCol);
        else
            cnt += blockCnt;

        if (midRow < r && r <= toRow && midCol < c && c <= toCol)
            dfs(midRow + 1, toRow, midCol + 1, toCol);
    }
}