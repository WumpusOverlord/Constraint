package search;

import constraints.Constraint;
import dataStructure.State;

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


                //Pick a variable
                String variable = pickVariable(unlabelled);

                //Pick a value
                Integer value = selectValueToAssign(variable, unlabelled);

                //Assign value

                //  LinkedList variableDomain = getDomain(x);

       ;

//BranchFCLeft(unlabelled, variable, value);


                // forward check if (forwardCheck(variable, assignment, state)) {assign, backtrack }

                //Else unassign value
                // if exclude(variable, assignment, state)) {assign, backtrack}

                //FOr the Constriants - FIND WHICH INDEX IS IN LABELLED

//Removed && valid check. Will introduce a new class called arc Consistency which checks all the other variables.
                LinkedList variableDomain = assignValue(state, variable, value);

                if (reviseFutureArcs(variable, state)) {

                    //NOW ASSIGN THE VARIABLE?

                    State result = backtrack(state);
                    if (result != null) {
                        solved = true;
                        System.out.println("Solved");
                        return result;
                    }
                } else {

                   if  (excludeValue(state, variable, variableDomain) && reviseFutureArcs(variable, state)){

                       State result = backtrack(state);
                       if (result != null) {
                           solved = true;
                           System.out.println("Solved");
                           return result;
                       }


                   }

                   restoreValue(state, variable, variableDomain, value);

                }

                System.out.println("UNDOING");
            }

        }
        System.out.println("Not Solved");
        return null;
    }


//   public void branchFCLeft(State state, LinkedList unlabelled, String variable, Integer value){
//
//        assignValue(state, variable, value);
//
//        if (reviseFutureArcs(state, unlabelled, variable)){
//
//       }
//
//
//    }
//
//
//    public void reviseFutureArcs(State state, LinkedList<String> varList, String variable){
//
//Boolean consistent = true;
//
//        for (String futureVariable : varList){
//consistent = revise(Constraint(futureVar, var));
//
//
//}
//
//    }
//
//    public void arc(State state, String futureVar, String var){
//
//
//
//    }
//    public void assignValue(State state, String variable, Integer value){
//state.
//
//    }
/*public State subroutine(){


}*/

    public LinkedList<Integer> getDomain(String x){

        return unlabelled.get(x);

    }


    public LinkedList<Integer> assignValue(State state, String variableIndex, Integer valueToAssign){

        //Inputs = State state, String variableIndex, Integer valueToAssign

        //get Domain and
        LinkedList<Integer> domain = state.unlabelled.get(variableIndex);

        //remove value to Assign
        domain.remove(valueToAssign);

        //remove variable from State - unlabelled
        state.unlabelled.remove(variableIndex);


        //Add variable To Labelled with the value To Assign
        state.labelled.put(variableIndex, valueToAssign);


        return domain;

    }

    //LinkedList variableDomain
    public Boolean excludeValue(State state, String variableIndex, LinkedList variableDomain){



        if (variableDomain.size()<=1){
            return false;
        }
        state.labelled.remove(variableIndex);


        //Add variable To Labelled with the value To Assign
        state.unlabelled.put(variableIndex, variableDomain);
        return true;

    }

    public Boolean restoreValue(State state, String variableIndex, LinkedList variableDomain, Integer value){


        state.labelled.remove(variableIndex);

        variableDomain.add(value);
        state.unlabelled.put(variableIndex, variableDomain);
        return true;

    }






    public String pickVariable(HashMap<String, LinkedList<Integer>> unlabelled) {

        List<String> keysAsArray = new ArrayList<String>(unlabelled.keySet());
        Random r = new Random();
        String variableToAssign = keysAsArray.get(r.nextInt(keysAsArray.size()));

        return variableToAssign;
    }


    public Integer selectValueToAssign(String variableToAssign, HashMap<String, LinkedList<Integer>> unlabelled) {

        Integer valueToAssign = null;
        if (unlabelled.get(variableToAssign).size() >= 1) {
            LinkedList<Integer> varDomain = unlabelled.get(variableToAssign);
            Random r2 = new Random();
            valueToAssign = varDomain.get(r2.nextInt(varDomain.size()));

        }
        return valueToAssign;
    }

    public Boolean reviseFutureArcs(String variable, State state) {

//ONLY CONSTRAINTS WHICH ARE REFERENCED IN UNASSIGNED VARIABLES?


        for (Constraint c : constraints) {

            if (c.index2.equals(variable) || c.index1.equals(variable)) {
                if (!c.revise(state)) {
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