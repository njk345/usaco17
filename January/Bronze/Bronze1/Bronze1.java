import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Bronze1 {
	private static String[] cows = {"Bessie", "Elsie", "Daisy", "Gertie", "Annabelle", "Maggie", "Henrietta"};
	public static void main(String[] args) {
		int[] milkings = getInput();
		String result = getSecondLeast(milkings);
		writeOutput(result);
	}
	private static int[] getInput() {
		int[] milkings = new int[7];
		try {
			BufferedReader br = new BufferedReader(new FileReader("notlast.in"));
			br.readLine();
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(" ");
				milkings[indexOf(cows, lineSplit[0])] += Integer.parseInt(lineSplit[1]);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return milkings;
	}
	private static void writeOutput(String result) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("notlast.out"));
			bw.write(result + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String getSecondLeast(int[] milkings) {
		int min = min(milkings);
		int secondSmallest = Integer.MAX_VALUE, ssIndex = -1;
		for (int i = 0; i < milkings.length; i++) {
			if (milkings[i] > min) {
				if (milkings[i] < secondSmallest) {
					secondSmallest = milkings[i];
					ssIndex = i;
				}
			}
		}
		if (ssIndex == -1) return "Tie";
		int numSS = 0;
		for (int i = 0; i < milkings.length; i++) {
			if (milkings[i] == secondSmallest) {
				numSS++;
			}
		}
		if (numSS > 1) return "Tie";
		return cows[ssIndex];
	}
	private static int indexOf(String[] a, String val) {
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals(val)) {
				return i;
			}
		}
		return -1;
	}
	private static int min(int[] a) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				min = a[i];
			}
		}
		return min;
	}
}