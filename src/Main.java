
public class Main {

  public static void main(String[] args) {
    int[][] checkSudokuTest = new int[16][16];

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        checkSudokuTest[i][j] = -1;
      }
    }

    HexadecimalSudoku.checkSudoku(checkSudokuTest, true);
   HexadecimalSudoku.solveSudoku(checkSudokuTest);
System.out.print("done");
  }
}
