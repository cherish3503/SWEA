import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SweaGeneratePassword1225 {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		int lenPassword = 8;
		for(int ttt=0; ttt<T; ++ttt) {
			int cnt = 1;
			Deque<Integer> dq = new ArrayDeque<>(lenPassword);
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<lenPassword; ++i) {
				dq.addLast(Integer.parseInt(st.nextToken()));
			}
			while(!decrease(dq, cnt)) {
				cnt = cnt < 5 ? cnt+1 : 1;
			}
			
//			StringBuilder sb = new StringBuilder();
//			while(!dq.isEmpty()) {
//				sb.append(" ").append(dq.pop());
//			}
//			System.out.println(sb.toString());
			
			System.out.println("#" + (ttt+1) + " " + dq.stream().map(String::valueOf).collect(Collectors.joining(" ")));
			
		}

	}
	
	private static boolean decrease(Deque<Integer> dq, int cnt) { //true : 종료
		int next = dq.poll() - cnt;
		next = next > 0 ? next : 0;
		dq.offer(next);
		
//		System.out.println(dq.toString());
		
		if(next == 0) {
			return true;
		}

		return false;
	}
}
