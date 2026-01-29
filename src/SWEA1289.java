import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1289 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; ++i) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = br.readLine();
			int cnt = 0;
			char now = '0';
			for(int j=0, size=str.length(); j<size; ++j) {
				
				if(now != str.charAt(j)) {
					now = str.charAt(j);
					cnt++;
				}
			}
			
			System.out.println("#" + (i+1) + " " + cnt);
			
		}
		
	}

}





