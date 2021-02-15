/*
time:
constructSegmentTree=O(n)
sum/update=O(logn)
applications: apart from 2 queries, find min/ max in a range
*/

class SegmentTreeLong{
	long segmentTree[];
	SegmentTreeLong(long arr[],long n){
		long temp=(long)(Math.ceil(Math.log(n)/Math.log(2)));
		long maxSize=2*(long)Math.pow(2,temp)-1;
		segmentTree=new long[(int)maxSize];
		constructSegmentTree(arr,0,n-1,0);
	}
	long constructSegmentTree(long arr[],long startIndex,
		long endIndex,long currentIndex){
		if(startIndex==endIndex){
			segmentTree[(int)currentIndex]=arr[(int)startIndex];
			return arr[(int)startIndex];
		}
		long mid=getMid(startIndex,endIndex);
		segmentTree[(int)currentIndex]=constructSegmentTree(arr,
			startIndex,mid,currentIndex*2+1)+constructSegmentTree(
			arr,mid+1,endIndex,currentIndex*2+2);
			return segmentTree[(int)currentIndex];
	}
	long getSum(long n,long startIndex,long endIndex){
		//returns sum of values in range (inclusive)
		if(startIndex<0||endIndex>n-1||startIndex>endIndex)
			return -1;
		return getSumHelper(0,n-1,startIndex,endIndex,0);
	}
	long getSumHelper(long startIndex,long endIndex,long qStartIndex,long qEndIndex,long currentIndex){
		if(qStartIndex<=startIndex&&qEndIndex>=endIndex)return segmentTree[(int)currentIndex];
		if(endIndex<qStartIndex||startIndex>qEndIndex)return 0;
		long mid=getMid(startIndex,endIndex);
		return getSumHelper(startIndex,mid,qStartIndex,qEndIndex,2*currentIndex+1)+
				getSumHelper(mid+1,endIndex,qStartIndex,qEndIndex,2*currentIndex+2);
	}
	void updateValue(long arr[],long n, long i,long newVal){
		if(i<0||i>n-1)return;
		long d=newVal-arr[(int)i];
		updateValueHelper(0,n-1,i,d,0);
	}
	void updateValueHelper(long startIndex,long endIndex,long i,long d,long currentIndex){
		if(i<startIndex||i>endIndex)return;
		segmentTree[(int)startIndex]=segmentTree[(int)currentIndex]+d;
		if(endIndex!=startIndex){
			long mid=getMid(startIndex,endIndex);
			updateValueHelper(startIndex,mid,i,d,2*currentIndex+1);
			updateValueHelper(mid+1,endIndex,i,d,2*currentIndex+2);
		}
	}
	long getMid(long a,long b){
		return (a+(b-a)/2);
	}
	public static void main(String args[]){
		long arr[] = {1, 3, 5, 7, 9, 11}; 
        long n = arr.length; 
        SegmentTreeLong  tree = new SegmentTreeLong(arr, n); 
  		
	}
}