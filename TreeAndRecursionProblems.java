import java.util.ArrayList;
import java.util.List;

public final class TreeAndRecursionProblems {
    private TreeAndRecursionProblems() {
    }

    public static List<List<String>> solveNQueens(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Board size must not be negative");
        }
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            java.util.Arrays.fill(row, '.');
        }
        placeQueen(0, board, new boolean[n], new boolean[Math.max(0, 2 * n - 1)],
                new boolean[Math.max(0, 2 * n - 1)], solutions);
        return solutions;
    }

    private static void placeQueen(
            int row,
            char[][] board,
            boolean[] columns,
            boolean[] descending,
            boolean[] ascending,
            List<List<String>> solutions) {
        if (row == board.length) {
            List<String> solution = new ArrayList<>();
            for (char[] line : board) {
                solution.add(new String(line));
            }
            solutions.add(solution);
            return;
        }
        int size = board.length;
        for (int column = 0; column < size; column++) {
            int down = row - column + size - 1;
            int up = row + column;
            if (columns[column] || descending[down] || ascending[up]) {
                continue;
            }
            columns[column] = true;
            descending[down] = true;
            ascending[up] = true;
            board[row][column] = 'Q';
            placeQueen(row + 1, board, columns, descending, ascending, solutions);
            board[row][column] = '.';
            columns[column] = false;
            descending[down] = false;
            ascending[up] = false;
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] sorted = candidates.clone();
        java.util.Arrays.sort(sorted);
        for (int candidate : sorted) {
            if (candidate <= 0) {
                throw new IllegalArgumentException("Candidates must be positive");
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(sorted, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void findCombinations(
            int[] candidates, int remaining, int start, List<Integer> current,
            List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length && candidates[i] <= remaining; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            current.add(candidates[i]);
            findCombinations(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static final class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static int maximumDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maximumDepth(root.left), maximumDepth(root.right));
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverseInorder(root, result);
        return result;
    }

    public static List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversePreorder(root, result);
        return result;
    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversePostorder(root, result);
        return result;
    }

    private static void traverseInorder(TreeNode node, List<Integer> result) {
        if (node != null) {
            traverseInorder(node.left, result);
            result.add(node.value);
            traverseInorder(node.right, result);
        }
    }

    private static void traversePreorder(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            traversePreorder(node.left, result);
            traversePreorder(node.right, result);
        }
    }

    private static void traversePostorder(TreeNode node, List<Integer> result) {
        if (node != null) {
            traversePostorder(node.left, result);
            traversePostorder(node.right, result);
            result.add(node.value);
        }
    }
}
