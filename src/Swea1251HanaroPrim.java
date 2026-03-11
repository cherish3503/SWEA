import java.util.*;
import java.io.*;


public class Swea1251HanaroPrim {
	static class Edge{
		int v;
		long dist;
		
		public Edge(int v, long dist) {
			super();
			this.v = v;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {

			int N = Integer.parseInt(br.readLine());

			List<Edge>[] graph = new ArrayList[N];
			int[][] islands = new int[N][2];

			for(int i =0; i<N; ++i) graph[i] = new ArrayList<>();

			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				islands[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				islands[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			
			
			for(int i=0; i<N; ++i) {
				for(int j=i+1; j<N; ++j) {
//					if(i == j) continue;
					long dist = getDistance(islands[i], islands[j]);
					graph[i].add(new Edge(j ,dist));
					graph[j].add(new Edge(i ,dist));
				}
			}
			
			

			long result = prim(graph, 0);
			System.out.println("#" + test + " " + Math.round(result*E));
			
		}
	}
	
	private static long prim(List<Edge>[] graph, int start) {
		int N = graph.length;
		boolean[] visited = new boolean[N];
		long result = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2) -> Long.compare(e1.dist, e2.dist));
		pq.offer(new Edge(0,0));
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			result += now.dist;
			cnt++;
			if(cnt == N) break;
			
			for(Edge next : graph[now.v]) if(!visited[next.v]) pq.offer(next);
		}
		return result;
	}
	
	private static long getDistance(int[] pos1, int[] pos2) {
		long dx = pos1[0]-pos2[0];
		long dy = pos1[1]-pos2[1];
		
		return dx*dx + dy*dy;
	}


}
