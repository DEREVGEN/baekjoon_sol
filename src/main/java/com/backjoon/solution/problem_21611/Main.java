package com.backjoon.solution.problem_21611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Stack<Coord> guide;
    static int[][] map;
    static int[] d; // 방향
    static int[] s; // 속력
    static int N, M;
    static int marbleOne = 0, marbleTwo, marbleThree = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        guide = new Stack<>();

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        d = new int[M];
        s = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            d[i] = Integer.parseInt(st.nextToken()) - 1;
            s[i] = Integer.parseInt(st.nextToken());
        }

        makeGuide();
        simulate();


        System.out.println(marbleOne+2*marbleTwo+3*marbleThree);
    }

    static void makeGuide() {


        int count = N * N;

        int[][] snailMap = new int[N][N];

        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0; // 0: 동, 1: 남, 2: 서, 3: 북

        int y = 0, x = 0;

        snailMap[y][x] = count--; // 시작조건
        guide.push(new Coord(y, x));

        while(count > 0) {
            int my = y + direction[d][0];
            int mx = x + direction[d][1];
            if (my < 0 || my >= N || mx < 0 || mx >= N || snailMap[my][mx] != 0) {
                d = (d + 1) % 4;
                my = y + direction[d][0];
                mx = x + direction[d][1];
            }
            y = my;
            x = mx;

            guide.push(new Coord(y, x));
            snailMap[y][x] = count--;
        }
    }

    static void simulate() {

        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 북, 남, 서, 동

        int sY = N/2, sX = N/2; //상어 위치(x, y)
        map[sY][sX] = 50; // 50은 시작을 위한 index

        for (int i = 0; i < M; i++) {
            int marbleY = sY;
            int marbleX = sX;

            for (int c = 1; c <= s[i]; c++) {
                marbleY += direction[d[i]][0];
                marbleX += direction[d[i]][1];

                map[marbleY][marbleX] = -1;
            }

            pushMarbles();

            while(explodeMarbles()) {
                pushMarbles();
            }

            separatedMarbles();
        }

/*        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", map[i][j]);
            }
            System.out.println();
        }*/
    }

    static void pushMarbles() {
        // -1 값을 가진 데이터에 대해서, 앞으로 밀어낸다. (나선형 방향으로..)

        // guide는 나선형 방향으로 index를 나타낸다. 중앙이 마지막이지만, stack 구조이기에, 가장 첫번째이다.

        /*
        목표,
        map에서 최초로 -1 값을 만나면, 해당 자리를 기록한다.
        그리고, -1값 이후로 0으로 채우고, -1을 제외한 데이터는 큐에 넣는다.

        그리고, 최초의 -1자리부터 큐에서 poll시켜 빌때까지 채운다.
         */

        Coord startNode = null;
        Queue<Integer> marbleNums = new LinkedList<>();


        for (var reverseIter = guide.listIterator(guide.size()); reverseIter.hasPrevious();) {
            Coord node = reverseIter.previous();

            if (map[node.y][node.x] == 0)
                break;

            if (map[node.y][node.x] == -1 && startNode == null) {
                startNode = node;
            }

            if (startNode != null) {
                if (map[node.y][node.x] != -1) {
                    marbleNums.add(map[node.y][node.x]);
                }
                map[node.y][node.x] = 0;
            }
        }


        boolean flag = false;
        for (var reverseIter = guide.listIterator(guide.size()); reverseIter.hasPrevious() && !marbleNums.isEmpty();) {
            Coord marble = reverseIter.previous();
            if (marble.y == startNode.y && marble.x == startNode.x)
                flag = true;

            if (flag) {
                map[marble.y][marble.x] = marbleNums.poll();
            }
        }
    }

    static boolean explodeMarbles() {
        /*
        4개 이상인 인접한 구슬에서 폭발한다. (이때, 나선형 구조에서 인접한 경우이다..)
        사실 자기자신으로 포함해서 default로 count 는 1 이여야 한다..
        다만, 직관성을 위해서, count는 0부터 시작한다.
         */

        int count = 0; // 일단 0으로 시작해서, count를 +1 시키고, 만약 인접한 노드의 개수가 4가 안넘어가면 0으로 초기화
        boolean isExplosion = false; //인접한 노드가 있을 때 까지 반복하기 위한 flag

        for (var reverseIter = guide.listIterator(guide.size()); reverseIter.hasPrevious();) {
            Coord node = reverseIter.previous();
            if (map[node.y][node.x] == 0)
                break;

            if (count == 0) {
                count++; // 자기자신..
                for (var subIter = guide.listIterator(reverseIter.nextIndex()); subIter.hasPrevious(); ) {
                    Coord subNode = subIter.previous();

                    if (map[node.y][node.x] == map[subNode.y][subNode.x])
                        count++; // 자기자신이 포함이 안됨..
                    else
                        break;
                }
                if (count < 4)
                    count = 0;
                else {
                    isExplosion = true;
                    switch (map[node.y][node.x]) {
                        case 1:
                            marbleOne += count;
                            break;
                        case 2:
                            marbleTwo += count;
                            break;
                        case 3:
                            marbleThree += count;
                            break;
                    }
                }
            }
            if (count >= 1){
                map[node.y][node.x] = -1;
                count--;
            }

        }

        return isExplosion;
    }

    static void separatedMarbles() {
        /*
        연속하는 구글이 분리가 된다.
        A는 구슬에 개수
        B는 구슬의 번호
        (1, 1) -> {2, 1}
        (2) -> {1, 2}
        (3) -> {1, 3}
        (3, 3) -> {3, 3}
        ...
         */
        // 상어 포인트는 제외

        Queue<Integer> addedMarbles = new LinkedList<>();

        for (var reverseIter = guide.listIterator(guide.size()-1); reverseIter.hasPrevious();) {
            Coord baseMarble = reverseIter.previous();

            if (map[baseMarble.y][baseMarble.x] == 0)
                break;

            int count = 1;
            int type = map[baseMarble.y][baseMarble.x];

            for (var subIter = guide.listIterator(reverseIter.nextIndex()); subIter.hasPrevious(); ) {
                Coord subMarble = subIter.previous();

                if (map[subMarble.y][subMarble.x] == type) { // 연속하는 구슬이 같을 경우
                    count++;
                } else { // 아닐 경우
                    break;
                }
            }

            addedMarbles.add(count); // 개수먼저
            addedMarbles.add(type); // 구슬의 번호

            // count > 1 인 이유는, 상위 for문에서, previous를 수행하기 때문이다. count가 1인 경우에는 어차피 previous를 수행한다.
            for (; count > 1 && reverseIter.hasPrevious(); count--)
                reverseIter.previous();
        }

        for (var reverseIter = guide.listIterator(guide.size()-1); reverseIter.hasPrevious();) {
            Coord node = reverseIter.previous();

            if (addedMarbles.isEmpty()) {
                if (map[node.y][node.x] == 0)
                    break;
                map[node.y][node.x] = 0;
            } else {
                int num = addedMarbles.poll();

                map[node.y][node.x] = num;
            }
        }
    }
}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}