

import java.util.*;
import java.io.*;

public class CodeTree25_1_1 {
    private static int N;
    private static int[] parent;
    private static int[][] F;
    private static int[][] B;
    private static PriorityQueue<int[]> representPq;
    private static int[][] dirArr = {{-1,0},{1,0},{0,-1},{0,1}}; // udlr
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        parent = new int[N*N];
        for(int i=0; i<N*N; ++i) parent[i] = i;

        F = new int[N][N];
        B = new int[N][N];

        representPq = new PriorityQueue<int[]>((p1,p2)-> {
            if(Integer.bitCount(F[p1[0]][p1[1]]) == Integer.bitCount(F[p2[0]][p2[1]])){
                if(B[p1[0]][p1[1]] == B[p2[0]][p2[1]]){
                    if(p1[0] == p2[0]) return Integer.compare(p1[1], p2[1]);
                    else return Integer.compare(p1[0], p2[0]);
                }
                else return Integer.compare(B[p2[0]][p2[1]], B[p1[0]][p1[1]]);
            }
            else return Integer.compare(Integer.bitCount(F[p1[0]][p1[1]]), Integer.bitCount(F[p2[0]][p2[1]]));
        });

        for(int i=0; i<N; ++i){
            String line = br.readLine();
            for(int j=0; j<N; ++j){
                char chr = line.charAt(j);
                // 민트 초코 우유
                if(chr == 'T') F[i][j] = 1<<2;
                else if(chr == 'C') F[i][j] = 1<<1;
                else if(chr == 'M') F[i][j] = 1<<0;
            }
        }

        for(int i=0; i<N; ++i){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; ++j){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        
        simulation(T);

    }

    private static void simulation(int T){
        for(int i=0; i<T; ++i){
            for(int j=0; j<N*N; ++j) parent[j] = j;
            morning();
            lunch();
            evening();
        }
    }

    private static void morning(){
        for(int r=0; r<N; ++r){
            for(int c=0; c<N; ++c){
                B[r][c]++;
            }
        }
    }

    private static void lunch(){
        for(int r=0; r<N; ++r){
            for(int c=0; c<N; ++c){
                for(int d=0; d<2; ++d){
                    int[] dir = dirArr[d*2+1]; // down right
                    int nr = r+dir[0];
                    int nc = c+dir[1];
                    if(nr<0||nc<0||nr>=N||nc>=N) continue;
                    if(F[r][c] == F[nr][nc]){
                        union(r*N+c, nr*N+nc);
                    }
                }
            }
        }
        Set<Integer> repSet = new HashSet<>();
        for(int i=0; i<N*N; ++i){
            int p = find(i);
            repSet.add(p);
            B[p/N][p%N]++;
            B[i/N][i%N]--;
        }
        for(int rep : repSet){
            representPq.offer(new int[]{rep/N, rep%N});
        }

    }

    private static void evening(){
        Set<Integer> defSet = new HashSet<>();

        while(!representPq.isEmpty()){
            int[] cur = representPq.poll();

            int r = cur[0];
            int c = cur[1];
             System.out.println(r + " " + c);
            if(defSet.contains(r*N+c)) continue;
            int dir = B[r][c]%4; //udlr
            int nr = r;
            int nc = c;
            int x = B[r][c]-1;
            B[r][c] = 1;
            while(x > 0){
                nr += dirArr[dir][0];
                nc += dirArr[dir][1];
                if(nr<0||nc<0||nr>=N||nc>=N) break;
                if(F[r][c] == F[nr][nc]) continue;
                if(x > B[nr][nc]){
                    // 깅한 전파
                    x -= B[nr][nc]+1;
                    B[nr][nc]++;
                    F[nr][nc] = F[r][c];
                    // 방어 상태
                    defSet.add(nr*N+nc);
                }
                else{
                    //약한 전파
                    B[nr][nc] += x;
                    x = 0;
                    F[nr][nc] |= F[r][c];
                    defSet.add(nr*N+nc);
                    //방어 상태

                }
            }
        }
        int[] sumB = new int[8];

        for(int r=0; r<N; ++r){
            for(int c=0; c<N; ++c){
                sumB[F[r][c]] += B[r][c];
            }
        }
        int[] orderToBit = {7,6,5,3,1,2,4};
        StringBuilder sb = new StringBuilder();
        for(int i : orderToBit) sb.append(sumB[i]).append(" ");
        System.out.println(sb);
    }




    private static void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(cmpUnion(px/N, px%N, py/N, py%N)) parent[py] = px;
        else parent[px] = py;
    }

    private static boolean cmpUnion(int r1, int c1, int r2, int c2){
        if(B[r1][c1] > B[r2][c2]) return true;
        else if(B[r1][c1] < B[r2][c2]) return false;
        if(r1 < r2) return true;
        else if(r1 > r2) return false;
        if(c1 < c2) return true;
        return false;
    }


    private static int find(int x){
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
}