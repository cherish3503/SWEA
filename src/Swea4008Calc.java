import java.util.*;
import java.io.*;

public class Swea4008Calc {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			
			int N = Integer.parseInt(br.readLine());
			int[] opPerm = new int[N-1];
			int[] cards = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int op=0, idx=0; op<4; ++op) {
				int opCnt = Integer.parseInt(st.nextToken());
				for(int i=0; i<opCnt; ++i) {
					opPerm[idx++] = op;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println("#" +(test+1) +" " + getMax(cards, opPerm));

		}
		
		
	}
	
	private static boolean nextPerm(int[] arr) {
		int N = arr.length;
		
		// 등산
		int i=N-1;
		while(i>0 && arr[i-1] >= arr[i]) i--;
		
		// 마지막 순열
		if(i==0) return false;
		
		// 바꿀 원소 찾기
		int j=N-1;
		while(arr[j] <= arr[i-1]) j--;
		swap(arr, j, i-1);
		
		//reverse
		int k=N-1;
		while(i<k) {
			swap(arr,i++,k--);
		}

		return true;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private static int calc(int[] cards, int[] opPerm) {
		int N = cards.length;
		int result = cards[0];
		for(int i=0; i<N-1; ++i) {
			switch(opPerm[i]) {
			case 0:
				result += cards[i+1];
				break;
			case 1:
				result -= cards[i+1];
				break;
			case 2:
				result *= cards[i+1];
				break;
			case 3:
				result /= cards[i+1];
				break;
			}
		}
		return result;
	}
	
	private static int getMax(int[] cards, int[] opPerm) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		do {
			int res = calc(cards, opPerm);
			max = Math.max(max, res);
			min = Math.min(min, res);
//			System.out.println(Arrays.toString(opPerm));
		}
		while(nextPerm(opPerm));
		
		return max-min;
	}
}
