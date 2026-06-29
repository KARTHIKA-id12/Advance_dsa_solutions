import java.util.*;

public class KosarajuSCC {

    static final int V = 8;

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> rev = new ArrayList<>();

    static {
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }
    }

    // First DFS (store vertices according to finishing time)
    static void dfs1(int node, boolean[] visited, Stack<Integer> stack) {

        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, visited, stack);
            }
        }

        stack.push(node);
    }

    // Reverse the graph
    static void reverseGraph() {
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                rev.get(neighbor).add(i);
            }
        }
    }

    // Second DFS on reversed graph
    static void dfs2(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : rev.get(node)) {
            if (!visited[neighbor]) {

                dfs2(neighbor, visited);
            }
        }
    }

    static void findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // Step 1: DFS on original graph
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(i, visited, stack);
            }
        }

        // Step 2: Reverse graph
        reverseGraph();

        // Step 3: Reset visited array
        Arrays.fill(visited, false);

        System.out.println("Strongly Connected Components:");

        // Step 4: Process vertices according to stack order
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                dfs2(curr, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(5);
        adj.get(4).add(7);
        adj.get(5).add(6);
        adj.get(6).add(4);
        adj.get(6).add(7);

        findSCCs();
    }
}