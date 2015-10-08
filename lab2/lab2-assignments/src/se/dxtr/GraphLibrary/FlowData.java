package se.dxtr.graphlibrary;

/**
 * Created by dexter on 08/10/15.
 */
public class FlowData {
    private int capacity;
    private int flow;

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

    public int restCapacity() {
        return capacity - flow;
    }

    public void addFlow(int flow) {
        this.flow += flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public String toString () {
        return "FlowData{" +
                "capacity=" + capacity +
                ", flow=" + flow +
                '}';
    }
}
