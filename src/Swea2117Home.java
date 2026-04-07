import java.util.*;
import java.io.*;

public class Swea2117Home {

    static class Pos {
        int r, c;
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; ++test) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<Pos> houses = new ArrayList<>();

            for (int r=0; r<N; ++r) {
                st = new StringTokenizer(br.readLine());
                for (int c=0; c<N; ++c) {
                    int cur = Integer.parseInt(st.nextToken());
                    if (cur == 1) {
                        houses.add(new Pos(r, c));
                    }
                }
            }

            int answer = 0;

            for (int sr=0; sr<N; ++sr) {
                for (int sc=0; sc<N; ++sc) {

                    int[] distCnt = new int[2*N];

                    for (Pos h : houses) {
                        int d = Math.abs(h.r - sr) + Math.abs(h.c - sc);
                        distCnt[d]++;
                    }

                    int covered = 0;

                    for (int K=1; K<=2*N; ++K) {
                        covered += distCnt[K - 1];

                        int cost = K*K+(K-1)*(K-1);
                        int revenue = covered*M;

                        if (revenue >= cost) {
                            answer = Math.max(answer, covered);
                        }
                    }
                }
            }
            
            System.out.println("#" + test + " " + answer); 
        }
    }
}