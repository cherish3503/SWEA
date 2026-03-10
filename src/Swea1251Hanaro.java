import java.util.*;
import java.io.*;


public class Swea1251Hanaro {
	static class Edge{
		int v1;
		int v2;
		long dist;
		
		public Edge(int v1, int v2, long dist) {
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

			int N = Integer.parseInt(br.readLine());

			List<Edge> edges = new ArrayList<>();
			int[][] islands = new int[N][2]; 
			
			int[] parents = new int[N];
			int[] ranks = new int[N];
			for(int i=0; i<N; ++i) parents[i] = i;
			
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
					edges.add(new Edge(i, j ,getDistance(islands[i], islands[j])));
				}
			}
			
			long result = 0;
			edges.sort((e1,e2)->Long.compare(e1.dist, e2.dist));
			
			for(Edge edge : edges) {
				if(find(parents, edge.v1) == find(parents, edge.v2)) continue; // 이미 경로가 존재하면 실패
				union(parents, ranks, edge.v1, edge.v2);
				result += edge.dist;
			}
			System.out.println("#" + test + " " + Math.round(result*E));
			
		}
	}
	
	private static long getDistance(int[] pos1, int[] pos2) {
		long dx = pos1[0]-pos2[0];
		long dy = pos1[1]-pos2[1];
		
		return dx*dx + dy*dy;
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
