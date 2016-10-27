
import java.util.ArrayList;

/**
 * Class for recursively finding a solution to a Hexadecimal Sudoku problem.
 * 
 * @author Biagioni, Edoardo, Cam Moore
 * @date August 5, 2016
 * @missing solveSudoku, to be implemented by the students in ICS 211
 */
public class HexadecimalSudoku {

  /**
   * Find an assignment of values to sudoku cells that makes the sudoku valid.
   * 
   * @param the sudoku to be solved
   * @return whether a solution was found if a solution was found, the sudoku is filled in with the solution if no
   * solution was found, restores the sudoku to its original value
   */
  public static boolean solveSudoku(int[][] sudoku) {
    toString(sudoku, false);
    int[] emptySpot = new int[2];
    int[][] tempSudoku;
    ArrayList<Integer> possibilities;

    emptySpot = findEmpty(sudoku);
    //
    //
    // System.out.println(emptySpot[0] + " " + emptySpot[1]);
    //
    //
    if (emptySpot[0] == -1 || emptySpot[1] == -1) {
      return checkSudoku(sudoku, false);
    }

    possibilities = legalValues(sudoku, emptySpot[0], emptySpot[1]);

    for (int i = 0; i < possibilities.size(); i++) {
      sudoku[emptySpot[0]][emptySpot[1]] = possibilities.get(i);
      //
      //
      // System.out.println(tempSudoku[emptySpot[0]][emptySpot[1]]);
      //
      //
      if (solveSudoku(sudoku)) {
        return true;
      }
      else {
        sudoku[emptySpot[0]][emptySpot[1]] = -1;
      }
    }
    return false;
  }


  /**
   * Find the legal values for the given sudoku and cell.
   * 
   * @param sudoku, the sudoku being solved.
   * @param row the row of the cell to get values for.
   * @param col the column of the cell.
   * @return an ArrayList of the valid values.
   */
  private static ArrayList<Integer> legalValues(int[][] sudoku, int row, int column) {

    if (sudoku[row][column] != -1) {
      return null;
    }
    else {

      ArrayList<Integer> legals = new ArrayList<Integer>();

      for (int i = 0; i < 16; i++) {
        sudoku[row][column] = i;
        if (checkSudoku(sudoku, false)) {
          legals.add(i);
        }
      }
      sudoku[row][column] = -1;
      return legals;
    }
  }


  /**
   * checks that the sudoku rules hold in this sudoku puzzle. cells that contain 0 are not checked.
   * 
   * @param the sudoku to be checked
   * @param whether to print the error found, if any
   * @return true if this sudoku obeys all of the sudoku rules, otherwise false
   */
  public static boolean checkSudoku(int[][] sudoku, boolean printErrors) {
    if (sudoku.length != 16) {
      if (printErrors) {
        System.out.println("sudoku has " + sudoku.length + " rows, should have 16");
      }
      return false;
    }
    for (int i = 0; i < sudoku.length; i++) {
      if (sudoku[i].length != 16) {
        if (printErrors) {
          System.out.println("sudoku row " + i + " has " + sudoku[i].length + " cells, should have 16");
        }
        return false;
      }
    }
    /* check each cell for conflicts */
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        int cell = sudoku[i][j];
        if (cell == -1) {
          continue; /* blanks are always OK */
        }
        if ((cell < 0) || (cell > 16)) {
          if (printErrors) {
            System.out
                .println("sudoku row " + i + " column " + j + " has illegal value " + String.format("%02X", cell));
          }
          return false;
        }
        /* does it match any other value in the same row? */
        for (int m = 0; m < sudoku.length; m++) {
          if ((j != m) && (cell == sudoku[i][m])) {
            if (printErrors) {
              System.out.println(
                  "sudoku row " + i + " has " + String.format("%X", cell) + " at both positions " + j + " and " + m);
            }
            return false;
          }
        }
        /* does it match any other value it in the same column? */
        for (int k = 0; k < sudoku.length; k++) {
          if ((i != k) && (cell == sudoku[k][j])) {
            if (printErrors) {
              System.out.println(
                  "sudoku column " + j + " has " + String.format("%X", cell) + " at both positions " + i + " and " + k);
            }
            return false;
          }
        }
        /* does it match any other value in the 4x4? */
        for (int k = 0; k < 4; k++) {
          for (int m = 0; m < 4; m++) {
            int testRow = (i / 4 * 4) + k; /* test this row */
            int testCol = (j / 4 * 4) + m; /* test this col */
            if ((i != testRow) && (j != testCol) && (cell == sudoku[testRow][testCol])) {
              if (printErrors) {
                System.out.println("sudoku character " + String.format("%X", cell) + " at row " + i + ", column " + j
                    + " matches character at row " + testRow + ", column " + testCol);
              }
              return false;
            }
          }
        }
      }
    }
    return true;
  }


  /**
   * 
   * @param sudoku: a 16 by 16 2d array of ints from -1 t0 15
   * @return: a length two array of ints containing the row number and column number of the first encountered '-1' value
   */
  private static int[] findEmpty(int[][] sudoku) {
    int[] location = new int[2];
    location[0] = -1;
    location[1] = -1;

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        if (sudoku[i][j] == -1) {
          location[0] = i;
          location[1] = j;
          return location;
        }
      }
    }
    return location;
  }


  /**
   * Converts the sudoku to a printable string
   * 
   * @param the sudoku to be converted
   * @param whether to check for errors
   * @return the printable version of the sudoku
   */
  public static String toString(int[][] sudoku, boolean debug) {
    if ((!debug) || (checkSudoku(sudoku, false))) {
      String result = "";
      for (int i = 0; i < sudoku.length; i++) {
        if (i % 4 == 0) {
          result = result + "+---------+---------+---------+---------+\n";
        }
        for (int j = 0; j < sudoku.length; j++) {
          if (j % 4 == 0) {
            result = result + "| ";
          }
          if (sudoku[i][j] == -1) {
            result = result + "  ";
          }
          else {
            result = result + String.format("%X", sudoku[i][j]) + " ";
          }
        }
        result = result + "|\n";
      }
      result = result + "+---------+---------+---------+---------+\n";
      return result;
    }
    return "illegal sudoku";
  }
}
