public class TrieImplementation{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Trie trie=new Trie();
		while(n--!=0){
			String op=sc.next(),entry=sc.next();
			if(op.equals("add"))trie.add(entry);
			else System.out.println(trie.find(entry));
		}
		sc.close();
	}
}
/*
for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            int c=1;
            System.out.print(c+" ");
            for(int i=1;i<=n;i++){
                c=c*(n-i+1)/i;
                System.out.print(c+" ");
            }
            System.out.println();
        }
           	 a) What is the MaximumFlow in the graph, show ALL augmented paths step-by-step

   	 b) What is the Time complexity and Space of the algorithm?

  	 c) What is the Edmonds-Karp contribution to Fulkerson method, and Why?

 	 d) Write the Java code for the algorithm?

*/
class TrieNode{
	private HashMap<Character, TrieNode> children=new HashMap<>();
	int size=0;
	public void putChildIfAbsent(char ch){
		children.putIfAbsent(ch,new TrieNode());
	}
	public TrieNode getChild(char ch){
		return children.get(ch);
	}
}
class Trie{
	TrieNode root;
	Trie(){
		if(root==null)
			root=new TrieNode();
	}
	Trie(String entries[]){
		for(String s:entries){
			add(s);
		}
	}
	public void add(String s){
		TrieNode curr=root;
		for(int i=0,n=s.length();i<n;i++){
			Character ch=s.charAt(i);
			curr.putChildIfAbsent(ch);
			curr=curr.getChild(ch);
			curr.size++;
		}
	}
	public int find(String prefix){
		TrieNode curr=root;
		for(int i=0,n=prefix.length();i<n;i++){
			Character ch=prefix.charAt(i);
			curr=curr.getChild(ch);
			if(curr==null)return 0;
		}
		return curr.size;
	}
}