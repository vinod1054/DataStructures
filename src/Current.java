import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

 class Current {
	
	public static void main(String[] args) throws IOException {
		FastReader fs = new FastReader();
		int n = fs.nextInt();
		int q = fs.nextInt();
		
		long[] a = new long[n];
		for(int i=0;i<n;i++) 
			a[i]=fs.nextInt();
		
		int[] next = new int[n];
		Queue<Integer> qu = new PriorityQueue<>();
		qu.add(0);
		for(int i=1;i<n;i++) {
			while(!qu.isEmpty() && a[qu.peek()]<a[i])
				next[qu.poll()] = i;
			qu.add(i);
			if(qu.size()>100)
				next[qu.peek()] = qu.poll();
		}
		while(!qu.isEmpty())
			next[qu.peek()] = qu.poll();

		int F[][] = new int[n][2];
		
		int bucketLength = (int) Math.ceil(n);
		for(int bi=0;bi<bucketLength;bi++) {
			for(int i=Math.min(((bi+1)*bucketLength)-1,n-1);i>(bi*bucketLength)-1;i--) {
				int nxt = next[i];
				if(i==nxt || nxt/bucketLength==bi+1) {
					F[i][0]=i;
					F[i][1]=0;
				}
				else {
					F[i][0]= F[nxt][0];
					F[i][1] = F[nxt][1]+1;
				}
			}
		}
		
		SquareRootDecomposition sq = new Current().new SquareRootDecomposition(a);
		while(q-->0) {
			int op = fs.nextInt();
			if(op==1) {
				int l =fs.nextInt()-1;
				int k = fs.nextInt();
				while(k-->0) {
					int tmp = sq.getMax(l);
					if(tmp==l)
						break;
					l = tmp;
				}
				System.out.println(l+1);
			}
			else {
				int l =fs.nextInt();
				int r = fs.nextInt();
				int val = fs.nextInt();
				sq.add(l-1, r-1, val);
			}
		}
		
	}
	

	class SquareRootDecomposition{
		
		int N;
		int cellLength;
		long[] a;
		long[] arr;
		
		public SquareRootDecomposition(long[] arr) {
			this.arr = arr;
			N = arr.length;
			cellLength = (int) Math.ceil(Math.sqrt(N));
			a = new long[(cellLength)];
			Arrays.fill(a, 0);
		}
		
		
		public int getMax(int i) {
			for(int j=i+1;j<=i+100 && j<arr.length;j++) {
				if(arr[j]+a[j/cellLength]>arr[i]+a[i/cellLength])
					return j;
			}
			return i;
		}
		
		// zero index based
		public void add(int l, int r, int val) {
			for(int i=l;i<=r;) {
				if(i%cellLength==0 && i+cellLength-1<=r) {
					a[i/cellLength]+=val;
					i+=cellLength;
				}
				else {
					arr[i++]+=val;
				}
			}
		}
	}
	
	static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    } 
	
	
}
