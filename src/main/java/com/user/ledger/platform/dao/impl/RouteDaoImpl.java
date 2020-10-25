package com.user.ledger.platform.dao.impl;

import com.user.ledger.platform.dao.RouteDao;
import com.user.ledger.platform.enums.ErrorCodes;
import com.user.ledger.platform.exceptions.MakeMyTripPlatformException;
import com.user.ledger.platform.responses.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RouteDaoImpl implements RouteDao {

    private static final int NO_PARENT = -1;

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private static String dijkstra(int[][] adjacencyMatrix,
                                 int startVertex, int endVertex)
    {
        int nVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++)
        {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        return "Shortest Distance : ".concat(String.valueOf(shortestDistances[endVertex]))
                .concat(", ").concat("Shortest Path : " )
                .concat(printSolution(startVertex, shortestDistances, parents, endVertex));
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private static String printSolution(int startVertex,
                                      int[] distances,
                                      int[] parents, int endVertex)
    {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (vertexIndex != startVertex && vertexIndex==endVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                String path="";
                path = printPath(vertexIndex, parents, path);
                return path;
            }
        }
        return "";
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static String printPath(int currentVertex,
                                  int[] parents, String path)
    {

        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return path;
        }
        path = printPath(parents[currentVertex], parents, path);
        System.out.print(currentVertex + " ");
        path=path.concat(currentVertex + " ");
        return path;
    }

    @Override
    public GenericResponse shortestRoute(String source, String destination){
        try {
            int[][] adjacencyMatrix = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                    {4, 0, 8, 0, 0, 0, 0, 11, 0},
                    {0, 8, 0, 7, 0, 4, 0, 0, 2},
                    {0, 0, 7, 0, 9, 14, 0, 0, 0},
                    {0, 0, 0, 9, 0, 10, 0, 0, 0},
                    {0, 0, 4, 0, 10, 0, 2, 0, 0},
                    {0, 0, 0, 14, 0, 2, 0, 1, 6},
                    {8, 11, 0, 0, 0, 0, 1, 0, 7},
                    {0, 0, 2, 0, 0, 0, 6, 7, 0}};
            String path = dijkstra(adjacencyMatrix, Integer.parseInt(source), Integer.parseInt(destination));
            return GenericResponse.builder().result(path).build();
        } catch (Exception e){
            log.error("Exception occurred while calling shortest path function :", e);
            throw new MakeMyTripPlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.NO_PATH_POSSIBLE_ERROR);
        }
    }

}
