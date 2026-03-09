import java.io.*;
import java.util.*;


public class Swea1868Mine {
	private static int[][] dirArr = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			int N = Integer.parseInt(br.readLine());
			char[][] board = new char[N][N];
			List<int[]> bombList = new ArrayList<>();
			
			for(int r=0; r<N; ++r) {
				String line = br.readLine();
				for(int c=0; c<N; ++c) {
					char now = line.charAt(c);
					if(now == '.') board[r][c] = '0';
					else{
						bombList.add(new int[] {r,c});
						board[r][c] = now;
					}
				}
			}
			for(int[] bomb : bombList) {
				nearBomb(board, bomb[0], bomb[1]);
			}
			
			int cnt = 0;
			for(int r=0; r<N; ++r) {
				for(int c=0; c<N; ++c) {
					if(board[r][c] == '0'){
						clickZero(board, r, c);
						cnt++;
					}
				}
			}
			
			for(int r=0; r<N; ++r) {
				for(int c=0; c<N; ++c) {
					if(board[r][c] != '*' && board[r][c] != '#') cnt++;
				}
			}

			System.out.println("#" + test + " " + cnt);
			
		}
		
	}
	
	private static void nearBomb(char[][] board, int row, int col) {
		int N = board.length;
		for(int[] d : dirArr) {
			int nr = row+d[0];
			int nc = col+d[1];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(board[nr][nc] != '*') board[nr][nc]++;
		}
	}
	
	private static void clickZero(char[][] board, int row, int col) {
		int N = board.length;
		boolean[][] visited = new boolean[N][N];
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {row, col});
		visited[row][col] = true;
		
//		int cnt = 0;
		while(!dq.isEmpty()) {
//			cnt++;
			int[] cur = dq.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			
			if(board[curR][curC] != '0') {
				board[curR][curC] = '#';
				continue;
			}
			board[curR][curC] = '#';
			
			for(int[] d : dirArr) {
				int nr = curR+d[0];
				int nc = curC+d[1];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(visited[nr][nc]) continue;
//				if(board[nr][nc] != '0') continue;
				visited[nr][nc] = true;	
				dq.offer(new int[] {nr,nc});
			}
			
			
		}
		

	}
	
	
	
	
}