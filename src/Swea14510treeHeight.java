import java.io.*;
import java.util.*;
/*
시간 : 109ms
메모리 : 25,984 kb
전략 : 그리디
1. 나무 높이를 최대 높이로 빼준다. -> 최대 높이를 현재 높이보다 더 높이는 경우는 최소가 아니다.
2. 남은 높이를 2를 최대화 하는 방법으로 2와 1로 분해한다. ex) 3->2+1  / 6->2+2+2
3. 2는 1 두개로 분해 할 수 있고, 2와 1의 개수 차이를 1 차이나게 만든다.
		아래는 계산의 편의를 고려해 횟수 세는 방법이다.
		1의 개수가 더 많은 경우 바로 횟수를 계산한다.
		2의 개수가 많은 경우 2가 더 많은 2,1,0의 차이로 줄이고 횟수를 계산해준다
*/

public class Swea14510treeHeight {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; ++test) {
			//쉬기 : 0 물주기: 1 2
			int N = Integer.parseInt(br.readLine());
			int[] trees = new int[N];
			int cnt1 = 0;
			int cnt2 = 0;
			st = new StringTokenizer(br.readLine());
			int maxH = 0;
			for(int i=0; i<N; ++i) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, trees[i]);
			}
			
			for(int i=0; i<N; ++i) {
				int height = maxH-trees[i];
				cnt1 += height%2;
				cnt2 += height/2;
			}

			int day = 0;
			// cnt1 과 cnt2가 하나 차이 나야됨
			if(cnt1> cnt2) {	// cnt1이 더 큰경우
				day = cnt1*2-1; 
			}
			else { // cnt2가 더 많은 경우
				int minus = cnt2-cnt1;
				cnt1 += minus/3*2; //cnt2이 1감소 할때마다 cnt1이 2증가
				cnt2 -= minus/3;
				if(cnt2 - cnt1 == 2) { //cnt2가 2많은경우, cnt2-1 cnt1+2  한다
					day = cnt2*2-1;
				}
				else day = cnt2*2; // cnt2가 1 많거나 둘이 같은 경우
			}
			
			
			System.out.println("#" + (test+1) + " " +day);
			
		}

	}

}
