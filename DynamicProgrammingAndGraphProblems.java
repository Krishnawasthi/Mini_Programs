import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class DynamicProgrammingAndGraphProblems {
    private DynamicProgrammingAndGraphProblems() {
    }

    public static int longestIncreasingSubsequenceLength(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        int[] tails = new int[values.length];
        int length = 0;
        for (int value : values) {
            int position = Arrays.binarySearch(tails, 0, length, value);
            if (position < 0) {
                position = -position - 1;
            }
            tails[position] = value;
            if (position == length) {
                length++;
            }
        }
        return length;
    }

    public static int minimumCoins(int[] coins, int amount) {
        Objects.requireNonNull(coins, "coins must not be null");
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must not be negative");
        }
        for (int coin : coins) {
            if (coin <= 0) {
                throw new IllegalArgumentException("Coin values must be positive");
            }
        }

        int unreachable = Integer.MAX_VALUE;
        int[] minimum = new int[amount + 1];
        Arrays.fill(minimum, unreachable);
        minimum[0] = 0;
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            for (int coin : coins) {
                if (coin <= currentAmount && minimum[currentAmount - coin] != unreachable) {
                    minimum[currentAmount] = Math.min(
                            minimum[currentAmount], minimum[currentAmount - coin] + 1);
                }
            }
        }
        return minimum[amount] == unreachable ? -1 : minimum[amount];
    }

    public static long countWaysToClimbStairs(int stairs) {
        if (stairs < 0) {
            throw new IllegalArgumentException("Stair count must not be negative");
        }
        if (stairs == 0) {
            return 1;
        }
        long oneStepBefore = 1;
        long twoStepsBefore = 1;
        for (int step = 2; step <= stairs; step++) {
            long current = Math.addExact(oneStepBefore, twoStepsBefore);
            twoStepsBefore = oneStepBefore;
            oneStepBefore = current;
        }
        return oneStepBefore;
    }

    public static long maximumNonAdjacentSum(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        long bestWithoutPrevious = 0;
        long bestWithPrevious = 0;
        for (int value : values) {
            long bestIncludingCurrent = bestWithoutPrevious + value;
            bestWithoutPrevious = bestWithPrevious;
            bestWithPrevious = Math.max(bestWithPrevious, bestIncludingCurrent);
        }
        return bestWithPrevious;
    }

    public static boolean hasDirectedCycle(List<List<Integer>> graph) {
        validateGraph(graph);
        int[] state = new int[graph.size()];
        for (int vertex = 0; vertex < graph.size(); vertex++) {
            if (state[vertex] == 0 && hasDirectedCycle(vertex, graph, state)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDirectedCycle(int vertex, List<List<Integer>> graph, int[] state) {
        state[vertex] = 1;
        for (int neighbor : graph.get(vertex)) {
            if (state[neighbor] == 1
                    || (state[neighbor] == 0 && hasDirectedCycle(neighbor, graph, state))) {
                return true;
            }
        }
        state[vertex] = 2;
        return false;
    }

    public static List<Integer> shortestPathBfs(List<List<Integer>> graph, int start, int target) {
        validateGraph(graph);
        validateVertex(start, graph.size());
        validateVertex(target, graph.size());

        int[] previous = new int[graph.size()];
        Arrays.fill(previous, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        previous[start] = start;
        queue.add(start);
        while (!queue.isEmpty() && previous[target] == -1) {
            int vertex = queue.remove();
            for (int neighbor : graph.get(vertex)) {
                if (previous[neighbor] == -1) {
                    previous[neighbor] = vertex;
                    queue.add(neighbor);
                }
            }
        }
        if (previous[target] == -1) {
            return List.of();
        }
        List<Integer> path = new ArrayList<>();
        for (int vertex = target; vertex != start; vertex = previous[vertex]) {
            path.add(vertex);
        }
        path.add(start);
        java.util.Collections.reverse(path);
        return path;
    }

    public static List<Integer> depthFirstSearch(List<List<Integer>> graph, int start) {
        validateGraph(graph);
        validateVertex(start, graph.size());

        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (visited[vertex]) {
                continue;
            }
            visited[vertex] = true;
            traversal.add(vertex);
            List<Integer> neighbors = graph.get(vertex);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                if (!visited[neighbors.get(i)]) {
                    stack.push(neighbors.get(i));
                }
            }
        }
        return traversal;
    }

    public static int countConnectedComponents(List<List<Integer>> graph) {
        validateGraph(graph);
        boolean[] visited = new boolean[graph.size()];
        int components = 0;
        for (int vertex = 0; vertex < graph.size(); vertex++) {
            if (!visited[vertex]) {
                components++;
                markReachable(vertex, graph, visited);
            }
        }
        return components;
    }

    private static void markReachable(int start, List<List<Integer>> graph, boolean[] visited) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (visited[vertex]) {
                continue;
            }
            visited[vertex] = true;
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }
    }

    public static List<Integer> topKFrequent(int[] values, int k) {
        Objects.requireNonNull(values, "values must not be null");
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int value : values) {
            frequencies.merge(value, 1, Integer::sum);
        }
        List<Integer> result = new ArrayList<>(frequencies.keySet());
        result.sort(Comparator.<Integer>comparingInt(frequencies::get)
                .reversed().thenComparingInt(Integer::intValue));
        return new ArrayList<>(result.subList(0, Math.min(k, result.size())));
    }

    public static List<String> wordLadderShortestPath(
            String beginWord, String endWord, List<String> wordList) {
        Objects.requireNonNull(beginWord, "beginWord must not be null");
        Objects.requireNonNull(endWord, "endWord must not be null");
        Objects.requireNonNull(wordList, "wordList must not be null");
        if (beginWord.length() != endWord.length()) {
            return List.of();
        }
        if (beginWord.equals(endWord)) {
            return List.of(beginWord);
        }

        Map<WordPattern, List<String>> wordsByPattern = new HashMap<>();
        boolean containsEndWord = false;
        for (String word : wordList) {
            Objects.requireNonNull(word, "wordList must not contain null");
            if (word.length() != beginWord.length()) {
                continue;
            }
            containsEndWord |= word.equals(endWord);
            for (int position = 0; position < word.length(); position++) {
                wordsByPattern.computeIfAbsent(pattern(word, position), ignored -> new ArrayList<>())
                        .add(word);
            }
        }
        if (!containsEndWord) {
            return List.of();
        }
        for (int position = 0; position < beginWord.length(); position++) {
            wordsByPattern.computeIfAbsent(pattern(beginWord, position), ignored -> new ArrayList<>())
                    .add(beginWord);
        }

        Deque<String> queue = new ArrayDeque<>();
        Map<String, String> previous = new HashMap<>();
        previous.put(beginWord, null);
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.remove();
            for (int position = 0; position < word.length(); position++) {
                List<String> neighbors = wordsByPattern.remove(pattern(word, position));
                if (neighbors == null) {
                    continue;
                }
                for (String neighbor : neighbors) {
                    if (!previous.containsKey(neighbor)) {
                        previous.put(neighbor, word);
                        if (neighbor.equals(endWord)) {
                            return buildWordPath(endWord, previous);
                        }
                        queue.add(neighbor);
                    }
                }
            }
        }
        return List.of();
    }

    private static WordPattern pattern(String word, int position) {
        return new WordPattern(position, word.substring(0, position), word.substring(position + 1));
    }

    private static List<String> buildWordPath(String endWord, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        for (String word = endWord; word != null; word = previous.get(word)) {
            path.add(word);
        }
        java.util.Collections.reverse(path);
        return path;
    }

    private static void validateGraph(List<List<Integer>> graph) {
        Objects.requireNonNull(graph, "graph must not be null");
        for (List<Integer> neighbors : graph) {
            Objects.requireNonNull(neighbors, "graph adjacency lists must not be null");
            for (Integer neighbor : neighbors) {
                if (neighbor == null || neighbor < 0 || neighbor >= graph.size()) {
                    throw new IllegalArgumentException("Graph contains an invalid vertex");
                }
            }
        }
    }

    private static void validateVertex(int vertex, int vertexCount) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("Vertex is outside the graph");
        }
    }

    private static final class WordPattern {
        private final int position;
        private final String prefix;
        private final String suffix;

        private WordPattern(int position, String prefix, String suffix) {
            this.position = position;
            this.prefix = prefix;
            this.suffix = suffix;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WordPattern)) {
                return false;
            }
            WordPattern that = (WordPattern) other;
            return position == that.position
                    && prefix.equals(that.prefix)
                    && suffix.equals(that.suffix);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, prefix, suffix);
        }
    }
}
