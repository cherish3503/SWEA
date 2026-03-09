
import java.util.*;
import java.io.*;

public class Swea7469Village {
	static int[] parents; 
	static int[] ranks;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			ranks = new int[N+1];
			for(int i=1; i<=N; ++i) parents[i] = i;
			
			for(int i=0; i<M; ++i) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}
			
			Set<Integer> set = new HashSet<>();
			for(int i=1; i<=N; ++i) {
				set.add(getRoot(i));
			}
			
			
			System.out.println("#" + test + " " + set.size());
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
