package constraints.comparison;

import dataStructure.VariableWithDomain;

import java.util.LinkedList;

/**
 * Created by hugo on 3/16/17.
 */
public class LessThanConstraint extends ComparisonConstraint {

    public LessThanConstraint(String index1, String index2) {
        super(index1, index2);
    }



    @Override
    public int getBoundaryValue(LinkedList<Integer> variable) {
        int min = variable.get(0);
        for (int i : variable){
            min = min < i ? min : i;
        }
        return min;
    }

    @Override
    public Boolean outsideBoundary(Integer integerToCheck, int boundaryValue) {

        return integerToCheck >= boundaryValue;
    }


}


