package com.vk.tree;

import java.util.Arrays;

public class BIT{
	long[] arr;
	int N;
	BIT(long[] arr){
		N= arr.length+1;
		this.arr = new long[N];
		Arrays.fill(this.arr, 0);
		for(int i=0;i<arr.length;i++)
			addValue(i+1, arr[i]);
	}
	
	// index 1 based
	public void addValue(int ind, long value) {
		while(ind<N) {
			arr[ind] += value;
			ind += ind&-ind;
		}
	}
	
	// index 1 based
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
