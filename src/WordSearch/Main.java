package WordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by brianwallace on 2/17/16.
 */
public class Main {

    private static String[] inputDimensions;
    private static int n;
    private static int m;
    private static boolean wrap;
    private static String[][] wordMap;
    private static String[] dictionary;

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Please enter file name. You can type testInput.txt for example");
        Scanner scan = new Scanner(System.in);
        String fileLocation = scan.nextLine();
        scan.close();

        // Run program. First load file, the feed inputs into driver
        load(fileLocation);
        Driver driver = new Driver(wordMap,dictionary,wrap,n,m);
        driver.run();
        driver.printResults();
        }


    // This opens our file and parses the data.
    public static void load(String fileLocation) throws FileNotFoundException {
        File inputFile = new File(fileLocation);
        Scanner in = new Scanner(inputFile);

        while(in.hasNextLine()){
            inputDimensions = in.nextLine().split(" ");
            n = Integer.parseInt(inputDimensions[0]);
            m = Integer.parseInt(inputDimensions[1]);

            wordMap = new String[n][m];

            for(int i = 0; i < n; i++){
                wordMap[i] = in.nextLine().split("");
            }

            String wrapper = in.nextLine();
            if(wrapper.equals("NO_WRAP")){wrap = false;}
            else{ wrap = true;}

            int length = Integer.parseInt(in.nextLine());
            dictionary = new String[length];

            for(int i = 0; i < length; i++){
                dictionary[i] = in.nextLine();
            }
        }

        in.close();

    }

}
