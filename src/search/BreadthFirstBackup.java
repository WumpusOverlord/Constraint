/*


import java.util.*;
import java.util.dataStructure.PriorityQueue;

*/
/*Breadth first search
* *//*

public class search.BreadthFirst extends search.Search {
    private double metrics = 0;

    private Queue<dataStructure.Node> queue;
    private HashSet<dataStructure.State> closedStateSet;
    private HashSet<dataStructure.State> openStateSet;
    private dataStructure.Node rootNode;

    int depth;

    public search.BreadthFirst(HashMap<dataStructure.VariableWithDomain.Index, dataStructure.VariableWithDomain> state, LinkedList<constraints.Constraint> constraints) {


        super(state, constraints);


        depth = 0;

        this.rootNode = new dataStructure.Node(null, 0, super.getInitialState());

        this.openStateSet = new HashSet<>();
        this.closedStateSet = new HashSet<>();
    }

    public int getDepth() {
        return super.depth;
    }


    public Optional<dataStructure.Node> search() {

        queue = new LinkedList<>();
        //this.queue = queue;
        this.rootNode = new dataStructure.Node(null, super.getInitialState());

        System.out.println(rootNode.getState().getState().size());

        this.closedStateSet.clear();
        this.openStateSet.clear();
        dataStructure.Node node = this.rootNode;
        int numberOfNodesAtLevel = 0;
        // dataStructure.Node endOfLineNode = new dataStructure.Node(null, true);

        this.addToSet(node);
        // this.addToSet(endOfLineNode);
        depth = 0;
        while (!queue.isEmpty()) {
            dataStructure.Node eNode = this.removeFirstFromSet();

            //IF THE NODE HAS ONLY ONE DOMAIN FOR EACH VARIABLE TERMINATE HERE



            System.out.println("b" + eNode.getState().getState().size());
            //for each variable. If there is only one domain, end
            HashMap<dataStructure.VariableWithDomain.Index, dataStructure.VariableWithDomain> state;


            state = eNode.getState().getState();

            System.out.println("c" + state.size());

            int c=1;
            //  for (int a = 0; a < state.size(); a++) {

            for (Map.Entry<dataStructure.VariableWithDomain.Index, dataStructure.VariableWithDomain> entry : state.entrySet()) {
                dataStructure.VariableWithDomain.Index key = entry.getKey();
                dataStructure.VariableWithDomain value = entry.getValue();
                //   stateMap.put(key, value);
                // ...

                int b = value.getValues().size();


                if (b > 1) {
                    c++;
                    System.out.println("STATE LENGTH IS" + state.size());
                    if (c>=state.size()){
                        System.out.println("Large C");
                        System.out.println("Value of c = " + c);
                        System.out.println("Value of stateLength = " + c);
                        System.out.println("Value of b is" + b);
                        System.out.println("This ran 1");
                        dataStructure.State state2 = eNode.getState();
                        break;
                    }
                    else {
                        System.out.println("This ran 2" + c);
                        System.out.println("Value of c = " + c);
                        System.out.println("Value of stateLength = " + c);
                    }
                }
                else {
                    System.out.println("Value of b is" + b);
                    System.out.println("This ran 3");
                }
                System.out.println("SOLUTION FOUND");
                //
                System.out.println(state);

                for (Map.Entry<dataStructure.VariableWithDomain.Index, dataStructure.VariableWithDomain> entry2 : state.entrySet()) {
                    dataStructure.VariableWithDomain.Index key2 = entry2.getKey();
                    dataStructure.VariableWithDomain value2 = entry2.getValue();
                    System.out.println("Index = " + key2);
                    System.out.println("Value at index = " + value2.getValues().getFirst());

                }

                return Optional.of(eNode);

            }


            // List<dataStructure.Node> nodes = this.expandNode.expandNode(eNode);
            List<dataStructure.Node> nodes = expandNode(eNode);
            this.metrics++;
            nodes.forEach(this::addToSet);

        }
        return Optional.empty();
    }


    @Override
    public double getMetric() {
        return metrics;
    }

    public ArrayList<Integer> getNumberOfNodesAtEachLevel() {
        return super.nodesAtEachLevel;
    }

    @Override
    public dataStructure.PriorityQueue getPriorityQueue() {
        return null;
    }

    @Override
    public double calculateNodeValue(dataStructure.Node node) {
        return 0;
    }

    @Override
    public ArrayList<dataStructure.Node> getNodeVisitList(){
        //  nodeVisitList = new ArrayList<dataStructure.Node>();

        return nodeVisitList;
    }

    private dataStructure.Node removeFirstFromSet() {

        dataStructure.Node node = this.queue.poll();
        this.closedStateSet.add(node.getState());
        this.openStateSet.remove(node.getState());
        return node;
    }

    private void addToSet(dataStructure.Node node) {
        if (! openStateSet.contains(node.getState()) && ! closedStateSet.contains(node.getState())) {
            this.openStateSet.add(node.getState());
            this.queue.add(node);
        }
    }
}*/
