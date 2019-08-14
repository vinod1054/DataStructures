package com.vk.array;

import java.util.Arrays;

public class SquareRootDecomposition{
	
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
	
	
	public long getValue(int i) {
		return arr[i]+a[i/cellLength];
	}
	
	public int getMax(int i) {
		for(int j=i+1;j<=i+100 && j<arr.length;j++) {
			if(getValue(j)>getValue(i))
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