package constraints;

import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by hj22 on 15/03/17.
 */
public abstract class Constraint {
    //List<dataStructure.VariableWithDomain> variables;

    // Set<dataStructure.VariableWithDomain.Index> variablesIndexes;
    public String index1;
    public String index2;

    /* public Boolean applyConstraint(dataStructure.State state) {

         for (dataStructure.VariableWithDomain.Index i: variablesIndexes){
 //Get variable by index state.getVariable(i)
            dataStructure.VariableWithDomain v = state.getVariable(i);

         }

             for (dataStructure.VariableWithDomain.Index index: variablesIndexes){
             dataStructure.VariableWithDomain v = state.getVariable(index);
             if (!Constrain(state, v))
                 return false;
         }
         return true;
     }
 */
    public Boolean applyConstraint(State state) {


        // LinkedList<Integer> v1 = state.getVariable(index1);

        //LinkedList<Integer> v2 = state.getVariable(index2);


        return Constrain(state, index1, index2);
    }

    public Constraint(String index1, String index2) {

        this.index1 = index1;
        this.index2 = index2;

    }

    //TO-DO: change state so it is a list of variables. Then it will be easier to do arc consistancy
    public Boolean Constrain(State state, String updatedVariable) {

        //Only check undefined variables
System.out.println("Constrain ran");
        if (this.index1.equals(updatedVariable) || this.index2.equals(updatedVariable)) {

            for (String index : state.unlabelled.keySet()) {

                if (!index.equals(updatedVariable)) {

                    if (!Constrain(state, index, updatedVariable)){
                        return false;
                    }

                }


            }


        }

return true;
    }



    //  public abstract Boolean Constrain(dataStructure.State state, dataStructure.VariableWithDomain updatedVariable);

//    public Boolean Constrain(String variable1, String variable2) {
//        return null;
//    }


    public abstract Boolean Constrain(Integer assignedVariable, LinkedList UnassignedDomain);

    public abstract Boolean Constrain(State state, String variable1, String variable2);
}
