package sample02algorithm.demo001_combination;

import java.util.ArrayList;
import java.util.Arrays;
public class Combination {

    private ArrayList<int[]> combinationList;

    public Combination(int pickCount, int[] noArr) {
        Arrays.sort(noArr);
        combinationList = new ArrayList<>();
        doCombination(new int[noArr.length], noArr.length, pickCount, 0, 0, noArr);
    }

    public ArrayList<int[]> getCombinationList() {
        return combinationList;
    }

    public void doCombination(int[] combArr, int n, int r, int index, int target, int[] arr){
        if (r == 0) {
            int[] aCombination = new int[index];
            for(int i=0; i<index; i++) aCombination[i] = arr[combArr[i]];
            combinationList.add(aCombination);

        } else if (target == n) {
            return;

        } else {
            combArr[index] = target;
            doCombination(combArr, n, r-1, index+1, target+1, arr);
            doCombination(combArr, n, r, index, target+1, arr);
        }
    }
}