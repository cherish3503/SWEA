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
            int[] dest = new int[2];
            
            for(int r=0; r<N; ++r){
            	st = new StringTokenizer(br.readLine());
                for(int c=0; c<N; ++c){
               
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
//            Arrays.stream(board).map(Arrays::toString).forEach(System.out::println);
            
			st = new StringTokenizer(br.readLine());
            now[0] = Integer.parseInt(st.nextToken());
            now[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            dest[0] = Integer.parseInt(st.nextToken());
            dest[1] = Integer.parseInt(st.nextToken());
            
            System.out.println("#"+(test_case)+" " + swimFinal(board, now[0], now[1], dest[0], dest[1]));
		}
	}
    
    private static int swimFinal(int[][] board, int startR, int startC, int destR, int destC){
        int N = board.length;
    	boolean[][] visited = new boolean[N][N]; //false
//    	for (int i = 0; i < N; i++) {
//    	    Arrays.fill(minBoard[i], -1);
//    	}
        int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};
        
        Deque<int[]> dq = new ArrayDeque<>();
        
        visited[startR][startC] = true;
        dq.add(new int[]{startR, startC, 0});
        
        while(!dq.isEmpty()) {
//        	dq.stream().map(Arrays::toString).forEach(System.out::println);
//        	System.out.println();
        	
        	int[] now = dq.poll();
        	int nowR = now[0];
        	int nowC = now[1];
        	int cnt = now[2];
        	
            if(nowR == destR && nowC == destC) {
            	return cnt;
            }
            
            for(int i=0, size = d.length; i<size; ++i){
                int nr = nowR + d[i][0];
                int nc = nowC + d[i][1];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                	continue;
                }
                if(visited[nr][nc]) {
                	continue;
                }
                if(board[nr][nc] == 1) {
                	continue;
                }
                
//                System.out.println("현" + Arrays.toString(now));
                if(board[nr][nc] == 2) {

                	if(cnt%3 != 2) { // 제자리에서 기다림
//                      visited[nowR][nowC] = false;
                        dq.add(new int[]{nowR, nowC, cnt+1});
                        continue;
                	}
//                	else {
//                		visited[nowR][nowC] = true;
//                	}
                }

                visited[nr][nc] = true;
                dq.add(new int[]{nr, nc, cnt+1});

            }
        }
        

        // todo: 도착 불가 체크
        return -1;
    }
    
    
}















