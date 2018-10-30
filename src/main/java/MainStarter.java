import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainStarter {
    private final static String SPLITTER = "\r\n\r\n";
    private final static String URL = "jdbc:sqlite:";

    private static String absPath = new File (".").getAbsolutePath().replace ('\\','/' ); //get path to program folder



    private static void createNewDatabase(String fileName) {
        String url = URL + absPath.substring (0, absPath.length () - 1) + fileName; //URL + absPath without '.' + fileName

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
        //read all parameters from default Claymore readme file (in constructor)
        TextFromFileSearcher t = new TextFromFileSearcher();

        //take only middle part
        String s = t.getSearchResult ();

        // Split whole text to parts with one parameter in each other...
        String[] strs = s.split (SPLITTER);

        //save all those texts with parameters in ArrayList
        ArrayList<ParameterString> list = new ArrayList<> ();
        for (String oneStr : strs){
            list.add (new ParameterString (oneStr));
        }

        String fileName = "test.db";
        File f = new File (absPath.substring (0, absPath.length () - 1) + fileName);
        if (!f.exists ()) {
            System.out.println ("Creating new DB...");
            createNewDatabase (fileName);
        }







//        for (ParameterString ps : list){
//            System.out.println (ps.getParam ().toUpperCase ());
//            System.out.println (ps.getDescription ());
//            System.out.println ();
//        }


    }
}
