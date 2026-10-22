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

        System.out.println("Advanced DSA smoke checks for questions 1-4 passed.");
    }
}
