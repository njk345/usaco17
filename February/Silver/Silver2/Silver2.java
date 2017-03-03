import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Silver2 {
    public static void main(String[] args) {
        int[] input = getInput();
        int[] broken = new int[input.length - 2];
        for (int i = 2; i < input.length; i++) {
            broken[i-2] = input[i];
        }
        Arrays.sort(broken);
        
        int min = getMinFixes(input[0], input[1], broken);
        writeOutput(min);
    }
    private static int getMinFixes(int n, int k, int[] broken) {
        ArrayList<int[]> lgs = getLargestGaps(broken);
        boolean[] isBroken = new boolean[n+1];
        for (int i = 0; i < broken.length; i++) {
            isBroken[broken[i]] = true;
        }
        int minFixes = Integer.MAX_VALUE;
        for (int[] gap : lgs) {
            int gapSize = (gap[1] - gap[0]) + 1;
            int fixes = 1 + getFixes(n, (k - gapSize) - 1, isBroken, gap[0] - 2, gap[1] + 2);
            if (fixes < minFixes) {
                minFixes = fixes;
            }
        }
        return minFixes;
    }
    private static int getFixes(int n, int toGo, boolean[] isBroken, int left, int right) {
        if (toGo <= 0) {
            return 0;
        }
        if (right >= n+1) { //off the right end
            return (isBroken[left]? 1 : 0) + getFixes(n, toGo-1, isBroken, left-1, right);
        }
        if (left <= 0) { //off the left end
            return (isBroken[right]? 1 : 0) + getFixes(n, toGo-1, isBroken, left, right + 1);
        }
        if (isBroken[left] && isBroken[right]) {
            //go left
            return 1 + getFixes(n, toGo-1, isBroken, left-1, right);
        }
        if (isBroken[left]) {
            //go right
            return getFixes(n, toGo-1, isBroken, left, right+1);
        }
        if (isBroken[right]) {
            //go left
            return getFixes(n, toGo-1, isBroken, left-1, right);
        }
        return 0; //wont get here 
    }
    private static ArrayList<int[]> getLargestGaps(int[] broken) {
        ArrayList<int[]> lgs = new ArrayList<>();
        int largestGap = broken[0] - 1;
        for (int i = 1; i < broken.length; i++) {
            if (broken[i] - broken[i-1] > largestGap) {
                largestGap = broken[i] - broken[i-1];
            }
        }
        if (broken[0] - 1 == largestGap) {
            lgs.add(new int[]{1, broken[0] - 1});
        }
        for (int i = 1; i < broken.length; i++) {
            if (broken[i] - broken[i-1] == largestGap) {
                lgs.add(new int[]{broken[i-1] + 1, broken[i] - 1});
            }
        }
        return lgs;
    }
    private static int[] getInput() {
        int[] ip = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
            String[] topSplit = br.readLine().split(" ");
            ip = new int[2+Integer.parseInt(topSplit[2])];
            ip[0] = Integer.parseInt(topSplit[0]);
            ip[1] = Integer.parseInt(topSplit[1]);
            for (int i = 2; i < ip.length; i++) {
                ip[i] = Integer.parseInt(br.readLine());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
    private static void writeOutput(int min) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("maxcross.out"));
            bw.write(min + "\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}