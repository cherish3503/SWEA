import java.io.*;
import java.util.*;


public class SweaMountain {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=0; test<T; ++test) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			for(int r=0; r<N; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; ++c) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#" +(test+1) +" "+ searchRoute(board));
		}
	}
	
	private static int searchRoute(int[][] board) {

		int N = board.length;
		int[][] minRoute = new int[N][N]; //min 
		for(int r=0; r<N; ++r) {
			for(int c=0; c<N; ++c) {
				minRoute[r][c] = -1;
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2)-> e1[0] - e2[0]);
		pq.offer(new int[] {0,0,0});
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; //udlr
		while(!pq.isEmpty()) {
			int[] entry = pq.poll();
			int dist = entry[0];
			int r = entry[1];
			int c = entry[2];
			
			if(minRoute[r][c] != -1) continue; // 이미 연료 처리 된 경우
			minRoute[r][c] = dist;
			
			if(r == N-1 && c == N-1) return dist;
			
			for(int d=0; d<dir.length; ++d) {
				int nr = r +dir[d][0];
				int nc = c +dir[d][1];
				
				if(nr<0 || nc<0|| nr>=N || nc>=N) continue;
				
				if(minRoute[nr][nc] != -1) continue;

				int fuel = 2*(board[nr][nc] - board[r][c]);
				
				if(fuel == 0) fuel = 1;
				else if(fuel<0) fuel = 0;
				
				pq.offer(new int[] {dist+fuel,nr,nc});
			}
			
		}
		return -1;
	}
}
