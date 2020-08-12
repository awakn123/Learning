package leetcode.easy.array;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曹云 on 2020/8/3.
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length!=9) return false;
		Map<Character, Integer> map = new HashMap<>();
		for (int i=0; i<board.length; i++) {
			char[] row = board[i];
			for (int j=0; j<row.length; j++){
				if ('.' == row[j]){
					continue;
				} else if (map.containsKey(row[j])) {
					return false;
				} else {
					map.put(row[j], 1);
				}
			}
			map.clear();
		}
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++){
				if ('.' == board[j][i]){
					continue;
				} else if (map.containsKey(board[j][i])) {
					return false;
				} else {
					map.put(board[j][i], 1);
				}
			}
			map.clear();
		}

		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++){
				for (int p = 0; p<3; p++) {
					for (int q=0; q<3; q++) {
						if ('.' == board[3*i+p][3*j+q]){
							continue;
						} else if (map.containsKey(board[3*i+p][3*j+q])) {
							return false;
						} else {
							map.put(board[3*i+p][3*j+q], 1);
						}
					}
				}
				map.clear();
			}
		}
		return true;
	}

	public boolean checkSudoku(char[][] board, Map<Character, Integer> map, int row, int col){
		if ('.' == board[row][col]){
			return true;
		} else if (map.containsKey(board[row][col])) {
			return false;
		} else {
			map.put(board[row][col], 1);
			return true;
		}
	}
	public boolean isValidSudoku2(char[][] board) {
		if (board == null || board.length!=9) return false;
		Map<Character, Integer> mapRow = new HashMap<>();
		Map<Character, Integer> mapCol = new HashMap<>();
		Map<Character, Integer> mapBlock = new HashMap<>();

		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++){
				for (int p = 0; p<3; p++) {
					for (int q=0; q<3; q++) {
						int rowI = 3*i+j;
						int rowJ = 3*p+q;
						if (!checkSudoku(board, mapRow, rowI, rowJ)) {
//							System.out.printf("row: %d, %d.\n", rowI, rowJ);
							return false;
						}
						int colI = 3*p+q;
						int colJ = 3*i+j;
						if (!checkSudoku(board, mapCol, colI, colJ)){
//							System.out.printf("col: %d, %d.\n", colI, colJ);
							return false;
						}
						int blockI = 3*i+p;
						int blockJ = 3*j+q;
						if (!checkSudoku(board, mapBlock, blockI, blockJ)) {
//							System.out.printf("block: %d, %d.\n", blockI, blockJ);
							return false;
						}
					}
				}
				mapRow.clear();
				mapCol.clear();
				mapBlock.clear();
			}
		}
		return true;
	}
	public static void main(String[] args){
		ValidSudoku main = new ValidSudoku();
		char[][] boardI = {
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
		};
		boolean answerI = true;
		boolean resultI = main.isValidSudoku2(boardI);
		main.checkResult(resultI, answerI);
		char[][] boardII = {
				{'8','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
		};
		boolean answerII = false;
		boolean resultII = main.isValidSudoku2(boardII);
		main.checkResult(resultII, answerII);
		char[][] boardIII = {
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','5','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
		};
		boolean answerIII = false;
		boolean resultIII = main.isValidSudoku2(boardIII);
		main.checkResult(resultIII, answerIII);
		char[][] boardIV = {
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','8','.','.','8','.','.','7','9'}
		};
		boolean answerIV = false;
		boolean resultIV = main.isValidSudoku2(boardIV);
		main.checkResult(resultIV, answerIV);
	}

	public static void checkResult(boolean result, boolean answer) {
		if (result == answer){
			System.out.println("Pass");
		} else {
			System.out.printf("Wrong. result:%b, answer:%b.\n", result, answer);
		}
	}
}
