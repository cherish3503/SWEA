import java.io.*;
import java.util.*;

public class Swea3421Burger {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] cantBurgerBit = new int[M];
			
			// 완탐 - 안되는경우
			for(int m=0; m<M; ++m) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				cantBurgerBit[m] = (1<<(a-1)) | (1<<(b-1)); // 두 조합의 비트를 1로 만든다.
			}
			
			System.out.println("#" +(test+1)+ " " + cntBurger(N,M, cantBurgerBit, 0, 0));
		}
	}
	private static int cntBurger(int N, int M, int[] cantBurgerBit, int depth, int bit) {
		if(depth == N) {
			for(int m=0; m<M; ++m) { //  안되는 조합을 갖는지 확인 : 해당 비트가 모두 1인지 체크 
				if((bit & cantBurgerBit[m]) == cantBurgerBit[m])
					return 0;
			}
			return 1;
		}
		
		int cnt = 0;
		cnt += cntBurger(N, M, cantBurgerBit, depth+1, bit);
		cnt += cntBurger(N, M, cantBurgerBit, depth+1, bit | (1<<depth));
		return cnt;
	}
}
