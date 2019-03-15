import javax.swing.plaf.ButtonUI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }
    private static Point maxHeight(Point a, Point b){return (a.y >= b.y) ? (a) : (b);}
    private static Point min(Point a, Point b) { return (a.x <= b.x) ? a : b;}

    private static List<Point> base(Building[] B){
        ArrayList<Point> res = new ArrayList<>();
        res.add(new Point(B[0].l, B[0].h));
        res.add(new Point(B[0].r, 0));
        return res;

    }

    private static List<Point> merge(List<Point> A, List<Point> B){
        ArrayList<Point> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        Point lastA = new Point(0, 0);
        Point lastB = new Point(0, 0);
        int lastRes = 0;
         while (i < A.size() || j < B.size()){
             if (i<A.size() && j<B.size()) {
                 Point p1 = A.get(i);
                 Point p2 = B.get(j);
                 int left = min(p1, p2).x;
                 int max_cur;
                 if (p1.x < p2.x) {
                     max_cur = maxHeight(p1, lastB).y;
                     lastA = p1;
                     i++;
                 } else {
                     if (p2.x < p1.x) {
                         max_cur = maxHeight(p2, lastA).y;
                         lastB = p2;
                         j++;
                     } else {
                         max_cur = maxHeight(p1, p2).y;
                         lastA = p1;
                         lastB = p2;
                         i++;
                         j++;
                     }
                 }
                 if (max_cur != lastRes){
                     res.add(new Point(left, max_cur));
                     lastRes = max_cur;
                 }
             }
             else if (i == A.size()){
                res.add(new Point(B.get(j).x, B.get(j).y)); //add points from l2
                j++;
            } else if (j == B.size()){
                res.add(new Point(A.get(i).x, A.get(i).y)); //add points from l1
                i++;
            }
        }
        return res;
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        if (B.length == 1){
            return base(B);
        }
        int half = B.length/2;
        Building[] b = Arrays.copyOfRange(B, 0, half);
        Building[] a = Arrays.copyOfRange(B, half, B.length);
        List<Point> A = skyline(b);
        List<Point> C = skyline(a);
        return merge(A, C);
    }
}

