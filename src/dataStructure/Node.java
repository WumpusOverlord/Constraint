package dataStructure;

import java.util.Set;

/*    The dataStructure.Node class is used for the nodes
*
*
* */
public class Node implements Comparable {
    int depth;
    private double totalCost;
    private State state;
    private Node parent;
    private Set<Node> children;
    private double pathCost;
    private double heuristicCost;
    public Boolean endOfLine = false;


    public Node(Node parent, State state) {

        this.setTotalCost(0);
        this.setPathCost(0);
        this.setHeuristicCost(0);
        this.parent = parent;
        this.state = state;
    }

    public Node(Node parent, double cost, State state) {

        setPathCost(cost);
        setState(state);
        setParent(parent);
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


    public Node(Node parent, Boolean endOfLine) {
        this.parent = parent;
        this.endOfLine = endOfLine;
    }


    public void setChildren(Set<Node> children) {
        this.children = children;
    }

    public Set<Node> getChildren() {
        return this.children;
    }


    public void setState(State state) {
        this.state = state;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }


    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (Double.compare(node.heuristicCost, heuristicCost) != 0) {

            return false;
        } else if (Double.compare(node.totalCost, totalCost) != 0) {
            return false;
        } else if (Double.compare(node.pathCost, pathCost) != 0) {
            return false;
        } else if (parent != null ? !parent.equals(node.parent) : node.parent != null) {
            return false;
        } else if (children != null ? !children.equals(node.children) : node.children != null) {
            return false;
        }


        return state != null ? state.equals(node.state) : node.state == null;

    }


    @Override
    public int hashCode() {
        int hashCode;
        long number;

        if (parent != null)
            hashCode = parent.hashCode();
        else
            hashCode = 0;

        hashCode = 31 * hashCode + (children != null ? children.hashCode() : 0);
        number = Double.doubleToLongBits(pathCost);

        hashCode = 31 * hashCode + (int) (number ^ (number >>> 32));

        number = Double.doubleToLongBits(heuristicCost);

        hashCode = 31 * hashCode + (int) (number ^ (number >>> 32));

        number = Double.doubleToLongBits(totalCost);

        hashCode = 31 * hashCode + (int) (number ^ (number >>> 32));

        hashCode = 31 * hashCode + (state != null ? state.hashCode() : 0);

        return hashCode;
    }


    @Override
    public int compareTo(Object o) {
        Node node = ((Node) o);
        return Double.compare(this.getPathCost(), node.getPathCost());
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }


    public double getTotalCost() {
        return totalCost;
    }

    public double getPathCost() {
        return pathCost;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }
}