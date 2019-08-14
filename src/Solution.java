import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

 class Solution {
    
    public static void main(String[] args) throws IOException {
        
        PrintWriter      writer = new PrintWriter( System.out );
        FastReader fs = new FastReader();
        int n = fs.nextInt();
        int q = fs.nextInt();
        int[][] arr = new int[n-1][3];
        for(int i=0;i<n-1;i++) {
            arr[i][0] = fs.nextInt();
            arr[i][1] = fs.nextInt();
            arr[i][2] = fs.nextInt();
        }
        Arrays.sort(arr, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[2], b[2]);
            }
        });
        
        long count[] = new long[n-1];
        Solution cr = new Solution();
        DisjointSet ds = cr.new DisjointSet(n);
        
        for(int i=0;i<n-1;i++) {
            long cc = ds.union(arr[i][0], arr[i][1]);
            count[i] = (i==0?0:count[i-1])+cc;
        }
        
        while(q-->0) {
            int l = fs.nextInt();
            int r = fs.nextInt();
            
            int l1 = Solution.findFirstGreaterElement(l, arr, 0, arr.length-1);
            int r1 = Solution.findFirstSmallerElement(r, arr, 0, arr.length-1);
            if(l1==-1 || r1==-1)
                writer.println(0);
            else
                writer.println(count[r1]-(l1==0?0:count[l1-1]));
        }
        
        writer.flush();
        writer.close();
    }
    
    
    public static int findFirstSmallerElement(int val, int[][] arr, int l, int r) {
        
        if(l==r) {
            int ans = arr[l][2]<=val?l:-1;
            if(ans==-1) {
                if(l>0 && arr[l-1][2]<=val)
                    ans = l-1;
            }
            return ans;
        }
        
        int mid = (l+r)/2;
        
        if(arr[mid][2]==val) {
            while(mid<arr.length && arr[mid][2]==val) {
                mid++;
            }
            return mid-1;
        }
        
        if(arr[mid][2]>val)
            return findFirstSmallerElement(val,arr,l,mid==l?mid:mid-1);
        else
            return findFirstSmallerElement(val,arr,mid+1,r);
    }
    
    
    public static int findFirstGreaterElement(int val, int[][] arr, int l, int r) {
        
        if(l==r) 
            return arr[l][2]>=val?l:-1;
        
        int mid = (l+r)/2;
        
        if(arr[mid][2]==val) {
            while(mid>=0 && arr[mid][2]==val) {
                mid--;
            }
            return mid+1;
        }
        
        if(arr[mid][2]>val)
            return findFirstGreaterElement(val,arr,l,mid);
        else
            return findFirstGreaterElement(val,arr,mid+1,r);
    }
    
    
    class DisjointSet{
        
        int[] p;
        int[] size;
        
        
        public DisjointSet(int n) {
            p = new int[n+1];
            size = new int[n+1];
            for(int i=0;i<=n;i++) {
                p[i] = i;
                size[i] = 1;
                }
        }
        
        public String minmax() {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i=1;i<size.length;i++) {
                if(size[findSet(i)]!=1)
                    min = Math.min(min, size[findSet(i)]);
                max = Math.max(max, size[findSet(i)]);
            }
            return min+" "+max;
        }
        
        public long union(int i, int j) {
            int a = findSet(i);
            int b = findSet(j);
            long cc = 0;
            if(a!=b) {
                cc = ((long)size[a])*((long)size[b]);
                if(size[a]<size[b]) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                }
                p[b] = a;
                size[a]+=size[b];
            }
            return cc;
        }
        
        public int findSet(int i) {
            if(i!=p[i])
                p[i] = findSet(p[i]);
            return p[i];
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
