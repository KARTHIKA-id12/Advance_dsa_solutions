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

public class Dijkstra {
    private List<List<Edge>> EdgeList = new ArrayList<>();
    int vertex;
    public Dijkstra(int V) {
        this.vertex = V;
        for (int i = 0; i < V; i++) {
            EdgeList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        EdgeList.get(src).add(new Edge(src, dest, weight));
    }

    public void getGraph() {
        for (int i = 0; i < vertex; i++) {
            List<Edge> edges = EdgeList.get(i);
            for (Edge e : edges) {
                System.out.println(
                        "from " + e.src +
                        " to " + e.dest +
                        " weight : " + e.weight
                );
            }
        }
    }

    public int[] dijkstra(int src) {
        int Dist[] = new int[vertex];
        Arrays.fill(Dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq =
                new PriorityQueue<>((a, b) -> a.weight - b.weight
                );
        Dist[src] = 0;
        pq.offer(new Edge(src, src, 0));
        while (!pq.isEmpty()) {
            Edge temp = pq.poll();
            int curr_node = temp.dest;
            int curr_weight = temp.weight;
            if (curr_weight > Dist[curr_node])
                continue;
            List<Edge> Neigh = EdgeList.get(curr_node);
            for (Edge E : Neigh) {
                if (Dist[E.dest] > curr_weight + E.weight) {
                    Dist[E.dest] = curr_weight + E.weight;
                    pq.offer(
                            new Edge(
                                    curr_node,
                                    E.dest,
                                    Dist[E.dest]
                            )
                    );
                }
            }
        }
        return Dist;
    }

    public static void main(String[] args) {
        Dijkstra g1 = new Dijkstra(5);  //g1--ve weighted graph
        g1.addEdge(1, 2, 3);    
        g1.addEdge(1, 4, 5);
        g1.addEdge(4, 3, 2);
        g1.addEdge(2, 3, -10);
        System.out.println("GRAPH 1");
        g1.getGraph();
        int[] dist1 = g1.dijkstra(1);
        System.out.println("\nShortest Distance from Source 1");

        for (int i = 1; i < dist1.length; i++) {
            System.out.println("1 -> " + i + " = " + dist1[i]);
        }



        Dijkstra g2 = new Dijkstra(7);

        g2.addEdge(1, 2, 50);
        g2.addEdge(1, 3, 45);
        g2.addEdge(1, 4, 10);

        g2.addEdge(4, 1, 10);

        g2.addEdge(4, 5, 15);
        g2.addEdge(4, 2, 15);

        g2.addEdge(2, 3, 10);

        g2.addEdge(5, 2, 20);
        g2.addEdge(5, 3, 35);
        g2.addEdge(5, 6, 3);

        g2.addEdge(3, 5, 30);

        System.out.println("\nGRAPH 2");

        g2.getGraph();

        int[] dist2 = g2.dijkstra(1);

        System.out.println("\nShortest Distance from Source 1");

        for (int i = 1; i < dist2.length; i++) {
            System.out.println("1 -> " + i + " = " + dist2[i]);
        }
    }
}
