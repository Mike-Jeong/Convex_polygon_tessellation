/*
 * This algorithm try to find the smallest interner edge and keep draw the smallest interner edge until 
 * there no any other polygon except triangles 
 */

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    
    double sum = 0;
    List pList, aList, bList,pList2;

    
    public Greedy(List pList) {
        this.pList = pList;
        pList2 = new ArrayList<Point>();
        for (int i = 0; i < pList.size(); i++) {
            
            pList2.add(pList.get(i));
        }
        aList = new ArrayList<Point>();
        bList = new ArrayList<Point>();
        greedy(pList.size());
    }

    private void greedy(int n) {
        if (n > 3) {
            sum += findMinDistance(n);
            greedy(n - 1);
        } 
    }

    private double findMinDistance(int n) {
        int minVer = 0;
        double minD = 0xFFFF;
        Point minA = new Point(0, 0);
        Point minB = new Point(0, 0);

        for (int i = 0; i < pList2.size(); i++) {
            int a = i - 1;
            int b = i + 1;

            if (a < 0) {
                a = n - 1;
            }
            if (b > n - 1) {
                b = 0;
            }

            Point pa = (Point) pList2.get(a);
            Point pb = (Point) pList2.get(b);

            double d = Point.calcDistance(pa, pb);
            if (d < minD) {
                minVer = i;
                minD = d;
                minA = pa;
                minB = pb;
            }
        }

        aList.add(minA);
        bList.add(minB);
        pList2.remove(minVer);
        return minD;
    }

}

