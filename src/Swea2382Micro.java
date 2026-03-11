import java.util.*;
import java.io.*;


public class Swea2382Micro {
	private static int N;
	private static int M;
	private static int[][] dirArr = {{-1,0},{1,0},{0,-1},{0,1}}; // 0123 : udlr
	static class Micro{ //  군집
		int row;
		int col;
		int microN;
		int dir;
		
		public Micro(int row, int col, int microN, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.microN = microN;
			this.dir = dir;
		}
		
		private int nextR() {
			return row+dirArr[dir][0];
		}
		private int nextC() {
			return col+dirArr[dir][1];
		}
		private void move() {
			this.row = this.nextR();
			this.col = this.nextC();
		}
		private void atBorder() {
			this.dir ^= 1;
			this.microN /=2;
		}

		@Override
		public String toString() {
			return "Micro [row=" + row + ", col=" + col + ", microN=" + microN + ", dir=" + dir + "]";
		}
		
	}
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; ++test) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken()); // 미생물 수
			List<Micro> microLs = new ArrayList<>();
			
			for(int i=0; i<K; ++i) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int microN = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				
				microLs.add(new Micro(row, col, microN, dir));
			}
			
			System.out.println("#" + test + " " + simulation(microLs)); 
		}
	}
	
	private static int simulation(List<Micro> microLs) {
		for(int i=0; i<M; ++i) {
//			System.out.println("turn : " + i);
//			print(microLs);
//			System.out.println();
			moveAndCrush(microLs);
		}
		
		int result = 0;
		for(Micro micro : microLs) result += micro.microN;
		return result;
	}
	
	private static void print(List<Micro> microLs) {
		int[][] board = new int[N][N];
		for(Micro micro : microLs) board[micro.row][micro.col] = micro.microN;
		for(Micro micro : microLs) System.out.println(micro);
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.printf("%4d ", board[i][j]);
			}
			System.out.println();
		}
	}

	
	
	
	private static void moveAndCrush(List<Micro> microLs) {
		Map<Integer, List<Micro>> posMicro = new HashMap<>(); // hash : List<Micro>  미생물이 도착한 지점에 도착한 미생물 
		Set<Micro> remSet = new HashSet<>();
		for(Micro micro : microLs) {
			micro.move();
			if(micro.row==0 || micro.col==0 || micro.row==N-1 || micro.col==N-1) {
				micro.atBorder();
				if(micro.microN == 0) remSet.add(micro); // 경계에서 반이 되었을때 수가 0이면 삭제
			}
		}
		
		for(Micro micro : microLs) { // 미생물이 도착한 지점에 대해서 개수를 센다.
			int hash = (micro.row<<10) | micro.col; // 10비트씩 사용
			List<Micro> ls = posMicro.getOrDefault(hash, new ArrayList<>());
			ls.add(micro);
			posMicro.put(hash, ls);
		}
		
		for(int hash : posMicro.keySet()) {
			List<Micro> crushMicros = posMicro.get(hash); // 해당 좌표에 있는 미생물 군집은 저장
			if(crushMicros.size() > 1) { // 2개이상의 도착한 장소
				
				for(Micro micro : crushMicros) remSet.add(micro); // 모두 삭제 (가장 큰 것은 아래에서 다시 뺀다)
				
				remSet.remove(crush(crushMicros)); // 가장 큰 것 제외 
			}
		}

		microLs.removeIf(m -> remSet.contains(m)); // 한번에 삭제
		
		
	}
	
	
	
	
	
	
	private static Micro crush(List<Micro> crushMicros) { // 충돌후 개수를 대표의 개수로 하고 대표 군집을 반환
		Micro bigMicro = null;
		int sum = 0;
		for(Micro micro : crushMicros) {
			sum += micro.microN;
			if(bigMicro == null || micro.microN > bigMicro.microN) bigMicro = micro;
		}
		bigMicro.microN = sum;
		return bigMicro;
	}
	

}
