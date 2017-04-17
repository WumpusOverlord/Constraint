package constraints;

import constraints.Constraint;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.*;

/**
 * Created by hugo on 3/16/17.
 */
public class InequalityConstraint extends Constraint {

    public InequalityConstraint(String index1, String index2) {
        super(index1, index2);
    }

    public Boolean Constrain(State state, String variable1, String variable2) {

        if (state.labelled.containsKey(variable1) && state.labelled.containsKey(variable2)) {
            return !Objects.equals(state.labelled.get(variable1), state.labelled.get(variable2));
        }

        if (state.labelled.containsKey(variable1)) {

//
            if (!state.unlabelled.get(variable2).contains(state.labelled.get(variable1))){

                return true;
            }

            else if (state.unlabelled.get(variable2).size()==1 || !state.unlabelled.get(variable2).remove(state.labelled.get(variable1))){

                return false;
            }

        }
        else if (state.labelled.containsKey(variable2)) {

            if (!state.unlabelled.get(variable1).contains(state.labelled.get(variable2))){

                return true;
            }

            else if (state.unlabelled.get(variable1).size()==1 || !state.unlabelled.get(variable1).remove(state.labelled.get(variable2))){

                return false;
            }


        }



//        if (state.labelled.containsKey(variable2)) {
//
//            if (state.labelled.containsKey(variable1) && state.labelled.get(variable2) != state.labelled.get(variable1)) {
//
//                if (!state.labelled.get(variable1).remove(state.labelled.get(variable2).getFirst())) {
//                    return false;
//                } else {
//                    return true;
//                }
//
//            }
//        }


        return true;
    }



//    public Boolean Constrain(Integer value1, Integer value2) {
//
//      if (value1 == value2){
//          return false;
//        }
//        else {
//          return true;
//      }
//
//    }

    public Boolean Constrain(Integer assignedValue, LinkedList unassignedDomain){

        if (unassignedDomain.contains(assignedValue) && !unassignedDomain.remove(assignedValue)){
            return false;
        }
        return true;
    }


}
     /*   if (variable1.size() == 1 && variable2.size() == 1){

            int x = variable1.getFirst();
            int y =variable2.getFirst();

            if  (x==y){
                return false;
            }
        }


        if (variable1.size() == 1 && variable2.size() > 1) {

            if (variable1 != variable2) {

                if  (!variable2.remove(variable1.getFirst())){
                    return false;
                }
            }
        }
        else if (variable2.size() == 1 && variable1.size() > 1) {

            if (variable1 != variable2) {

                if  (!variable1.remove(variable2.getFirst())){
                    return false;
                }
            }
        }
        return true;*/

   /* public Boolean Constrain(State state, VariableWithDomain updatedVariable) {



        if (updatedVariable.getValues().size() == 1) {

            for (Map.Entry<VariableWithDomain.Index, VariableWithDomain> entry : state.getStateMap().entrySet()) {
                VariableWithDomain.Index key = entry.getKey();
                VariableWithDomain value = entry.getValue();

                //change to if key is different
                if (value != updatedVariable) {

                    if (!state.exclude(value, (updatedVariable.getValues().getFirst()))) {

                        return false;
                    }

                }
                    }


            }
        return true;
    }*/


