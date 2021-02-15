/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryIndexedTree;


/**
 *
 * @author Gaurav
 */
/*
calculate prefix sum (one of the funtions)
point update operations
the operation or the funtion must be associative
*/
class BIT{
	int bit[],a[],n=-1;
	BIT(int size){
		this.bit=new int[size];
		this.a=new int[size];
		this.n=size;
	}
	void update(int index,int val){
		for(;index<=this.n;index+=index & (-index)){
			bit[index]+=val;
			
		}
                System.out.println("updated: "+ val+"at "+index);
	}
	int prefixSumUptoIndex(int index){//O(logn)
		int sum=0;
		for(;index>0;index-=index & (-index)){
			sum+=bit[index];
			
		}
                //System.out.println("updated: " +index);
		return sum;
	}
	public static void main(String args[]){
		int arr[] = {-666 ,1, 3, 5, 7, 9, 11}; 
        int n = arr.length;
        System.out.println(n);
        
        BIT binaryIndexedTree=new BIT(n);
        //start from index 1 or else infinite loop when performing index+=index & (-index)
        for(int i=1;i<n;i++)binaryIndexedTree.update(i,arr[i]);

        System.out.println(binaryIndexedTree.prefixSumUptoIndex(4));
    	for(int i=0;i<n;i++)System.out.println(binaryIndexedTree.bit[i]);
	}
}