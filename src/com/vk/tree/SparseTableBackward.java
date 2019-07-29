package com.vk.tree;


// Zero index based
public class SparseTableBackward {

	
	private double sparseTable[][];
	
	private int N;
	
	private double[] log2;
	
	private void computeLog2() {
		log2[1] = 0;
		for(int i=2; i<=N; i++) {
			log2[i] = log2[i/2]+1;
		}
	}
	
	public SparseTableBackward(int[] array) {
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
	
	
	// Find max towards left from n-1 -> 0 side
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
	
}
