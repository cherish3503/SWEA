import java.util.*;
import java.io.*;

public class Swea1210Ladder {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		int size = 100;
		for(int test=0; test<T; ++test) {
			br.readLine();
			int destR = -1;
			int destC = -1;
			int[][] board = new int[size][size];
			
			for(int r=0; r<size; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<size; ++c) {
					int e = Integer.parseInt(st.nextToken());
					if(e==2) {
						destR = r;
						destC = c;
					}
					
					board[r][c] = e;
				}
			}
			System.out.println("#" +(test+1) +" " + upLadder(board, destR, destC, 2));
		}
	}
	

	
	
	
	private static int upLadder(int[][] board, int r, int c, int direction) {
		 // direction -> left:0, right:1, up:2
		int size = board.length;
		if(r == 0) {
			return c;
		}
		
		int[] dir = new int[]{-1,1}; // left:0, right:1
		
		for(int i=0; i<dir.length; ++i) { //좌우
			if(direction != 2 && direction != i) {
				continue;
			}
			int nr = r;
			int nc = c + dir[i];
			
			if(nr < 0 || nc <0 || nr >= size || nc >= size) {
				continue;
			}
			
			if(board[nr][nc] == 1) {
				return upLadder(board, nr, nc, i);
			}
		}

		return upLadder(board, r-1, c, 2);
	}
}

/*
1
1 0 0 0 1 0 1 0 0 1
1 0 0 0 1 0 1 1 1 1
1 0 0 0 1 0 1 0 0 1
1 0 0 0 1 1 1 0 0 1
1 0 0 0 1 0 1 0 0 1
1 1 1 1 1 0 1 1 1 1
1 0 0 0 1 0 1 0 0 1
1 1 1 1 1 0 1 0 0 1
1 0 0 0 1 1 1 0 0 1
1 0 0 0 1 0 1 0 0 2

 */

