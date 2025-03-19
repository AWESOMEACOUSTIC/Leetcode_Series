import java.util.Arrays;
import java.util.Scanner;

public class maxheap {
    private int[] heap;
    int size;
    int capacity;

    public maxheap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }
    public maxheap(int[] array){
        this.heap = Arrays.copyOf(array,array.length);
        this.size = this.capacity = array.length;
        buildheap();
    }

    private int parent(int i){
        return (i-1)/2;
    }

    private int leftChild(int i){
        return 2*i+1;
    }

    private int rightChild(int i){
        return 2*i+2;
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private void capacityEnough(){
        if(size == capacity){
            capacity *= 2;
            heap = Arrays.copyOf(heap,capacity);
        }
    }

    public void insert(int element){
        capacityEnough();
        heap[size] = element;
        size++;
        heapifyup();
    }
    private void heapifyup(){
        int index = size - 1;
        while(index>0 && heap[parent(index)] < heap[index]){
            swap(parent(index), index);
            index = parent(index);
        }
    }
    public void printHeap(){
        for(int i=0; i<size; i++){
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    public int peek(){
        if(size == 0) throw new IllegalStateException("heap is empty");
        return heap[0];
    }
    public int poll(){
        if(size == 0) throw new IllegalStateException("heap is empty");
        int item = heap[0];
        heap[0] = heap[size-1];
        size--;
        heapifydown();
        return item;
    }
    private void heapifydown() {
        int index = 0;
        while(leftChild(index) < size){
            int leftindex = leftChild(index);
            int rightindex = rightChild(index);
            if(rightindex < size && heap[rightindex] > heap[leftindex]) leftindex = rightindex;
            if(heap[index] >= heap[leftindex]) break;
            else{
                swap(index, leftindex);
                index = leftindex;
            }
        }
    }
    public void delete(int index){
        if(index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of the range");
        heap[index] = heap[size-1];
        size--;
        if(index > 0 && heap[index]> heap[parent(index)]) heapifyupfrom(index);
        else heapifydownfrom(index);
    }
    public void buildheap(){
        for(int i = (size/2)-1; i>=0; --i){
            heapifydownfrom(i);
        }
    }
    private void heapifydownfrom(int index){
        while(leftChild(index) < size){
            int leftindex = leftChild(index);
            int rightindex = rightChild(index);
            if(rightindex < size && heap[rightindex] > heap[leftindex]) leftindex = rightindex;
            if(heap[index] >= heap[leftindex]) break;
            else{
                swap(index, leftindex);
                index = leftindex;
            }
        }
    }
    private void heapifyupfrom(int index){
        while(index > 0 && heap[parent(index)] > heap[index]){
            swap(parent(index),index);
            index = parent(index);
        }
    }
    public void heapSort(int[] array){
        maxheap heap = new maxheap(array);
        for(int i = array.length - 1; i>=0; --i){
            array[i] = heap.poll();
        }
        heap.printHeap();
    }
    public static void main(String[] args) {
//        System.out.print("Enter elements inside an array: ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of array: ");
        int n = sc.nextInt();
        maxheap max = new maxheap(n);
        int[] arr = new int[n];
        System.out.println("Enter elements: ");
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        for(int elements : arr){
            max.insert(elements);
        }
        System.out.println("Elements entered inserted in the heap are shown below:");
        max.printHeap();
        System.out.println("Applying heapify algorithm");
        max.buildheap();
//        System.out.println("Max element: " + max.peek());
//        System.out.println("After deleting the root element:" + max.poll());
//        max.printHeap();
//        System.out.println("Enter the element you want to insert:");
//        int ins = sc.nextInt();
//        max.insert(ins);
//        max.printHeap();
//        System.out.println("Enter the element you want to delete:");
//        int del = sc.nextInt();
//        if(del == max.peek()) max.poll();
//        else max.delete(del);
        max.printHeap();
        max.heapSort(arr);
    }
}
