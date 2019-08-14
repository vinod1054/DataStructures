package com.vk.ds.graph;

public class DisjointSet{
	
	int[] p;
	int[] size;
	public int[] v;
	
	public DisjointSet(int n) {
		p = new int[n+1];
		size = new int[n+1];
		v = new int[n+1];
		for(int i=0;i<=n;i++) {
			p[i] = i;
			v[i] = 0;
			size[i] = 1;
			}
	}
	
	public void updateV(int x, int val) {
		v[findSet(x)] += val;
	}
	
	public boolean voltDiff(int c1, int c2) {
		int a = v[findSet(c1)];
		int b = v[findSet(c2)];
		return (a>0 && b==0) || (a==0 && b>0);
	}
	
	public void union(int i, int j) {
		int a = findSet(i);
		int b = findSet(j);
		if(a!=b) {
			if(size[a]<size[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			p[b] = a;
			if(size[a]==size[b])
				size[a] ++;
			v[a] += v[b];
		}
		
	}
	
	public int findSet(int i) {
		if(i!=p[i])
			p[i] = findSet(p[i]);
		return p[i];
	}
	
	
}
