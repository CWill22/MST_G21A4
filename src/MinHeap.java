import java.util.HashMap;
import java.util.Map;

public class MinHeap {
    private int[] keys;
    private int[] id;
    private int n; //size
    private Map<Integer, Integer> pos; //position of id in heap

    //minimum key heap
    public void heap_ini(keys,n){
        this.keys = keys;
        this.n = n;
        this.id = new int[n];
        this.pos = new HashMap<>();
        for(int i = 0; i < n; i++){
            id[i] = i;
            pos.put(i,i);
        }

    }

    in_heap(id){

    }

    public int min_key(){

    }

    min_id(){

    }

    key(id){

    }

    delete_min(){

    }
    decrease_key(){

    }
}
