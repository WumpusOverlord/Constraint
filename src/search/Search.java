package search;

import constraints.Constraint;
import dataStructure.ExpandNode;
import dataStructure.Node;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.*;
import java.util.PriorityQueue;

public abstract class Search {

    protected ArrayList<Node> nodeVisitList;

    private Integer pathCost;
    private State initialState;
    int depth;
    String searchType;

    ArrayList<Integer> nodesAtEachLevel;


    Search(HashMap<String, LinkedList<Integer>> state, LinkedList<Constraint> constraints){
        nodeVisitList = new ArrayList<Node>();
        initialState = new State(state, constraints);
        nodesAtEachLevel = new ArrayList<Integer>();

    }

    void increaseDepth(int numberOfNodesAtLevel){

        nodesAtEachLevel.add(numberOfNodesAtLevel);
        depth = depth + 1;
    }

    public State getInitialState() {
        return initialState;
    }

    public abstract Optional<Node> search();

    public abstract double getMetric();

    /*Gets the comparator to be used for the search for comparing the nodes to be added to the priority queue
   * */
    public Comparator<Node> getComparator() {
        Comparator<Node> nc = new NodeComparator();
        return nc;
    }

    public double compareNodes(Node node1, Node node2){

        return node1.compareTo(node2);
    }

    public abstract PriorityQueue getPriorityQueue();

    public double getTotalCost(Node node){
        return node.getTotalCost();
    }

    public double getPathCost(Node node){
        return node.getPathCost();
    }

    public double getHeuristicCost(Node node){
        return node.getHeuristicCost();
    }


    public abstract double calculateNodeValue(Node node);

    public List<Node> expandNode(Node parentNode){
        this.nodeVisitList.add(parentNode);

     //  Node solution = new BreadthFirst(parentNode.getState().getState(), parentNode.getState().getConstraints())

/*for (VariableWithDomain.Index index: parentNode.getState().getState().keySet()){


}*/


        return ExpandNode.expandNode(parentNode);
    }


    public ArrayList<Node> getNodeVisitList(){

        return nodeVisitList;
    }



    /*The node comparator is used for comparing the node values for where they are inserted into the priority queue
* */
    class NodeComparator implements Comparator<Node>, Comparable<Node> {

        @Override
        public int compareTo(Node o) {
            return 0;
        }

        @Override
        public int compare(Node o1, Node o2) {
            return Double.compare(calculateNodeValue(o1), calculateNodeValue(o2));

        }
    }

}
