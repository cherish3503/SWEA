import java.util.*;
import java.io.*;


public class Swea9229SpotMart {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			
			System.out.println("#" + (test+1) + " " + twoPointer(arr, M));
		}

	}
	
	
	private static int twoPointer(int[] arr, int M) {
		int N = arr.length;
		int max = -1;
		int s =0; int e=N-1;
		while(s<e) {
			int sum = arr[s] + arr[e];
			if(sum == M) {
				return sum;
			}
			else if(sum > M) {
				e--;
			}
			else {
				s++;
				max = Math.max(max, sum);
			}
			
		}
		
		return max;
	}
}
