public final class SortingAndSearchProblems {
    private SortingAndSearchProblems() {
    }

    public static int[] firstAndLastOccurrence(int[] sortedValues, int target) {
        return new int[] {boundary(sortedValues, target, true), boundary(sortedValues, target, false)};
    }

    private static int boundary(int[] values, int target, boolean first) {
        int low = 0;
        int high = values.length - 1;
        int result = -1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (values[middle] == target) {
                result = middle;
                if (first) {
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            } else if (values[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return result;
    }

    public static int searchRotatedSortedArray(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (values[middle] == target) {
                return middle;
            }
            if (values[low] == values[middle] && values[middle] == values[high]) {
                low++;
                high--;
            } else if (values[low] <= values[middle]) {
                if (values[low] <= target && target < values[middle]) {
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            } else if (values[middle] < target && target <= values[high]) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static void mergeSort(int[] values) {
        if (values.length < 2) {
            return;
        }
        mergeSort(values, new int[values.length], 0, values.length);
    }

    private static void mergeSort(int[] values, int[] buffer, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int middle = start + (end - start) / 2;
        mergeSort(values, buffer, start, middle);
        mergeSort(values, buffer, middle, end);
        int left = start;
        int right = middle;
        int write = start;
        while (left < middle && right < end) {
            buffer[write++] = values[left] <= values[right] ? values[left++] : values[right++];
        }
        while (left < middle) {
            buffer[write++] = values[left++];
        }
        while (right < end) {
            buffer[write++] = values[right++];
        }
        System.arraycopy(buffer, start, values, start, end - start);
    }

    public static void quickSort(int[] values) {
        quickSort(values, 0, values.length - 1);
    }

    private static void quickSort(int[] values, int low, int high) {
        while (low < high) {
            int pivot = values[low + (high - low) / 2];
            int less = low;
            int current = low;
            int greater = high;
            while (current <= greater) {
                if (values[current] < pivot) {
                    swap(values, less++, current++);
                } else if (values[current] > pivot) {
                    swap(values, current, greater--);
                } else {
                    current++;
                }
            }
            if (less - low < high - greater) {
                quickSort(values, low, less - 1);
                low = greater + 1;
            } else {
                quickSort(values, greater + 1, high);
                high = less - 1;
            }
        }
    }

    private static void swap(int[] values, int first, int second) {
        int temp = values[first];
        values[first] = values[second];
        values[second] = temp;
    }
}
