import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TextFromFileSearcher {
    private static final String FROM_TXT = "COMMAND LINE OPTIONS:"; //get text after those words
    private static final String TO_TXT = "CONFIGURATION FILE"; //get text before those words
    private static final String README_FILE_ENG = "Readme!!!.txt"; //get text from this file
    private static final int SPACE_BEFORE_COMMANDS = 1; //cut some chars in the start of text
    private static final int SPACE_AFTER_COMMANDS = 10; //cut some chars in the end of text
    private static String searchResult;


    TextFromFileSearcher () {
        try (FileReader fileReader = new FileReader(README_FILE_ENG);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            // pass first part of file for FROM_TXT:
            while (!line.equals (FROM_TXT) )
                line = bufferedReader.readLine();

            // take only rest part of file after FROM_TXT (and zero line after that) to TO_TXT :
            for (int i = 0; i < SPACE_BEFORE_COMMANDS; i++)
                line = bufferedReader.readLine(); // read zero line and pass it

            while (!line.equals (TO_TXT)) {
                line = bufferedReader.readLine();
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            searchResult = stringBuilder.toString ();
            searchResult = searchResult.substring (0, searchResult.length () - (TO_TXT.length () + SPACE_AFTER_COMMANDS));

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public String getSearchResult () {
        return searchResult;
    }

}
