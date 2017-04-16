package search;

import constraints.Constraint;
import dataStructure.Node;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.*;
import java.util.PriorityQueue;

/*Breadth first search
* */
public class BreadthFirst extends Search {
    private double metrics = 0;

    private Queue<Node> queue;
    private HashSet<State> closedStateSet;
    private HashSet<State> openStateSet;
    private Node rootNode;

    int depth;

    public BreadthFirst(HashMap<String, LinkedList<Integer>> state, LinkedList<Constraint> constraints) {


        super(state, constraints);
        depth = 0;
        this.rootNode = new Node(null, 0, super.getInitialState());

        this.openStateSet = new HashSet<>();
        this.closedStateSet = new HashSet<>();
    }

    public int getDepth() {
        return super.depth;
    }


    public Optional<Node> search() {

        queue = new LinkedList<>();
        //this.queue = queue;
        this.rootNode = new Node(null, super.getInitialState());

      //  System.out.println(rootNode.getState().getState().size());

        this.closedStateSet.clear();
        this.openStateSet.clear();
        Node node = this.rootNode;
        int numberOfNodesAtLevel = 0;
        // dataStructure.Node endOfLineNode = new dataStructure.Node(null, true);

        this.addToSet(node);
        // this.addToSet(endOfLineNode);
        depth = 0;
        while (!queue.isEmpty()) {
            Node eNode = this.removeFirstFromSet();


printValues(eNode);

System.out.println("Domain Size Below");
            printDomain(eNode);

            System.out.println("Open set size" + openStateSet.size());

           //IF THE NODE HAS ONLY ONE DOMAIN FOR EACH VARIABLE TERMINATE HERE

Boolean unSolved = false;

           // System.out.println("b" + eNode.getState().getState().size());
            //for each variable. If there is only one domain, end
           // HashMap<VariableWithDomain.Index, VariableWithDomain> state = eNode.getState().getState();
           // state = eNode.getState().getState().entrySet();
System.out.println("Number of variables in state: " + eNode.getState().getState().size());


          /*  for (VariableWithDomain value : eNode.getState().getState().values()) {
                // ...
            }
*/

                for (Map.Entry<String, LinkedList<Integer>> entry : eNode.getState().getState().entrySet()) {

                    String key = entry.getKey();
                    LinkedList<Integer> value = entry.getValue();
                    System.out.println(key);
                  //  System.out.println("Col: " + key.getCol());
System.out.println("Domain size: " + value.size());

                    if (entry.getValue().size()>1){
                        unSolved=true;
                        break;
                    }
                   /* Optional<Node> solution = new BreadthFirst(eNode.getState().getState(), eNode.getState().getConstraints()).search();

                            if (solution!=null){
                        System.out.println("SOLUTIONFOUND");
                        return solution;
                            }*/

            }

            System.out.println("--------");

            if (!unSolved){
                    System.out.println("SOLUTIONFOUND");


                   for (int row1=0; row1<10; row1++){

                       for (int col1=0; col1<10; col1++){

                           for (Map.Entry<String, LinkedList<Integer>> entry2 : eNode.getState().getState().entrySet()) {
                               String key2 = entry2.getKey();

                           //    if (key2.getRow()==row1 && key2.getCol() == col1){
                                   int value = eNode.getState().getState().get(key2).getFirst();
                                   System.out.print(value + ", ");
                                   break;
                            //   }
                       }


                    }
                       System.out.println();
                }
                return Optional.of(eNode);
            }

            List<Node> nodes = expandNode(eNode);
            this.metrics++;
            nodes.forEach(this::addToSet);

        }
        return Optional.empty();
    }

public void printValues(Node eNode){

    for (int row1=0; row1<10; row1++){

        for (int col1=0; col1<10; col1++){

            for (Map.Entry<String, LinkedList<Integer>> entry2 : eNode.getState().getState().entrySet()) {
                String key2 = entry2.getKey();

               // if (key2.getRow()==row1 && key2.getCol() == col1){
                    int value = eNode.getState().getState().get(key2).getFirst();
                    System.out.print(value + ", ");
                    break;
                //}
            }


        }
        System.out.println();
    }
}

    public void printDomain(Node eNode){

        for (int row1=0; row1<10; row1++){

            for (int col1=0; col1<10; col1++){

                for (Map.Entry<String, LinkedList<Integer>> entry2 : eNode.getState().getState().entrySet()) {
                    String key2 = entry2.getKey();

                    //if (key2.getRow()==row1 && key2.getCol() == col1){
                        int value = eNode.getState().getState().get(key2).size();
                        System.out.print(value + ", ");
                        break;
                   // }
                }


            }
            System.out.println();
        }
    }
    @Override
    public double getMetric() {
        return metrics;
    }

    public ArrayList<Integer> getNumberOfNodesAtEachLevel() {
        return super.nodesAtEachLevel;
    }

    @Override
    public PriorityQueue getPriorityQueue() {
        return null;
    }

    @Override
    public double calculateNodeValue(Node node) {
        return 0;
    }

    @Override
    public ArrayList<Node> getNodeVisitList(){
        //  nodeVisitList = new ArrayList<dataStructure.Node>();

        return nodeVisitList;
    }

    private Node removeFirstFromSet() {

        Node node = this.queue.poll();
        this.closedStateSet.add(node.getState());
        this.openStateSet.remove(node.getState());
        return node;
    }

    private void addToSet(Node node) {
        if (! openStateSet.contains(node.getState()) && ! closedStateSet.contains(node.getState())) {
            this.openStateSet.add(node.getState());
            this.queue.add(node);
        }
    }
}