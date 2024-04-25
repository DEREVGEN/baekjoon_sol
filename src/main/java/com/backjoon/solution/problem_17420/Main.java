package com.backjoon.solution.problem_17420;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int[] expiredArr = new int[N];
        int[] remainArr = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            expiredArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            remainArr[i] = Integer.parseInt(st.nextToken());
        }

        List<Node> gifticons = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            gifticons.add(new Node(expiredArr[i], remainArr[i]));
        }

        Collections.sort(gifticons, (o1, o2) -> {
            if (o1.remain == o2.remain) {
                return Long.compare(o1.expired, o2.expired);
            }
            return Long.compare(o1.remain, o2.remain);
        });

        long cnt = 0;

        long prev = gifticons.get(0).remain;
        long cur = 0;

        for (int i = 0; i < N; i++) {
            Node g = gifticons.get(i);

            if (prev > g.expired) {
                prev = Math.max(prev, g.remain);

                long c = ((prev - g.expired) + 29) / 30;
                g.expired += c * 30;

                cnt += c;
            }

            cur = Math.max(cur, g.expired);

            if ((i + 1) < N && g.remain != gifticons.get(i+1).remain)
                prev = cur;
        }

        System.out.println(cnt);
    }
}

class Node {
    long expired;
    long remain;

    public Node(long expired, long remain) {
        this.expired = expired;
        this.remain = remain;
    }
}