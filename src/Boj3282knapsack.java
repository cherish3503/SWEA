import java.util.*;
import java.io.*;

public class Boj3282knapsack {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] volumes = new int[N];
			int[] costs = new int[N];
			int[][] knapsack = new int[N+1][K+1];
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				volumes[i] = v;
				costs[i]= c;
			}
			
			for(int i=1; i<=N; ++i) {
				for(int j=0; j<=K; ++j) {
					int v = volumes[i-1];
					int c = costs[i-1];
					knapsack[i][j] = knapsack[i-1][j];
					if(j-v >= 0) knapsack[i][j] = Math.max(knapsack[i][j], knapsack[i-1][j-v] + c);
				}
			}
			
			System.out.println("#"+test+" "+ knapsack[N][K]);
			
		}
		
	}
}