import java.io.*;
import java.util.*;

public class Swea1873BattleField {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String tankStr = "^v<>";
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			char[][] board = new char[H][W];
			
			int curR =0; int curC=0; int curDir=0;
			for(int r=0; r<H; ++r) {
				String inpStr = br.readLine();
				for(int c=0; c<W; ++c) {
					char nowChar = inpStr.charAt(c);
					board[r][c] = nowChar;
					int tempDir = tankStr.indexOf(nowChar);
					if(tempDir != -1) {
						curR = r;
						curC = c;
						curDir = tempDir;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String inpStr = br.readLine();
			for(int i=0; i<N; ++i) {
				int[] nPos = battleField(board, curR, curC, curDir, inpStr.charAt(i));
				curR = nPos[0];
				curC = nPos[1];
				curDir = nPos[2];
				
//				Arrays.stream(board).map(Arrays::toString).forEach(System.out::println);
//				System.out.println();
			}
			System.out.print("#" + (test+1) + " ");
			for(int r=0; r<H; ++r) {
				System.out.println(new String(board[r]));
			}
			
		}
	}
	
	
	private static int[] battleField(char[][] board, int curR, int curC, int curDir, char command) {
		int[] nPos = new int[] {curR, curC, curDir};
		String udlr = "UDLR";
		
		switch(command) {
		case 'S':
			shoot(board, curR, curC, curDir);
			break;
		case 'U':
		case 'D':
		case 'L':
		case 'R':
			nPos = move(board, curR, curC, udlr.indexOf(command));
			break;
		}
		return nPos;
	}
	
	private static void shoot(char[][] board, int curR, int curC, int dir ) {
		int H = board.length;
		int W = board[0].length;
		int[][] dirArr = new int[][]{{-1,0},{1,0},{0,-1},{0,1}}; 
		int nr = curR + dirArr[dir][0];
		int nc = curC + dirArr[dir][1];
		
		//return -> 포탄 사라짐
		if(nr <0 || nc <0 || nr>=H || nc >=W) {
			return;
		}
		if(board[nr][nc] == '#') return;
		
		if(board[nr][nc] == '*') {
			board[nr][nc] = '.';
			return;
		}
		shoot(board, nr, nc, dir);
	}
	
	private static int[] move(char[][] board, int curR, int curC, int dir) {
		int H = board.length;
		int W = board[0].length;
		int[][] dirArr = new int[][]{{-1,0},{1,0},{0,-1},{0,1}}; 
		String tankStr = "^v<>";
		int nr = curR + dirArr[dir][0];
		int nc = curC + dirArr[dir][1];
		boolean canMove = true;
		
		if(nr <0 || nc <0 || nr>=H || nc >=W) canMove = false;

		else if(board[nr][nc] != '.') canMove = false;
		
		if(!canMove) {
			board[curR][curC] = tankStr.charAt(dir);
			return new int[]{curR,curC,dir};
		}
		else {
			board[curR][curC] = '.';
			board[nr][nc] = tankStr.charAt(dir);
			return new int[]{nr,nc,dir};	
		}
	}
}