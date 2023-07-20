package com.backjoon.solution.problem_19238;

import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    final static int[][] offset = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N;
    static Coord[] goals; // goal은 사람을 찾으면 확정적으로 결정이 되니, 굳이 맵으로 기록 안하고 따로 list로 유지시킴.
    static int fuel;

    public static void main(String[] args) throws IOException {
        int M;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        goals = new Coord[M];

        map = new int[N][N]; // map is n by n
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sX, sY;
        st = new StringTokenizer(input.readLine());
        sY = Integer.parseInt(st.nextToken()) - 1; // 시작 할 때 택시 위치
        sX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int pY = Integer.parseInt(st.nextToken()) - 1;
            int pX = Integer.parseInt(st.nextToken()) - 1;
            int gY = Integer.parseInt(st.nextToken()) - 1;
            int gX = Integer.parseInt(st.nextToken()) - 1;

            map[pY][pX] = i + 2; // 사람의 위치는 index

            goals[i] = new Coord(gY, gX);
        }

        for (int i = 0; i < M; i++) {
            Node person = findPersonByTaxi(sY, sX); // return: person의 정보, y값, x값, cost값
            if (person == null) {
                System.out.print(-1);
                return;
            }

            int pIdx = map[person.y][person.x];
            map[person.y][person.x] = 0; // 발견한 곳은 사람이 탔으니, 이제 빈자리
            fuel -= person.cost; // cost비용 발생, 택시 연료 뺌,

            ANode taxi = findGoalByTaxi(person.y, person.x, pIdx - 2);
        if (taxi == null) {
            System.out.print(-1);
            return;
        }
        fuel += taxi.f; // going to goal cost.
        sY = taxi.y;
        sX = taxi.x;
    }

        System.out.print(fuel);
    }

    static Node findPersonByTaxi(int sY, int sX) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];

        int count = Integer.MAX_VALUE; // 같은 거리의 손님을 위한, 먼저 최소로 같은 거리를 찾기 위한 count

        q.offer(new Node(sY, sX, 0)); // 택시 시작
        visit[sY][sX] = true;

        Node gNode = null;

        while(!q.isEmpty()) {
            Node node = q.poll();

            if (node.cost > count || node.cost > fuel)
                break;

            if (map[node.y][node.x] >= 2) {
                if (gNode == null) {
                    gNode = node;
                    count = gNode.cost; // 최소 거리 노드 cost 갱신, 같은 cost를 가진, 그리고 적은 index를 찾기 위한 count변수
                } else {
                    if (node.y < gNode.y || (node.y == gNode.y && node.x < gNode.x))
                        gNode = node;
                }
            }

            for (int d = 0; d < 4; d++) {
                int my = node.y + offset[d][0];
                int mx = node.x + offset[d][1];

                if (my >= N || my < 0 || mx >= N || mx < 0 || visit[my][mx] || map[my][mx] == 1) // bfs 조건 검사, count(최소의 거리 노드)에서 bfs상에서 같은 거리 노드가 있을경우 가장 작은 index를 찾기위함
                    continue; // 이 부분 node.cost 최적화 x


                q.offer(new Node(my, mx, node.cost+1));
                visit[my][mx] = true;
            }
        }

        if(gNode == null)
            return null;

        return gNode;
    }

    static ANode findGoalByTaxi(int sY, int sX, int personIdx) {
        PriorityQueue<ANode> pq = new PriorityQueue<>();

        boolean[][] visit = new boolean[N][N];

        pq.offer(new ANode(sY, sX, 0, 0));
        visit[sY][sX] = true;

        Coord goal = goals[personIdx];

        while(!pq.isEmpty()) {
            ANode aNode = pq.poll();

            if (fuel < aNode.g)
                return null;


            if (aNode.y == goal.y && aNode.x == goal.x) {
                return aNode;
            }

            for (int d = 0; d < 4; d++) {
                int my = aNode.y + offset[d][0];
                int mx = aNode.x + offset[d][1];

                if (my >= N || my < 0 || mx >= N || mx < 0 || visit[my][mx] || map[my][mx] == 1) // bfs 조건 검사
                    continue;

                int dist = Math.abs(goal.y - my) + Math.abs(goal.x - mx);
                int g = aNode.g + 1;
                // in a star algo, dist is H, g is G, F = G + H

                pq.offer(new ANode(my, mx, dist + g, g));
                visit[my][mx] = true;
            }
        }

        return null; // 발견하지 못함
    }
}

class Coord {
    int y, x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Node extends Coord{
    int cost;

    public Node(int y, int x, int cost) {
        super(y, x);
        this.cost = cost;
    }
}

class ANode extends Coord implements Comparable<ANode>{
    int f, g;

    public ANode(int y, int x, int f, int g) {
        super(y, x);
        this.f = f;
        this.g = g;
    }

    @Override
    public int compareTo(ANode o) {
        if (this.f < o.f)
            return -1;
        else if (this.f > o.f)
            return 1;
        else
            return 0;
    }
}