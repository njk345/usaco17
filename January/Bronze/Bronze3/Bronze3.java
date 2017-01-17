import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Bronze3 {
	public static void main(String[] args) {
		int[][] grid = getInput();
		int minMoves = getNumMoves(grid);
		writeOutput(minMoves);
	}
	
	private static int getNumMoves(int[][] grid) {
		int moves = 0;
		while (!isClear(grid)) {
			int[] farthest = farthestOut(grid);
			flipSection(grid, farthest[0], farthest[1]);
			moves++;
		}
		return moves;
	}
	
	private static int[] farthestOut(int[][] grid) {
		int farthestTot = -1;
		int[] farthestSpot = {-1, -1};
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					//there's a cow here
					int tot = i + j;
					if (tot > farthestTot) {
						farthestTot = tot;
						farthestSpot[0] = i;
						farthestSpot[1] = j;
					}
				}
			}
		}
		return farthestSpot;
	}
	
	private static void flipSection(int[][] grid, int h, int w) {
		for (int i = 0; i <= h; i++) {
			for (int j = 0; j <= w; j++) {
				grid[i][j] = (grid[i][j] + 1) % 2;
			}
		}
	}
	
	private static boolean isClear(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) return false;
			}
		}
		return true;
	}
	
	private static int[][] getInput() {
		int[][] grid = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("cowtip.in"));
			int n = Integer.parseInt(br.readLine());
			grid = new int[n][n];
			for (int i = 0; i < n; i++) {
				String row = br.readLine();
				for (int j = 0; j < n; j++) {
					grid[i][j] = Integer.parseInt(row.substring(j, j+1));
				}
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}
	
	private static void writeOutput(int min) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("cowtip.out"));
			bw.write(min + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}