/*
package search;

import heuristics.Heuristic;
import data.dataStructure.Node;
import data.dataStructure.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.dataStructure.PriorityQueue;
import java.util.Queue;

*/
/*The general search is used for Astar search, uniform search and greedy search
* *//*

public class General extends search.Search {
    protected HashSet<dataStructure.State> closedSet;

    //  protected ArrayList<dataStructure.Node> nodeVisitList;

    protected Queue<dataStructure.Node> queueNode;
    protected double metrics;
    protected Heuristic heuristic;
    protected HashMap<dataStructure.State, dataStructure.Node> openSet;
    dataStructure.PriorityQueue<dataStructure.Node> queueFunction;


    private dataStructure.Node node;

    public General(int[] puzzle, int[] goal, Heuristic heuristic) {
        super(puzzle, goal);
        this.closedSet = new HashSet<>();
        this.openSet = new HashMap<>();

        this.metrics = 0;
        setHeuristicFunction(heuristic);
        queueFunction = getPriorityQueue();
    }

    public void setHeuristicFunction(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public Optional<dataStructure.Node> search() {
        this.queueNode = queueFunction;
        dataStructure.Node node = new dataStructure.Node(null, 0, super.getInitialState());
        // dataStructure.Node eNode = new dataStructure.Node(null, true);


        this.clearObjects();
        this.add(node);
        // this.add(eNode);
        while (! queueNode.isEmpty()) {
            dataStructure.Node currentNode = this.removeTopNode();

            if (super.goalState().equals(currentNode.getState())) {
                return Optional.of(currentNode);
            }

            for (dataStructure.Node n : expandNode(currentNode)) {
                double hCost = this.heuristic.calculateCost(n.getState(), super.goalState());
                n.setHeuristicCost(hCost);
                n.setTotalCost(currentNode.getPathCost() + n.getHeuristicCost());
                this.add(n);
            }
        }
        return Optional.empty();

    }




    protected void add(dataStructure.Node node) {
        if (! contains(node)) {
            if (this.openSet.containsKey(node.getState())) {
                dataStructure.Node updateNode = this.openSet.get(node.getState());
                if (super.compareNodes(node, updateNode) < 0) {
                    if (queueNode.remove(updateNode)) {
                        openSet.remove(updateNode.getState());
                    }
                    this.openSet.put(node.getState(), node);
                    this.queueNode.add(node);
                }
            } else {
                this.queueNode.add(node);
                this.openSet.put(node.getState(), node);
            }
        }
    }

    public ArrayList<dataStructure.Node> getNodeVisitList(){
        //  nodeVisitList = new ArrayList<dataStructure.Node>();

        return nodeVisitList;
    }

    protected void clearObjects() {
        this.queueNode.clear();
        this.closedSet.clear();
    }

    protected dataStructure.Node removeTopNode() {
        this.metrics++;
        dataStructure.Node node = this.queueNode.poll();
        this.openSet.remove(node.getState());
        this.closedSet.add(node.getState());

        return node;
    }


    @Override
    public double getMetric() {
        return this.metrics;
    }

    public boolean contains(dataStructure.Node node) {
        return this.closedSet.contains(node.getState());
    }


    @Override
    public dataStructure.PriorityQueue getPriorityQueue() {

        Comparator<dataStructure.Node> compareNodesMethod = super.getComparator();
        return new dataStructure.PriorityQueue<>(10, compareNodesMethod);
    }


    @Override
    public double calculateNodeValue(dataStructure.Node node) {
        return super.getTotalCost(node);
    }

}*/
