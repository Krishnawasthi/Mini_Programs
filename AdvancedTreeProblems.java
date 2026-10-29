public final class AdvancedTreeProblems {
    private AdvancedTreeProblems() {
    }

    public static String serialize(TreeAndRecursionProblems.TreeNode root) {
        StringBuilder serialized = new StringBuilder();
        writePreorder(root, serialized);
        return serialized.toString();
    }

    private static void writePreorder(TreeAndRecursionProblems.TreeNode node, StringBuilder output) {
        if (output.length() > 0) {
            output.append(',');
        }
        if (node == null) {
            output.append('#');
            return;
        }
        output.append(node.value);
        writePreorder(node.left, output);
        writePreorder(node.right, output);
    }

    public static TreeAndRecursionProblems.TreeNode deserialize(String serialized) {
        if (serialized == null || serialized.isEmpty()) {
            throw new IllegalArgumentException("Serialized tree must not be null or empty");
        }
        String[] tokens = serialized.split(",", -1);
        int[] index = {0};
        TreeAndRecursionProblems.TreeNode root = readPreorder(tokens, index);
        if (index[0] != tokens.length) {
            throw new IllegalArgumentException("Serialized tree contains extra tokens");
        }
        return root;
    }

    private static TreeAndRecursionProblems.TreeNode readPreorder(String[] tokens, int[] index) {
        if (index[0] >= tokens.length) {
            throw new IllegalArgumentException("Serialized tree is incomplete");
        }
        String token = tokens[index[0]++];
        if (token.equals("#")) {
            return null;
        }
        final int value;
        try {
            value = Integer.parseInt(token);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid tree node value: " + token, exception);
        }
        TreeAndRecursionProblems.TreeNode node = new TreeAndRecursionProblems.TreeNode(value);
        node.left = readPreorder(tokens, index);
        node.right = readPreorder(tokens, index);
        return node;
    }

    public static int diameterInEdges(TreeAndRecursionProblems.TreeNode root) {
        int[] maximum = {0};
        depth(root, maximum);
        return maximum[0];
    }

    private static int depth(TreeAndRecursionProblems.TreeNode node, int[] maximum) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.left, maximum);
        int rightDepth = depth(node.right, maximum);
        maximum[0] = Math.max(maximum[0], leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
