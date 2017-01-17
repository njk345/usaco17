import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Silver1 {
	public static void main(String[] args) {
		int[] times = getInput();
		int kmin = getMinK(times);
		writeOutput(kmin);
	}
	
	private static int getMinK(int[] times) {
		//do a binary search on k to minimize t
		int tmax = times[times.length - 1];
		int n = times.length - 1;
		int leftBound = 1, rightBound = n;
		int k = (leftBound + rightBound) / 2;
		int lastT = Integer.MAX_VALUE;
		
		while (true) {
			int t = getT(times, k);
			if (leftBound == rightBound) {
				return k;
			}
			if (t > tmax) {
				leftBound = k+1;
			} else if (t <= tmax) {
				rightBound = k;
			}
			k = (leftBound + rightBound) / 2;
		}
	}
	
	private static int getT(int[] times, int k) {
		int t = 0, n = times.length - 1;
		int[] onStage = new int[k];
		for (int i = 0; i < k; i++) {
			onStage[i] = times[i];
		}
		for (int i = 0; i < n - k; i++) {
			int nextDone = minIndex(onStage);
			t += onStage[nextDone];
			bumpDown(onStage, onStage[nextDone]);
			onStage[nextDone] = times[k + i];
		}
		t += max(onStage);
		return t;
	}
	
	private static int minIndex(int[] a) {
		int min = Integer.MAX_VALUE, minInd = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				min = a[i];
				minInd = i;
			}
		}
		return minInd;
	}
	
	private static int max(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int i : a) {
			if (i > max) max = i;
		}
		return max;
	}
	
	private static void bumpDown(int[] a, int val) {
		for (int i = 0; i < a.length; i++) {
			a[i] -= val;
		}
	}
	
	private static int[] getInput() {
		int[] times = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
			String[] topSplit = br.readLine().split(" ");
			times = new int[Integer.parseInt(topSplit[0]) + 1];
			times[times.length - 1] = Integer.parseInt(topSplit[1]); //stuff tmax at end
			for (int i = 0; i < times.length-1; i++) {
				times[i] = Integer.parseInt(br.readLine());
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return times;
	}
	
	private static void writeOutput(int kmin) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("cowdance.out"));
			bw.write(kmin + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}