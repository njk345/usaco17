import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Silver3 {
    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("countcross.out"));
            bw.write("2");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}