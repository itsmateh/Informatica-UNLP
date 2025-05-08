package Parcial3;

import GT.GeneralTree;

import java.util.LinkedList;
import java.util.Queue;

public class Parcial {
    public int resolver(GeneralTree<Integer> tree){
        int ans = 0;
        if(tree != null && !tree.isEmpty()) ans = find_ans(tree);
        return ans;
    }

    private int find_ans(GeneralTree<Integer> tree){
        int ans = 0;
        int curr = 1;
        Queue<GeneralTree<Integer>> q = new LinkedList<GeneralTree<Integer>>();
        q.add(tree);
        q.add(null);

        while(!q.isEmpty()){
            GeneralTree<Integer> ab = q.remove();
            if(ab != null){
                q.addAll(ab.getChildren());
                curr *= ab.getData();
            }
            else{
                if(!q.isEmpty()) q.add(null);
                ans = curr;
                curr = 1;
            }
        }

        return ans;
    }

}
