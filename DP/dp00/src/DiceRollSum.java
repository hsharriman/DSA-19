public class DiceRollSum {

    // Runtime: O(6^n)
    // Space: O(logn*6)?
    public static int helper(int N, int[] DP) {
        // base case
        if (N == 0) return 1;

        // have we already solved this subproblem
        if (DP[N] != -1) return DP[N];

        // sum from j=1 to 6 of DP[n-j]
        int answer = 0;
        for (int j=1; j<=6; j++)
            if (j <= N) answer += helper(N - j, DP);

        // store our answer and return it
        DP[N] = answer;
        return answer;
    }

    public static int diceRollSum(int N) {
        int[] DP = new int[N + 1];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1; // set a special empty value
        }
        return helper(N, DP);
    }

}
