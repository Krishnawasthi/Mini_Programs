import java.util.Arrays;
import java.util.List;

public final class DynamicProgrammingAndGraphProblemsSmokeTest {
    private DynamicProgrammingAndGraphProblemsSmokeTest() {
    }

    public static void main(String[] args) {
        assert DynamicProgrammingAndGraphProblems.longestIncreasingSubsequenceLength(
                new int[] {10, 9, 2, 5, 3, 7, 101, 18}) == 4;
        assert DynamicProgrammingAndGraphProblems.longestIncreasingSubsequenceLength(
                new int[] {2, 2, 2}) == 1;
        assert DynamicProgrammingAndGraphProblems.minimumCoins(new int[] {1, 2, 5}, 11) == 3;
        assert DynamicProgrammingAndGraphProblems.minimumCoins(new int[] {2}, 3) == -1;
        assert DynamicProgrammingAndGraphProblems.countWaysToClimbStairs(5) == 8;
        assert DynamicProgrammingAndGraphProblems.countWaysToClimbStairs(0) == 1;
        assert DynamicProgrammingAndGraphProblems.maximumNonAdjacentSum(
                new int[] {2, 7, 9, 3, 1}) == 12;
        assert DynamicProgrammingAndGraphProblems.maximumNonAdjacentSum(
                new int[] {-2, -1}) == 0;

        List<List<Integer>> directedCycle = List.of(
                List.of(1), List.of(2), List.of(0), List.of());
        assert DynamicProgrammingAndGraphProblems.hasDirectedCycle(directedCycle);
        assert !DynamicProgrammingAndGraphProblems.hasDirectedCycle(
                List.of(List.of(1), List.of(2), List.of()));

        List<List<Integer>> graph = List.of(
                List.of(1, 2), List.of(0, 3), List.of(0, 3), List.of(1, 2, 4), List.of(3));
        assert DynamicProgrammingAndGraphProblems.shortestPathBfs(graph, 0, 4)
                .equals(List.of(0, 1, 3, 4));
        assert DynamicProgrammingAndGraphProblems.shortestPathBfs(graph, 4, 4)
                .equals(List.of(4));
        assert DynamicProgrammingAndGraphProblems.depthFirstSearch(graph, 0)
                .equals(List.of(0, 1, 3, 2, 4));
        assert DynamicProgrammingAndGraphProblems.countConnectedComponents(
                List.of(List.of(1), List.of(0), List.of(3), List.of(2), List.of())) == 3;
        assert DynamicProgrammingAndGraphProblems.countConnectedComponents(List.of()) == 0;

        assert DynamicProgrammingAndGraphProblems.topKFrequent(
                new int[] {1, 1, 1, 2, 2, 3}, 2).equals(Arrays.asList(1, 2));
        assert DynamicProgrammingAndGraphProblems.topKFrequent(
                new int[] {4, 4, 3, 3}, 1).equals(List.of(3));
        assert DynamicProgrammingAndGraphProblems.wordLadderShortestPath(
                "hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"))
                .equals(List.of("hit", "hot", "dot", "dog", "cog"));
        assert DynamicProgrammingAndGraphProblems.wordLadderShortestPath(
                "hit", "cog", List.of("hot", "dot", "dog")).isEmpty();

        System.out.println("All dynamic programming and graph smoke checks passed.");
    }
}
