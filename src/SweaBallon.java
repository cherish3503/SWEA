import java.util.*;
import java.io.*;

public class SweaBallon {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=0; test<T; ++test) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> balloons = new ArrayList<>(N);
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				balloons.add(Integer.parseInt(st.nextToken()));
			}
			System.out.println("#" + (test+1)+" " + balloonPop(balloons, balloons.size(),0, 0));
		}
		

	}
	
	// 처음에 list 0~n-1까지 고름
	// 다음에 list 0~n-2
	private static int balloonPop(List<Integer> balloons, int N, int depth, int score) {
		if(depth >= N) return score;
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N-depth; ++i) {
			int val = balloons.get(i);
			int now = 1;
			if(balloons.size() == 1) now = val;
			if(i>0) now *= balloons.get(i-1);
			if(i<balloons.size()-1) now *= balloons.get(i+1);

			balloons.remove(i); // O(n)
			max = Math.max(max, balloonPop(balloons, N, depth+1, score+now));
			balloons.add(i, val); //O(n)
		}

		return max;
	}

}
