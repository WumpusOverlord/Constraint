package dataStructure;

import constraints.Constraint;

import java.util.*;
import java.util.List;



public class State {

    public HashMap<String, Integer> labelled;
    public HashMap<String, LinkedList<Integer>> unlabelled;
    public HashMap<String, LinkedList<Integer>> domain;
   // LinkedList<Constraint> constraints = new LinkedList<Constraint>();

    public LinkedList<Constraint> constraints;

  int currentConstraint=-1;
public Boolean validState;
HashMap<String, LinkedList<Integer>> stateMap;// = new HashMap<>();


    //NOTE THIS DOES NOT COPY THEM
    public State(HashMap<String, LinkedList<Integer>> state, LinkedList<Constraint> constraints) {

        labelled = new HashMap <String, Integer>();
        unlabelled = new HashMap <String, LinkedList<Integer>>();
        domain = new HashMap <String, LinkedList<Integer>>();



        this.unlabelled = new HashMap<>(state);
        this.domain = new HashMap<>(state);
        this.stateMap = state;
        this.constraints = constraints;

    }

    public State(State state) {

        labelled = new HashMap <String, Integer>();
        unlabelled = new HashMap <String, LinkedList<Integer>>();
        domain = new HashMap <String, LinkedList<Integer>>();


        this.labelled = new HashMap<>(state.labelled);
        this.unlabelled = new HashMap<>(state.unlabelled);
        this.domain = new HashMap<>(state.domain);
       // this.stateMap = state;
        this.constraints = state.constraints;
this.stateMap = domain;


    /*   stateMap = new HashMap<>();

        for (Map.Entry<String, LinkedList<Integer>> entry : state.stateMap.entrySet()) {
            String key = entry.getKey();
            LinkedList<Integer> value = DeepClone.copy(entry.getValue());//.copy();
            stateMap.put(key, value);

        }

        this.constraints=state.constraints;*/


    }


    public LinkedList<Integer> getVariable(int row, int col) {

        VariableWithDomain.Index i = new VariableWithDomain.Index(row, col);

        for (int a = 0; a < stateMap.size(); a++) {


            LinkedList<Integer> variable = stateMap.get(i);
                return variable;
        }
        return null;
    }

    public LinkedList<Integer> getVariable(String index) {

return stateMap.get(index);
    }

    public HashMap<String, LinkedList<Integer>>  getState() {
        return this.stateMap ;
    }

    public void setState(HashMap<String, LinkedList<Integer>> stateMap) {
        this.stateMap = stateMap;
    }

    public LinkedList<Constraint> getConstraints() {

        return constraints;
    }



    public Boolean constrainTo(String index, LinkedList<Integer> value) {

       // VariableWithDomain.Index i = this.getState().getValue(variable);

return setValue(index, value);

    }



    public Boolean setValue(String i, LinkedList<Integer> value) {
        //  Integer value = new Integer(val);
        LinkedList<Integer> values = new LinkedList<Integer>();
        values = this.getState().get(i);

        if (values.contains(value.getFirst())) {

            values.clear();
            values.add(value.getFirst());
            return true;
        }
System.out.println("error");
        return false;
    }

//    public Boolean exclude(String index, Integer valueToExclude) {
//
//       LinkedList<Integer> numbers = new LinkedList<Integer>(this.domain.get(index));
////numbers = this.labelled.get(index);
//       // LinkedList<Integer> numbers2 = new LinkedList<>(this. unlabelled.get(index));
//
////        for (Integer i: valueToExclude){
////            numbers.remove(i);
////        }
//
//        if (numbers.contains(valueToExclude)){
//            this.unlabelled.get(index).remove(valueToExclude);
//        }
//
//
//
//      //  this.labelled.get(index).clear();
//       // this.labelled.get(index).addAll(numbers);
//
//       //this.unlabelled.replace(index, domain.get(index));//=numbers2;
//      //  this.labelled.replace(index, numbers2);
//
//     /*  this.unlabelled.get(index);
//
//
//        this.unlabelled.get(index);
//
//        if (this.unlabelled.get(index).size()==1){
//             return false;
//        }
//        else {
////VariableWithDomain.Index i = variable.getIndex();
//LinkedList<Integer> v = this.unlabelled.get(index);
//v.remove(valueToExclude);
//
////variable.getValues().remove(valueToExclude);
//
////System.out.println(variable.getValues().size());
//            System.out.println();
//
//        }*/
//
//        return true;
//    }

public HashMap<String, LinkedList<Integer>> getStateMap(){

        return this.stateMap;
}


    public String pickAVariable() {

    int x = 0;
        List<String> keysAsArray = new ArrayList<String>(stateMap.keySet());
    while (x==0){


      //  List<VariableWithDomain.Index> valuesAsArray = new ArrayList<VariableWithDomain.Index>(stateMap.valueSet());

        Random r = new Random();
      //  x = keysAsArray.get(r.nextInt(keysAsArray.size()));
        String i = keysAsArray.get(r.nextInt(keysAsArray.size()));

       if (stateMap.get(i).size()>1){
          // return stateMap.get(keysAsArray.get(r.nextInt(keysAsArray.size())));

           return i;
       }

      //  return stateMap.get(keysAsArray.get(r.nextInt(keysAsArray.size())));

    }

//return false;

        return null;
    }
}