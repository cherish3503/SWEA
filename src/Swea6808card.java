import java.util.*;
import java.io.*;

public class Swea6808card {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			int N = 9;
			st = new StringTokenizer(br.readLine());
			boolean[] cntOpp = new boolean[N*2+1];
			int[] opponent = new int[N]; // 순서 있음
			int[] my = new int[N]; // 순서 없음
			boolean[] mySelected = new boolean[N];
			
			for(int i=0; i<N; ++i) {
				int card = Integer.parseInt(st.nextToken());
				opponent[i] = card;
				cntOpp[card] = true;
			}
			for(int i=1, idx=0; i<2*N+1; ++i) {
				if(!cntOpp[i])	my[idx++] = i; 
			}
//			System.out.println(Arrays.toString(my));
			int[] resCnt = new int[2]; // lose, win
			game(opponent, my, mySelected, 0, 0, N, 0, resCnt);
			System.out.println("#" + (test+1) + " " + resCnt[0] + " " + resCnt[1]);
		}
		
	}
	
	private static void game(int[] opponent, int[] my, boolean[] mySelected, int oppScore, int myScore, int N, int depth, int[] resCnt) {
		if(depth >= N) { // 길이보다 한 칸 더 내려 감
			if(myScore < oppScore)	resCnt[0]++;
			else if(myScore > oppScore)	resCnt[1]++;
			return;
		}
		
		int oppCard = opponent[depth];
		for(int i=0; i<N; ++i) {
			if(!mySelected[i]) {
				mySelected[i] = true;
				
				int myCard = my[i];
				int sum = oppCard + myCard;
				if(oppCard < myCard) { //win
					game(opponent, my, mySelected, oppScore, myScore+sum, N, depth+1, resCnt);
				}
				else { //lose
					game(opponent, my, mySelected, oppScore+sum, myScore, N, depth+1, resCnt);
				}
				mySelected[i] = false;
				
			}
		}

		
		
		return;
	}
	
	
}
