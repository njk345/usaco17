import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Silver1 {
    public static void main(String[] args) {
        int[][] input = getInput();
        ArrayList<Integer> chickenTimes = new ArrayList<>();
        for (int i = 0; input[i].length == 1; i++) {
            chickenTimes.add(input[i][0]);
        }
        ArrayList<int[]> cowIntervals = new ArrayList<>();
        for (int i = chickenTimes.size(); i < input.length; i++) {
            cowIntervals.add(input[i]);
        }
        Collections.sort(chickenTimes);
        cowIntervals.sort((intv1, intv2) -> Integer.compare(intv1[0], intv2[0]));
        
        int maxMatches = getMaxMatches(chickenTimes, cowIntervals);
        writeOutput(maxMatches);
    }
    private static int getMaxMatches(ArrayList<Integer> ts, ArrayList<int[]> cintvs) {
        int m = 0;
        boolean[] taken = new boolean[cintvs.size()];
        for (int i = 0; i < ts.size(); i++) {
            for (int j = 0; j < cintvs.size(); j++) {
                if (taken[j]) continue;
                if (fits(ts.get(i), cintvs.get(j))) {
                    m++;
                    taken[j] = true;
                    break;
                }
            }
        }
        return m;
    }
    private static boolean fits(int t, int[] ab) {
        return t >= ab[0] && t <= ab[1];
    }
    private static int[][] getInput() {
        int[][] ip = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
            String[] topSplit = br.readLine().split(" ");
            ip = new int[Integer.parseInt(topSplit[0]) + Integer.parseInt(topSplit[1])][];
            for (int i = 0; i < Integer.parseInt(topSplit[0]); i++) {
                ip[i] = new int[]{Integer.parseInt(br.readLine())};
            }
            for (int i = Integer.parseInt(topSplit[0]); i < ip.length; i++) {
                String[] lineSplit = br.readLine().split(" ");
                ip[i] = new int[]{Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1])};
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
    private static void writeOutput(int max) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("helpcross.out"));
            bw.write(max + "\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}