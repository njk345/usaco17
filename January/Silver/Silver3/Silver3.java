import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Silver3 {
	public static void main(String[] args) {
		writeOutput("C");
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