import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Swea16504 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			int[][] board = makeBoard(arr, 100);
//			Arrays.stream(board).forEach(bb -> {Arrays.stream(bb).forEach(v->System.out.print(v + " ")); System.out.println();});
//			System.out.println();
			
			board = rotate(board);
//			Arrays.stream(board).forEach(bb -> {Arrays.stream(bb).forEach(v->System.out.print(v + " ")); System.out.println();});
//			System.out.println();
//			
			System.out.println("#" + (i+1) + " " + maxFall(board));
		}
		
		
		
	}
	
	
	public static int[][] rotate(int[][] board) {
		int height = board.length;
		int width = board[0].length;
		int[][] result = new int[width][height];
		

		
		
		for(int r=0; r<height; ++r) {
			for(int c=0; c<width; ++c) {
				result[c][r] = board[r][c];
			}
		}
		
		
		for(int r=0; r<width; ++r) {
			for(int c=0; c<height/2; ++c) {
				int temp = result[r][c];
				result[r][c] = result[r][height-1-c];
				result[r][height-1-c] = temp;
			}
		}	
	
		
		
		
		return result;

	}
	
	
	
	
	
	
	public static int[][] makeBoard(int[] arr, int height) {
		int width = arr.length;
		int[][] board = new int[height][width];
		for(int c=0; c<width; ++c) {
			for(int r=0; r<arr[c]; ++r) {
				board[height-1-r][c] = 1;
			}
		}
		return board;
	}
	
	public static int maxFall(int[][] board) {
		int max = 0;
		int height = board.length;
		int width = board[0].length;
		
		
		
		
	for(int c=0; c<width; ++c) {
		List<Integer> heightList = new ArrayList<>();
		for(int r=0; r<height; ++r) {
			if(board[r][c] == 1) {
				heightList.add(height-1-r);
			}
		}
		int cntBox = heightList.size();
		for(int i=0; i< cntBox; ++i) {
			max = Math.max(heightList.get(i)-(cntBox-i-1), max);
		}
		
		
	}
		
		
		
		return max;
	}
	
	
}
