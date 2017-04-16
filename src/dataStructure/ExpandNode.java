package dataStructure;

import dataStructure.Node;
import dataStructure.State;
import dataStructure.VariableWithDomain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * For expanding a node
 * Created by hjeff on 12/21/2016.
 */

//https://en.wikipedia.org/wiki/Branch_and_bound

//note need a method in each node to check number of candidate solutions
//aka search them and add to the list
public interface ExpandNode {


    static List<Node> expandNode(Node parentNode) {
        List<Node> successors = new ArrayList<>();
      //  LinkedList<Integer> variable = parentNode.getState();
        String i = parentNode.getState().pickAVariable();
        State newState1 = new State(parentNode.getState());
        LinkedList<Integer> variable = parentNode.getState().getState().get(i);
 //       dataStructure.State newState1 = (dataStructure.State) deepClone(parentNode.getState());
        State newState2 = new State(parentNode.getState());

       // dataStructure.State newState1 = new dataStructure.State(parentNode.getState().getConstraints());
       // dataStructure.State newState2 = new dataStructure.State(array2,  parentNode.getState().getConstraints());

//copy dataStructure.VariableWithDomain
        LinkedList<Integer> v1 = variable;//.copy(); //change this so variable only once
        Integer valueConstrain = pickValueFromDomain(v1);

       // VariableWithDomain v2 = variable.copy();



        // newState1.constrainTo(i, valueConstrain);
        //newState2.exclude(i, valueConstrain);

        //CHECK IF BOTH VALID OPTIONS
        if (validCheck(newState1)) { //THIS NEEDS TO CHECK EACH CONSTRAINT IS VALUD
        //   System.out.println("State1 is valid");

            Node newNode1 = new Node(parentNode, parentNode.getPathCost() + 1, newState1);
            successors.add(newNode1);

            //call the search function???
        }
        else {
            System.out.println("dataStructure.State 1 invalid");
           // int x =0;
        }
        if (validCheck(newState2)) {
          //  System.out.println("State2 is valid");
            Node newNode2 = new Node(parentNode, parentNode.getPathCost() + 1, newState2);
            successors.add(newNode2);

            //call search function?
        }
        else {
           System.out.println("dataStructure.State 2 invalud");
          //  int x =0;
        }
        //   }
     //   System.out.println("Returning succesors");
        return successors;
    }

    static Integer pickValueFromDomain(LinkedList<Integer> variableDomain){


        Random rand = new Random();

        Integer a =variableDomain.size();
        int b =a;
        int  n = rand.nextInt(variableDomain.size()) + 0;
        // System.out.println("Val of n is" + n);

        //System.out.println("Value in domain is" + v.getValues().get(n));

        return variableDomain.get(n);

        //  return domain[0];
        //return this.domain;
    }


    static boolean checkConstraints(State state) {

        System.out.println("Constraints number: " + state.getConstraints().size());

        for (int i = 0; i < state.getConstraints().size(); i++) {
            //  if (currentConstraint != i) {
            if (!state.getConstraints().get(i).applyConstraint(state)) {
                return false;
            }
            // }
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


    //  static int[] pickValueFromDomain (dataStructure.VariableWithDomain variable){
//return variable.pickAValue();

    //}


}