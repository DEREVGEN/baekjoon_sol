package com.backjoon.solution.problem_3043;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        List<Node> tankList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            tankList.add(new Node(y, x, i+1));
        }

        /*
            각 탱크는 좌표로 정렬 한 이후, 오름차순부터, 1..N 의 각 위치에 있도록 해야함
         */

        StringBuilder sb = new StringBuilder();

        int cnt = 0;

        // 세로부터 접근하도록 함..
        Collections.sort(tankList, (o1, o2) -> o1.y - o2.y);


        for (int i = 0; i < N; i++) {
            Node tank = tankList.get(i);

            // [i] 번째 탱크는 i+1 위치에 있도록 해야함
            int to = i+1;

            int from = tank.y;

            while (from > to) {
                from--;
                cnt++;
                sb.append(tank.idx).append(" U\n");
            }
            tank.y = from;
        }

        for (int i = N-1; i >= 0; i--) {
            Node tank = tankList.get(i);

            // [i] 번째 탱크는 i+1 위치에 있도록 해야함
            int to = i+1;

            int from = tank.y;

            while (from < to) {
                from++;
                cnt++;
                sb.append(tank.idx).append(" D\n");
            }
            tank.y = from;
        }


        // 가로부터 접근하도록 함..
        Collections.sort(tankList, (o1, o2) -> o1.x - o2.x);
        // 가로로, 각 행으로 배치 시킴..

        for (int i = 0; i < N; i++) {
            Node tank = tankList.get(i);

            // [i] 번째 탱크는 i+1 위치에 있도록 해야함
            int to = i+1;

            int from = tank.x;

            while (from > to) {
                from--;
                cnt++;
                sb.append(tank.idx).append(" L\n");
            }
            tank.x = from;
        }

        for (int i = N-1; i >= 0; i--) {
            Node tank = tankList.get(i);

            // [i] 번째 탱크는 i+1 위치에 있도록 해야함
            int to = i+1;

            int from = tank.x;

            while (from < to) {
                from++;
                cnt++;
                sb.append(tank.idx).append(" R\n");
            }
            tank.x = from;
        }



        System.out.println(cnt);
        System.out.println(sb);
    }
}

class Node {
    int y, x, idx;

    public Node(int y, int x, int idx) {
        this.y = y;
        this.x = x;
        this.idx = idx;
    }
}
