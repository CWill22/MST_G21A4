import java.util.HashMap;
import java.util.Map;

public class MinHeap {
    private int[] keysIn;
    private int[] id;
    private int n; //size
    private Map<Integer, Integer> pos; //position of id in heap

    //constructor
    public MinHeap(int n){
        keysIn = new int[n+1];
        id = new int[n+1];
        this.n=n;
        pos = new HashMap<>();
    }


    //minimum key heap
    public void heap_ini(int[] keys,int n){
        MinHeap heap = new MinHeap(n);
        for(int i = 0; i < n; i++){
            heap.keysIn[i+1] = keys[i];
            heap.id[i+1] = i+1;
            heap.pos.put(i+1,i+1);
        }
        heap.build_heap();

        for(int i = (n/2); i >= 1; i--){
            heap.heapify(i);
        }

    }

    public boolean in_heap(int id){
        return pos.containsKey(id);
    }

    public int min_key(){
        return keysIn[1];
    }

    public int min_id(){
        return id[1];
    }

    public int key(int id){
        return keysIn[pos.get(id)];
    }

    public void delete_min(){
        //if heap is empty, return
        if(n== 0){
            return;
        }
        int min = id[0];
        id[1] = id[n];
        keysIn[1] = keysIn[n];
        pos.put(id[n],1);
        n--;
        heapify(1);
        pos.remove(min);

    }
    public void decrease_key(int id, int key) {
        int index = pos.get(id);
        if(keysIn[index] <= key){
            return;
        }
        keysIn[index] = key;
        //(index)/2 is the parent of index
        while(index > 1 && keysIn[index] < keysIn[(index)/2]){
            swap(index,(index)/2);
            index = (index)/2;
        }
    }

    private void build_heap(){
        for(int i = n/2; i >= 0; i--){
            heapify(i);
        }
    }

    private void heapify(int i){
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
            heapify(smallest);
        }
    }

    private void swap(int i, int j){
        int temp = keysIn[i];
        keysIn[i] = keysIn[j];
        keysIn[j] = temp;
        pos.put(id[i],j);
        pos.put(id[j],i);
        int temp2 = id[i];
        id[i] = id[j];
        id[j] = temp2;
    }

    public void print(){
        for(int i = 0; i < n; i++){
            System.out.println(keysIn[i]);
        }
    }
}
