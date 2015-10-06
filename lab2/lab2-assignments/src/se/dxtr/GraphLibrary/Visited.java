package se.dxtr.graphlibrary;

public class Visited {
    private boolean visited;

    public Visited () {
        this.visited = false;
    }

    public boolean isVisited () {
        return visited;
    }

    public void setVisited (boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString () {
        return "Visited{" +
                "visited=" + visited +
                '}';
    }
}
