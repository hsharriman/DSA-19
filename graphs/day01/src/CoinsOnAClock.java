import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        char[] clock = new char[hoursInDay];
        for (int i = 0; i < hoursInDay; i++) {
            clock[i] = '.';
        }
        solve(result, clock, pennies, nickels, dimes, 0);
        return result;
    }
    //O(3^n) every call has 3 branches, there are n layers where n=hoursInDay
    private static void solve(List<char[]> sols, char[] clock, int p, int n, int d, int pos){
        if (p == 0 && n == 0 && d == 0){
            sols.add(Arrays.copyOf(clock, clock.length));
        } else {
            if (clock[pos] == '.'){
                if (p > 0){
                    clock[pos] = 'p';
                    solve(sols, clock, p-1, n, d, (pos+1)%clock.length);
                }
                if (n > 0){
                    clock[pos] = 'n';
                    solve(sols, clock, p, n-1, d, (pos+5)%clock.length);
                }
                if (d > 0){
                    clock[pos] = 'd';
                    solve(sols, clock, p, n, d-1, (pos+10)%clock.length);
                }
                clock[pos] = '.';
            }
        }
    }
}
