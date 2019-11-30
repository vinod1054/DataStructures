package com.vk.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

 class DigitDp {
	 
	 public static long mod = 1000000000+7;
     public static int MAX_N = 100000;
     public static long pow[] = new long[MAX_N+1];
     public static long dp[] = new long[MAX_N+1];
     public static long d[] = new long[MAX_N+1];
     public static long xp[] = new long[MAX_N+1];
     public static long dpsum[] = new long[MAX_N+1];
    public static void main(String[] args) throws IOException {
    	
    	xp[0]=0;xp[1]=1;
    	for(int kl=2;kl<=9;kl++)
    		xp[kl] = xp[kl-1]+kl;
    	pow[0] = 1; pow[1] =10; pow[2]=100;
    	dp[0] = 0;d[0]=0;
    	dp[1] = 45;d[1] = dp[1];
    	dp[2] = 4860;d[2] = d[1]+dp[2];
    	
    	dpsum[0]=0;dpsum[1]=dpsum[1];dpsum[2]=dpsum[1]+dpsum[2];
    	for(int i=3;i<MAX_N+1;i++) {
    		int j = i-1;
    		pow[i] = (pow[i-1]*10)%mod;
    		dp[i] = ((m(m(dp[1],pow[j]),pow[j]))+(m(9,dpsum[i-1])))%mod-(m(m(dp[1],pow[j-1]),pow[j-1]))%mod;
    		dpsum[i] = (dpsum[i-1]+dp[i])%mod;
    		d[i] = d[i-1]+dp[i];
    	}
    	 
        PrintWriter      writer = new PrintWriter( System.out );
        FastReader fs = new FastReader();
        
        int q = fs.nextInt();
        while(q-->0) {
        	int l1 = fs.nextInt();
        	String s1 = fs.next();
        	int l2 = fs.nextInt();
        	String s2 = fs.next();
        }
        
        writer.flush();
        writer.close();
    }
    
    public static long getRangeCount(String x, int i, long carry) {
    	int d = x.charAt(i)-'0';                                                                                 
    	long sum = 0;
    	sum = ((m(m(xp[d-1],pow[i]),pow[i]))+(m(d-1,dpsum[i-1])))%mod-(m(m(xp[d-1],pow[i-1]),pow[i-1]))%mod;
    	sum = sum+(m(i,pow[i]))+getRangeCount(x,i-1, carry);
    	return sum;
    }
    
    public static long m(long x, long y) { return (x*y)%mod; }
    
    class Trie{
    	class Node{
    		public Node(char c) {
    			this.c = c;
    		}
    		char c;
    		int prefixCount = 0;
    		int wordCount = 0;
    		Node[] childs = new Node[26];
    	}
    	
    	Node root;
    	
    	public Trie(){
    		root = new Node('-');
    	}
    	
    	public String addWord(String word, Node node, int i) {
    		node.prefixCount++;
    		char c = word.charAt(i);
    		if(node.childs[c-'a']==null) {
    			node.childs[c-'a'] = new Node(c);
    		}
    		if(node.wordCount>0)
    			return word;
    		if(i+1<word.length())
    			return addWord(word, node.childs[c-'a'], i+1);
    		else {
    			if(node.childs[c-'a'].wordCount>0 || node.childs[c-'a'].prefixCount>0)
        			return word;
    			node.childs[c-'a'].prefixCount++;
    			node.childs[c-'a'].wordCount++;
    		}
    		return null;
    	}
    	
    	
    	public int getPrefixCount(String prefix) {
    		Node node = root.childs[prefix.charAt(0)-'a'];
    		int i=0;
    		int count = 0;
    		while(true){
    			if (node==null) return 0;
    			count = node.prefixCount;
				if(i<prefix.length()-1) {
					char c = prefix.charAt(++i);
	    			node = node.childs[c-'a'];
				}
				else
					break;
    		}
    		return count;
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

