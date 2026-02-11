import java.io.*;
import java.util.*;


public class Swea1767process {
	public static int maxCoreCnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			maxCoreCnt = Integer.MIN_VALUE;
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			List<int[]> cores = new ArrayList<>();
			for(int r=0; r<N; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; ++c) {
					int now = Integer.parseInt(st.nextToken());
					board[r][c] = now;
					if(now == 1) cores.add(new int[]{r,c});
				}
			}
			
			// 최대한 많은 코어를 연결할때 직선의 최솟값
			//5^12
			int[] resArr = new int[cores.size()+1];
			for(int i=1; i<resArr.length; ++i) resArr[i] = Integer.MAX_VALUE;
			setCore(board, cores, 0, 0, 0, resArr);

			System.out.println("#" + (test+1) + " " + resArr[maxCoreCnt]);
		}
		
	}
	
	
	// 선을 그으면서 그을 수 있는지 확인
	// 그을 수 있으면 끝까지 가서 setCore(다음 셀에 대해 연산)
	// 실패 했을지 리턴

	private static void setLine(int[][] board, List<int[]> cores, int coreDepth, int r, int c, int dir, int coreCnt, int lineCnt , int[] res) {
		int[][] d = {{-1,0},{1,0},{0,-1},{0,1}}; // udlr
		int len = board.length;
		int nr = r+d[dir][0];
		int nc = c+d[dir][1];
		
		if(nr<0 || nc<0 || nr>=len || nc>=len) {
			setCore(board, cores, coreDepth+1, coreCnt, lineCnt, res);
			return;
		}
		
		if(board[nr][nc] != 0) { // 실패
			return; 
		}
		
		board[nr][nc] = 2;
		setLine(board, cores, coreDepth, nr, nc, dir, coreCnt, lineCnt+1, res);
		board[nr][nc] = 0;

		return;
	}
	
	// 다음 코어에 대한 연산
	// 모든 코어에 대한 연산을 했을 때 결과를 저장
	// 4방으로 setLine(줄을 그음)을 한다
	// 이 코어를 선택하지 않을 경우에 대해 setCore를 한다 
	// 나온 결과 중 최솟값을 리턴
	private static void setCore(int[][] board, List<int[]> cores, int coreDepth, int coreCnt, int lineCnt , int[] res) {
		if(coreDepth >= cores.size()) {
			maxCoreCnt = Math.max(maxCoreCnt, coreCnt);
			res[coreCnt] = Math.min(res[coreCnt], lineCnt);
			return;
		}
		
		int[][] d = {{-1,0},{1,0},{0,-1},{0,1}};
		int[] pos = cores.get(coreDepth);
		
		// 이 코어를 선택
		for(int dir=0; dir<d.length; ++dir) {	//udlr
			setLine(board, cores, coreDepth, pos[0], pos[1], dir, coreCnt+1, lineCnt, res); // cnt+1
		}
		
		// 이 코어를 선택하지 않는 경우
		if(maxCoreCnt <= cores.size() - coreDepth + coreCnt) { // 최대 코어의 개수보다 작은 코어의 개수는 더이상 계산안함
			setCore(board, cores, coreDepth+1, coreCnt, lineCnt, res); 
		}
		
		return;
	}
	
}



