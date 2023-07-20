package com.backjoon.solution.problem_23289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K;
    static Queue<Node> heaters;
    static Queue<Coord> findingPlaces;
    static Cell obstacles[][];
    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        obstacles = new Cell[R][C];

        heaters = new LinkedList<>();
        findingPlaces = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < C; j++) {
                int mapData = Integer.parseInt(st.nextToken());

                if (mapData >= 1 && mapData <= 4)
                    heaters.add(new Node(i, j, mapData-1, 0));
                else if (mapData == 5)
                    findingPlaces.add(new Coord(i, j));

                obstacles[i][j] = new Cell();
            }
        }

        int W = Integer.parseInt(input.readLine());

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(input.readLine());

            int x, y, t;

            // 체크할것..
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                obstacles[y][x].n = true;
                obstacles[y-1][x].s = true;
            } else {
                obstacles[y][x].e = true;
                obstacles[y][x+1].w = true;
            }
        }

        simulate();

    }

    static void simulate() {
        int[][][] direction = {{{0, 1}, {-1, 1}, {1, 1}}, {{0, -1}, {-1, -1}, {1, -1}}, {{-1, 0}, {-1, -1}, {-1, 1}}, {{1, 0}, {1, -1}, {1, 1}}};

        int chocolate = 0;

        int[][] sumOfMap = new int[R][C];
        Queue<Node> q = new LinkedList<>();

        int my, mx;
        do {
            for (var iter = heaters.iterator(); iter.hasNext(); ) {
                Node heater = iter.next();

                int[][] map = new int[R][C];

                // 추가 할 때도, 조건 검증 필요..
                my = heater.y + direction[heater.d][0][0];
                mx = heater.x + direction[heater.d][0][1];

                if (my < 0 || my >= R || mx < 0 || mx >= C || map[my][mx] != 0 || !canGo(heater.y, heater.x, my, mx, heater.d)) {
                    continue;
                }

                q.add(new Node(my, mx, heater.d, 5));
                map[my][mx] = 5;


                while (!q.isEmpty()) {
                    Node node = q.poll();

                    if (node.c == 1)
                        continue;
                    for (int d = 0; d < 3; d++) {

                        my = node.y + direction[node.d][d][0];
                        mx = node.x + direction[node.d][d][1];

                        if (my < 0 || my >= R || mx < 0 || mx >= C || map[my][mx] != 0 || !canGo(node.y, node.x, my, mx, node.d)) {
                            continue;
                        }

                        map[my][mx] = node.c - 1;
                        q.add(new Node(my, mx, node.d, node.c - 1));

                    }
                }

                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        sumOfMap[i][j] += map[i][j];
                    }
                }

            }

            // 온도 조절 수행
        /*
        동, 서, 남, 북에 대해서 수행한다.
        이러한 direction offset에 대하여, 현재 위치와 [동, 서, 남, 북]에서, 크기가 클때만 비교를 수행한다.
        그렇게되면, N * N 번을 비교하게 된다. 장애물시 가면 갈 수 없다..

        1. 현재 위치에서, [동, 서, 남, 북]으로 갈 수 있는지 체크.
        2. 갈 수 있다면, 큰지 비교, 크다면 온도 조절 수행
        온조 조절 알고리즘:
        (현재 온도-해당 온도)/4 값을 현재위치에 뺀다. 그리고, 해당 위치에 더한다.
         */
            int[][] tempDirection = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            int[][] tempMap = new int[R][C];

            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {

                    if (sumOfMap[y][x] == 0)
                        continue;

                    for (int d = 0; d < 4; d++) {
                        my = y + tempDirection[d][0];
                        mx = x + tempDirection[d][1];

                        if (my < 0 || my >= R || mx < 0 || mx >= C || !canGo(y, x, my, mx, d))
                            continue;

                        if (sumOfMap[y][x] > sumOfMap[my][mx]) {
                            int dividedTemp = (sumOfMap[y][x] - sumOfMap[my][mx]) / 4;

                            tempMap[y][x] -= dividedTemp;
                            tempMap[my][mx] += dividedTemp;
                        }
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sumOfMap[i][j] += tempMap[i][j];
                }
            }

        /* 바깥쪽 칸 1씩 감소
          (완전 바깥이 아닌, 그냥 첫번째 행에서 시작해서 값이 있는 행, 마지막행부터 시작해서 값이 있는 행
          첫번째 열부터 시작해서 값이 있는 열, 마지막 열부터 시작해서 값이 있는 열)
        * */

            for(int c = 0; c < C; c++) {
                if(sumOfMap[0][c] > 0) sumOfMap[0][c]--;
                if(sumOfMap[R-1][c] > 0) sumOfMap[R-1][c]--;
            }

            for(int r = 1; r < R - 1; r++) {
                if(sumOfMap[r][0] > 0) sumOfMap[r][0]--;
                if(sumOfMap[r][C-1] > 0) sumOfMap[r][C-1]--;
            }


            chocolate++;
        } while (terminateCondition(sumOfMap) && chocolate <= 100);

        System.out.println(chocolate);
    }

    static boolean canGo(int y, int x, int movedY, int movedX, int direction) {

        switch (direction) {
            case 2:
            case 3:
                if (movedX > x) {
                    if (obstacles[y][movedX].w)
                        return false;
                } else if (movedX < x) {
                    if (obstacles[y][movedX].e)
                        return false;
                }

                if (movedY > y) {
                    if (obstacles[movedY][movedX].n)
                        return false;
                } else if (movedY < y) {
                    if (obstacles[movedY][movedX].s)
                        return false;
                }
                break;
            case 0:
            case 1:
                if (movedY > y) {
                    if (obstacles[movedY][x].n)
                        return false;
                } else if(movedY < y) {
                    if (obstacles[movedY][x].s)
                        return false;
                }

                if (movedX > x) {
                    if (obstacles[movedY][movedX].w)
                        return false;
                } else if (movedX < x) {
                    if (obstacles[movedY][movedX].e)
                        return false;
                }
                break;
        }

        return true;
    }

    static boolean terminateCondition(int[][] map) {

        for (var iter = findingPlaces.iterator(); iter.hasNext();) {
            Coord findingPlace = iter.next();

            if (map[findingPlace.y][findingPlace.x] < K)
                return true;
        }

        return false;
    }
}

class Cell {
    boolean e,w,s,n;
}

class Coord {
    int x, y;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Node extends Coord {
    int c, d; // cost, direction

    public Node(int y, int x, int d, int c) {
        super(y, x);
        this.d = d;
        this.c = c;
    }
}