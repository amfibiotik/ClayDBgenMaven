import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainStarter {

    final static String URL = "jdbc:sqlite:";

    static String absPath = new File (".").getAbsolutePath().replace ('\\','/' );



    public static void createNewDatabase(String fileName) {

        String url = URL;
        url +=  absPath.substring (0, absPath.length () - 1);
        url += fileName;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main (String[] args) {
        TextFromFileSearcher t = new TextFromFileSearcher(); //read all parameters from default Claymore readme file (in constructor)
        String s = TextFromFileSearcher.getSearchResult (); //take only middle part
        char[] ch = s.toCharArray ();                           // Split whole text to parts with one parameter in each other...
        String split = Character.toString (ch[0]) + Character.toString (ch[1]);
        String[] strs = s.split (split + split);

        ArrayList<ParameterString> list = new ArrayList<> (); //...and save all those texts with parameters in ArrayList
        for (String oneStr : strs){
            list.add (new ParameterString (oneStr));
        }

        String fileName = "test.db";
        File f = new File (URL + absPath.substring (0, absPath.length () - 1) + fileName);
        if (!f.exists ()) {
            System.out.println ("Creating new DB...");
            createNewDatabase (fileName);
        }


//TODO Постійно "створює" нову базу, хоч файл існує. Перевірити шлях повний генеруємий.




//        for (ParameterString ps : list){
//            System.out.println (ps.getParam ().toUpperCase ());
//            System.out.println (ps.getDescription ());
//            System.out.println ();
//        }


    }
}
