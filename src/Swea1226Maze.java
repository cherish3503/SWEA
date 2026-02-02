import java.util.*;
import java.io.*;


public class Swea1226Maze {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		int size = 16;
		for(int test=0; test<T; ++test) {
			int[][] board = new int[size][size];
			int startR =-1; 
			int startC= -1;
			int destR = -1;
			int destC = -1;
			
			br.readLine();
			for(int r=0; r<size; ++r) {
				String line = br.readLine();
				for(int c=0; c<size; ++c) {
					int now  = line.charAt(c) - '0';
					board[r][c] = now;
					if(now == 2) {
						startR = r; 
						startC = c; 
					}
					else if(now == 3) {
						destR = r; 
						destC = c; 
					}
				}
			}
			
			System.out.println("#" +(test+1)+" " +  (mazeBfs(board, startR, startC, destR, destC) ? 1 : 0) );
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	private static boolean mazeBfs(int[][] board, int startR, int startC, int destR, int destC) {
		
		int[][] dir = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
		int size = board.length;
		Deque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[size][size];
		
		visited[startR][startC] = true;
		dq.add(new int[]{startR, startC});
		
		while(!dq.isEmpty()) {
			int[] now = dq.poll();
			int nowR = now[0];
			int nowC = now[1];
			for(int d=0; d<dir.length; ++d) {
				int nr = nowR + dir[d][0];
				int nc = nowC + dir[d][1];
				
				if(nr <0 || nc < 0 || nr>=size || nc>= size) {
					continue;
				}
				if(visited[nr][nc]) {
					continue;
				}
				if(board[nr][nc] == 1) {
					continue;
				}
				if(board[nr][nc] == 3) {
					// 찾으면
					return true;
				}
				visited[nr][nc] = true;
				dq.add(new int[]{nr,nc});

			}
			
		}
		// 못 찾으면
		return false;
	}
	
}









































