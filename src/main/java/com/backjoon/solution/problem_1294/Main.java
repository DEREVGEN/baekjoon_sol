package com.backjoon.solution.problem_1294;

import java.io.*;
import java.util.*;

public class Main {

    static int[] indexes;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = input.readLine();
        }

        indexes = new int[N];

        StringBuilder ans = new StringBuilder();

        /*
            BBC
            BBA
            문제,

            ZA
            ZAA
            문제,

            만약 같다면, 끝까지 비교해야함, 근데, 만약 하나가 끝까지 가버리면..

            ZA
            ZAB
            ZABZA 가 답이여야 하는데,

            어쨌든, 하나가 끝까지 가버리면, 길이가 남은걸 우선..
         */

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.f == o2.f) {
                int fIdx = indexes[o1.idx];
                int sIdx = indexes[o2.idx];

                for (; fIdx < words[o1.idx].length() && sIdx < words[o2.idx].length(); fIdx++, sIdx++) {
                    if (words[o1.idx].charAt(fIdx) < words[o2.idx].charAt(sIdx))
                        return -1;
                    else if (words[o1.idx].charAt(fIdx) > words[o2.idx].charAt(sIdx))
                        return 1;
                }

                // 여기서, 둘 중 하나가 끝나야함, 둘 다 끝에 있을 수 있음..
                // 근데, 여기서까지 안끝났다는 말은, A 혹은 B 둘 중 하나가 끝에 있다는 말..
                // 근데, 이러한 상황에서는 그냥 긴게 우선일지도 모름.
                int ll = words[o1.idx].length() - fIdx;
                int rl = words[o2.idx].length() - sIdx;

                if (ll > rl)
                    return -1;
                else if (ll < rl)
                    return 1;
                return 0;
            }
            return o1.f - o2.f;
        });

        for (int i = 0; i < N; i++) {
            pq.offer(new Node(words[i].charAt(0), i));
        }


        while (!pq.isEmpty()) {
            Node c = pq.poll();

            ans.append(c.f);

            if (indexes[c.idx] + 1 >= words[c.idx].length()) continue;

            indexes[c.idx]++;
            pq.offer(new Node(words[c.idx].charAt(indexes[c.idx]), c.idx));
        }

        System.out.println(ans);
        // System.out.println(ans.toString().equals("CCCAAACCCAAACCCCCC"));
    }
}

class Node {
    char f;
    int idx;

    public Node(char f, int idx) {
        this.f = f;
        this.idx = idx;
    }
}