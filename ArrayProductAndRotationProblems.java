public final class ArrayProductAndRotationProblems {
    private ArrayProductAndRotationProblems() {
    }

    public static long[] productExceptSelf(int[] values) {
        long[] products = new long[values.length];
        long prefix = 1;
        for (int i = 0; i < values.length; i++) {
            products[i] = prefix;
            prefix *= values[i];
        }
        long suffix = 1;
        for (int i = values.length - 1; i >= 0; i--) {
            products[i] *= suffix;
            suffix *= values[i];
        }
        return products;
    }

    public static long maximumProductSubarray(int[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        long maximumEndingHere = values[0];
        long minimumEndingHere = values[0];
        long maximum = values[0];
        for (int i = 1; i < values.length; i++) {
            long value = values[i];
            long withMaximum = maximumEndingHere * value;
            long withMinimum = minimumEndingHere * value;
            maximumEndingHere = Math.max(value, Math.max(withMaximum, withMinimum));
            minimumEndingHere = Math.min(value, Math.min(withMaximum, withMinimum));
            maximum = Math.max(maximum, maximumEndingHere);
        }
        return maximum;
    }

    public static int minimumInRotatedSortedArray(int[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        int low = 0;
        int high = values.length - 1;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (values[middle] < values[high]) {
                high = middle;
            } else if (values[middle] > values[high]) {
                low = middle + 1;
            } else {
                high--;
            }
        }
        return values[low];
    }

    public static double medianOfSortedArrays(int[] first, int[] second) {
        if (first.length > second.length) {
            return medianOfSortedArrays(second, first);
        }
        if (first.length + second.length == 0) {
            throw new IllegalArgumentException("Both arrays must not be empty");
        }
        int low = 0;
        int high = first.length;
        int half = (first.length + second.length + 1) / 2;
        while (low <= high) {
            int cutFirst = low + (high - low) / 2;
            int cutSecond = half - cutFirst;
            int leftFirst = cutFirst == 0 ? Integer.MIN_VALUE : first[cutFirst - 1];
            int rightFirst = cutFirst == first.length ? Integer.MAX_VALUE : first[cutFirst];
            int leftSecond = cutSecond == 0 ? Integer.MIN_VALUE : second[cutSecond - 1];
            int rightSecond = cutSecond == second.length ? Integer.MAX_VALUE : second[cutSecond];
            if (leftFirst <= rightSecond && leftSecond <= rightFirst) {
                int left = Math.max(leftFirst, leftSecond);
                if (((first.length + second.length) & 1) != 0) {
                    return left;
                }
                int right = Math.min(rightFirst, rightSecond);
                return ((double) left + right) / 2;
            }
            if (leftFirst > rightSecond) {
                high = cutFirst - 1;
            } else {
                low = cutFirst + 1;
            }
        }
        throw new IllegalArgumentException("Inputs must be sorted in nondecreasing order");
    }

}
