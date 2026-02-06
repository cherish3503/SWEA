import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Swea8275Hamster {
	public static int maxH;
	public static List<int[]> maxCage;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=0; test<T; ++test) {
			maxH = Integer.MIN_VALUE;
			maxCage = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] conditions = new int[M][3]; 
			for(int m=0; m<M; ++m) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<3; ++c) {
					conditions[m][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			Arrays.sort(conditions, (a,b) -> a[0]-b[0]);
			
//			Arrays.stream(conditions).map(Arrays::toString).forEach(System.out::println);
			int[] prefixCage = new int[N+1];
			setCage(prefixCage, conditions, N, X, 1);
//			maxCage.stream().map(Arrays::toString).forEach(System.out::println);

			String resStr;
			if(maxCage.isEmpty()) {
				resStr = "-1";
			}
			else { // 사전 상 앞선 것 출력
				int[] resCage = new int[N];
				for(int i=0; i<N; ++i) {
					resCage[i] = maxCage.get(0)[i+1] - maxCage.get(0)[i];
				}
				resStr = Arrays.stream(resCage).mapToObj(String::valueOf).collect(Collectors.joining(" "));
			}
			
			System.out.println("#" + (test+1) + " " + resStr);
			
		}

	}
	
	// 조건 정렬 후 사용
	// 완전 탐색
	private static void setCage(int[] prefixCage, int[][] conditions, int N, int X, int idx) {
		if(idx == N+1) { // 햄스터의 수를 최대로 하는 경우만 저장
			if(prefixCage[N] == maxH) {
				maxCage.add(Arrays.copyOf(prefixCage, N+1));
			}
			if(prefixCage[N] > maxH) {
				maxH = prefixCage[N];
				maxCage = new ArrayList<>();
				maxCage.add(Arrays.copyOf(prefixCage, N+1));
			}
			return; 
		}
		
		for(int n=0; n<=X; ++n) {
			prefixCage[idx] = prefixCage[idx-1] + n;
			boolean flag = true;
			for(int[] c : conditions) { //정렬된 조건
				int s = c[0];
				int e = c[1];
				int maxN = c[2];
				if(idx < s) break;
				if(idx > e) continue;
				if(idx == e && prefixCage[idx] - prefixCage[s-1] != maxN) { // e에서 햄스터의 수가 일치하는 지 체크
					flag = false;
					continue;
				}
				if(prefixCage[idx] - prefixCage[s-1] > maxN) { // s-e 사이에서 햄스터의 수를 초과 하는 지 체크
					flag = false;
					continue;
				}
			}
			if(flag) setCage(prefixCage, conditions, N, X, idx+1);
		}
	}
}
