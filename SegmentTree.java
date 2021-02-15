/*
time:
constructSegmentTree=O(n)
sum/update=O(logn)
applications: apart from 2 queries, find min/ max in a range
*/

class SegmentTree{
	int segmentTree[];
	SegmentTree(int arr[],int n){
		int temp=(int)(Math.ceil(Math.log(n)/Math.log(2)));
		int maxSize=2*(int)Math.pow(2,temp)-1;
		segmentTree=new int[maxSize];
		constructSegmentTree(arr,0,n-1,0);
	}
	int constructSegmentTree(int arr[],int startIndex,
		int endIndex,int currentIndex){
		if(startIndex==endIndex){
			segmentTree[currentIndex]=arr[startIndex];
			return arr[startIndex];
		}
		int mid=getMid(startIndex,endIndex);
		segmentTree[currentIndex]=constructSegmentTree(arr,
			startIndex,mid,currentIndex*2+1)+constructSegmentTree(
			arr,mid+1,endIndex,currentIndex*2+2);
			return segmentTree[currentIndex];
	}
	int getSum(int n,int startIndex,int endIndex){
		//returns sum of values in range (inclusive)
		if(startIndex<0||endIndex>n-1||startIndex>endIndex)
			return -1;
		return getSumHelper(0,n-1,startIndex,endIndex,0);
	}
	int getSumHelper(int startIndex,int endIndex,int qStartIndex,int qEndIndex,int currentIndex){
		if(qStartIndex<=startIndex&&qEndIndex>=endIndex)return segmentTree[currentIndex];
		if(endIndex<qStartIndex||startIndex>qEndIndex)return 0;
		int mid=getMid(startIndex,endIndex);
		return getSumHelper(startIndex,mid,qStartIndex,qEndIndex,2*currentIndex+1)+
				getSumHelper(mid+1,endIndex,qStartIndex,qEndIndex,2*currentIndex+2);
	}
	void updateValue(int arr[],int n, int i,int newVal){
		if(i<0||i>n-1)return;
		int d=newVal-arr[i];
		updateValueHelper(0,n-1,i,d,0);
	}
	void updateValueHelper(int startIndex,int endIndex,int i,int d,int currentIndex){
		if(i<startIndex||i>endIndex)return;
		segmentTree[startIndex]=segmentTree[currentIndex]+d;
		if(endIndex!=startIndex){
			int mid=getMid(startIndex,endIndex);
			updateValueHelper(startIndex,mid,i,d,2*currentIndex+1);
			updateValueHelper(mid+1,endIndex,i,d,2*currentIndex+2);
		}
	}
	int getMid(int a,int b){
		return (a+(b-a)/2);
	}
	/*public static void main(String args[]){
		int arr[] = {1, 3, 5, 7, 9, 11}; 
        int n = arr.length; 
        SegmentTree  tree = new SegmentTree(arr, n); 
  		
	}*/
}