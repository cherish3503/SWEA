import java.util.*;
import java.io.*;


public class Swea2383lunch {
	static class Pos{
		int r;
		int c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static List<Pos> stairs;
	private static List<Integer> stairsH;
	private static List<Pos> people;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] board = new int[N][N];
			stairs = new ArrayList<>();
			stairsH = new ArrayList<>();
			people = new ArrayList<>();
			
			for(int r=0; r<N; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; ++c){
					int now = Integer.parseInt(st.nextToken());
					board[r][c] = now;
					if(now == 1) people.add(new Pos(r,c));
					else if(now != 0) {
						stairs.add(new Pos(r,c));
						stairsH.add(now);
					}
				}
			}
			
			System.out.println("#" + test + " " + selectStair(0, 0));
		}
	}
	
	private static int selectStair(int depth, int bit) {
		if(depth == people.size()) {
			return simulation(bit);
//			return;
		}
//		int min = Integer.MAX_VALUE;
		return Math.min(selectStair(depth+1, bit), selectStair(depth+1, bit|(1<<depth)));
	}
	
	
	private static int simulation(int bit) {
		List<Integer>[] order = new ArrayList[2];
		for(int i=0; i<2; ++i) order[i] = new ArrayList<>();
		
		for(int i=0; i<people.size(); ++i) {
			int strIdx = (bit>>i)&1;
			order[strIdx].add(dist(stairs.get(strIdx), people.get(i)));
		}
		for(int i=0; i<2; ++i) order[i].sort(Integer::compare);
		
		int result = Integer.MIN_VALUE;
		for(int i=0; i<2; ++i) result = Math.max(result, orderStair(order[i], stairsH.get(i)));
		
		return result;
	}
	
	private static int orderStair(List<Integer> order, int stairH) {
		int idx = 0;
		int len = order.size();
		Queue<Integer> dq = new ArrayDeque<>();
		while(dq.size()<3 && idx < len) {
			dq.add(order.get(idx++)+1 + stairH);
		}
		
		int cur = 0;
		while(!dq.isEmpty()) {
			cur = dq.poll();
			if(idx < len) {
				dq.offer(Math.max(cur, order.get(idx++)+1) + stairH);
			}
		}
		
		return cur;
	}
	
	private static int dist(Pos p1, Pos p2) {
		return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c); 
	}
}