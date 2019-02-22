import java.util.HashMap;
import java.util.Map;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int count = 0;
        int dist;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<points.length; i++){
            for (int j=0; j<points.length; j++){
                if (i==j){
                    continue;
                }
                dist = distance(points[i], points[j]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for (int val : map.values()){   //Look at map, calc num boomerangs per distance
                count += val * (val - 1);
            }
            map.clear();
        }
        return count;
    }

    //Takes 2 points and returns the square of the distance between them
    private static int distance(int[] a, int[] b){
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}

