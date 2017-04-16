import constraints.Constraint;
import constraints.InequalityConstraint;
import dataStructure.State;
import dataStructure.VariableWithDomain;
import search.BacktrackSearch;
import search.BreadthFirst;

import java.util.*;

import static constraints.AllDiff.AllDiff;

/**
 * Created by hugo on 3/16/17.
 */
public class Main {
    //static List<dataStructure.VariableWithDomain> variables = new LinkedList<dataStructure.VariableWithDomain>();

    static ArrayList<VariableWithDomain> stateArray = new ArrayList<>();// = new Set<>();
    static HashMap<String, LinkedList<Integer>> stateMap = new HashMap<>();

//private static LinkedList<dataStructure.VariableWithDomain> state = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("RAN");
        new SolveExample2();
    }

    private static class SolveExample2 {

        SolveExample2() {

            LinkedList<Integer> domain1 = new LinkedList<>();
            domain1.add(1);
            domain1.add(2);
            domain1.add(3);

            LinkedList<Integer> domain2 = new LinkedList<>();
            domain2.add(1);
            domain2.add(2);
            domain2.add(3);


            LinkedList<Constraint> constraints = new LinkedList<Constraint>();
            //LinkedList<Integer> v = new LinkedList<Integer> (domain1);
            // LinkedList<Integer> v2 = new LinkedList<Integer> (domain2);

            LinkedList<Integer> domain3 = new LinkedList<Integer>(domain1);

            VariableWithDomain.Index i = new VariableWithDomain.Index(0, 0);// v.getIndex();
            stateMap.put(i.toString(), domain1);
            VariableWithDomain.Index i2 = new VariableWithDomain.Index(0, 1);// v.getI
            stateMap.put(i2.toString(), domain2);

            VariableWithDomain.Index i3 = new VariableWithDomain.Index(0, 2);// v.getI
            stateMap.put(i3.toString(), domain3);

            //stateArray.add(v);
            //stateArray.add(v2);

            InequalityConstraint ic = new InequalityConstraint(i.toString(), i2.toString());
            InequalityConstraint ic1 = new InequalityConstraint(i2.toString(), i3.toString());
            InequalityConstraint ic2 = new InequalityConstraint(i.toString(), i3.toString());
            constraints.add(ic);
            constraints.add(ic1);
            constraints.add(ic2);
            System.out.println("constraintslength" + constraints.size());
            State s = new State(stateMap, constraints);
            System.out.println("New state made");

           // BreadthFirst bf = new BreadthFirst(stateMap, constraints);

            BacktrackSearch bt = new BacktrackSearch();
            bt.backtrack(s);

        }

        private static class SolveExample {


            SolveExample() {


                LinkedList<Integer> domain = new LinkedList<>();

                VariableWithDomain.Index i2 = null;
                String i1;
                // LinkedList<Constraint> constraints=new LinkedList<Constraint>();

                for (int a = 0; a < 7; a++) {
                    Integer b = new Integer(a);
                    domain.add(b);
                }

                int col = 0;
                for (int row = 0; row < 4; row++) {
                    // for (int col =0; col<1; col++){
                    VariableWithDomain v = new VariableWithDomain(row, col);
                    for (Integer b : domain) {
                        //  Integer b = new Integer(a);
                        v.addToDomain(b);
                    }

                    i1 = v.getIndex().toString();
                    stateMap.put(i1, domain);

     /*   if (row!=0 || col!=0){
            InequalityConstraint ic = new InequalityConstraint(i1, i2);
            constraints.add(ic);

        }*/


                    i2 = v.getIndex();
                    //}

                }

                LinkedList<Constraint> constraints = AllDiff(stateMap);

                //  constraints.InequalityConstraint ic = new constraints.InequalityConstraint(stateMap.keySet());
                //constraints.add(ic);
                BreadthFirst bf = new BreadthFirst(stateMap, constraints);
                bf.search();

            }

        }
    }
}
