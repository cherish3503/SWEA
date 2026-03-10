import java.util.*;
import java.io.*;


public class Swea3124MST {
	static class Edge{
		int v1;
		int v2;
		int dist;
		
		public Edge(int v1, int v2, int dist) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			Edge[] edges = new Edge[E];
			int[] parents = new int[V+1];
			int[] ranks = new int[V+1];
			for(int i=1; i<=V; ++i) parents[i] = i;
			
			for(int i=0; i<E; ++i) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());

				edges[i] = new Edge(v1,v2,dist);
			}
			
			Arrays.sort(edges, (e1,e2)->Integer.compare(e1.dist, e2.dist));
			long result = 0;
			for(Edge edge: edges) {
				if(find(parents, edge.v1) == find(parents, edge.v2)) continue; // 이미 경로가 존재하면 실패
				union(parents, ranks, edge.v1, edge.v2);
				result += edge.dist;
			}
			System.out.println("#" + test + " " + result);
			
		}
	}
	
	private static void union(int[] parents, int[] ranks, int a, int b) {
		int rootA = find(parents, a);
		int rootB = find(parents, b);
		
		if(ranks[rootA] >= ranks[rootB]) {
			if(ranks[rootA] == ranks[rootB]) ranks[rootA]++;
			parents[rootB] = rootA;
			
		}
		else parents[rootA] = rootB; 
		
	}
	private static int find(int[] parents, int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents, parents[x]);
	}


}
