package WordSearch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by brianwallace on 2/17/16.
 */
public class WordList {
    String[] words;
    HashMap<String,LinkedList<String>> hMap = new HashMap<String,LinkedList<String>>();

    /**
     * Constructor
     *
     * @param words takes the list of words we're searching for
     *
     * Like the letterMatrix, we also create a hashmap of the words here
     *  HOWEVER, we're indexing on the first character of the words
     *  ie cat and car are grouped together because they start with 'c'
     *
     */

    public WordList(String[] words){

        this.words = words;
        LinkedList list;

        //Creating our hashmap
        for(int i = 0; i < words.length; i++){
            String firstLetter = words[i].substring(0,1);
            list = hMap.get(firstLetter);
            if(list == null){
                list = new LinkedList();
            }
            list.add(words[i]);
            hMap.put(firstLetter,list);
        }

    }

    // Used if we want to retrieve an iterator for this class
    public Iterator iterator(){
        Iterator it = hMap.entrySet().iterator();
        return it;
    }
}
