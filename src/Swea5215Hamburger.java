import java.io.*;
import java.util.*;


public class Swea5215Hamburger {
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test=0; test<T; ++test) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int L = Integer.parseInt(st.nextToken());
        	
        	
        	int[][] ingredientArr = new int[N][2]; // score, cal 
        	int[][] knapsack = new int[N+1][L+1];
        	for(int i=0; i<N; ++i) {
        		st = new StringTokenizer(br.readLine());
        		ingredientArr[i][0] = Integer.parseInt(st.nextToken());
        		ingredientArr[i][1] = Integer.parseInt(st.nextToken());
        		
        	}
        	
        	for(int i=1; i<=N; ++i) {
        		for(int j=1; j<=L; ++j) {
        			int val = ingredientArr[i-1][0];
        			int weight = ingredientArr[i-1][1];
        			int res;
        			if(j < weight) {
        				res = knapsack[i-1][j];
        			}
        			else res = Math.max(knapsack[i-1][j-weight] +val, knapsack[i-1][j]);
        			knapsack[i][j] = res;
        		}
        	}
        	System.out.println("#" + (test+1) +" " +knapsack[N][L] );
        }
	}
}
