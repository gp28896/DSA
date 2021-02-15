import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class MinHeapMain {
    public static void main(String args[]){
        MinHeap h=new MinHeap();
        double arr[]=new double[]{3,7,9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56};
        for(double x:arr)h.add(x);
        for(double x:h.items)System.out.print(x+", ");
    }
}


class MinHeap{
    private int capacity=20;
    private int size=0;
    double items[]=new double[capacity];
    private int getLeftChildIndex(int parentIndex){return 2*parentIndex+1;}
    private int getRightChildIndex(int parentIndex){return 2*parentIndex+2;}
    private int getParentIndex(int childIndex){return (childIndex-1)/2;}
    private boolean hasLeftChild(int i){return getLeftChildIndex(i)<size;}
    private boolean hasRightChild(int i){return getRightChildIndex(i)<size;}
    private boolean hasParent(int i){return getParentIndex(i)>=0;}
    private double getLeftChild(int i){return items[getLeftChildIndex(i)];}
    private double getRightChild(int i){return items[getRightChildIndex(i)];}
    private double getParent(int i){return items[getParentIndex(i)];}
    private void swap(int i,int j){
        double temp=items[i];
        items[i]=items[j];
        items[j]=temp;
    }
    private void ensureExtraCapacity(){
        if(size==capacity){
            items=Arrays.copyOf(items,capacity*2);
            capacity*=2;
        }
    }
    public double peek(){
        if(size==0){
            return 0;
        }
        return items[0];
    }
    public double pull(){
        if(size==0){
            return 0;
        }
        double item=items[0];
        items[0]=items[size-1];
        size--;
        heapifyDown();
        return item;
    }
    public void add(double item){
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
