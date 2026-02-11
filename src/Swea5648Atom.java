import java.io.*;
import java.util.*;


public class Swea5648Atom {
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
		public boolean samePos(Atom vic) {
			if(vic.x == this.x && vic.y == this.y) return true;
			return false;
		}
		
		public int nextX() {
			int[][] dArr = {{0,1},{0,-1},{-1,0},{1,0}}; //udlr
			return x + dArr[dir][0];
		}
		
		public int nextY() {
			int[][] dArr = {{0,1},{0,-1},{-1,0},{1,0}}; //udlr
			return y + dArr[dir][1];
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test=0; test<T; ++test) {
        	int N = Integer.parseInt(br.readLine());
        	List<Atom>[] atoms = new ArrayList[4]; //udlr
        	for(int d=0; d<4; ++d)	atoms[d] = new ArrayList<>();
        	for(int i=0; i<N; ++i) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		int dir = Integer.parseInt(st.nextToken());
        		int energy = Integer.parseInt(st.nextToken());
        		atoms[dir].add(new Atom(x,y,dir,energy));
        	}
        	
            System.out.println("#" + (test+1) + " " +simulation(atoms)); 
        	
        }
         
        // 3개 충돌
        // 0.5초 충돌 후 1초 충돌
        // 다른 방향 충돌
        
        //n*n 하면 되는거아님? - 시간 고려해야해서 안됨
        // 시뮬 끝나는 시간을 정해야됨 - 모든 원자의 진행 방향에 방향이 같지않은 원자가 있는지 확인 n*n * 시간
        

        
        
   
	}
	
	private static int simulation(List<Atom>[] atoms) {
		int score = 0;
		while(true) {
			score+= halfCheck(atoms);
			moveAll(atoms);
			score += afterCheck(atoms);
			if(finishCheck(atoms)) break;
		}
		return score;
	}
	
	private static void moveAll(List<Atom>[] atoms) {
		for(int d=0; d<4; ++d) {
			for(int i=0; i<atoms[d].size(); ++i) {
				atoms[d].get(i).move();
			}
		}
	}
	
	private static int halfCheck(List<Atom>[] atoms) {
		int score = 0;
		Set<Atom> remSet = new HashSet<>();
		for(int d1=0; d1<4; ++d1) {
			int d2 = d1^1; // 0비트 토글 - 반대방향
			Iterator<Atom> it1 = atoms[d1].iterator();
			
			while(it1.hasNext()) {
				Atom atom1 = it1.next();
				int nx1 = atom1.nextX();
				int ny1 = atom1.nextY();		
				
				Iterator<Atom> it2 = atoms[d2].iterator();
				while(it2.hasNext()) {
					Atom atom2 = it2.next(); // 반대 방향이고 0.5초에 만남
					if(nx1 == atom2.x && ny1 == atom2.y && atom1.dir/2 == atom2.dir/2) {
						remSet.add(atom1);
						remSet.add(atom2);
					}
				}
			}
		}
		for(Atom atom : remSet) {
			score += atom.energy;
		}
		
		for(int d=0; d<4; ++d) {
			atoms[d].removeIf(remSet::contains);
		}
		return score;
	}
	
	private static int afterCheck(List<Atom>[] atoms) {
		int score = 0;
		Set<Atom> remSet = new HashSet<>();
		for(int d1=0; d1<4; ++d1) {
			Iterator<Atom> it1 = atoms[d1].iterator();
			
			while(it1.hasNext()) {
				Atom atom1 = it1.next();	
				
				for(int d2=0; d2<4; ++d2) {
					if(d1 == d2) continue; // 같은 방향은 x
					
					Iterator<Atom> it2 = atoms[d2].iterator();
					while(it2.hasNext()) {
						Atom atom2 = it2.next(); // 같은 위치인지 확인
						if(atom1.samePos(atom2) && atom1.samePos(atom2)) {
							remSet.add(atom1);
							remSet.add(atom2);
						}
					}
				}
			}
		}
		
		for(Atom atom : remSet) {
			score += atom.energy;
		}
		
		for(int d=0; d<4; ++d) {
			atoms[d].removeIf(remSet::contains);
		}
		
		return score;
	}
	
	private static boolean finishCheck(List<Atom>[] atoms) {
		for(int d1=0; d1<4; ++d1) {
			Iterator<Atom> it1 = atoms[d1].iterator();
			
			while(it1.hasNext()) {
				Atom atom1 = it1.next();	
				
				for(int d2=0; d2<4; ++d2) {
					if(d1 == d2) continue; // 같은 방향인 경우 탈출
					
					Iterator<Atom> it2 = atoms[d2].iterator();
					while(it2.hasNext()) {
						Atom atom2 = it2.next(); 
						if(atom1.dir == 0 && atom1.y < atom2.y) return false;
						if(atom1.dir == 1 && atom1.y > atom2.y) return false;
						if(atom1.dir == 2 && atom1.x > atom2.x) return false;
						if(atom1.dir == 3 && atom1.x < atom2.x) return false;
					}
				}
			}
		}

		return true;
	}
	
}
