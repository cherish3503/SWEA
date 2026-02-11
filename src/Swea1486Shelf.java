import java.util.*;
import java.io.*;

public class Swea1486Shelf {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println("#" + (test+1) +" " +getMinHeight(arr, B, 0, 0));
		}
		
		
	}
	
	private static int getMinHeight(int[] arr, int B, int depth, int sum) {
		int N = arr.length;
		if(depth >= N) {
			return sum>=B ? sum-B : Integer.MAX_VALUE;
		}
		
		if(sum >= B) {
			return sum-B;
		}

		return Math.min(getMinHeight(arr, B, depth+1, sum), getMinHeight(arr, B, depth+1, sum+arr[depth]));
	}

}
