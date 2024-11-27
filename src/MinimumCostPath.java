import java.util.*;

public class MinimumCostPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Indicate input section
        System.out.println("Input");

        // Number of test cases
        int testCases = scanner.nextInt();

        // Process each test case
        while (testCases-- > 0) {
            // Number of cities in this test case
            int cityCount = scanner.nextInt();

            // Map to store graph representation (city name -> list of neighbors)
            Map<String, List<int[]>> graph = new HashMap<>();

            // Map to associate city names with their indices
            Map<String, Integer> cityIndex = new HashMap<>();

            // Read city data
            for (int i = 1; i <= cityCount; i++) {
                // Read city name
                String cityName = scanner.next();
                cityIndex.put(cityName, i); // Map city name to its index

                // Number of neighbors for the current city
                int neighbors = scanner.nextInt();

                // Initialize adjacency list for the city
                graph.putIfAbsent(cityName, new ArrayList<>());

                // Read neighbor data
                for (int j = 0; j < neighbors; j++) {
                    int neighborIndex = scanner.nextInt(); // Index of the neighbor city
                    int cost = scanner.nextInt(); // Cost of transportation to the neighbor
                    graph.get(cityName).add(new int[]{neighborIndex, cost}); // Add edge to graph
                }
            }

            // Number of queries (paths to find)
            int queryCount = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            // Indicate output section
            System.out.println("Output");

            // Process each query
            while (queryCount-- > 0) {
                // Read source and destination city names
                String source = scanner.next();
                String destination = scanner.next();

                // Calculate the minimum cost using Dijkstra's algorithm
                int result = dijkstra(cityIndex, graph, source, destination, cityCount);

                // Print the result (minimum cost for the path)
                System.out.println(result);
            }

            scanner.nextLine(); // Consume the empty line between test cases
        }

        scanner.close(); // Close the scanner to free resources
    }

    /**
     * Dijkstra's algorithm implementation to find the shortest path in a weighted graph.
     *
     * @param cityIndex   Map of city names to their indices.
     * @param graph       Adjacency list representation of the graph.
     * @param source      The source city name.
     * @param destination The destination city name.
     * @param cityCount   The total number of cities.
     * @return The minimum transportation cost from source to destination.
     */
    private static int dijkstra(Map<String, Integer> cityIndex, Map<String, List<int[]>> graph,
                                String source, String destination, int cityCount) {
        // Priority queue to process cities based on the minimum distance (Dijkstra's main data structure)
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Array to store the shortest distance to each city (initialized to infinity)
        int[] distances = new int[cityCount + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Get the index of the source city
        int sourceIndex = cityIndex.get(source);

        // Set the distance to the source city as 0
        distances[sourceIndex] = 0;

        // Add the source city to the priority queue
        queue.offer(new int[]{sourceIndex, 0});

        // Main loop to process all cities
        while (!queue.isEmpty()) {
            // Get the city with the smallest distance from the queue
            int[] current = queue.poll();
            int currentCity = current[0];
            int currentCost = current[1];

            // Skip this city if it was already processed with a shorter distance
            if (currentCost > distances[currentCity]) continue;

            // Iterate over all neighbors of the current city
            for (int[] neighbor : graph.getOrDefault(getCityByIndex(cityIndex, currentCity), new ArrayList<>())) {
                int neighborCity = neighbor[0]; // Neighbor city index
                int pathCost = neighbor[1]; // Cost of the edge to the neighbor

                // If a shorter path to the neighbor is found, update the distance
                if (distances[currentCity] + pathCost < distances[neighborCity]) {
                    distances[neighborCity] = distances[currentCity] + pathCost;
                    // Add the neighbor to the queue with the updated distance
                    queue.offer(new int[]{neighborCity, distances[neighborCity]});
                }
            }
        }

        // Return the shortest distance to the destination city
        return distances[cityIndex.get(destination)];
    }

    /**
     * Helper method to retrieve the city name by its index.
     *
     * @param cityIndex Map of city names to their indices.
     * @param index     The index of the city.
     * @return The name of the city corresponding to the given index.
     */
    private static String getCityByIndex(Map<String, Integer> cityIndex, int index) {
        // Iterate through the map to find the city with the given index
        for (Map.Entry<String, Integer> entry : cityIndex.entrySet()) {
            if (entry.getValue() == index) return entry.getKey();
        }
        return null; // Return null if no city is found (should not happen in valid input)
    }
}
