import java.util.ArrayList;

public class MinHeap {
    private double[] keysIn;
    private int[] id;
    private int n; //size
    private ArrayList<Integer> pos; //position of id in heap


    //getter for n
    public int getN(){
        return n;
    }

    //constructor

    public MinHeap(int n){
        keysIn = new double[n+1];
        id = new int[n+1];
        this.n=n;
        pos = new ArrayList<>(n+1);
    }


    //minimum key heap
    public void heap_ini(double[] keys, int n){
        this.keysIn = new double[n+1];
        this.id = new int[n+1];
        this.n = n;
        this.pos = new ArrayList<>(n+1);
        for(int i = 0; i < n; i++){
            this.keysIn[i+1] = keys[i+1];
            this.id[i+1] = i+1;
            this.pos.add(i+1);
        }
        this.build_heap();
        for(int i = (n/2); i >= 1; i--){
            this.reHeapify(i);
        }
    }

    public boolean in_heap(int id){
        // Check if the id is within the range of the pos list.
        return id > 0 && id < pos.size() && pos.get(id) != null;
    }

    public double min_key(){
        return keysIn[1];
    }

    public int min_id(){
        return id[1];
    }

    public double key(int id){
        return keysIn[pos.get(id)];
    }

    public void delete_min() {
        // If the heap is empty, return without doing anything.
        if (n == 0) {
            return;
        }
        // Swap the first element with the last element.
        swap(1, n);
        // Remove the last element (the previous min) from the heap.
        pos.set(id[n]-1, null); // Set the position of the removed element to null.
        n--;
        // Restore the heap property starting from the root.
        reHeapify(1);
    }

    public void decrease_key(int id, double key) {
        int index = pos.get(id);
        if (keysIn[index] <= key) {
            return;
        }
        keysIn[index] = key;
        // Bubble up the element at index if its key is less than that of its parent's.
        while (index > 1 && keysIn[index] < keysIn[index / 2]) {
            swap(index, index / 2);
            index = index / 2;
        }
    }


    private void build_heap(){
        for(int i = n/2; i >= 1; i--){
            reHeapify(i);
        }

        printHeap();
    }

    private void reHeapify(int i){
        int l = 2*i;
        int r = 2*i+1;
        int smallest = i;
        if(l <= n && keysIn[l] < keysIn[smallest]){
            smallest = l;
        }
        if(r <= n && keysIn[r] < keysIn[smallest]){
            smallest = r;
        }
        if(smallest != i){
            swap(i,smallest);
            reHeapify(smallest);
        }
    }

    private void swap(int i, int j){
        double temp = keysIn[i];
        keysIn[i] = keysIn[j];
        keysIn[j] = temp;
        pos.set(id[i]-1,j);
        pos.set(id[j]-1,i);
        int temp2 = id[i];
        id[i] = id[j];
        id[j] = temp2;
    }

    //print function
    public void printHeap(){
        System.out.println("Printing heap inside minheap...");
        for(int i = 0; i <= n; i++){
            System.out.println(keysIn[i]);
        }
    }
}
