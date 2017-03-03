import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Silver3 {
	public static void main(String[] args) {
		String[] inp = getInput();
		String solution = getNthChar(inp);
		writeOutput(solution);
	}
	private static String getNthChar(String[] inp) {
		long n = Long.parseLong(inp[1]);
		String s = inp[0];
		
		
	}
	private static String[] getInput() {
		String[] inp = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
			inp = br.readLine().split(" ");
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inp;
	}
	private static void writeOutput(String s) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("cowcode.out"));
			bw.write(s + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}