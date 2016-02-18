package WordSearch;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by brianwallace on 2/17/16.
 * WordSearch.LetterMatrix is responsible for storing letters in the matrix
 * 
 * WordSearch also stores the values of the matrix in a hashmap
 * This helps us search for characters in the map
 *
 */
public class LetterMatrix {
    private int n;
    private int m;
    private String[][] matrixInput;
    private HashMap<String,LinkedList<int[]>> hMap = new HashMap<String,LinkedList<int[]>>();

    /**
     * Constructor
     *
     * @param n = number of row starts 0 -> n-1
     * @param m = number of columns starts 0 -> m-1
     * @param matrix = input matrix
     *
     *  We create our LetterMatrix hashmap here.  We use this to find characters in the
     *     map without iterating through each character.
     */

    public LetterMatrix(int n, int m, String[][] matrix){
        this.n = n;
        this.m = m;
        this.matrixInput = matrix;
        LinkedList list;

        // Creating our hashmap
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                list = hMap.get(matrixInput[i][j]);
                if(list == null){
                    list = new LinkedList();
                }
                int[] coords = new int[]{i, j};
                list.add(coords);
                hMap.put(matrixInput[i][j],list);
            }
        }

    }

    // LetterMatrix Getters.  Setting is all done in the constructor
    public HashMap getHashMap(){
        return hMap;
    }

}
