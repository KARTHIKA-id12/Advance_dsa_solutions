import java.util.*;

public class TarjanSCC {
    static final int V = 7;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int timer = 0;
    static void DFS(int u, int[] disc, int[] low, Stack<Integer> stack, boolean[] presentInStack) {
        disc[u] = low[u] = timer++;
        stack.push(u);
        presentInStack[u] = true;
        for (int v : adj.get(u)) {

            // Tree Edge
            if (disc[v] == -1) {
                DFS(v, disc, low, stack, presentInStack);
                low[u] = Math.min(low[u], low[v]);
            }

            // Back Edge
            else if (presentInStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // Root of SCC
        if (low[u] == disc[u]) {
            System.out.print("SCC : ");
            while (stack.peek() != u) {
                int node = stack.pop();
                System.out.print(node + " ");
                presentInStack[node] = false;
            }

            int node = stack.pop();
            System.out.print(node);

            presentInStack[node] = false;

            System.out.println();
        }
    }

    static void findSCCsTarjan() {
        int[] disc = new int[V];
        int[] low = new int[V];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        boolean[] presentInStack = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                DFS(i, disc, low, stack, presentInStack);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(3).add(4);
        adj.get(4).add(0);
        adj.get(4).add(5);
        adj.get(4).add(6);
        adj.get(5).add(6);
        adj.get(6).add(5);

        findSCCsTarjan();
    }
}