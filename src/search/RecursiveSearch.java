/*

*/
/**
 * This class implements a Backtracking search to find
 * a solution to a CSP Problem.
 *//*

public class RecursiveSearch {

  //Have a recursive search
    //This takes the state and assignments as inputs
    //if solution recursiveSOlve returns solution
    //if not solution it takes the two branches
    //it then makes inferences? and checks consistancy for each
    //it then runs a new recursiveSolve


    //take unassigned variable
    //FOr all values: assign that value

    */
/**
     * Starts the Backtracker given a blank assignment.
     *//*

    public RecursiveSearch(CSPProblem prob) {
        this(prob, Assignment.blank());
    }

    */
/**
     * Starts the Backtracker given a problem and a specific
     * assignment.
     *//*

    public RecursiveSearch(State state, Assignment initial) {
        State = state;
        start = initial;
    }

    */
/**
     * Solves the problem.
     * @return An assignment that satisfies all constraints or null
     * if none is found.
     *//*

    public Assignment solve() {
        // Start solving from the initial state
        return recursiveSolve(start);
    }


    private Assignment recursiveSolve(Assignment assign) {
        if (State.satisfiedByAssignment(assign)) {
            return assign;
        }

        // Get an unassigned variable
        Variable v = unassignedVar(assign);
        if (v == null) return null;

        // Get the domain values for a variable
        List<Object> values = state.domainValues(assign, v);
        values = orderValues(assign, values, v);

        for (Object value : values) {
            // Make a new assignment
            Assignment newAssign = assign.assign(v, value);

            // Try making some inferences
            try {
                newAssign = state.inference(newAssign, v);
            } catch (IllegalStateException e) {
                continue;
            }

            // Check the consistency
            if (!state.consistentAssignment(newAssign, v)) {
                continue;
            }

            // Recurse
            newAssign = recursiveSolve(newAssign);
            if (newAssign != null) return newAssign;
        }

        // Failed
        return null;
    }

    */
/**
     * Returns an assigned variable to try to assign.
     * This can be sub-classed to add heuristics.
     *//*

    protected Variable unassignedVar(Assignment assign) {
        // Find any non-assigned variable
        for (Variable v : problem.variables()) {
            if (assign.getValue(v) == null) return v;
        }
        return null;
    }

    */
/**
     * Returns an ordered list of values to try to assign.
     * May be sub-classed to add heuristics.
     *//*

    protected List<Object> orderValues(Assignment asign, List<Object> domain, Variable v) {
        return domain;
    }
}

*/
/* Optional<Node> solution = new BreadthFirst(eNode.getState().getState(), eNode.getState().getConstraints()).search();

                            if (solution!=null){
                        System.out.println("SOLUTIONFOUND");
                        return solution;
                            }*/
