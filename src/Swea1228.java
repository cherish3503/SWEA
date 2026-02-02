import java.util.*;
import java.io.*;


public class Swea1228 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		for(int test = 0; test<T; ++test) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> cert = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				cert.add(Integer.parseInt(st.nextToken()));
			}
			
			int cmdN = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<cmdN; ++i) {
				char cmd = st.nextToken().charAt(0);
				switch(cmd) {
					case 'I':
						int idxAdd = Integer.parseInt(st.nextToken());
						int cntAdd = Integer.parseInt(st.nextToken());
						List<Integer> addList = new LinkedList<>();
						for(int j=0; j<cntAdd; ++j) {
							addList.add(Integer.parseInt(st.nextToken()));
						}
						cert.addAll(idxAdd, addList);
						break;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			Iterator<Integer> it = cert.iterator();
			for(int i=0; i<10; ++i) {
				if(it.hasNext()) {
					sb.append(it.next()).append(" ");
				}
			}
			System.out.println("#" + (test+1)+ " " +sb);
		}
		
		
		
	}
}
