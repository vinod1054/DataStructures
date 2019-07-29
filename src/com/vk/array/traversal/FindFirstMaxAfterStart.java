package com.vk.array.traversal;

public class FindFirstMaxAfterStart {

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
	
}
