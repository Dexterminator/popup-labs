package se.dxtr.graphlibrary;

import java.util.Objects;

/**
 * Created by dexter on 08/10/15.
 */
public class FlowData {
    private int capacity;
    private int flow;
    private Edge<FlowData> reverseEdge = null;

    public FlowData (int capacity) {
        this.capacity = capacity;
        flow = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void addFlow(int flow) {
        this.flow += flow;
    }

    public Edge<FlowData> getReverseEdge () {
        return reverseEdge;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        FlowData flowData = (FlowData) o;
        return Objects.equals (capacity, flowData.capacity) &&
                Objects.equals (flow, flowData.flow) &&
                Objects.equals (reverseEdge.getTo ().getId (), flowData.reverseEdge.getTo ().getId ()) &&
                Objects.equals (reverseEdge.getFrom ().getId (), flowData.reverseEdge.getFrom ().getId ());
    }

    @Override
    public int hashCode () {
        return Objects.hash (capacity, flow, reverseEdge);
    }

    public void setReverseEdge (Edge<FlowData> reverseEdge) {
        this.reverseEdge = reverseEdge;
    }

    @Override
    public String toString () {
        return "FlowData{" +
                "capacity=" + capacity +
                ", flow=" + flow +
                '}';
    }
}
