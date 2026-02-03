import java.util.*;
import java.io.*;

public class Swea1233Calc {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		
		int T = 10;
		
		// 모든 leaf node 는 숫자이다.
		// leaf노드가 아닌 모든 노드는 연산자이고, 2개의 자식 노드를 가진다.
		
		for(int test=0; test<T; ++test) {
			
			
			
			System.out.println("#" + (test+1) + " " + (canCalc() ? 1 : 0));
		}

	}
	
	private static boolean canCalc() throws Exception{
		char[] ops = {'*','/','+','-'};
		int N = Integer.parseInt(br.readLine());
		boolean result = true;
		
		for(int i=0; i<N; ++i) {
			String[] line =  br.readLine().split(" ");
			int len = line.length;
			if(len == 4) {
				boolean flag = false;
				for(char op : ops) {
					if(line[1].charAt(0) == op) flag = true;
				}
				if(!flag) result = false;
			}
			else if(len ==2) {
				for(char op : ops) {
					if(line[1].charAt(0) == op) result = false;
				}
			}
			else result =  false;
		}
		
		return result;
	}
}
