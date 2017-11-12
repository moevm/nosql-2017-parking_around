import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.io.fs.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import static org.apache.commons.io.FileUtils.deleteDirectory;

/**
 * Created by Stanislav on 13.11.2017.
 */
public class Controller {

    private static String DATABASE_NAME;
    private static GraphDatabaseService graphDb;


    public Controller(){
        DATABASE_NAME = null;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static void setDatabaseName(String databaseName) {
        DATABASE_NAME = databaseName;
    }

    public static GraphDatabaseService getGraphDb() {
        return graphDb;
    }

    public static void setGraphDb(GraphDatabaseService graphDb) {
        Controller.graphDb = graphDb;
    }

    public boolean checkCurrentDb(){
        Properties prop = new Properties();
        InputStream inputStream = null;
        try {
            String filename = "application.properties";
            inputStream = Test.class.getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                System.out.println("Sorry, unable to find " + filename);
                return false;
            }
            prop.load(inputStream);
            DATABASE_NAME = prop.getProperty("DATABASE_NAME");
        }catch (IOException ex){
            ex.printStackTrace();
        } finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(DATABASE_NAME != null && !DATABASE_NAME.equals("null")) {
            System.out.println("Current db name is " + DATABASE_NAME);
            return true;
        }
        else {
            System.out.println("No any db in properties");
            return false;
        }

    }
    public void writeDBnameInProperties(String newDATABASE_NAME){
        DATABASE_NAME = newDATABASE_NAME;
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("src\\main\\resources\\application.properties");
            // set the properties value
            prop.setProperty("DATABASE_NAME", DATABASE_NAME);
            // save properties to project root folder
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void dumpBD(){
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        System.out.println("Saving db to data\\dump:\n");
        String dataPath = currentRelativePath.toString() + "\\data\\dump";
        File dumpDir = new File(dataPath);
        if (!dumpDir.exists()) {
            dumpDir.mkdir();
            System.out.println("Creating dump directory");
        }
        boolean result = false;
        try {
            File dump = new File(dumpDir + "\\" + DATABASE_NAME + ".zip");
            ZipUtil.pack(new File(currentRelativePath.toString() + "\\data\\" + DATABASE_NAME), dump);
            result = true;
        } catch (SecurityException e) {
            System.out.println("SecurityException while creating dump directory");
        }
        if (result)
            System.out.println("Dump dir created!");
        System.out.println(currentRelativePath);
    }

    public void createNewBd(){
        System.out.println("Enter new db name: ");
        Scanner scanner = new Scanner(System.in);
        final String newDATABASE_NAME = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        setGraphDb(graphDbFactory.newEmbeddedDatabase(
                new File("data/" + newDATABASE_NAME)));
        try (Transaction tx = getGraphDb().beginTx()) {
            tx.success();
            writeDBnameInProperties(newDATABASE_NAME);
        }
        System.out.println("BD " + newDATABASE_NAME + " created");
    }

    public void deleteCurrentBd(){
        if (DATABASE_NAME != null & !DATABASE_NAME.equals("null")) {
            System.out.println("Deleting current db(" + DATABASE_NAME + ")...");
            File dirForRemoving = new File(Paths.get("").toAbsolutePath() + "\\data\\" + DATABASE_NAME);
            try {
                deleteDirectory(dirForRemoving);
                System.out.println("DB " + DATABASE_NAME + " deleted");
                writeDBnameInProperties("null");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Some trouble with deletion");
            }

        } else {
            System.out.println("There is no database for deletion. You must first select the database");
        }
    }

    public void selectNewCurrentBdFromList(){
        Scanner scanner = new Scanner(System.in);
        File dataDir = new File(Paths.get("").toAbsolutePath() + "\\data");
        String[] dbDirectories = dataDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                if (name.equals("dump"))
                    return false;
                return new File(current, name).isDirectory();
            }
        });
        boolean success = false;
        while (success == false) {
            System.out.println("Available databases:");
            for (String s : dbDirectories)
                System.out.println(s);
            System.out.println("Enter the name of the selected database: ");
            String selectedDb = scanner.nextLine();
            if (Arrays.asList(dbDirectories).contains(selectedDb)) {
                writeDBnameInProperties(selectedDb);
                success = true;
            } else
                System.out.println("Wrong name of db! Try again:");
        }
    }

    public void importBdFromPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Import bd from path. Enter the full path to the database:");
        File importDBPath = new File(scanner.nextLine());
        String importDBbName = importDBPath.getName();
        File dataDir = new File(Paths.get("").toAbsolutePath() + "\\data");
        File directionDir = new File(dataDir.toString() + "\\" + importDBbName);
        try {
            FileUtils.copyRecursively(importDBPath, directionDir);

        } catch (IOException ex) {
            System.out.println("Some trouble while importing db");
        }
    }
}
