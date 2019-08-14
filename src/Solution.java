import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

 class Solution {
	 
	 public static long mod = 1000000000+7;
    
    public static void main(String[] args) throws IOException {
        PrintWriter      writer = new PrintWriter( System.out );
        FastReader fs = new FastReader();
        int n = fs.nextInt();
        Solution cr = new Solution();
        Trie ds = cr.new Trie();
        boolean fail = false;
        String res = null;
        while(n-->0) {
        	String word = fs.next();
        	if(fail) continue;
        	res = ds.addWord(word, ds.root, 0);
        	if(res!=null)
        		fail = true;
        }
        if(fail) {
        	writer.println("BAD SET");
        	writer.println(res);
        }
        else {
        	writer.println("GOOD SET");
        }
        writer.flush();
        writer.close();
    }
    
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
