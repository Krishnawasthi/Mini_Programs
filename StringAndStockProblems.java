import java.util.Optional;

public final class StringAndStockProblems {
    private StringAndStockProblems() {
    }

    public static int[] twoSumIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if ((long) values[i] + values[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }

    public static long maximumStockProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int minimum = prices[0];
        long profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, (long) prices[i] - minimum);
            minimum = Math.min(minimum, prices[i]);
        }
        return profit;
    }

    public static String reverse(String value) {
        char[] characters = value.toCharArray();
        int left = 0;
        int right = characters.length - 1;
        while (left < right) {
            char temp = characters[left];
            characters[left++] = characters[right];
            characters[right--] = temp;
        }
        return new String(characters);
    }

    public static boolean isPalindrome(String value) {
        int left = 0;
        int right = value.length() - 1;
        while (left < right) {
            if (value.charAt(left++) != value.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
