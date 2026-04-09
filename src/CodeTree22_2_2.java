import java.util.*;
import java.io.*;

public class CodeTree22_2_2 {
//	static class LLNode{
//		int data;
//		LLNode next;
//		
//		public LLNode(int data, LLNode next) {
//			this.data = data;
//			this.next = next;
//		}
//	}
	
//    static class Pos{
//        int belt;
//        int idx;
//
//        public Pos(int belt, int idx){
//            this.belt = belt;
//            this.idx = idx;
//        }
//    }

//    static class Product{
//        int id;
//        int weight;
//        Pos pos;
//
//        public Product(int id, int weight, int belt, int beltIdx){
//            this.id = id;
//            this.weight = weight;
//            this.pos = new Pos(belt, beltIdx);
//        }
//    }
    
    static class Product{
        int id;
        int weight;
        Product next;

        public Product(int id, int weight){
            this.id = id;
            this.weight = weight;
        }
    }

    static class Belt{
    	Product s;
    	Product e;
    	
        public Belt(Product s){
            this.s = s;
        }
        
        public boolean isEmpty() {
        	if(this.s == null) return true;
        	else return false;
        }
    }
    
    

    private static final int CMD_FACTORY = 100;
    private static final int CMD_FALL = 200;
    private static final int CMD_REMOVE = 300;
    private static final int CMD_CHECK = 400;
    private static final int CMD_FAILURE = 500;

    private static int N;
    private static int M;
    private static int K;
    private static Belt[] belts;
    private static Map<Integer, Product> products; 
//    private static Map<Integer, LLNode> productLL;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int cmd = Integer.parseInt(st.nextToken());

        if(cmd == CMD_FACTORY){
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = N/M;
            int[] Ids = new int[N];
            int[] Ws = new int[N];
            for(int i=0; i<N; ++i) Ids[i] = Integer.parseInt(st.nextToken());
            for(int i=0; i<N; ++i) Ws[i] = Integer.parseInt(st.nextToken());  
            factory(Ids, Ws);
        }
        // else if()
        

    }

    private static void factory(int[] Ids, int[] Ws){
        belts = new Belt[M];
        products = new HashMap<>();
        
        for(int b=0; b<M; ++b) {
        	Product pre = new Product(Ids[b*K], Ws[b*K]);
        	belts[b] = new Belt(pre);
        	for(int i=1; i<K; ++i) {
        		Product p = new Product(Ids[i], Ws[i]);
        		pre.next = p;
        		pre = p;
        	}
        	belts[b].e = pre;
        }
        
    }

    private static int fall(int wMax){
        int result = 0;
        for(int b=0; b<M; ++b){
            Belt belt = belts[b];
            if(belt == null || belt.isEmpty()) continue;
            Product p = belt.s;
            
            if(p.weight <= wMax) { // 하차
            	result += p.weight;
            	products.remove(p.id);
            }
            else {
            	p.next = null;
            	belt.e.next = p;
            	belt.e = p;
            }
            
            belt.s = p.next;
            
        }

        return result;
    }

    private static int remove(int id){
        Product p = products.get(id);
        if(p == null) return -1;
        for(int b=0; b<M; ++b) {
        	Belt belt = belts[];
            if(belt == null || belt.isEmpty()) continue;
            
        }
        
        return id;
    }

    private static int check(int id){
        Product p = products.get(id);
        if(p == null) return -1;
        Belt belt = belts[p.pos.belt];
        belt.now = p.pos.idx;
        return id;
    }


    private static int failure(){
        for(int )
        
    }

    private static int isBeltFail(Belt belt){
        if(belt.fail) return -1;
        int now = belt.now;
        Product p;
        for(int i=0; i<K; ++i){ // 존재하는 상자까지 순회
            p = products.get(belt.products[now]);
            if(p.pos != null) return now;
            now = (now+1)%K;
        }
        belt.fail = true;
        return -1; // 못찾은 경우
    }
}