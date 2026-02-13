import java.io.*;
import java.util.*;

public class SweaMaxSubSum {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test=0;test<T;test++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; ++i){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int[] rangeSum = slidingwindow(arr, K);
            int max = Integer.MIN_VALUE;
            for(int i=0; i<N-2*K+1; ++i){ // 마지막 원소 : (N-2k)~(N-K-1) 이때 j: (N-k)~(N-1)
                for(int j=i+K; j<N-K+1; ++j){
                    max = Math.max(max, rangeSum[i] + rangeSum[j]);
//                    System.out.println(i + " " + j + " "+ rangeSum[i] + rangeSum[j]);
                }
            }
            System.out.println("#" + (test+1)+ " " + max);

        }

    }

    private static int[] slidingwindow(int[] arr, int K){
        int sum = 0;
        int N = arr.length;
        for(int i=0; i<K; ++i)  sum+= arr[i];
        int[] result = new int[N-K+1]; //i부터 i+k-1 까지의 합이 담긴 배열
        result[0] = sum;
        for(int i=1; i<=N-K; ++i){
            result[i] = result[i-1] +  arr[i+K-1] - arr[i-1];
        }

        return result;
    }
}