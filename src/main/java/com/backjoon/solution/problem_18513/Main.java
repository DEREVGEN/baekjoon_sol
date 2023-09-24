package com.backjoon.solution.problem_18513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static Queue<Integer> houses;
    static Set<Integer> visit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        houses = new LinkedList<>();
        visit = new HashSet<>();

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            int point = Integer.parseInt(st.nextToken());
            houses.add(point); // 샘물 입력
            visit.add(point); // visit 처리
        }

        findHouses();
    }

    public static void findHouses() {

        int[] direction = {-1, 1};
        long dist = 1; // 기본 거리 차이
        long cost = 0; // 최소 값을 갱신할 만족도

        while(!houses.isEmpty()) {
            int size = houses.size(); // 큐의 개수(총 갱신된 집의 개수)

            for (int i = 0; i < size; i++) {
                int house = houses.poll();

                for (int d = 0; d < 2; d++) {
                    int mP = house + direction[d];

                    if (!visit.contains(mP)) { // 방문하지 않았을 경우
                        houses.add(mP);
                        visit.add(mP);
                        cost += dist;
                        if (--K == 0) { // 종료조건
                            System.out.println(cost);
                            return;
                        }
                    }
                }
            }
            dist++;
        }
    }
}