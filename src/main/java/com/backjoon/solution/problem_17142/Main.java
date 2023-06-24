package com.backjoon.solution.problem_17142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Coord> viruses;
    static int[][] direction = {{0,1}, {0, -1}, {1,0}, {-1, 0}};
    static int min = Integer.MAX_VALUE;
    static int emptySpaceData = 0;
    static boolean[][] visit;
    static Queue<Virus> q;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2)
                    viruses.add(new Coord(i, j));
                else if (map[i][j] == 0)
                    emptySpaceData++;
            }
        }

        if (emptySpaceData == 0) { // 빈칸이 없고, 벽이나 바이러스만 존재할때,
            System.out.println(0);
            return;
        }

        virusComb(0, 0, new Coord[M]);

        if (min == Integer.MAX_VALUE)
            System.out.println(min);
        else
            System.out.println(-1);
    }

    static void virusComb(int idx, int currentIdx, Coord[] actViruses) {
        if (currentIdx == M) {
            infectionVirus(actViruses);
            return;
        }

        for (int i = idx; i < viruses.size(); i++) {
            actViruses[currentIdx] = viruses.get(i);
            virusComb(i+1, currentIdx+1, actViruses);
        }
    }

    static void infectionVirus(Coord[] actViruses) {
        q = new LinkedList<>();

        visit = new boolean[N][N];

        int emptySpace = emptySpaceData;

        for (Coord virus : actViruses) {
            q.add(new Virus(new Coord(virus.y, virus.x), 0));
            visit[virus.y][virus.x] = true;
        }

        while(!q.isEmpty()) {
            Virus virus = q.poll();

            for (int i = 0; i < 4; i++) {
                int my = virus.virus.y + direction[i][0];
                int mx = virus.virus.x + direction[i][1];

                if (!canGo(my, mx, visit)) {
                    continue;
                }

                if (map[my][mx] == 0) {
                    emptySpace--;

                    if (emptySpace <= 0) { // emptySpace는 빈공간(map상에서 0인 데이터)를 모두 갔을 때, 이제 아무데도 갈 수 없음 -> 종료
                        if (min > virus.time + 1)
                            min = virus.time + 1;
                        return;
                    }
                }

                q.add(new Virus(new Coord(my, mx), virus.time+1));
                visit[my][mx] = true;
            }
        }


    }

    static boolean canGo(int y, int x, boolean[][] visit) {
        return y >= 0 && y < N && x >= 0 && x < N && map[y][x] != 1 && !visit[y][x];
    }
}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Virus {
    int time;
    Coord virus;

    public Virus(Coord virus, int time) {
        this.virus = virus;
        this.time = time;
    }
}