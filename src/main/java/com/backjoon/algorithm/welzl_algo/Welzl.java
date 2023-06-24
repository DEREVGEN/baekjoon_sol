package com.backjoon.algorithm.welzl_algo;

/*
    2023-06-20,
    Smallest Enclosing Circle 알고리즘 - Welzl 알고리즘
    이 알고리즘은, 처음부터 하나씩 점을 추가하면서, 원을 그려가는 재귀적 구조이다.
    아마, 이중 재귀적 구조 때문에 헷갈렸을지도 모르겠지만, 원을 형성하는 두개 혹은 세개의 Point로 구성을 한다.

    아직 보완해야 할 점이 있다.. R을 넘길 때, 계속해서 clone을 하는데, 이 부분이 성능에 영향을 미칠것 같기도 하다..
    단순 추가 정도면 좋을것 같지만..
 */

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Welzl {

    static int num = 0; // 재귀호출의 count를 셈..

    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();
        points.add(new Point(5, -2));
        points.add(new Point(-3, -2));
        points.add(new Point(-2, 5));
        points.add(new Point(1, 6));
        points.add(new Point(0, 2));
        points.add(new Point(8, 6));
        points.add(new Point(0, 4));
        points.add(new Point(1, 8));
        points.add(new Point(0, 2));
        points.add(new Point(7, -2));
        points.add(new Point(-8, -2));
        points.add(new Point(-9, 5));
        points.add(new Point(2, 6));
        points.add(new Point(3, 2));
        points.add(new Point(4, 6));
        points.add(new Point(5, 4));
        points.add(new Point(6, 8));
        points.add(new Point(7, 2));

        Collections.shuffle(points); // 요소 섞음..

        Circle c = miniCircle(points, new ArrayList<Point>());

        System.out.println(c);
        System.out.println("points size: " + points.size() + " calling num: " + num);
    }

    public static Circle miniCircle(List<Point> P, ArrayList<Point> R) {
        num++;
        if (P.isEmpty() || R.size() == 3) {
            return b_md(P, R);
        }

        int idx = (int) (Math.random() * P.size());
        Collections.swap(P, idx, P.size()-1); // 랜덤한 index인, idx와 P의 마지막 요소 swap

        Circle d = miniCircle(P.subList(0, P.size()-1), R);

        if (d.contain(P.get(P.size()-1))) { // 마지막 요소와 비교, 마지막요소 = 랜덤하게 뽑힌 index
            return d;
        }

        ArrayList<Point> newR = (ArrayList<Point>) R.clone();
        newR.add(P.get(P.size()-1));

        return miniCircle(P.subList(0, P.size()-1), newR);
    }

    public static Circle b_md(List<Point> P, List<Point> R) {
        if (R.size() == 0) {
            return new Circle(new Point(0, 0), 0);
        } else if (R.size() == 1) {
            return new Circle(new Point(R.get(0).X, R.get(0).Y), 0);
        } else if (R.size() == 2) {
            return twoPointCircle(R.get(0), R.get(1));
        }

        // when R.size() == 3
        // R[0] R[1] R[2] 에서, R[0] 는 root 재귀 처음 발생한 root index이므로,
        // (R[0], R[1]) contain R[2]? , (R[0], R[2]) contain R[1]? 비교 후에, 어느것도 충족하지 않으면, R[0],R[1],R[2]로 원을 만든다.
        Circle nc = twoPointCircle(R.get(0), R.get(1));
        if (nc.contain(R.get(2)))
            return nc;
        nc = twoPointCircle(R.get(0), R.get(2));
        if (nc.contain(R.get(1)))
            return nc;

        return threePointCircle(R.get(0), R.get(1), R.get(2));
    }

    public static Circle twoPointCircle(Point p1, Point p2) { // 두 개의 포인트로 circle 형성
        Point newP = new Point((p1.X+p2.X)/2, (p1.Y+p2.Y)/2);

        return new Circle(newP, newP.dist(p1));
    }

    public static Circle threePointCircle(Point p1, Point p2, Point p3) { // 3개의 포인트로 circle 형성
        Point I = centerFrom(p2.X - p1.X, p2.Y - p1.Y, p3.X - p1.X, p3.Y - p1.Y);

        I.X += p1.X;
        I.Y += p1.Y;

        return new Circle(I, I.dist(p1));
    }

    public static Point centerFrom(double bx, double by, double cx, double cy) {
        double B = bx * bx + by * by;
        double C = cx * cx + cy * cy;
        double D = bx * cy - by * cx; // 여기서 D가 0이 되어버리므로, NaN 발생..

        return new Point((cy * B - by * C) / (2 * D), (bx * C - cx * B) / (2 * D));
    }

}

@AllArgsConstructor
@ToString
class Point {
    double X, Y;

    public double dist(Point p2) { // Point간 거리계산
        return Math.sqrt(Math.pow(this.X - p2.X, 2) + Math.pow(this.Y - p2.Y, 2));
    }
}

@AllArgsConstructor
@ToString
class Circle{
    Point C;
    double R;

    boolean contain(Point p2) {
        // this 의 circle 범위 안에서, p point를 포함하는가?

        return this.C.dist(p2) <= R;
    }
}