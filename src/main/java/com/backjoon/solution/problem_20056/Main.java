package com.backjoon.solution.problem_20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    파이어볼은, 방향과 속력으로 위치가 결정된다. (해당 방향으로 얼마의 속력인지,)

 */

public class Main {

    static Queue<FireBall>[][] map;
    static Queue<FireBall> balls;
    static int N, M, K;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new LinkedList[N][N];
        balls = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<FireBall>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            // 입력 받을 r c m s d 순으로 지옥의 코드
            balls.offer(new FireBall(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        simulate();


        // 질량의 합 출력

        int sumOfm = 0;

        for (var iter = balls.iterator(); iter.hasNext();) {
            FireBall ball = iter.next();

            sumOfm += ball.m;
        }

        System.out.println(sumOfm);
    }

    static void simulate() {
        /*
        이를 K번 반복하며,

        첫번째로, fireball은 이동한다.
        두번째로, 이동한 fireball은 map에 저장한다.
         */

        // direction 0~7까지 12시 방향으로부터 시계방향
        int[][] direction = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int k = 1; k <= K; k++) {
            while (!balls.isEmpty()) {
                FireBall ball = balls.poll();

                int my, mx;

/*                my = ball.r + direction[ball.d][0] * ball.s;
                mx = ball.c + direction[ball.d][1] * ball.s;*/

                // 속력 * 방향, 1번 열은 N번 열과 연결, 1번 행은 N번 행과 연결
                my = (ball.r + direction[ball.d][0] * ball.s) % N;
                if (direction[ball.d][0] < 0) {
                    my = (- N + 1 + ball.r + direction[ball.d][0] * ball.s) % N;
                    my = N - 1 + my;
                }

                mx = (ball.c + direction[ball.d][1] * ball.s) % N;
                if (direction[ball.d][1] < 0) {
                    mx = (-N + 1 + ball.c + direction[ball.d][1] * ball.s) % N;
                    mx = N - 1 + mx;
                }

                ball.r = my;
                ball.c = mx;
                map[my][mx].offer(ball);
            }

            //  세번째로, map의 size가 >= 2 이상인 위치에서만, 파이어볼이 추가가 된다. 그렇지 않으면, 기존 큐에 추가가 된다.

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].size() == 1) {
                        balls.offer(map[i][j].poll());
                    } else if (map[i][j].size() >= 2) {
                    /*
                       파이어볼이 추가 되는 조건은 다음과 같다.
                       질량의 합 / 5
                       속력의 합 / 파이어볼의 개수
                       파이어볼의 방향이 모두 홀수이거나 짝수이면,
                    */
                        int avgOfM = 0;
                        int avgOfS = 0;
                        int odd = 0, even = 0;
                        int size = map[i][j].size();

                        while (!map[i][j].isEmpty()) {
                            FireBall ball = map[i][j].poll();

                            avgOfM += ball.m;
                            avgOfS += ball.s;

                            if (ball.d % 2 == 0) {
                                even++;
                            } else {
                                odd++;
                            }
                        }

                        avgOfM /= 5;
                        avgOfS /= size;

                        if (avgOfM == 0)
                            continue;

                        int[][] addedDirection = {{0, 2, 4, 6}, {1, 3, 5, 7}};

                        if (even == 0 || odd == 0) {
                            // 모두 홀수 혹은 짝수 일 때,
                            // 방향은 0,2,4,6
                            for (int d = 0; d < 4; d++)
                                balls.offer(new FireBall(i, j, avgOfM, avgOfS, addedDirection[0][d]));
                        } else {
                            // 방향은 1,3,5,7
                            for (int d = 0; d < 4; d++)
                                balls.offer(new FireBall(i, j, avgOfM, avgOfS, addedDirection[1][d]));
                        }

                    }
                }
            }

        }
    }
}

class FireBall {

    int r, c, m, s, d; // 세로, 가로, 질량, 속력, 방향

    public FireBall(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
