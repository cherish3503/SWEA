import java.io.*;
import java.util.*;

public class Swea5648AtomMap {
	private static class Atom {
		int x;
		int y;
		int dir;
		int energy;
		
		Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
		public void move(){
			int[][] dArr = {{0,1},{0,-1},{-1,0},{1,0}}; //udlr
			this.x = x+dArr[dir][0];
			this.y = y+dArr[dir][1];
		}
//		public boolean samePos(Atom vic) {
//			if(vic.x == this.x && vic.y == this.y) return true;
//			return false;
//		}
		
		public int nextX() {
			int[][] dArr = {{0,1},{0,-1},{-1,0},{1,0}}; //udlr
			return x + dArr[dir][0];
		}
		
		public int nextY() {
			int[][] dArr = {{0,1},{0,-1},{-1,0},{1,0}}; //udlr
			return y + dArr[dir][1];
		}
		// int 두개를 long으로 만들어서 key로 만듬
		public long getKey() {
			return ((long)this.x)<<32 | (this.y & 0xffffffffL);
		}
		public long nextKey() {
			return ((long)nextX())<<32 | (nextY() & 0xffffffffL);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        
        int T = Integer.parseInt(br.readLine());
        
        for(int test=0; test<T; ++test) {

        	int N = Integer.parseInt(br.readLine());
        	List<Atom> atoms = new ArrayList<>(); //udlr
        	for(int i=0; i<N; ++i) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken())*2+2000;	// 좌표 2배
        		int y = Integer.parseInt(st.nextToken())*2+2000;
        		int dir = Integer.parseInt(st.nextToken());
        		int energy = Integer.parseInt(st.nextToken());
        		atoms.add(new Atom(x,y,dir,energy));
        	}
        	
            System.out.println("#" + (test+1) + " " +simulation(atoms)); 
        	
        }

	}
	
	private static int simulation(List<Atom> atoms) {
		int score = 0;
        
		while(!atoms.isEmpty()) {
//			score+= halfCheck(atoms);
			moveAll(atoms);
			score += afterCheck(atoms);
			moveAll(atoms);
			score += afterCheck(atoms);
			outCheck(atoms);
		}
		return score;
	}
	
	
	private static void moveAll(List<Atom> atoms) {
		for(Atom atom : atoms) {
			atom.move();
		}
	}
	
//	private static int halfCheck(List<Atom> atoms) {
//		int score = 0;
//		Map<Long, List<Atom>> board = new HashMap<>();
//		Map<Long, List<Atom>> nextBoard = new HashMap<>();
//		Set<Atom> remSet = new HashSet<>();
//		
//		for(Atom atom : atoms) {
//			board.computeIfAbsent(atom.getKey(), v -> new ArrayList<>()).add(atom);
//		}
//		for(Atom atom : atoms) {
//			nextBoard.computeIfAbsent(atom.nextKey(), v -> new ArrayList<>()).add(atom);
//		}
//		
//		for(Atom atom : atoms) {
//			if(board.containsKey(atom.nextKey())) {
//				for(Atom victim : board.get(atom.nextKey())){
//					if(victim.dir == (atom.dir^1)) { // 반대방향
//						remSet.add(atom);
//					}
//				}
//			}
//		}
//		
//		for(Atom atom : remSet) {
//			score += atom.energy;
//		}
//		
//		atoms.removeIf(remSet::contains);
//		
//		return score;
//	}
	
	// 같은 좌표인지 검사하여 score 반환
	private static int afterCheck(List<Atom> atoms) {
		int score = 0;
		Map<Long, List<Atom>> board = new HashMap<>();
		Set<Atom> remSet = new HashSet<>();
		
		for(Atom atom : atoms) {
			board.computeIfAbsent(atom.getKey(), v -> new ArrayList<>()).add(atom);
		}
		
		for(List<Atom> aList :board.values()) {
			if(aList.size() >= 2) {
				for(Atom atom : aList) {
					remSet.add(atom);
				}
			}
		}
		
		for(Atom atom : remSet) {
			score += atom.energy;
		}
		
		atoms.removeIf(remSet::contains);
		
		return score;
	}
	
	private static void outCheck(List<Atom> atoms) {
		Iterator<Atom> it = atoms.iterator();
		while(it.hasNext()) {
			Atom atom = it.next();
			if(atom.x > 4000 ||  atom.x < 0 || atom.y > 4000 || atom.y < 0) {
				it.remove();
			}
		}

		return;
	}
	
	
	
	
	
	
}
