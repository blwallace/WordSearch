package WordSearch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by brianwallace on 2/18/16.
 */
public class Driver {

    String[][] wordMap;
    String[] dictionary;
    String[] results;
    boolean wrap;
    int n;
    int m;

    /**
     * Constructor
     *
     * @param wordMap is the character matrix
     * @param dictionary is our list of words
     * @param wrap is a boolean
     * @param n and m are our boundaries
     *
     */

    public Driver(String[][] wordMap, String[] dictionary, boolean wrap, int n, int m){
        this.wordMap = wordMap;
        this.dictionary = dictionary;
        this.wrap = wrap;
        this.n = n;
        this.m = m;
        this.results = new String[dictionary.length];

        // Formats characters to all lower case and sets up our results array
        // Create result array.  Default values are "NOT FOUND"
        for(int i = 0; i < dictionary.length; i++){
            results[i] = "NOT FOUND";
            dictionary[i] = dictionary[i].toLowerCase();
        }
        // Format table matrix to all lower case
        for(int i = 0; i < n; i++){
            for(int j=0; j < m; j++){
                wordMap[i][j] = wordMap[i][j].toLowerCase();
            }
        }
    }


    /**
     * This is the heart of our calculations.
     * First we create a hashmap with our words
     * Next we creaet a hashmap of the character matrix
     * We iterate through the hashmap of words, using the first character in the word to search
     *  for the corresponding characters in the hashmap of characters
     * Once we find a character in the table, we then use our 'Seeker' iterator search in different directions
     * If we find a match, we store it in the results array
     */

    public void run(){
        Iterator words = new WordList(dictionary).iterator();
        HashMap matrix = new LetterMatrix(n, m, wordMap).getHashMap();

        while(words.hasNext()){
            // Grab all words with a common first character
            Map.Entry entry = (Map.Entry) words.next();
            String firstChar = (String) entry.getKey();
            Iterator wordList = ((LinkedList) entry.getValue()).iterator();

            // Loop through all instances of character in matrix
            while(wordList.hasNext()){
                // Find instances in matrix with character
                if(matrix.get(firstChar) == null){
                    break;
                }
                Iterator coordinates = ((LinkedList) matrix.get(firstChar)).iterator();
                boolean found = false;
                String word = (String) wordList.next();

                while(coordinates.hasNext() && !found) {
                    // Iterate through all the words starting with character
                    int[] coord = (int[]) coordinates.next();
                    int wordLength = word.length();

                    // Iterate through all direction options
                    for (Seeker.Op direction : Seeker.Op.values()) {
                        Iterator seeker = new Seeker(coord[0], coord[1], direction, wrap, n, m).iterator();
                        String wordCompare = firstChar + "";
                        int ticker = 1;
                        int[] nextCoord = new int[0];


                        while (seeker.hasNext() && ticker < wordLength) {
                            nextCoord = (int[]) seeker.next();
                            String nextChar = wordMap[nextCoord[0]][nextCoord[1]];
                            wordCompare += nextChar;
                            ticker++;
                        }

                        if (word.equals(wordCompare)) {
//                            System.out.println(wordCompare);
                            for(int i = 0; i < dictionary.length; i++){
                                if(dictionary[i].equals(wordCompare)){
                                    results[i] = ("("+ coord[0] + "," + coord[1] + ")" + "("+ nextCoord[0] + "," + nextCoord[1] + ")");
                                }
                            }
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void printResults(){
        for(int i = 0; i < dictionary.length; i++){
            System.out.println(results[i]);
        }
    }

}
