 
public class Main {

  public static void main(String[] args) {
    int[][] checkSudokuTest = new int[16][16];
    HexadecimalSudoku firstTest = new HexadecimalSudoku();

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        checkSudokuTest[i][j] = -1;
      }
    }

    firstTest.checkSudoku(checkSudokuTest, true);

  }
}
