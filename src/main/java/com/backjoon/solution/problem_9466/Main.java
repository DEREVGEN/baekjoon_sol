package com.backjoon.solution.problem_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        for (int i = 1; i <= N; i++) {
            int membersNum = Integer.parseInt(input.readLine());
            int[] peek = new int[membersNum+1];

            st = new StringTokenizer(input.readLine());

            for (int j = 1; j <= membersNum; j++)
                peek[j] = Integer.parseInt(st.nextToken());

            int res = findNoTeam(membersNum, peek);
            System.out.println(res);
        }
    }

    // 매개변수: 사람 수, 각 사람들이 같이 팀원하고 싶은 번호
    public static int findNoTeam(int N, int[] peek) {

        final int NotVisit = 0;
        int[] sum = new int[N+1]; // 누적합을 위한, 데이터

        int n = 0;
        int hasNoTeamMembers = 0;

        for (int i = 1; i <= N; i++) {
            if (sum[i] != NotVisit) continue;

            int nextIdx = i;
            int startOffset = n+1;

            while(true) {
                sum[nextIdx] = ++n;

                if (sum[peek[nextIdx]] != NotVisit)
                    break;

                nextIdx = peek[nextIdx]; // 고른 사람의 번호로 이동.
            }

            int eIdx = peek[nextIdx]; // cycle 발생한 index 파악, only cycle 발생한 것에 대해서

            if (sum[eIdx] >= startOffset) {
                hasNoTeamMembers += (sum[eIdx] - startOffset);
            } else {
                hasNoTeamMembers += (sum[nextIdx] - startOffset + 1);
            }
        }

        return hasNoTeamMembers;
    }
}
