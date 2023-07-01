package com.backjoon.solution.problem_17143;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static public void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int R, C, M;

        R = Integer.parseInt(st.nextToken()); // 열
        C = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 상어개수

        int[][] map = new int[R][C];
        Shark[] sharks = new Shark[M];
        boolean[] mark = new boolean[M]; // 상어가 맵 상에 존재하는지

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                map[i][j] = -1;

        for (int i = 0; i < M; i++) {
            int y,x,s,d,z;

            st = new StringTokenizer(input.readLine());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(y-1,x-1,s,d,z);
            sharks[i] = shark;

            map[y-1][x-1] = i;
        }

        int getcha = 0;

        for (int i = 0; i < C; i++) {
            for (int k = 0; k < R; k++) { // 낚시왕이 물고기를 잡는다.
                if (map[k][i] != -1) {
                    mark[map[k][i]] = true;
                    getcha += sharks[map[k][i]].z;
                    map[k][i] = -1;
                    break;
                }
            }

            for(int j = 0; j < M; j++) {
                if (!mark[j]){ // mark false:존재 true:존재x
                    int distance;
                    int remain;

                    if (map[sharks[j].y][sharks[j].x] == j) // 이전에 존재하는 자리에서 이동, 다른 상어가 이미 존재하면 갱신x
                        map[sharks[j].y][sharks[j].x] = -1;

                    switch (sharks[j].d) {
                        case 1: // 위
                            distance = R - sharks[j].y - 1 + sharks[j].s;
                            remain = distance % (R - 1);
                            if ((distance / (R-1)) % 2 == 0) { //방향 같고, 같은 위치
                                sharks[j].y = R-1-remain;
                            } else { //방향 다르고, 다른 위치
                                sharks[j].y = remain;
                                sharks[j].d = 2;
                            }
                            break;
                        case 2: // 아래
                            distance = sharks[j].y + sharks[j].s;
                            remain = distance % (R - 1);
                            if ((distance / (R-1)) % 2 == 0) { //방향 같고, 같은 위치
                                sharks[j].y = remain;
                            } else { //방향 다르고, 다른 위치
                                sharks[j].y = R-1-remain;
                                sharks[j].d = 1;
                            }
                            break;
                        case 3: // 오른쪽
                            distance = sharks[j].x + sharks[j].s;
                            remain = distance % (C - 1);
                            if ((distance / (C-1)) % 2 == 0) { //방향 같고, 같은 위치
                                sharks[j].x = remain;
                            } else { //방향 다르고, 다른 위치
                                sharks[j].x = C-1-remain;
                                sharks[j].d = 4;
                            }
                            break;
                        case 4: // 왼쪽
                            distance = C - sharks[j].x - 1 + sharks[j].s;
                            remain = distance % (C-1);
                            if ((distance / (C-1)) % 2 == 0) { //방향 같고, 같은 위치
                                sharks[j].x = C-1-remain;
                            } else { //방향 다르고, 다른 위치
                                sharks[j].x = remain;
                                sharks[j].d = 3;
                            }
                            break;
                    }
                    // 이동완료

                    // 잡아먹음
                    if (map[sharks[j].y][sharks[j].x] != -1 && map[sharks[j].y][sharks[j].x] < j) {
                        Shark anotherShark = sharks[map[sharks[j].y][sharks[j].x]];
                        if (anotherShark.z < sharks[j].z) {
                            mark[map[anotherShark.y][anotherShark.x]] = true;
                            map[sharks[j].y][sharks[j].x] = j;
                        } else {
                            mark[j] = true;
                        }
                    } else {
                        map[sharks[j].y][sharks[j].x] = j;
                    }
                }
            }

            //System.out.println(i + " round");
        }

        System.out.println(getcha);
    }

}

class Shark {
    int y, x, s, d, z; // d=1,2,3,4(위,아래,오른쪽,왼쪽)

    public Shark(int y, int x, int s, int d, int z) {
        this.y = y;
        this.x = x;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}