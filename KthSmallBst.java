class KthSmallBst{
	private static int val=0,c=0;
	public int KthSmallest(Node root,int k){
		c=k;
		helper(root);
		return val;
	}
	void helper(Node root){
		if(root.left!=null)helper(root.left);
		c--;
		if(c==0){
			val=root.val;
			return;
		}
		if(root.right!=null)helper(n.right);
	}
}