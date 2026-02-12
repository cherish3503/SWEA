import java.util.*;
import java.io.*;

public class Swea1952pool {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			int[] prices = new int[4];
			int[] swimCnt = new int[12];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<prices.length; ++i) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<swimCnt.length; ++i) {
				swimCnt[i] = Integer.parseInt(st.nextToken());
			}
			int[][] dp = new int[12][12];
			for(int i=0; i<12; ++i) {
				for(int j=0; j<12; ++j) {
					dp[i][j] = -1;
				}
			}
			
			System.out.println("#"+ (test+1)+" "+minSwimPrice(prices,swimCnt,dp,0,12-1));
			
		}
	}
	
	
	private static int minSwimPrice(int[] prices, int[] swimCnt, int[][] dp, int s, int e) {
		if(dp[s][e] != -1)	return dp[s][e];
		
		if(s==e) {
			dp[s][e] = Math.min(prices[0]*swimCnt[s], prices[1]);
			return dp[s][e]; // 1일권, 1달권 판단
		}

		int min = Integer.MAX_VALUE;
		for(int i=s; i<e; ++i) {
			min = Math.min(min, minSwimPrice(prices, swimCnt,dp,s,i) + minSwimPrice(prices, swimCnt,dp,i+1,e));
		}

		if(e-s == 2) min = Math.min(min, prices[2]); // 3달권 고려
		
		if(e-s == 11) min = Math.min(min, prices[3]); // 연간권 고려
		
		dp[s][e] = min;
		return min;
	}
}
