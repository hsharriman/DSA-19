import java.util.HashMap;

public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return helper(0, n,isBadVersion);
    }

    private static long helper(long low, long high, IsFailingVersion isBad){
        long guess = (high-low)/2 + low;
        if (!isBad.isFailingVersion(guess-1) && isBad.isFailingVersion(guess)){
            return guess;
        } else {
            if (isBad.isFailingVersion(guess)){
                guess = helper(low, guess, isBad);
            } else{
                guess = helper(guess, high, isBad);
            }
        }
        return guess;
    }
}
