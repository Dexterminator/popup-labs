package se.dxtr.graphlibrary;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Created by dexter on 08/10/15.
 */
public class EdmondsKarp {

    @SuppressWarnings ("unchecked") // Needed since arrays do not allow generics, but we are safe as the method only handles FlowData graphs
    public static MaxFlowResult maxFlow (Graph<FlowData> graph, Vertex<FlowData> source, Vertex<FlowData> sink) {
        int flow = 0;
        while (true) {
            Queue<Vertex<FlowData>> queue = new LinkedList<> ();
            queue.add (source);
            Edge[] previous = new Edge[graph.getVertices ().size ()];
            while (!queue.isEmpty ()) {
                Vertex<FlowData> current = queue.poll ();
                for (Edge<FlowData> edge : current.getEdges ()) {
                    int toId = edge.getTo ().getId ();
                    if (previous[toId] == null && toId != source.getId () &&
                            edge.getData ().getCapacity () > edge.getData ().getFlow ()) {
                        previous[toId] = edge;
                        queue.add (edge.getTo ());
                    }
                }
            }

            if (previous[sink.getId ()] == null)
                break;

            int deltaFlow = Integer.MAX_VALUE;
            for (Edge<FlowData> edge = previous[sink.getId ()]; edge != null; edge = previous[edge.getFrom ().getId ()]) {
                int capacity = edge.getData ().getCapacity ();
                int edgeFlow = edge.getData ().getFlow ();
                deltaFlow = Math.min (deltaFlow, capacity - edgeFlow);
            }

            for (Edge<FlowData> edge = previous[sink.getId ()]; edge != null; edge = previous[edge.getFrom ().getId ()]) {
                edge.getData ().addFlow (deltaFlow);
                edge.getData ().getReverseEdge ().getData ().addFlow (-deltaFlow);
            }

            flow += deltaFlow;
        }

        List<Edge<FlowData>> solution = graph.getEdges ().stream ()
                .filter (edge -> edge.getData ().getFlow () > 0)
                .collect (Collectors.toList ());

        return new MaxFlowResult (flow, solution);
    }

    public static class MaxFlowResult {
        public final int maxFlow;
        public final List<Edge<FlowData>> flowSolution;

        public MaxFlowResult (int maxFlow, List<Edge<FlowData>> flowSolution) {
            this.maxFlow = maxFlow;
            this.flowSolution = flowSolution;
        }
    }
}
