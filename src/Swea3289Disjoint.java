import java.io.*;
import java.util.*;

public class Swea3289Disjoint {
	private static class Node{
		Node parent;
		
	}
	static int[] parents; 
	static int[] ranks;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			ranks = new int[n+1];
			for(int i=0; i<=n; ++i) parents[i] = i;
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<m; ++i) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(cmd == 0) union(a,b);

				else if(cmd == 1) {
					if(isSameSet(a,b)) sb.append(1);
					else sb.append(0);
				}
			}
			
			System.out.println("#" + test + " " + sb);
		}
	}
	
	
	private static void union(int a, int b) {
		int rootA = getRoot(a);
		int rankA = ranks[rootA];
		int rootB = getRoot(b);
		int rankB = ranks[rootB];
		
		if(rankA >= rankB) { // 같은경우도 A밑에 B를 붙임
			parents[rootB] = rootA;
			if(rankA == rankB) {
				ranks[rootA]++;
			}
		}
		else {
			parents[rootA] = rootB;
		}
		
		return;
		
	}
	
	private static int getRoot(int x) {
		if(parents[x] == x) return x;
		return parents[x] = getRoot(parents[x]);
	}
	
	private static boolean isSameSet(int a, int b) {
		if(getRoot(a) == getRoot(b)) return true;
		else return false;
	}
}
