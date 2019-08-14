package com.vk.array;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

 class SparseTable {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] df = new int[n];
		StringTokenizer stz = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)
			arr[i] = Integer.parseInt(stz.nextToken());
		for(int i=n-1;i>0;i--)
			df[i] = arr[i]-arr[i-1];
		int q = Integer.parseInt(br.readLine());
		SparseTable st = new SparseTable();
		
		ST sMax = st.new ST(df);
		
		for(int i=0;i<q;i++) {
			stz = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(stz.nextToken());
			int d = Integer.parseInt(stz.nextToken());
			int k = SparseTable.findFirstMax(arr, 0, n-1, t);
			if(k==-1) k=n-1; else k=k-1;
			System.out.println(sMax.findMax(k, d)+1);
		}
	}
	
	
	public static int findFirstMax(int[] arr,int l, int r, int target) {
		if(l==r && arr[l]<=target)
			return -1;
		int mid = (l+r)/2;
		if(arr[mid]>target)
			if(mid==0 || arr[mid-1]<=target)
				return mid;
			else
				return findFirstMax(arr,l,mid,target);
		else
			return findFirstMax(arr, mid+1, r, target);
		
	}
	
	
	public class ST{
		
		private double sparseTable[][];
		
		private int N;
		
		private double[] log2;
		
		private void computeLog2() {
			log2[1] = 0;
			for(int i=2; i<=N; i++) {
				log2[i] = log2[i/2]+1;
			}
		}
		
		public ST(int[] array) {
			this.N= array.length;
			this.log2 = new double[N+1];
			this.computeLog2();
			this.sparseTable = new double[N][(int)(log2[N]+1)];
			this.fillSparseTable(array);
		}
		
		private void fillSparseTable(int[] array) {
		    for(int k=0;k<N;k++) {
		        sparseTable[k][0] = array[k];
		    }
			for(int j=1; j<=log2[N]; j++) {
				for(int i=N-1; i-(1<<j)>=0; i--) {
					sparseTable[i][j] = Math.max(sparseTable[i][j-1], sparseTable[i-(1<<(j-1))][j-1]);
				}
			}
		}
		
		public int findMax(int start, int d) {
			int j=0;
			while(sparseTable[start][j]<=d && start-(1<<j)>=0) {
				j++;
				if(!(j<=log2[N] && sparseTable[start][j]<=d && start-(1<<j)>=0)) {
					start-=(1<<(j-1));
					j=0;
				}
			}
			return start;
		}
		
		public double findRMQ(int l, int r) {
			if(l==r)
				return sparseTable[l][0];
			int length = r-l+1;
			int j = (int)this.log2[length];
			return Math.max(sparseTable[l][j], sparseTable[r-(1<<j)+1][j]);
		}
	}
	

}
