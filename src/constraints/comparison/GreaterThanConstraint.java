package constraints.comparison;

import dataStructure.VariableWithDomain;

import java.util.LinkedList;

/**
 * Created by hugo on 3/16/17.
 */
public class GreaterThanConstraint extends ComparisonConstraint {


    public GreaterThanConstraint(String index1, String index2) {
        super(index1, index2);
    }

    @Override
    public int getBoundaryValue(LinkedList<Integer> variable) {
        int max = variable.get(0);
        for (int i : variable){
            max = max > i ? max : i;
        }
        return max;
    }

    @Override
    public Boolean outsideBoundary(Integer integerToCheck, int boundaryValue) {

        return integerToCheck <= boundaryValue;
    }


}


