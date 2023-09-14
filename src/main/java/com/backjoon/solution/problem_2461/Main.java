package com.backjoon.solution.problem_2461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] SS = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++)
                SS[i][j] = Integer.parseInt(st.nextToken());

            Arrays.sort(SS[i]); // 오름차순 정렬
        }

        int[] indexes = new int[N]; // 각 배열의 index

        Queue<Node> minPq = new PriorityQueue<>();
        Queue<Integer> maxPq = new PriorityQueue<>();

        //초기화
        for (int i = 0; i < N; i++) {
            minPq.add(new Node(i, SS[i][0]));
            maxPq.add(-SS[i][0]);
        }

        Node minNode = null;
        int arrayNum = 0;
        int indexNum = 0;

        int gap = Integer.MAX_VALUE;

        while(true) {
            int maxNum = -maxPq.peek();

            do {
                minNode = minPq.poll();
                arrayNum = minNode.arrayNum; // min 값의 배열 번호
                indexNum = indexes[arrayNum]; // 배열 번호에 대한 해당 배열의 index 값
            } while (SS[arrayNum][indexNum] > minNode.value);

            gap = Math.min(gap, maxNum - minNode.value); // 차이값 최소치 갱신

            indexes[arrayNum]++;
            if (indexes[arrayNum] < M) {
                minPq.add(new Node(arrayNum, SS[arrayNum][indexes[arrayNum]]));
                maxPq.add(-SS[arrayNum][indexes[arrayNum]]);
            } else if (indexes[arrayNum] == M) { // 종료조건, 하나의 배열이 길이 M에 닿았을 때..
                System.out.println(gap);
                return;
            }

        }
    }
}

class Node implements Comparable<Node>{

    public Node(int arrayNum, int value) {
        this.value = value;
        this.arrayNum = arrayNum;
    }

    int value;
    int arrayNum;

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
