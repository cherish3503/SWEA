import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
//import java.util.ArrayList;
//import java.util.List;

public class SweaPlus3260 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int ttt=0; ttt<T; ++ttt) {
			String[] strArr = br.readLine().split(" ");
			String strA = strArr[0];
			String strB = strArr[1];
			
			
//			List<Integer> l = new ArrayList<>();

			Deque<Integer> dq1 = new ArrayDeque<>();
			Deque<Integer> dq2 = new ArrayDeque<>();
			StringBuilder sb = new StringBuilder();
			
			
			for(int i=0, size=strA.length(); i<size; ++i) {
				dq1.add(strA.getCharAt(size-1-i));
			}
			
			
			
			
			
			
			
			
			
			int carry = 0;
			boolean empty1 = false; boolean empty2 = false;
			
			while(true) {
				int n1 = 0; int n2 = 0;
				
				empty1 = dq1.isEmpty();
				empty2 = dq2.isEmpty();
				
				if(empty1 && empty2) {
					if(carry != 0) {
						sb.append((char)(carry + '0'));
					}
					break;
				}
				
				
				if(!empty1) {
					n1 = dq1.pollLast();
				}
	
				if(!empty2) {
					n2 = dq2.pollLast();
				}
				
				sb.append(digitSum(n1, n2, carry));
				carry = carrySum(n1, n2, carry);
			}
			
			System.out.println("#" + (ttt+1) + " " + sb.toString());
			
			
		}
		
		
		
		
		
		
	}
	
	// int 2^31

	
	private static int digitSum(int a, int b, int c) { // 일의 자리 덧셈, 일의 자리만 반환
		return (a+b+c)%10;
	}
	
	private static int carrySum(int a, int b, int c) {
		return (a+b+c)/10;
	}
}
