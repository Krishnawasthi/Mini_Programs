import java.util.Arrays;
public final class DSAAdvancedProblemsSmokeTest {
    private DSAAdvancedProblemsSmokeTest() {
    }

    public static void main(String[] args) {
        assert Arrays.equals(ArrayProductAndRotationProblems.productExceptSelf(new int[] {1, 2, 3, 4}),
                new long[] {24, 12, 8, 6});
        assert ArrayProductAndRotationProblems.maximumProductSubarray(new int[] {2, 3, -2, 4}) == 6;
        assert ArrayProductAndRotationProblems.minimumInRotatedSortedArray(new int[] {4, 5, 1, 2, 3}) == 1;
        assert ArrayProductAndRotationProblems.medianOfSortedArrays(new int[] {1, 3}, new int[] {2}) == 2.0;
        assert ArrayProductAndRotationProblems.medianOfSortedArrays(
                new int[] {Integer.MAX_VALUE}, new int[] {Integer.MAX_VALUE}) == Integer.MAX_VALUE;

        assert StringWindowProblems.threeSumZero(new int[] {-1, 0, 1, 2, -1, -4}).size() == 2;
        assert StringWindowProblems.longestPalindromicSubstring("babad").length() == 3;
        assert StringWindowProblems.minimumWindowSubstring("ADOBECODEBANC", "ABC").equals("BANC");
        int[] duplicates = {1, 1, 2, 2, 3};
        int uniqueCount = StringWindowProblems.removeDuplicatesFromSortedArray(duplicates);
        assert uniqueCount == 3;
        assert Arrays.equals(Arrays.copyOf(duplicates, uniqueCount), new int[] {1, 2, 3});

        assert Arrays.deepEquals(ArraySelectionProblems.mergeOverlappingIntervals(
                new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}}),
                new int[][] {{1, 6}, {8, 10}, {15, 18}});
        assert ArraySelectionProblems.trappedRainWater(
                new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) == 6;
        int[] colors = {2, 0, 2, 1, 1, 0};
        ArraySelectionProblems.sortColors(colors);
        assert Arrays.equals(colors, new int[] {0, 0, 1, 1, 2, 2});
        assert ArraySelectionProblems.kthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2) == 5;

        System.out.println("Advanced DSA smoke checks for questions 1-12 passed.");
    }
}
