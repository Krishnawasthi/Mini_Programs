import java.util.Optional;

public final class TreeAndDynamicProgrammingProblems {
    private TreeAndDynamicProgrammingProblems() {
    }

    public static boolean isValidBst(TreeAndRecursionProblems.TreeNode root) {
        return isValidBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBst(TreeAndRecursionProblems.TreeNode node, long minimum, long maximum) {
        if (node == null) {
            return true;
        }
        return node.value > minimum && node.value < maximum
                && isValidBst(node.left, minimum, node.value)
                && isValidBst(node.right, node.value, maximum);
    }

    public static Optional<TreeAndRecursionProblems.TreeNode> lowestCommonAncestor(
            TreeAndRecursionProblems.TreeNode root,
            TreeAndRecursionProblems.TreeNode first,
            TreeAndRecursionProblems.TreeNode second) {
        if (root == null || first == null || second == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(findAncestor(root, first, second));
    }

    private static TreeAndRecursionProblems.TreeNode findAncestor(
            TreeAndRecursionProblems.TreeNode node,
            TreeAndRecursionProblems.TreeNode first,
            TreeAndRecursionProblems.TreeNode second) {
        if (node == null || node == first || node == second) {
            return node;
        }
        TreeAndRecursionProblems.TreeNode left = findAncestor(node.left, first, second);
        TreeAndRecursionProblems.TreeNode right = findAncestor(node.right, first, second);
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }

    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        if (n < 2) {
            return n;
        }
        long previous = 0;
        long current = 1;
        for (int i = 2; i <= n; i++) {
            long next = Math.addExact(previous, current);
            previous = current;
            current = next;
        }
        return current;
    }

    public static long zeroOneKnapsack(int[] weights, int[] values, int capacity) {
        if (weights.length != values.length || capacity < 0) {
            throw new IllegalArgumentException("Weights and values must match and capacity must be nonnegative");
        }
        long[] best = new long[capacity + 1];
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] < 0) {
                throw new IllegalArgumentException("Weights must not be negative");
            }
            for (int currentCapacity = capacity; currentCapacity >= weights[i]; currentCapacity--) {
                best[currentCapacity] = Math.max(
                        best[currentCapacity], best[currentCapacity - weights[i]] + values[i]);
            }
        }
        return best[capacity];
    }
}
