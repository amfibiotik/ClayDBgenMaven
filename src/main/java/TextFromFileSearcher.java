import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFromFileSearcher {
    public static final String FROM_TXT = "COMMAND LINE OPTIONS:"; //get text after those words
    public static final String TO_TXT = "CONFIGURATION FILE"; //get text before those words
    public static final String README_FILE_ENG = "Readme!!!.txt"; //get text from this file
    public static final int SPACE_AFTER_COMMANDS = 10; //cut some chars in the end of text
    private static String searchResult;


    public TextFromFileSearcher () {
        this (FROM_TXT, TO_TXT);
    }

    public TextFromFileSearcher(String from_txt, String to_txt){
        try (FileReader fr = new FileReader(README_FILE_ENG);
            BufferedReader br = new BufferedReader(fr)) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            // pass first part of file for FROM_TXT:
            while (!line.equals (from_txt) ) {
                line = br.readLine();
            }
            // take only rest part of file after FROM_TXT to TO_TXT:
            while (!line.equals (to_txt)) {
                line = br.readLine();
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            searchResult = sb.toString ();
            searchResult = searchResult.substring (0, searchResult.length () - (to_txt.length () + SPACE_AFTER_COMMANDS));

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static String getSearchResult () {
        return searchResult;
    }

}
