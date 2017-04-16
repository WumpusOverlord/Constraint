package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by hj22 on 15/03/17.
 */
public class VariableWithDomain {

    private final Index index;
    private LinkedList<Integer> domain;

    //private LinkedList<constraints.Constraint> constraints = new LinkedList<>();


    VariableWithDomain(Index index, LinkedList<Integer> newDomain) {
        this.index = index;
        this.domain = newDomain;

    }

    public VariableWithDomain(int row, int col, LinkedList<Integer> newDomain) {
        this.index = new Index(row, col);
this.domain = newDomain;


    }

    public VariableWithDomain(int row, int col) {
        this.index = new Index(row, col);
domain = new LinkedList<>();


    }

    public void addToDomain(Integer value) {
     // int a = value.intValue();
     //Integer newValue = a;
       domain.add(value);
    }

    public static class Index {
        private int row;
        private int col;

        public Index(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow(){
            return this.row;
        }

        public int getCol(){
            return this.col;
        }

        public String toString() {
            return "[" + row + "," + col + "]";
        }
    }
public int getRow(){
    return index.row;
}

    public int getCol(){
        return index.col;
    }

    public Boolean setDomain(List<Integer> newDomain){

        System.arraycopy( newDomain, 0, domain, 0, newDomain.size() );
        return true;
    }

    public Boolean setValue(Integer value){
      //  Integer value = new Integer(val);
        if (this.domain.contains(value)){

     this.domain.clear();
     this.domain.add(value);
     return true;
 }

 System.out.println("THIS SHOULD NOT BE RUNNING");
        return false;
    }

    public Boolean removeValue(Integer value){
        //  Integer value = new Integer(val);

if (!this.domain.contains(value)){
    return true;
}

        if (this.domain.size()>1){

            this.domain.remove(value);

            return true;
        }

        return false;
    }

    public Index getIndex(){
        return this.index;
    }

    public LinkedList<Integer> getValues(){

        return this.domain;
    }




    public Integer pickValueFromDomain(LinkedList <Integer> variableDomain){


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


   /* public LinkedList<constraints.Constraint> getConstraints(){
        return this.constraints;
    }
*/

   /* public dataStructure.VariableWithDomain(dataStructure.VariableWithDomain[] state, LinkedList<constraints.Constraint> constraints) {

        this.state = new dataStructure.VariableWithDomain[state.length];

        this.constraints = constraints;
        System.arraycopy(state, 0, this.state, 0, state.length);

    }
*/

//    public Boolean checkNumberOfDomains(dataStructure.State state) {
//
//        for (int i = 1; i < state.getConstraints().length; i++) {
//currentConstraint = i;
//            for (int b = 1; b < state.length; b++) {
//
//                state.getVariable(i).checkConstraints(state);
//            }
//        }
//        return true;
//    }

    /*public constraints.Constraint getConstraints(){

    }*/

//    public boolean checkConstraints(dataStructure.State state) {
//
//        for (int i = 1; i < state.getConstraints().length; i++) {
//if (currentConstraint!=i) {
//    if (!state.getConstraints(i).applyConstraint()) {
//        return false;
//    }
//}
//        }
//return true;
//    }


}
