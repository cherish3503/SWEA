import java.util.*;
import java.io.*;


public class Swea2115Bee {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] board = new int[N][N];
			
			for(int r=0; r<N; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; ++c) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int[][] dp = new int[N][N-M+1]; // 각 자리부터 시작하는 M칸의 최대 값
			//경우1. 두 사람이 같은 행
			int[] rowMax = new int[N]; // 각 행에서의 최대값
			for(int r=0; r<N; ++r) {
				int max = Integer.MIN_VALUE;
				for(int c=0; c<N-M+1; ++c) {
					int res = getRangeMax(board[r], c, M, C);// 값 저장
					dp[r][c]= res; // 값 저장
					max = Math.max(max, res);
				}
				rowMax[r] = max; 
			}
			Arrays.sort(rowMax);
			int max1 = rowMax[N-1] + rowMax[N-2];
			//
			
			//경우2. 두 사람이 다른 행
			int max2 = Integer.MIN_VALUE;
			for(int r=0; r<N; ++r) {
				int maxR = Integer.MIN_VALUE;
				for(int c1=0; c1<N-M+1; ++c1) {
					for(int c2=c1+M; c2<N-M+1; ++c2) {
						maxR = Math.max(maxR, dp[r][c1] + dp[r][c2]);
					}
				}
				max2 = Math.max(max2, maxR);
			}
			//
			
			int result = Math.max(max1,max2);
			

			System.out.println("#" +(test+1)+" " + result);
		}
	}
	
	private static int getRangeMax(int[] row, int s, int M, int C) {
		int N = row.length;
		int[][] knapsack = new int[M+1][C+1];
		
		
		for(int i=1; i<=M; ++i) {
			int weight = row[s+i-1];
			int value = weight*weight;
			for(int j=1; j<=C; ++j) {
				int res;
				if(j<weight) res = knapsack[i-1][j];
				else res = Math.max(knapsack[i-1][j], knapsack[i-1][j-weight] + value);
				knapsack[i][j] = res;
			}
		}
		
		return knapsack[M][C];
	}
}
