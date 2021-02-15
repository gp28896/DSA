/*
find the kth smallest element in the unsorted array with unique elements
worst case: O(n^2)
but Math.rand makes worst case almost impossible
average case: O(n)
*/

class KthSmallest{

    //generates a random number between l to r
    //swaps it with the rth element of array[l...r]
    //passes the array with l,r to partition()
    int randomPivot(int arr[],int l int r){
        int n=r-l;//num of elements -1
        int pivot=(int)Math.random()*n;
        
        //the swap
        //just because our traditional quicksort assumes us to 
        //pick a pivot that is the last element of the sub-array

        int temp=arr[l+pivot];
        arr[l+pivot]=arr[r];
        arr[r]=temp;

        return partition(arr,l,r);

    }
    //vanilla quicksort partition method
    /*
    declare i as the leftmost index;

    following loop will create 2 subarrays where left subarray
    contains elements less than pivot and right subarr the elements
    greater than the pivot
    
    loop j from l to r-1 
        if(ele at j < pivot)swap ele at i and j and i++;
    
    swap arr[r], arr[i]: to insert the pivot(arr[r]) at the correct position
    */
    int partition(int []arr,int l, int r){
        int pivot=arr[r],i=l;
        for(int j=l;j<r;j++){
            if(arr[j]<pivot){
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i++]=temp;
            }
        }
        int temp=arr[r];
        arr[r]=arr[i];
        arr[i]=temp;
        return i;
    }
    int kthSmall(int arr[],int l,int r,int k){
        //if(k<num of elements in array[l...r])
        if(k<r-l+1&&k>0){
            //position or index of the pivot 
            int pos=randomPivot(arr,l,r);
            //pos-l because the position is relative to arr[l...r] and not arr[0 to n-1]
            if(pos-l=k-1)return arr[pos];
            if(pos-l>k-1)return kthSmall(arr,l,pos-1,k);
            else return kthSmall(arr,pos+1,r,k);
        }
    }

}