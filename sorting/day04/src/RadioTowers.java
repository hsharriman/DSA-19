import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RadioTowers {
    static class Tower {
        double x, y;
        Tower(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double getDist(Tower t1, Tower t2) {
        double xDiff = t1.x - t2.x;
        double yDiff = t1.y - t2.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    private static boolean isWithin(Tower t1, Tower t2, int dist) {
        return getDist(t1, t2) <= dist;
    }

    // Strip contains a list of Towers sorted by x-coordinate, whose y-coordinates all fall in a 2-mile strip
    // Return true if no two towers are within 1 mile
    public static boolean checkStrip(List<Tower> strip) {
        for (int i=0; i<strip.size(); i++){
            for (int j=i+1; j<i+8; j++){
                if (j < strip.size() && getDist(strip.get(i), strip.get(j)) <= 1){
                    return false;
                }
            }
        }
        return true;
    }

    // Return true if no two towers are within distance 1 of each other
    public static boolean validTowers(List<Tower> Lx, List<Tower> Ly) {
        assert Lx.size() == Ly.size();
        //base case and combine
        if (Ly.get(Ly.size()-1).y >= Ly.get(0).y + 2){
            return checkStrip(Lx);
        }

        List<Tower> loListy = Ly.subList(0, Ly.size()/2);
        List<Tower> hiListy = Ly.subList(Ly.size()/2, Ly.size()-1);
        List<Tower> loListx = new ArrayList<>();
        List<Tower> hiListx = new ArrayList<>();

        //Sort x lists according to y list split
        for (int i=0; i<Lx.size(); i++){
            //select the correct x's
            if (Lx.get(i).y <= loListy.get(loListy.size()-1).y){    //if curr x coord <= cutoff
                //add  to lo list
                loListx.add(Lx.get(i));
            } else {
                hiListx.add(Lx.get(i)); //add to lo list
            }
        }
        //conquer
        boolean loTrue = validTowers(loListx, loListy);
        boolean hiTrue = validTowers(hiListx, hiListy);

        return (loTrue && hiTrue) ? (true) : (false);
    }
}