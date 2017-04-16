package search;

import constraints.Constraint;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.*;

import static dataStructure.ExpandNode.checkConstraints;

/**
 * Created by hugo on 3/20/17.
 */
public class BacktrackSearch {
    HashMap<String, Integer> labelled;
    HashMap<String, LinkedList<Integer>> unlabelled;
    HashMap<String, LinkedList<Integer>> domain;
    LinkedList<Constraint> constraints = new LinkedList<Constraint>();

    public BacktrackSearch() {
        labelled = new HashMap<String, Integer>();
        unlabelled = new HashMap<String, LinkedList<Integer>>();
        domain = new HashMap<String, LinkedList<Integer>>();


    }

    public State backtrack(State state) {

        labelled = state.labelled;
        unlabelled = state.unlabelled;
        domain = state.domain;
        constraints = state.constraints;
        Boolean solved = false;

        while (!solved) {
            if (unlabelled.size() == 0) {
               System.out.println("No unlabelled");
                return state;
            } else {


                for (LinkedList<Integer> d : domain.values()) {
                    int size = d.size();
                    if (d.size() > 1) {
                        solved = false;
                        break;
                    }
                }


                // while (!solved) {

                String x = pickVariable(unlabelled);

                //Pick a variable


                //Pick a value


                //Assign value and forward check if (forwardCheck(variable, assignment, state)) {assign, backtrack }

                //Else if exclude(variable, assignment, state)) {assign, backtrack}

                //FOr the Constriants - FIND WHICH INDEX IS IN LABELLED

//Removed && valid check. Will introduce a new class called arc Consistency which checks all the other variables.
                if (forwardChecking(x, state)) {

                    //NOW ASSIGN THE VARIABLE?

                    State result = backtrack(state);
                    if (result != null) {
                        solved = true;
                        System.out.println("Solved");
                        return result;
                    }
                } else if (state.exclude(x, labelled.get(x))) {

                    State result = backtrack(state);
                    if (result != null) {
                        solved = true;
                        System.out.println("Solved");
                        return result;
                    }
                }

                // }
           /* else {
                System.out.println("Solved");

            }*/
            }

        }
        System.out.println("Not Solved");
        return null;
    }



/*public State subroutine(){


}*/

    public String pickVariable(HashMap<String, LinkedList<Integer>> unlabelled) {

         List<String> keysAsArray = new ArrayList<String>(unlabelled.keySet());
        Random r = new Random();
        String i = keysAsArray.get(r.nextInt(keysAsArray.size()));
        ArrayList<String> keys = new ArrayList<String>(unlabelled.keySet());

//        while (unlabelled.get(i).size() <= 1) {
//
//
//            r = new Random();
//            i = keysAsArray.get(r.nextInt(keysAsArray.size()));
//            if (unlabelled.get(i).size() > 1) {
//                break;
//            }
//
//        }

        if (unlabelled.get(i).size() >= 1) {
            LinkedList<Integer> varDomain = unlabelled.get(i);


            Random r2 = new Random();
            Integer integer = varDomain.get(r2.nextInt(varDomain.size()));
            LinkedList<Integer> integers = new LinkedList<Integer>();
            unlabelled.remove(i);

            LinkedList<Integer> label = new LinkedList<>();
            label.add(integer);

            labelled.put(i, label);

        }

        return i;
    }

    public Boolean forwardChecking(String i, State state) {


        for (Constraint c : constraints) {
            if (c.index2.equals(i) || c.index1.equals(i)) {

                String index = c.index2;
                if (c.index1.equals(i)){
                    index = c.index1;
                }


                //index = index1 || index = index2
                //set add (i1,i2) if set does not contain i1,i2 or i2,i1


                //NOTE NEED TO CHANGE THIS SO IT ONLY COMPARES THE UPDATED VARIABLE TO THE UNLABELLED LISTS AS FORWARD CHECKING DOES
                //NOT REQUIRE CHECKING BACKWARDS (see slides)
//                if (!c.Constrain(state.labelled.get(i), state.unlabelled.get(c.index1))) {
//                    return false;
//                }

//
                if (!c.Constrain(state.labelled.get(i).getFirst(), state.unlabelled.get(c.index2))) {
                    return false;
                }
            }
            else {
                System.out.println("it didnt run");
            }
        }
        return true;
    }

    static Boolean validCheck(State state) {


        if (!checkConstraints(state)) {

            state.validState = false;
            return false;
        }

        state.validState = true;
        return true;
    }



   /* public Boolean testConstraints(LinkedHashSet labelled, VariableWithDomain.Index x, Integer v){


}*/

}