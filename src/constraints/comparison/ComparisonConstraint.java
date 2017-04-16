package constraints.comparison;

import constraints.Constraint;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.LinkedList;

/**
 * Created by hugo on 3/16/17.
 */
public abstract class ComparisonConstraint extends Constraint {

    public ComparisonConstraint(String index1, String index2) {
        super(index1, index2);
    }

    public Boolean Constrain(State state, String smallerVariable, String greaterVariable) {



        if (smallerVariable != greaterVariable) {

           int boundary = getBoundaryValue(state.getVariable(greaterVariable));


            for (Integer i: (state.getVariable(smallerVariable))) {

                if (outsideBoundary(i, boundary) && !state.getVariable(smallerVariable).remove(i)) {
                    return false;
                }
            }
        }

        return true;
    }

public abstract int getBoundaryValue(LinkedList<Integer> variable);

    public abstract Boolean outsideBoundary(Integer integerToCheck, int boundaryValue);
}


