import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Silver2 {
	private static String[] moves = {"H", "P", "S"};
	
	public static void main(String[] args) {
		String[] fMoves = getInput();
		int maxWins = getMaxWins(fMoves);
		writeOutput(maxWins);
	}
	
	private static int getMaxWins(String[] fMoves) {
		int max = -1;
		for (String startMove : moves) {
			for (String endMove : moves) {
				if (startMove.equals(endMove)) continue;
				
				int[] winsForward = winningsForward(fMoves, startMove);
				int[] winsBackward = winningsBackward(fMoves, endMove);
				
				for (int turn = 1; turn <= fMoves.length; turn++) {
					int wins;
					if (turn == fMoves.length) {
						wins = winsForward[winsForward.length - 1];
					}
					else {
						wins = winsForward[turn-1] + winsBackward[turn];
					}
					if (wins > max) {
						max = wins;
					}
				}
			}
		}
		return max;
	}
	
	private static int[] winningsForward(String[] fMoves, String bMove) {
		int[] winnings = new int[fMoves.length];
		winnings[0] = bessieWins(bMove, fMoves[0])? 1 : 0;
		for (int i = 1; i < fMoves.length; i++) {
			winnings[i] = winnings[i-1] + (bessieWins(bMove, fMoves[i])? 1 : 0);
		}
		return winnings;
	}
	
	private static int[] winningsBackward(String[] fMoves, String bMove) {
		int[] winnings = new int[fMoves.length];
		winnings[winnings.length - 1] = bessieWins(bMove, fMoves[fMoves.length - 1])? 1 : 0;
		for (int i = winnings.length - 2; i >= 0; i--) {
			winnings[i] = winnings[i+1] + (bessieWins(bMove, fMoves[i])? 1 : 0);
		}
		return winnings;
	}
	
	private static boolean bessieWins(String bMove, String fMove) {
		return bMove.equals("H") && fMove.equals("S") || bMove.equals("S") && fMove.equals("P")
			|| bMove.equals("P") && fMove.equals("H");
	}
	
	private static String[] getInput() {
		String[] moves = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("hps.in"));
			int n = Integer.parseInt(br.readLine());
			moves = new String[n];
			for (int i = 0; i < n; i++) {
				moves[i] = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moves;
	}
	
	private static void writeOutput(int max) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("hps.out"));
			bw.write(max + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}