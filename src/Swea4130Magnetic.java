import java.io.*;
import java.util.*;

public class Swea4130Magnetic {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int n = 4; //magnetic num
		int mSize = 8;
		
		for(int test=0; test<T; ++test) {
			int K = Integer.parseInt(br.readLine());
			ArrayList<Integer>[] magnetic = new ArrayList[n];
			for(int i=0; i<n; ++i)	magnetic[i] = new ArrayList<>();
			
			for(int i=0; i<n; ++i) { //4개의 자석
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<mSize; ++j) {
					magnetic[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for(int i=0; i<K; ++i) {
				st = new StringTokenizer(br.readLine());
				rotateAll(magnetic, Integer.parseInt(st.nextToken())-1, st.nextToken().equals("1") ? true : false);
				//연쇄
			}
			int sum = 0;
			for(int i=0; i<n; ++i) {
				sum += magnetic[i].get(0)<<i;
			}
			System.out.println("#" + (test+1) + " " + sum);
		}
	}
	
	private static void rotateAll(List<Integer>[] magnetic, int idx, boolean cw) {
		int n = magnetic.length;
		int s = idx;
		int e = idx;
		
		//2,6
		while(s>0) { //left
			if(magnetic[s].get(6) != magnetic[s-1].get(2)) {
				s--;
			}
			else break;
		}

		while(e<n-1) { //right
			if(magnetic[e].get(2) != magnetic[e+1].get(6)) {
				e++;
			}
			else break;
		}
		
		for(int i=s; i<=e; ++i) {
			rotate(magnetic[i], ((i-idx)%2 == 0) ^ !cw);
		}
		return;
	}
	
	private static void rotate(List<Integer> ls, boolean cw) {
		int mSize = ls.size();
		if(cw) {
			ls.add(0, ls.remove(mSize-1));
			
		}
		else{
			ls.add(ls.remove(0));
		}
		return;
	}
}
