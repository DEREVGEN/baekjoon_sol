package com.backjoon.algorithm.rotatingcalipers;

// Java Program for the above approach
import java.awt.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

    // Function to detect the orientation
    static int orientation(Point p,
                           Point q,
                           Point r)
    {
        int x = area(p, q, r);

        // If area > 0 then
        // points are clockwise
        if (x > 0) {
            return 1;
        }

        // If area<0 then
        // points are counterclockwise
        if (x < 0) {
            return -1;
        }

        // If area is 0 then p, q
        // and r are co-linear
        return 0;
    }

    // Function to find the area
    static int area(Point p, Point q, Point r)
    {
        // 2*(area of triangle)
        return ((p.y - q.y) * (q.x - r.x)
                - (q.y - r.y) * (p.x - q.x));
    }

    // Function to find the absolute Area
    static int absArea(Point p,
                       Point q, Point r)
    {
        // Unsigned area
        // 2*(area of triangle)
        return Math.abs(area(p, q, r));
    }

    // Function to find the distance
    static int dist(Point p1, Point p2)
    {
        // squared-distance b/w
        // p1 and p2 for precision
        return ((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    // Function to implement Convex Hull
    // Approach
    static ArrayList<Point> convexHull(Point points[])
    {
        int n = points.length;

        Point min = new Point(Integer.MAX_VALUE,
                Integer.MAX_VALUE);

        // Choose point having min.
        // y-coordinate and if two points
        // have same y-coordinate choose
        // the one with minimum x-coordinate
        int ind = -1;

        // Iterate Points[]
        for (int i = 0; i < n; i++) {
            if (min.y > points[i].y) {
                min.y = points[i].y;
                min.x = points[i].x;
                ind = i;
            }
            else if (min.y == points[i].y
                    && min.x > points[i].x) {
                min.x = points[i].x;
                ind = i;
            }
        }
        points[ind] = points[0];
        points[0] = min;

        // Sort points which have
        // minimum polar angle wrt
        // Point min
        Arrays.sort(points, 1, n,
                new Comparator<Point>() {

                    @Override
                    public int compare(Point o1,
                                       Point o2)
                    {

                        int o = orientation(min, o1, o2);

                        // If points are co-linear
                        // choose the one having smaller
                        // distance with min first.
                        if (o == 0) {
                            return dist(o1, min)
                                    - dist(o2, min);
                        }

                        // If clockwise then swap
                        if (o == 1) {
                            return 1;
                        }

                        // If anticlockwise then
                        // don't swap
                        return -1;
                    }
                });

        Stack<Point> st = new Stack<>();

        // First hull point
        st.push(points[0]);

        int k;
        for (k = 1; k < n - 1; k++) {
            if (orientation(points[0], points[k], points[k + 1]) != 0)
                break;
        }

        // Second hull point
        st.push(points[k]);

        for (int i = k + 1; i < n; i++) {
            Point top = st.pop();

            while (orientation(st.peek(),
                    top,
                    points[i])
                    >= 0) {
                top = st.pop();
            }

            st.push(top);
            st.push(points[i]);
        }

        ArrayList<Point> hull
                = new ArrayList<>();

        // Iterate stack and add node to hull
        for (int i = 0; i < st.size(); i++) {
            hull.add(st.get(i));
        }
        return hull;
    }

    // Function to find the maximum
    // distance between any two points
    // from a set of given points
    static double rotatingCaliper(Point points[])
    {
        // Takes O(n)
        ArrayList<Point> convexHull = convexHull(points);
        int n = convexHull.size();

        // Convex hull point in counter-
        // clockwise order
        Point hull[] = new Point[n];
        n = 0;

        while (n < convexHull.size()) {
            hull[n] = convexHull.get(n++);
        }

        // Base Cases
        if (n == 1)
            return 0;
        if (n == 2)
            return Math.sqrt(dist(hull[0], hull[1]));
        int k = 1;

        // Find the farthest vertex
        // from hull[0] and hull[n-1]
        while (absArea(hull[n - 1], hull[0], hull[(k + 1) % n]) > absArea(hull[n - 1], hull[0], hull[k])) {
            k++;
        }

        double res = 0;

        // Check points from 0 to k
        for (int i = 0, j = k; i <= k && j < n; i++) {
            res = Math.max(res, Math.sqrt((double)dist(hull[i], hull[j])));

            while (j < n &&
                    absArea(hull[i], hull[(i + 1) % n], hull[(j + 1) % n]) >
                    absArea(hull[i], hull[(i + 1) % n], hull[j])) {

                // Update res
                res = Math.max(res, Math.sqrt(dist(hull[i], hull[(j + 1) % n])));
                j++;
            }
        }

        // Return the result distance
        return res;
    }

    // Driver Code
    public static void main(String[] args)
    {
        // Total points
        int n = 5;
        Point p[] = new Point[n];

        // Given Points
        p[0] = new Point(4, 0);
        p[1] = new Point(0, 2);
        p[2] = new Point(-1, -7);
        p[3] = new Point(1, 10);
        p[4] = new Point(2, -3);

        // Function Call
        System.out.println(rotatingCaliper(p));
    }
}
