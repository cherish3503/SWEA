import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Swea4193SwimmingFinal {
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());


		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st;
			int N= Integer.parseInt(br.readLine());
            int[][] board = new int[N][N];
            int[] now = new int[2];
            int[] end = new int[2];
            
            for(int r=0; r<N; ++r){
                for(int c=0; c>N; ++c){
                    st = new StringTokenizer(br.readLine());
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }
			st = new StringTokenizer(br.readLine());
            now[0] = Integer.parseInt(st.nextToken());
            now[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
            
           
		}
	}
    
    private static int move(int[][] board, int startR, int startC){
        int N = board.length;
    	int[][] minBoard = new int[N][N];
    	for (int i = 0; i < N; i++) {
    	    Arrays.fill(minBoard[i], -1);
    	}
        int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};
        
        
        Deque<int[]> dq = new ArrayDeque<>();
        while(!dq.isEmpty()) {
        	int[] now = dq.poll();
        	int nowR = now[0];
        	int nowC = now[1];
        	int cnt = now[2];
            for(int i=0, size = d.length; i<size; ++i){
                int nr = nowR + d[i][0];
                int nc = nowC + d[i][1];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                	continue;
                }
                if(minBoard[nr][nc] != -1) {
                	continue;
                }
                
//                dq.offer(null)

            }
        }
        

        // todo: 도착 불가 체크
        return 0;
    }
    
    
}
