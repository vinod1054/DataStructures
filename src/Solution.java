import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Current {
	
	public static void main(String[] args) {
		FastReader fs = new FastReader();
		int n = fs.nextInt();
		long[] a = new long[n];
		for(int i=0;i<n;i++)
			a[n-1-i]=fs.nextLong();
		int q = fs.nextInt();
		BIT bit = new Current().new BIT(a);
		while(q-->0) {
			int op = fs.nextInt();
			if(op==2) {
				long sum = fs.nextLong();
				if(bit.isSumPresent(sum, 1, a.length)!=-1)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
			else {
				int ind = fs.nextInt()-1;
				long val = fs.nextLong();
				val = val-a[n-1-ind];
				a[n-1-ind] += val;
				bit.addValue(n-ind, val);
			}
		}
	}
	

	class BIT{
		long[] arr;
		int N;
		BIT(long[] arr){
			N= arr.length+1;
			this.arr = new long[N];
			Arrays.fill(this.arr, 0);
			for(int i=0;i<arr.length;i++)
				addValue(i+1, arr[i]);
		}
		
		public void addValue(int ind, long value) {
			while(ind<N) {
				arr[ind] += value;
				ind += ind&-ind;
			}
		}
		
		public long getSum(int ind) {
			long sum = 0;
			while(ind>0) {
				sum +=  arr[ind];
				ind -= ind&-ind;
			}
			return sum;
		}
		
		// is prefix sum present
		public int isSumPresent(long sum, int l, int r) {
			if(l==r)
				if(getSum(l)==sum)
					return l;
				else
					return -1;
			int mid = (l+r)/2;
			long s = getSum(mid);
			if(s==sum)
				return mid;
			else if(s>sum)
				return isSumPresent(sum, l, mid);
			else
				return isSumPresent(sum, mid+1, r);
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
