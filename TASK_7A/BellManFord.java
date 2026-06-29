import java.util.*;

class Edge {
    int src;
    int dest;
    int weight;     

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class BellManFord {
    private List<Edge> EdgeList = new ArrayList<>();
    int vertex;
    public BellManFord(int V) {
        this.vertex = V;
    }

    public void addEdge(int src, int dest, int weight) {
        EdgeList.add( new Edge(src, dest, weight));
    }

    public void getGraph() {
        for (Edge e : EdgeList) {
            System.out.println("from " + e.src + " to " + e.dest + " weight : " + e.weight);
        }
    }

    public int[] bellmanFord(int src) {
        int Dist[] = new int[vertex];
        Arrays.fill(Dist, Integer.MAX_VALUE);
        Dist[src] = 0;
        for (int i = 1; i < vertex; i++) {
            for (Edge e : EdgeList) {
                if (Dist[e.src] != Integer.MAX_VALUE &&
                        Dist[e.src] + e.weight
                                < Dist[e.dest]) {
                    Dist[e.dest] =
                            Dist[e.src] + e.weight;
                }
            }
        }
        return Dist;
    }

    public static void main(String[] args) {

        BellManFord g1 = new BellManFord(8);

        g1.addEdge(1, 2, 6);
        g1.addEdge(1, 3, 5);
        g1.addEdge(1, 4, 5);

        g1.addEdge(2, 5, -1);

        g1.addEdge(3, 2, -2);
        g1.addEdge(3, 5, 1);

        g1.addEdge(4, 3, -2);
        g1.addEdge(4, 6, -1);

        g1.addEdge(5, 7, 3);

        g1.addEdge(6, 7, 3);

        System.out.println("GRAPH 1");
        g1.getGraph();
        int[] dist1 = g1.bellmanFord(1);
        System.out.println(
                "\nShortest Distance from Source 1"
        );

        for (int i = 1; i < dist1.length; i++) {
            System.out.println("1 -> " + i + " = " + dist1[i]);
        }


    // graph2 negative cycle graph

    BellManFord g2 = new BellManFord(5);

        g2.addEdge(1, 2, 4);
        g2.addEdge(1, 3, 5);

        g2.addEdge(2, 3, 5);

        g2.addEdge(3, 4, 3);

        g2.addEdge(4, 2, -10);

        System.out.println("\nGRAPH 2");

        g2.getGraph();

        int[] dist2 = g2.bellmanFord(1);

        System.out.println(
                "\nShortest Distance from Source 1"
        );

        for (int i = 1; i < dist2.length; i++) {
            System.out.println("1 -> " + i + " = " + dist2[i]);
        }
    }
}