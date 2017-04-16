package constraints;

import constraints.Constraint;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.*;

/**
 * Created by hugo on 3/16/17.
 */
public interface AllDiff {


    static LinkedList<Constraint> AllDiff(HashMap<String, LinkedList<Integer>> variables) {

        HashSet<String> constraintSet = new HashSet<>();
        LinkedList<Constraint> constraintList = new LinkedList<>();
        //  for (LinkedList<Integer> v2 : variables.values()) {

        //    for (LinkedList<Integer> v1 : variables.values()) {


        for (String entry : variables.keySet()) {

           // LinkedList<Integer> v1 = variables.get(entry);
            for (String entry2 : variables.keySet()) {

              //  LinkedList<Integer> v2 = variables.get(entry2);

                if (entry != entry2) {

                    //    String string1 = v1.getIndex().toString()+v2.getIndex().toString();
                    // String string2 = v2.getIndex().toString() + v1.getIndex().toString();

                    //   if (constraintSet.add(string1) && constraintSet.add(string2)){
                    Constraint c = new InequalityConstraint(entry, entry2);
                    constraintList.add(c);

                }

            }

        }
            return constraintList;


    }
    }

