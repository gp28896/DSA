/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XnO;

/**
 *
 * @author Gaurav
 */import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
class Codechef{
	public static void main(String args[]) throws IOException{
		try{Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t--!=0){
			int n=sc.nextInt();
			MinHeap heap=new MinHeap();
			while(n--!=0){
				int h=sc.nextInt();
				heap.add(h);
			}
			int minVal=heap.peek();
			if(minVal>0){System.out.println(0);}
			else{
				int thresh=1;
				minVal=heap.poll();
				while(heap.size()>0&&heap.peek()<=thresh){
					thresh++;
					minVal=heap.poll();
				}
				System.out.println(thresh);
			}
		}}catch(Exception e){System.out.println(e);}
	}
}


class MinHeap{
    private int capacity=100000;
    private int size=0;
    int items[]=new int[capacity];
    private int getLeftChildIndex(int parentIndex){return 2*parentIndex+1;}
    private int getRightChildIndex(int parentIndex){return 2*parentIndex+2;}
    private int getParentIndex(int childIndex){return (childIndex-1)/2;}
    private boolean hasLeftChild(int i){return getLeftChildIndex(i)<size;}
    private boolean hasRightChild(int i){return getRightChildIndex(i)<size;}
    private boolean hasParent(int i){return getParentIndex(i)>=0;}
    private int getLeftChild(int i){return items[getLeftChildIndex(i)];}
    private int getRightChild(int i){return items[getRightChildIndex(i)];}
    private int getParent(int i){return items[getParentIndex(i)];}
    private void swap(int i,int j){
        int temp=items[i];
        items[i]=items[j];
        items[j]=temp;
    }
    private void ensureExtraCapacity(){
        if(size==capacity){
            items=Arrays.copyOf(items,capacity*2);
            capacity*=2;
        }
    }
    public int peek(){
        if(size==0){
            return 0;
        }
        return items[0];
    }
    public int poll(){
        if(size==0){
            return 0;
        }
        int item=items[0];
        items[0]=items[size-1];
        size--;
        heapifyDown();
        return item;
    }
    public void add(int item){
        ensureExtraCapacity();
        items[size]=item;
        size++;
        heapifyUp();
    }
    public void heapifyUp(){
        int index=size-1;
        while(hasParent(index)&&getParent(index)>items[index]){
            swap(getParentIndex(index),index);
            index=getParentIndex(index);
        }
    }
    void heapifyDown(){
        int index=0;
        while(hasLeftChild(index)){
            int smallerChildIndex=getLeftChildIndex(index);
            if(hasRightChild(index)&&getRightChild(index)<getLeftChild(index)){
                smallerChildIndex=getRightChildIndex(index);
            }
            if(items[index]<items[smallerChildIndex])break;
            else swap(index,smallerChildIndex);
            index=smallerChildIndex;
        }
    }
    public int size(){
        return size;
    }
}   
