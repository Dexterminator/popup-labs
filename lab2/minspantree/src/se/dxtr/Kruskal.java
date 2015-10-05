package se.dxtr;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dexter on 05/10/15.
 */
public class Kruskal {

    public static<T extends Comparable<T>> Optional<UndirectedGraph<T>> minimumSpanningTree (UndirectedGraph<T> graph) {
        if (!isConnected (graph))
            return Optional.empty ();

        Set<Vertex<T>> vertices = graph.getVertices ();
        Map<Vertex<T>, Integer> mappings = getMappings (vertices);
        List<UndirectedEdge<T>> sortedEdges = graph.getEdges ().stream ()
                .sorted (Comparator.comparing (graph::getWeight))
                .collect (Collectors.toList ());
        DisjointSets sets = new DisjointSets (mappings.size ());

        UndirectedGraph<T> tree = new UndirectedGraph<> ();
        for (UndirectedEdge<T> edge : sortedEdges) {
            int a = mappings.get (edge.getVertexA ());
            int b = mappings.get (edge.getVertexB ());
            if (!sets.inSameSet (a, b)) {
                sets.union (a, b);
                tree.addVertex (edge.getVertexA ());
                tree.addVertex (edge.getVertexB ());
                tree.addEdge (edge, graph.getWeight (edge));
            }
        }

        return Optional.of (tree);
    }

    public static <T extends Comparable<T>> boolean isConnected (UndirectedGraph<T> graph) {
        Queue<Vertex<T>> queue = new LinkedList<> ();
        queue.add (graph.getVertices ().iterator ().next ());
        Set<Vertex<T>> visited = new HashSet<> ();
        while (!queue.isEmpty ()) {
            Vertex<T> vertex = queue.poll ();
            for (Vertex<T> neighbor : graph.getNeighbors (vertex)) {
                if (!visited.contains (neighbor)) {
                    visited.add (neighbor);
                    queue.add (neighbor);
                }
            }
        }
        return visited.equals (graph.getVertices ());
    }

    public static <T extends Comparable<T>> Map<Vertex<T>, Integer> getMappings (Set<Vertex<T>> vertices) {
        Map<Vertex<T>, Integer> mappings = new HashMap<> ();
        int i = 0;
        for (Vertex<T> vertex : vertices) {
            mappings.put (vertex, i);
            i++;
        }
        return mappings;
    }
}
