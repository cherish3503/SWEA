import java.util.*;
import java.io.*;

public class Swea1247Route {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; ++test) {
			int N = Integer.parseInt(br.readLine());
			int[] start = new int[2];
			int[] end = new int[2];
			int[][] places = new int[N][2];

			
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; ++i) {
				places[i][0]= Integer.parseInt(st.nextToken());
				places[i][1]= Integer.parseInt(st.nextToken());
			}
			
			boolean[] visited = new boolean[N];
			System.out.println("#" + test +" "+ visit(places, visited, 0, N, start, end, 0)); 
		}

	}
	
	private static int visit(int[][] places, boolean[] visited, int depth, int N, int[] now, int[] end, int sum) {
		if(depth >= N) {
			return sum+getDistance(now, end);
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; ++i) {
			if(visited[i]) continue;
			visited[i] = true;
//			getDistance(now, places[i]);
			min = Math.min(min, visit(places, visited, depth+1, N, places[i], end, sum+ getDistance(now, places[i])));
			visited[i] = false;
		}
		return min;
	}
	
	private static int getDistance(int[] a, int[] b) {
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
	}

}
