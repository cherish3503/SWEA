import java.util.*;
import java.io.*;


public class Swea2001CatchFly {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] board = new int[N][N];
			int[][] sumBoard = new int[N+1][N+1];
			for(int r=0; r<N; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; ++c) {
					int now = Integer.parseInt(st.nextToken());
					board[r][c] = now;
				}
			}
			// r==0 c==0 인 구간은 0 으로
			for(int r=1; r<N+1; ++r) {
				for(int c=1; c<N+1; ++c) {
					sumBoard[r][c] = sumBoard[r-1][c] + sumBoard[r][c-1] + board[r-1][c-1] - sumBoard[r-1][c-1];
				}
			}
			
	//		Arrays.stream(sumBoard).map(Arrays::toString).forEach(System.out::println);
			int max = Integer.MIN_VALUE;
			for(int r=M; r<N+1; ++r) {
				for(int c=M; c<N+1; ++c) {
					int now = sumBoard[r][c] - sumBoard[r-M][c] - sumBoard[r][c-M] + sumBoard[r-M][c-M];
					max = Math.max(now, max);
				}
			}
			System.out.println("#" +(test+1)+" "+ max);
		}
			
	}
}
