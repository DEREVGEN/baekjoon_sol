package com.backjoon.algorithm.angle;

public class ThreePointAngle {

    public static void main(String[] args) {
        Point p1 = new Point(2, 0);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(4, 0);

        // p1 <- p2, p2 <- p3
        Vector v1 = new Vector(p1.y - p2.y, p1.x - p2.x);
        Vector v2 = new Vector(p2.y - p3.y, p2.x - p3.x);

        // 각도 계산
        double angleInRadians = calculateAngle(v1, v2);

        // 라디안을 도로 변환
        double angleInDegrees = Math.toDegrees(angleInRadians);

        // 결과 출력
        System.out.println("두 벡터 간의 각도(라디안): " + angleInRadians);
        System.out.println("두 벡터 간의 각도(도): " + angleInDegrees);
    }

    // 두 벡터 간의 각도 계산
    private static double calculateAngle(Vector vectorA, Vector vectorB) {
        // 내적 계산
        double dotProduct = dotProduct(vectorA, vectorB);

        // 벡터 크기 계산
        double magnitudeA = magnitude(vectorA);
        double magnitudeB = magnitude(vectorB);

        // 아크코사인 계산
        return Math.acos(dotProduct / (magnitudeA * magnitudeB));
    }

    // 두 벡터의 내적 계산
    private static double dotProduct(Vector vectorA, Vector vectorB) {
        return vectorA.y * vectorB.y + vectorA.x * vectorB.x;
    }

    // 벡터의 크기(길이) 계산
    private static double magnitude(Vector vector) {
        return Math.sqrt(Math.pow(vector.y, 2) + Math.pow(vector.x, 2));
    }
}

class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Vector {
    int y, x;

    public Vector(int y, int x) {
        this.y = y;
        this.x = x;
    }
}