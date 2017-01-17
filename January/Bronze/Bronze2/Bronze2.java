import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Bronze2 {
	private static String[] config1 = {"H", "P", "S"};
	private static String[] config2 = {"H", "S", "P"};
	private static String[] config3 = {"S", "H", "P"};
	private static String[] config4 = {"S", "P", "H"};
	private static String[] config5 = {"P", "H", "S"};
	private static String[] config6 = {"P", "S", "H"};
	private static String[][] allConfigs = {config1, config2, config3, config4, config5, config6};
	
	public static void main(String[] args) {
		int[][] games = getInput();
		int maxWins = getMaxWins(games);
		writeOutput(maxWins);
	}
	
	private static int[][] getInput() {
		int[][] games = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("hps.in"));
			int numGames = Integer.parseInt(br.readLine());
			games = new int[numGames][2];
			for (int i = 0; i < numGames; i++) {
				String[] lineSplit = br.readLine().split(" ");
				games[i][0] = Integer.parseInt(lineSplit[0]);
				games[i][1] = Integer.parseInt(lineSplit[1]);
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return games;
	}
	
	private static void writeOutput(int maxWins) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("hps.out"));
			bw.write(maxWins + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int getMaxWins(int[][] games) {
		int max = -1;
		for (int i = 0; i < allConfigs.length; i++) {
			int wins = getWins(games, i);
			if (wins > max) {
				max = wins;
			}
		}
		return max;
	}
	
	private static int getWins(int[][] games, int configNum) {
		String[] config = allConfigs[configNum];
		int numWins = 0;
		for (int i = 0; i < games.length; i++) {
			String p1Move = config[games[i][0] - 1];
			String p2Move = config[games[i][1] - 1];
			if (player1Wins(p1Move, p2Move)) numWins++;
		}
		return numWins;
	}
	private static boolean player1Wins(String p1Move, String p2Move) {
		return p1Move.equals("H") && p2Move.equals("S") || p1Move.equals("S") && p2Move.equals("P")
			|| p1Move.equals("P") && p2Move.equals("H");
	}
}