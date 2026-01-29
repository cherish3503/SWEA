import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3499 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int ttt=0; ttt<T; ++ttt) {
			int N = Integer.parseInt(br.readLine());
			String[] arr = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				arr[i] = st.nextToken();
				
			}
			
			String[] result = new String[N];
			for(int i=0; i<N; ++i) {
				if(i%2 == 0) {
					result[i] = arr[i/2];
				} else {
					result[i] = arr[(N+i)/2];
				}
				
			}
			
	
//			Arrays.stream(result).join
			
//			String.join
			
			
			System.out.println("#" + (ttt+1) +" " + String.join(" ", result));
		}
	
	}
}
