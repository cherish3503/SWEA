import java.util.*;
import java.io.*;


public class Swea4012chef {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			int N = Integer.parseInt(br.readLine());
			int[][] S = new int[N][N];
			for(int r=0; r<N; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; ++c) {
					S[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println("#" + (test+1) + " " +choose(S, 0, 0));
			
		}
	}
	
	
	private static int choose(int[][] S, int depth, int bit) {
		int N = S.length;
		int cnt = bitCnt(bit);
		
		if(cnt == N/2) {
			return Math.abs((cook(S, bit)-cook(S, bit^((1<<N)-1))));
			
		}
		if(cnt < depth - N/2){
			return Integer.MAX_VALUE; //fail
		}
		
		return Math.min(choose(S, depth+1, bit), choose(S, depth+1, bit|(1<<depth)));
	}
	
	private static int bitCnt(int bit) {
		int cnt = 0;
		while(bit != 0) {
			cnt += (bit&1);
			bit = bit>>1; 
		}
		return cnt;
	}
	
	private static int cook(int[][] S, int bit) {
		if(bitCnt(bit) ==1) return 0;
		
		int N = S.length;
		int sum = 0;

		for(int i=0; i<N; ++i) {
			if((bit&(1<<i)) == 1<<i) {
				for(int j=0; j<N; ++j) {
					if(i == j) continue;
					if(((bit>>j)&1) == 1) {
						sum += S[i][j];
					}
				}
			}
		}
		
		return sum;
	}
}















//
//
//
//
//import java.util.*;
//import java.io.*;
//
//
//public class Swea4012chef {
//	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		
//		int T = Integer.parseInt(br.readLine());
//		for(int test=0; test<T; ++test) {
//			int N = Integer.parseInt(br.readLine());
//			int[][] S = new int[N][N];
//			for(int r=0; r<N; ++r) {
//				st = new StringTokenizer(br.readLine());
//				for(int c=0; c<N; ++c) {
//					S[r][c] = Integer.parseInt(st.nextToken());
//				}
//			}
//
//			System.out.println("#" + (test+1) + " " +choose(S, 0, 0));
//			
//		}
//	}
//	
//	
//	private static int choose(int[][] S, int depth, int bit) {
//		int N = S.length;
//		int cnt = bitCnt(bit);
//		int[] dp = new int[1<<N];
//		for(int i=0; i<dp.length; ++i) dp[i] = -1;
//
//		if(cnt == N/2) {
//			return Math.abs((cook(S, dp, bit)-cook(S, dp, bit^((1<<N)-1))));
//			
//		}
//		if(cnt < depth - N/2){
//			return Integer.MAX_VALUE; //fail
//		}
//		
//		return Math.min(choose(S, depth+1, bit), choose(S, depth+1, bit|(1<<depth)));
//	}
//	
//	private static int bitCnt(int bit) {
//		int cnt = 0;
//		while(bit != 0) {
//			cnt += (bit&1);
//			bit = bit>>1; 
//		}
//		return cnt;
//	}
//	
//	private static int cook(int[][] S, int[] dp, int bit) {
//		if(bitCnt(bit) ==1) return 0;
//		if(dp[bit] != -1) return dp[bit];
//		
//		int N = S.length;
//		int sum = 0;
////		for(int i=0; i<N; ++i) {
////			if((bit&(1<<i)) == 1<<i) {
////				sum += cook(S, dp, bit^(1<<i));
////				for(int j=0; j<N; ++j) {
////					if(i == j) continue;
////					if(((bit>>j)&1) == 1) {
////						sum += S[i][j] + S[j][i];
////					}
////				}
////				dp[bit] = sum;
////				break; // 1번만 실핼
////			}
////		}
//		
//		
//		for(int i=0; i<N; ++i) {
//			if((bit&(1<<i)) == 1<<i) {
//				for(int j=0; j<N; ++j) {
//					if(i == j) continue;
//					if(((bit>>j)&1) == 1) {
//						sum += S[i][j];
//					}
//				}
//			}
//		}
//		
//		return sum;
//	}
//}
//
//
//
