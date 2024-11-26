import java.util.*;

public class Second_task {
    static class Edge {
        int destination, cost;

        Edge(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    static Map<String, Integer> cityIndex = new HashMap<>();
    static List<List<Edge>> graph = new ArrayList<>();

    // Dijkstra's Algorithm
    public static int dijkstra(int src, int dest, int n) {
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            for (Edge edge : graph.get(current.destination)) {
                int newCost = current.cost + edge.cost;
                if (newCost < minCost[edge.destination]) {
                    minCost[edge.destination] = newCost;
                    pq.add(new Edge(edge.destination, newCost));
                }
            }
        }

        return minCost[dest];
    }

    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();
        while (input-- > 0) {
            int n = scanner.nextInt();
            scanner.nextLine();

            cityIndex.clear();
            graph.clear();

            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < n; i++) {
                String city = scanner.nextLine();
                cityIndex.put(city, i);

                int neighbors = scanner.nextInt();
                for (int j = 0; j < neighbors; j++) {
                    int neighbor = scanner.nextInt() - 1;
                    int cost = scanner.nextInt();
                    graph.get(i).add(new Edge(neighbor, cost));
                }
                scanner.nextLine();
            }

            int paths = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Output:");
            for (int i = 0; i < paths; i++) {
                String[] query = scanner.nextLine().split(" ");
                int src = cityIndex.get(query[0]);
                int dest = cityIndex.get(query[1]);
                System.out.println(dijkstra(src, dest, n));
            }

            if (input > 0) scanner.nextLine(); // Skip empty line
        }
    }
}
