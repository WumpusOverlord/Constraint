/*
import java.util.LinkedList;

*/
/**
 * Created by hj22 on 15/03/17.
 *//*

public class ForwardChecking {

    public static LinkedList<Solver.Inference> forwardCheckInferences(SudokuPuzzle puzzle, Solver.Index assigned, int value) {
        LinkedList<Solver.Inference> inferences = new LinkedList<Solver.Inference>();

		*/
/* Flush domain for current square *//*

        for (int i = 1; i <= 9; i++) {
            if (i != value) {
                addInference(puzzle, inferences, assigned.row, assigned.col, i);
            }
        }

		*/
/* Flush domains in current unit *//*

        int startRow = (assigned.row / puzzle.unitSide) * puzzle.unitSide;
        int startCol = (assigned.col / puzzle.unitSide) * puzzle.unitSide;
        int endRow = startRow + puzzle.unitSide;
        int endCol = startCol + puzzle.unitSide;

        for (int row = startRow; row < endRow; row++) {
            for (int col = startCol; col < endCol; col++) {
                if (row == assigned.row || col == assigned.col) {
                    continue;
                }

                if (!addInference(puzzle, inferences, row, col, value)) {
                    return null;
                }
            }
        }

		*/
/* Flush domains in current row *//*

        for (int r = 0; r < puzzle.puzzleSide; r++) {
            if (r == assigned.row) {
                continue;
            }

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
}
*/
