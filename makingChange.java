public class makingChange {
    public static void main(String[] args) {
        int[] testCases = {0, 11, 19, 25, 100};
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(testCases[i] + " cents has " + makeChange(testCases[i], 0) + " ways");
        }
    }

    // recusrively find the max unique ways to make change
    private static int makeChange(int coin, int largestCoin) {
        int[] coins = {25, 10, 5, 1};

        int sum = 0;

        for (int i = largestCoin; i < coins.length; i++) {
            if (coin - coins[i] == 0) {
                sum++;
            } else if (coin - coins[i] > 0) {
                sum += makeChange(coin - coins[i], i);
            }
        }
        return sum;
    }
}