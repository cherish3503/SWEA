
import java.util.*;
import java.io.*;

public class Swea5644Wireless {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int cntBC = Integer.parseInt(st.nextToken());
			int[][] bcList = new int[cntBC][4]; // AC info (x,y,c,p) c:충전거리 p:충전량
			
			int[] moveA = new int[M];
			int[] moveB = new int[M];
			int[] playerA = {0,0}; //x,y
			int[] playerB = {9,9}; //x,y
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; ++i) moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; ++i) moveB[i] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<cntBC; ++i) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcList[i] = new int[] {x,y,c,p};
			}
			
			
			System.out.println(simulation(bcList, moveA, moveB, playerA, playerB));
		}
	}
	
	private static int simulation(int[][] bcList, int[] moveA, int[] moveB, int[] playerA, int[] playerB) {
		int M = moveA.length;
		int sum =0;
		for(int i=0; i<M; ++i) {
			move(playerA, moveA[i]);
			move(playerB, moveB[i]);
			
			sum += getP(bcList, playerA, playerB);
			
		}
		
		return sum;
	}
	
	private static int getP(int[][] bcList, int[] a, int[] b) {
		int max = 0;
		List<Integer> aConnect = new ArrayList<>();
		List<Integer> bConnect = new ArrayList<>();
		for(int i=0; i<bcList.length; ++i) {
			if(canConnect(bcList[i], a)) aConnect.add(i); 	// a가 연결가능 한경우
			if(canConnect(bcList[i], b)) bConnect.add(i);	// b가 연결가능 한경우
		}
		if(aConnect.size() == 0) {
			if(bConnect.size() == 0) return 0; // 둘다 없음
			else for(int bcB : bConnect) max = Math.max(max, bcList[bcB][3]); // b만 됨
		}
		else {
			if(bConnect.size() == 0) for(int bcA : aConnect) max = Math.max(max, bcList[bcA][3]); //a만 됨
			else { // 둘 다 있는 경우
				for(int bcA : aConnect) {
					for(int bcB : bConnect) {
						if(bcA == bcB) max = Math.max(max, bcList[bcA][3]);
						else max = Math.max(max, bcList[bcA][3] + bcList[bcB][3]);
					}
				}
			}
		}
		return max;
	}
	
	private static boolean canConnect(int[] bc, int[] p) {
		return bc[2] >= Math.abs(bc[0]-p[0]) + Math.abs(bc[1]-p[1]);
	}
	
	private static void move(int p[], int dir) {
		int[][] dirArr = {{0,0},{0,-1},{1,0},{0,1},{-1,0}}; // 0urdl
		
		p[0] += dirArr[dir][0]; 
		p[1] += dirArr[dir][1]; 
		return;
	}
}
