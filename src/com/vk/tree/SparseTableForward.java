package com.vk.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Zero index based */
public class SparseTableForward {
	
	private long sparseTable[][];
	private int N;
	private int MinMax;
	private double[] log2;
	private int K;
	
	private void computeLog2() {
		log2[1] = 0;
		for(int i=2; i<=N; i++) {
			log2[i] = log2[i/2]+1;
		}
	}
	
	public SparseTableForward(long[] array, int m) {
		this.MinMax = m;
		this.N= array.length;
		this.log2 = new double[N+1];
		this.computeLog2();
		K = (int) (log2[N]+1);
		this.sparseTable = new long[N][(int)(K+1)];
		this.fillSparseTable(array);
	}
	
	private void fillSparseTable(long[] array) {
	    for(int k=0;k<N;k++)
	        sparseTable[k][0] = array[k];
		for(int j=1; j<=K; j++) {
			for(int i=0; i+(1<<j)<=N; i++) {
				if(MinMax==1)
					sparseTable[i][j] = Math.max(sparseTable[i][j-1], sparseTable[i+(1<<(j-1))][j-1]);
				else
					sparseTable[i][j] = Math.min(sparseTable[i][j-1], sparseTable[i+(1<<(j-1))][j-1]);
			}
		}
	}
	
	public long findRMQ(int l, int r) {
		if(l==r)
			return sparseTable[l][0];
		int length = r-l+1;
		int j = (int)log2[length];
		if(MinMax==1)
			return Math.max(sparseTable[l][j], sparseTable[r-(1<<j)+1][j]);
		else
			return Math.min(sparseTable[l][j], sparseTable[r-(1<<j)+1][j]);
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] arr = new long[n];
		StringTokenizer stz = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)
			arr[i] = Long.parseLong(stz.nextToken());
		int q = Integer.parseInt(br.readLine());
		SparseTableForward mint = new SparseTableForward(arr,0);;
		SparseTableForward maxt = new SparseTableForward(arr,1);;
		for(int i=0;i<q;i++) {
			stz = new StringTokenizer(br.readLine());
			String m = stz.nextToken();
			int minmax = m.charAt(0)=='m'?0:1;
			int l = Integer.parseInt(stz.nextToken());
			int r = Integer.parseInt(stz.nextToken());
			if(l==1 && r==arr.length){
			    System.out.println(0);continue;
			}
			long left = l>1 ? (minmax==0?mint.findRMQ(1, l-1):maxt.findRMQ(1, l-1)):(minmax==0?Long.MAX_VALUE:Long.MIN_VALUE);
			long right = r<n ? (minmax==0?mint.findRMQ(r+1, n):maxt.findRMQ(r+1, n)):(minmax==0?Long.MAX_VALUE:Long.MIN_VALUE); 
			if(minmax==0)
				System.out.println(Math.min(left, right));
			else
				System.out.println(Math.max(left, right));
		}
	}
	

}
