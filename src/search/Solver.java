/*
import java.util.*;

public class Solver {
    private static class Index {
        int row;
        int col;

        public Index(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "[" + row + "," + col + "]";
        }
    }


    //The inference class
    private static class Inference extends Index {
        int exclusion;

        public Inference(int row, int col, int exclusion) {
            super(row, col);
            this.exclusion = exclusion;
        }

        public boolean equals(Object otherObj) {
            Inference other = (Inference) otherObj;
            return (this.row == other.row && this.col == other.col && this.exclusion == other.exclusion);
        }

        public String toString() {
            return super.toString() + "-" + exclusion;
        }
    }

    static int numSteps = 0;
    static double start = 0.0;
    static double thinkingTime = 0.0;
    static double doingTime = 0.0;
    static enum Algorithm { DFS, FORWARD_CHECKING, ARC_CONSISTENCY, ALLDIFF };
    static Algorithm algorithm = Algorithm.FORWARD_CHECKING; // Performs best on average

    public static void main(String[] args) {
        int[][] easyPuzzle = {
                { 0, 3, 0, 1, 8, 0, 0, 0, 0 },
                { 0, 9, 6, 0, 3, 0, 0, 0, 0 },
                { 0, 8, 0, 7, 0, 6, 3, 0, 0 },
                { 0, 1, 9, 0, 0, 3, 2, 0, 0 },
                { 0, 0, 7, 0, 0, 0, 8, 0, 0 },
                { 0, 0, 3, 5, 0, 0, 6, 1, 0 },
                { 0, 0, 4, 2, 0, 8, 0, 5, 0 },
                { 0, 0, 0, 0, 6, 0, 1, 2, 0 },
                { 0, 0, 0, 0, 4, 7, 0, 6, 0 }
        };

        int[][] mediumPuzzle = {
                { 0, 2, 0, 8, 0, 0, 0, 0, 0 },
                { 6, 9, 7, 0, 0, 5, 4, 0, 0 },
                { 0, 0, 8, 4, 0, 0, 0, 9, 0 },
                { 1, 0, 0, 7, 0, 8, 5, 0, 0 },
                { 0, 3, 0, 0, 0, 0, 0, 8, 0 },
                { 0, 0, 2, 3, 0, 9, 0, 0, 4 },
                { 0, 8, 0, 0, 0, 6, 1, 0, 0 },
                { 0, 0, 3, 1, 0, 0, 8, 7, 5 },
                { 0, 0, 0, 0, 0, 3, 0, 4, 0 }
        };

        int[][] hardPuzzle = {
                { 7, 0, 0, 9, 0, 4, 6, 0, 0 },
                { 9, 0, 8, 0, 1, 0, 0, 0, 3 },
                { 4, 2, 0, 0, 0, 5, 0, 0, 0 },
                { 0, 0, 7, 0, 0, 8, 2, 6, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 6, 9, 5, 0, 0, 8, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 2, 6 },
                { 1, 0, 0, 0, 6, 0, 5, 0, 4 },
                { 0, 0, 5, 8, 0, 2, 0, 0, 7 }
        };

        int[][] veryHardPuzzle = {
                { 9, 0, 0, 0, 0, 0, 0, 5, 0 },
                { 2, 0, 0, 7, 0, 6, 0, 3, 0 },
                { 0, 4, 0, 0, 2, 5, 0, 0, 6 },
                { 0, 8, 9, 0, 0, 0, 5, 0, 2 },
                { 7, 0, 0, 0, 0, 0, 0, 0, 8 },
                { 5, 0, 1, 0, 0, 0, 3, 4, 0 },
                { 3, 0, 0, 6, 5, 0, 0, 2, 0 },
                { 0, 9, 0, 4, 0, 3, 0, 0, 5 },
                { 0, 1, 0, 0, 0, 0, 0, 0, 3 }
        };

        int[][] emptyPuzzle = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        SudokuPuzzle puzzle = new SudokuPuzzle(veryHardPuzzle);
        SudokuPuzzle solution = backtrackingSearch(puzzle);

        if (solution == null) {
            System.out.println("No solution");
        } else {
            solution.print();
        }

        System.out.println();
        System.out.println("Steps:\t\t" + numSteps);
        System.out.println("Doing:\t\t" + (doingTime / 1000) + "s");
        System.out.println("Thinking:\t" + (thinkingTime / 1000) + "s");
        System.out.println();
    }

    public static void startThinking() {
        start = System.currentTimeMillis();
    }

    public static void startDoing() {
        startThinking();
    }

    public static void stopThinking() {
        if (start != 0) {
            double stop = System.currentTimeMillis();
            thinkingTime += (stop - start);
            start = 0;
        }
    }

    public static void stopDoing() {
        if (start != 0) {
            double stop = System.currentTimeMillis();
            doingTime += (stop - start);
            start = 0;
        }
    }

    public static SudokuPuzzle backtrackingSearch(SudokuPuzzle puzzle) {
        startDoing();
        SudokuPuzzle solution = backtrack(puzzle);
        stopDoing();

        return solution;
    }


    //This is the main search class

    public static SudokuPuzzle backtrack(SudokuPuzzle puzzle) {
        if (isFilledIn(puzzle)) {
            return puzzle;
        }
//Selects an unassigned index
        //Gets the domains of that index
        Index unassigned = selectUnAssignedVariable(puzzle);
        LinkedList<Integer> domainValues = orderDomainValues(unassigned, puzzle);


        //For each domain value
        for (int i = 0; i < domainValues.size(); i++) {

            //Initializes the linked list of inferences
            LinkedList<Inference> inferences = null;

            //Gets the value
            int value = domainValues.get(i);

            //sets the value in the puzzle to the domain

            //sets that position to a
            puzzle.set(unassigned.row, unassigned.col, value);

            numSteps++;

            //If that variable does not violate any constraints

            //sets the value to the unassigned row and column
            if (isGoodSoFar(puzzle)) {

                //Stop doing Gets resets time
                stopDoing();

                //gets current time
                startThinking();

                //gets the checking method
                //The puzzle is the new puzzle with the new value added
                //unassigned is the index of the variable that was just assigned with a value from its domain
                //the value is the value that variable was just assigned
                inferences = getInferences(puzzle, unassigned, value);

                //stops the timer
                stopThinking();

                //gets the current time
                startDoing();

                //if there are some inferences (any of the squares can only have that possible value) continue
                if (inferences == null) {
                    continue;
                }


                stopDoing();
                startThinking();

                //applies the inferences to the puzzle (the inferences are what you can exclude)
                //excludes them from the domain
                applyInferences(puzzle, inferences);
                stopThinking();
                startDoing();

//now to check if it is valid without the constrain MY CODE BELOW
                //puzzleClone.unset(unassigned.row, unassigned.col);

//set domain to anything without the value
                //run backtrack?



                //recursive - for another square, it checks the domains and sees if you can exclude anything
                //
                SudokuPuzzle result = backtrack(puzzle);

                //if you can apply all the other inferences than result is the final puzzle
                if (result != null) {
                    return result;
                }
            }

            //if there are no more inferences possible or violates constraint it undoes the last action
            //it sets the current variable (square it checked inferences for) to nil
            puzzle.unset(unassigned.row, unassigned.col);

           //it readds the excluded domains to the variable
            unapplyInferences(puzzle, inferences);
        }

        //of there was no possible results it returns null
        return null;
    }

    public static SudokuPuzzle backtrack2(SudokuPuzzle puzzle){

           //Expand node

        //Check both children are valid

//for children nodes 1 and 2(){

            if (isGoodSoFar(puzzle)) {

                SudokuPuzzle result = backtrack(puzzle);

                //if you can apply all the other inferences than result is the final puzzle
                if (result != null) {
                    return result;
                }
            }
        //}
//else {   //if not backtrack
                puzzle.unset(unassigned.row, unassigned.col);
                unapplyInferences(puzzle, inferences);
                return null;

           // }

    }

    public static void unapplyInferences(SudokuPuzzle puzzle, LinkedList<Inference> inferences) {
        if (inferences == null || inferences.size() == 0) {
            return;
        }

        for (Inference inference : inferences) {
            if (!puzzle.inDomain(inference.row, inference.col, inference.exclusion)) {
                puzzle.addToDomain(inference.row, inference.col, inference.exclusion);
            }
        }
    }

    public static void applyInferences(SudokuPuzzle puzzle, LinkedList<Inference> inferences) {
        if (inferences == null || inferences.size() == 0) {
            return;
        }

        for (Inference inference : inferences) {
            puzzle.removeFromDomain(inference.row, inference.col, inference.exclusion);
        }
    }
//removes the value
    //
    public static LinkedList<Inference> getInferences(SudokuPuzzle puzzle, Index assigned, int value) {
        LinkedList<Inference> inferences = new LinkedList<Inference>();

        if (algorithm == Algorithm.DFS) {
            return inferences;
        } else if (algorithm == Algorithm.FORWARD_CHECKING) {

            //gets the inferrences of the assigned variable
            return forwardCheckInferences(puzzle, assigned, value);
        } else if (algorithm == Algorithm.ARC_CONSISTENCY) {
            if (AC3(puzzle)) {
                return inferences;
            }

            return null;
        } else if (algorithm == Algorithm.ALLDIFF) {
            if (Alldiff(puzzle, inferences)) {
                return inferences;
            }

            return null;
        }

        return null;
    }

    public static void applyInference(SudokuPuzzle puzzle, SudokuPuzzle inference) {
        for (int r = 0; r < puzzle.puzzleSide; r++) {
            for (int c = 0; c < puzzle.puzzleSide; c++) {
                puzzle.set(r, c, inference.get(r, c));
                puzzle.setDomain(r, c, inference.getDomain(r, c));
            }
        }
    }

    public static void prepareQueue(SudokuPuzzle puzzle, dataStructure.PriorityQueue<Index[]> arcs) {
        for (int assignedRow = 0; assignedRow < puzzle.puzzleSide; assignedRow++) {
            for (int assignedCol = 0; assignedCol < puzzle.puzzleSide; assignedCol++) {
				*/
/* Queue squares in current unit *//*

                int startRow = (assignedRow / puzzle.unitSide) * puzzle.unitSide;
                int startCol = (assignedCol / puzzle.unitSide) * puzzle.unitSide;
                int endRow = startRow + puzzle.unitSide;
                int endCol = startCol + puzzle.unitSide;

                for (int row = startRow; row < endRow; row++) {
                    for (int col = startCol; col < endCol; col++) {
                        if (row != assignedRow && col != assignedCol) {
                            arcs.enqueue(new Index[] { new Index(row, col), new Index(assignedRow, assignedCol) });
                        }
                    }
                }

				*/
/* Queue squares in current row *//*

                for (int row = 0; row < puzzle.puzzleSide; row++) {
                    if (row != assignedRow) {
                        arcs.enqueue(new Index[] { new Index(row, assignedCol), new Index(assignedRow, assignedCol) });
                    }
                }

				*/
/* Queue squares in current column *//*

                for (int col = 0; col < puzzle.puzzleSide; col++) {
                    if (col != assignedCol) {
                        arcs.enqueue(new Index[] { new Index(assignedRow, col), new Index(assignedRow, assignedCol) });
                    }
                }
            }
        }
    }

    public static boolean Alldiff(SudokuPuzzle puzzle, LinkedList<Inference> inferences) {
        boolean alldiff = true;

        for (char row = 'A'; row <= 'I'; row++) {
            alldiff &= Alldiff(puzzle, inferences, row);
        }

        for (int col = 0; col < puzzle.puzzleSide; col++) {
            alldiff &= Alldiff(puzzle, inferences, col);
        }

        for (int row = 0; row < puzzle.puzzleSide; row += puzzle.unitSide) {
            for (int col = 0; col < puzzle.puzzleSide; col += puzzle.unitSide) {
                alldiff &= Alldiff(puzzle, inferences, row, col);
            }
        }

        return alldiff;
    }

    public static boolean AllDiff(SudokuPuzzle puzzle, LinkedList<Inference> inferences, LinkedList<Index> variables, LinkedList<LinkedList<Integer>> domains) {
        boolean areSingleton = true;
        LinkedList<Inference> tempInferences = new LinkedList<Inference>();

        while (areSingleton) {
            areSingleton = false;

            for (int i = 0; i < variables.size(); i++) {
                Index index = variables.get(i);

                if (index == null) {
                    continue;
                }

                int value = puzzle.get(index.row, index.col);

                if (puzzle.getDomainLength(index.row, index.col) != 1) {
                    continue;
                }

                int domainValue = domains.get(i).get(0);

                areSingleton = true;
                variables.set(i, null);

                for (int j = 0; j < variables.size(); j++) {
                    domains.get(j).remove((Integer) domainValue);
                    Index var = variables.get(j);

                    if (var == null) {
                        continue;
                    }

                    tempInferences.add(new Inference(var.row, var.col, domainValue));

                    if (domains.get(j).size() == 0) {
                        return false;
                    }
                }
            }

            int numVars = 0;
            int numDomainValues = 0;
            LinkedList<Integer> domain = new LinkedList<Integer>();

            for (int i = 0; i < variables.size(); i++) {
                if (variables.get(i) == null) {
                    continue;
                }

                numVars++;

                for (Integer j : domains.get(i)) {
                    if (!domain.contains(j)) {
                        domain.add(j);
                        numDomainValues++;
                    }
                }
            }

            if (numVars > numDomainValues) {
                return false;
            }
        }

        for (Inference inference : tempInferences) {
            inferences.add(inference);
        }

        return true;
    }

    public static boolean Alldiff(SudokuPuzzle puzzle, LinkedList<Inference> inferences, char rowChar) {
        int row = rowChar - 'A';
        LinkedList<Index> variables = new LinkedList<Index>();
        LinkedList<LinkedList<Integer>> domains = new LinkedList<LinkedList<Integer>>();

        for (int col = 0; col < puzzle.puzzleSide; col++) {
            variables.add(new Index(row, col));
            domains.add(puzzle.getDomainCopy(row, col));
        }

        return AllDiff(puzzle, inferences, variables, domains);
    }

    public static boolean Alldiff(SudokuPuzzle puzzle, LinkedList<Inference> inferences, int col) {
        LinkedList<Index> variables = new LinkedList<Index>();
        LinkedList<LinkedList<Integer>> domains = new LinkedList<LinkedList<Integer>>();

        for (int row = 0; row < puzzle.puzzleSide; row++) {
            variables.add(new Index(row, col));
            domains.add(puzzle.getDomainCopy(row, col));
        }

        return AllDiff(puzzle, inferences, variables, domains);
    }

    public static boolean Alldiff(SudokuPuzzle puzzle, LinkedList<Inference> inferences, int row, int col) {
        LinkedList<Index> variables = new LinkedList<Index>();
        LinkedList<LinkedList<Integer>> domains = new LinkedList<LinkedList<Integer>>();

        for (int r = row; r < row + puzzle.unitSide; r++) {
            for (int c = col; c < col + puzzle.unitSide; c++) {
                variables.add(new Index(r, c));
                domains.add(puzzle.getDomainCopy(r, c));
            }
        }

        return AllDiff(puzzle, inferences, variables, domains);
    }

    public static boolean AC3(SudokuPuzzle puzzle) {
        dataStructure.PriorityQueue<Index[]> arcs = new dataStructure.PriorityQueue<Index[]>();
        prepareQueue(puzzle, arcs);

        while (arcs.size() > 0) {
            Index[] edge = arcs.dequeue();

            if (!revise(puzzle, edge[0], edge[1])) {
                continue;
            }

            int assignedRow = edge[0].row;
            int assignedCol = edge[0].col;
            int otherRow = edge[1].row;
            int otherCol = edge[1].col;

            if (puzzle.getDomainLength(assignedRow, assignedCol) == 0) {
                return false;
            }

			*/
/* Queue squares in current unit *//*

            int startRow = (assignedRow / puzzle.unitSide) * puzzle.unitSide;
            int startCol = (assignedCol / puzzle.unitSide) * puzzle.unitSide;
            int endRow = startRow + puzzle.unitSide;
            int endCol = startCol + puzzle.unitSide;

            for (int row = startRow; row < endRow; row++) {
                for (int col = startCol; col < endCol; col++) {
                    if (row != assignedRow && col != assignedCol && row != otherRow && col != otherCol) {
                        arcs.enqueue(new Index[] { new Index(row, col), new Index(assignedRow, assignedCol) });
                    }
                }
            }

			*/
/* Queue squares in current row *//*

            for (int row = 0; row < puzzle.puzzleSide; row++) {
                if (row != assignedRow && row != otherRow) {
                    arcs.enqueue(new Index[] { new Index(row, assignedCol), new Index(assignedRow, assignedCol) });
                }
            }

			*/
/* Queue squares in current column *//*

            for (int col = 0; col < puzzle.puzzleSide; col++) {
                if (col != assignedCol && col != otherCol) {
                    arcs.enqueue(new Index[] { new Index(assignedRow, col), new Index(assignedRow, assignedCol) });
                }
            }
        }

        return true;
    }

    public static boolean revise(SudokuPuzzle puzzle, Index i, Index j) {
        boolean revised = false;

        LinkedList<Integer> domainI = puzzle.getDomainCopy(i.row, i.col);
        LinkedList<Integer> domainJ = puzzle.getDomain(j.row, j.col);

        for (Integer x : domainI) {
            boolean satisfiable = false;

            for (Integer y : domainJ) {
                if ((int) y != (int) x) {
                    satisfiable = true;
                }
            }

            if (!satisfiable) {
                puzzle.removeFromDomain(i.row, i.col, x);
                revised = true;
            }
        }

        return revised;
    }


    //for all the possible values in the domain for the assigned square, it adds the values as exclusions/inferences
    public static LinkedList<Inference> forwardCheckInferences(SudokuPuzzle puzzle, Index assigned, int value) {
        LinkedList<Inference> inferences = new LinkedList<Inference>();

		*/
/* Flush domain for current square *//*

		//i is looped in the domain (each square can be 1 to 9))
        for (int i = 1; i <= 9; i++) {
            if (i != value) {
                //adds the possible values for that square to the inferences
                //e.g. if domain is 1-9 and just assigned 9 inferences would contain 1-8
//the inferences are all the values apart from a
                addInference(puzzle, inferences, assigned.row, assigned.col, i);

            }
        }

        //For the square it gets all the exclusions = anything that isnt the value assigned :)

		*/
/* Flush domains in current unit *//*

        int startRow = (assigned.row / puzzle.unitSide) * puzzle.unitSide;
        int startCol = (assigned.col / puzzle.unitSide) * puzzle.unitSide;
        int endRow = startRow + puzzle.unitSide;
        int endCol = startCol + puzzle.unitSide;


        //Gets the square on the board
        for (int row = startRow; row < endRow; row++) {
            for (int col = startCol; col < endCol; col++) {
                if (row == assigned.row || col == assigned.col) {
                    continue;
                }

                //if add inferences is null it is not good
                if (!addInference(puzzle, inferences, row, col, value)) {
                    //if there is only one domain value in that square only has one possible value (which isn't the value
                    //that was just assigned then quit the loop.

                    //if that value is the only one possible in another square it returns null as it violates constraint
                    return null;
                }
            }
        }


        //If any other squares on the board do not have any possible inferences than it is bad
		*/
/* Flush domains in current row *//*

        for (int r = 0; r < puzzle.puzzleSide; r++) {
            if (r == assigned.row) {
                continue;
            }

            //for each square on that row, if there is only
            if (!addInference(puzzle, inferences, r, assigned.col, value)) {
                return null;
            }
        }

		*/
/* Flush domains in current column *//*

        for (int c = 0; c < puzzle.puzzleSide; c++) {
            if (c == assigned.col) {
                continue;
            }

            if (!addInference(puzzle, inferences, assigned.row, c, value)) {
                return null;
            }
        }

        return inferences;
    }

    public static boolean addInference(SudokuPuzzle puzzle, LinkedList<Inference> inferences, int row, int col, int value) {

        //the inference is given an exclusionvalue - the value variable (what has just been assigned)
        //and the index of the square (this is the one a value has just been assigned to)
        Inference newInference = new Inference(row, col, value);

        //if the inferences list does not contain the new inference (what is excluded) - and the value is in the correct domain
        //it adds the inference to the list of inferences
        if (!inferences.contains(newInference) && puzzle.inDomain(row, col, value)) {

            //if the domain length for that square is 1 (only 1 possible value) it returns false

            //if that value is the only one possible in another square than it returns false
            if (puzzle.getDomainLength(row, col) == 1) {
                return false;
            }

            inferences.add(newInference);
        }

        return true;
    }

    public static LinkedList<Integer> orderDomainValues(Index var, SudokuPuzzle puzzle) {
        LinkedList<Integer> domain = puzzle.getDomain(var.row, var.col);

        return domain;
    }

    public static Index selectUnAssignedVariable(SudokuPuzzle puzzle) {
        for (int row = 0; row < puzzle.puzzleSide; row++) {
            for (int col = 0; col < puzzle.puzzleSide; col++) {
                if (puzzle.get(row, col) == 0) {
                    return new Index(row, col);
                }
            }
        }

        return null;
    }

    public static boolean isFilledIn(SudokuPuzzle puzzle) {
        for (int row = 0; row < puzzle.puzzleSide; row++) {
            for (int col = 0; col < puzzle.puzzleSide; col++) {
                if (puzzle.get(row, col) == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isSolved(SudokuPuzzle puzzle) {
        return isFilledIn(puzzle) && isGoodSoFar(puzzle);
    }

    public static boolean isGoodSoFar(SudokuPuzzle puzzle) {
		*/
/* Check each unit *//*

        for (int uRow = 0; uRow < puzzle.unitSide; uRow++) {
            for (int uCol = 0; uCol < puzzle.unitSide; uCol++) {
                int[] nums = new int[9];
                int startRow = uRow * puzzle.unitSide;
                int startCol = uCol * puzzle.unitSide;
                int endRow = startRow + puzzle.unitSide;
                int endCol = startCol + puzzle.unitSide;
                int at = 0;

                for (int row = startRow; row < endRow; row++) {
                    for (int col = startCol; col < endCol; col++) {
                        nums[at++] = puzzle.get(row, col);
                    }
                }

                Arrays.sort(nums);

                for (int i = 1; i < at; i++) {
                    if (nums[i] != 0 && nums[i - 1] != 0 && nums[i] == nums[i - 1]) {
                        return false;
                    }
                }
            }
        }

		*/
/* Check each row *//*

        for (int row = 0; row < puzzle.puzzleSide; row++) {
            int[] nums = new int[9];

            for (int col = 0; col < puzzle.puzzleSide; col++) {
                nums[col] = puzzle.get(row, col);
            }

            Arrays.sort(nums);

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != 0 && nums[i - 1] != 0 && nums[i] == nums[i - 1]) {
                    return false;
                }
            }
        }

		*/
/* Check each column *//*

        for (int col = 0; col < puzzle.puzzleSide; col++) {
            int[] nums = new int[9];

            for (int row = 0; row < puzzle.puzzleSide; row++) {
                nums[row] = puzzle.get(row, col);
            }

            Arrays.sort(nums);

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != 0 && nums[i - 1] != 0 && nums[i] == nums[i - 1]) {
                    return false;
                }
            }
        }

        return true;
    }
}*/
