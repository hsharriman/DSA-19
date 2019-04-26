import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    static int answer;
    // Runtime: TODO
    // Space: TODO
    public static int LIShelp(int[] A, int[] DP, int index) {
//        // base case
        if (index == 0) return 0;
        if (index == 1) return 1;
        if (DP[index-1] != -1) return DP[index-1];
        int tempMax = 1;
        for (int i=1; i < index; i++) {
            int subsol = LIShelp(A, DP, i);
            if (A[i-1] < A[index-1] && subsol + 1 > tempMax){
                tempMax = subsol + 1;
            }
        }
        if (tempMax > answer) {
            answer = tempMax;
        }
        DP[index-1] = tempMax;
        return tempMax;
    }

    public static int LIS(int[] A){
        int[] DP = new int[A.length];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1;
        }
        answer = 0;
        LIShelp(A, DP, A.length);
        return answer;
    }
}