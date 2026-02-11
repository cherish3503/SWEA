import java.io.*;
import java.util.*;


public class SweaApple {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			// greedy : 다음 사과를 나의 직진 혹은 (앞 +오른쪽)으로 오게 한다.
			int N = Integer.parseInt(br.readLine());
			Map<Integer, int[]> apples = new HashMap<>(); 
			int appleN = 0;
			
			int[][] board = new int[N][N];
			for(int r=0; r<N; ++r) {
				String line = br.readLine();
				for(int c=0; c<N; ++c) {
					int now = line.charAt(c) -'0';
					board[r][c] = now;
					if(now != 0) {
						apples.put(now, new int[]{r,c});
						appleN ++;
					}
				}
			}
			
			for(int i=1; i<appleN; ++i) {
				if
			}
			
			
		}
	}
	
	private static int getRot(int dir) {
		
		return 0;
	}
}
