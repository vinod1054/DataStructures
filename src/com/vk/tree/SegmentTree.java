package com.vk.tree;

// 1-Index based
public class SegmentTree {

	
	private int N;
	private int K;
	private long[][] st;
	
	public SegmentTree(long[] arr) {
		N = arr.length;
		K = (int) Math.ceil((Math.log(N) / Math.log(2)));
		st = new long[(int) Math.pow(2,K+1) ][2];
		ConstructTree(arr,1,N,1);
	}
	
	// 1-Index based
	private void ConstructTree(long arr[], int l, int r, int ind) {
		
		if(l==r) {st[ind][0]=st[ind][1] = arr[l-1];}
		else {
			int mid = (l+r)/2;
			ConstructTree(arr,l,mid,left(ind));
			ConstructTree(arr,mid+1,r,right(ind));
			st[ind][0] = Math.min(st[left(ind)][0], st[right(ind)][0]);
			st[ind][1] = Math.max(st[left(ind)][1], st[right(ind)][1]);
		}
		//System.out.println(l+" "+r+" "+ind+" "+st[ind][0]+"--");
	}
	
	public long find(int l, int r, int intMinMax) {
		return RMQ(l,r,1,1,N,intMinMax);
	}
	
	
	// 1-Index based
	private long RMQ(int l, int r, int ind, int tl, int tr, int MinMax) {
		//System.out.println(tl+" "+tr+" "+ind+" "+st[ind][MinMax]);
		if(tl>=l && tr<=r)
			return st[ind][MinMax];
		int mid = (tl+tr)/2;
		if (l>tr || r<tl)
			return MinMax==0?Long.MAX_VALUE:Long.MIN_VALUE;
		else 
			if(MinMax==0)
				return Math.min(RMQ(l,r,left(ind),tl,mid,MinMax), RMQ(l,r,right(ind),mid+1,tr,MinMax));
			else
				return Math.max(RMQ(l,r,left(ind),tl,mid,MinMax), RMQ(l,r,right(ind),mid+1,tr,MinMax));
	}
	
	private int left(int x) {return 2*x;}
	private int right(int x) {return 2*x+1;}
	
	
}
