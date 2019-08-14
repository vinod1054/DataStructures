package com.vk.tree;


public class Trie{
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
		if(i+1<word.length())
			return addWord(word, node.childs[c-'a'], i+1);
		else {
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