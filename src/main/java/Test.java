/**
 * Created by Stanislav on 21.09.2017.
 */
import org.neo4j.graphdb.*;
import java.util.Scanner;

public class Test {
    public static String DATABASE_NAME = null;
    public static GraphDatabaseService graphDb;

    //RelationTypes
    public enum Ways implements RelationshipType{
        HaveWay,NoWay;
    }

    //For Circle on site
    public enum Vertex implements Label {
        Node;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.checkCurrentDb();
        String answer = "0";
        while (!answer.equals("7")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What to do?\n" +
                    "1.Dump current db\n" +
                    "2.Create new db\n" +
                    "3.Delete current db\n" +
                    "4.Select db from list\n" +
                    "5.Import db\n" +
                    "6.Continue working with this database\n" +
                    "7.Exit");
            answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    controller.dumpBD();
                    break;
                case "2":
                    controller.createNewBd();
                    break;
                case "3":
                    controller.deleteCurrentBd();
                    break;
                case "4":
                    controller.selectNewCurrentBdFromList();
                    break;
                case "5":
                    controller.importBdFromPath();
                    break;
                case "6":

            }
        }
                /*
            default: {
                GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();

                //Creating new db in "path"
                graphDb = graphDbFactory.newEmbeddedDatabase(
                        new File("data/cars2"));

                try (Transaction tx = graphDb.beginTx()) {
                    //Creating Node with propery
                    Node A = graphDb.createNode(Vertex.Node);
                    A.setProperty("Name", "A");
                    A.setProperty("Latitude", "59.98649");
                    A.setProperty("Longitude", "30.30295");

                    Node B = graphDb.createNode(Vertex.Node);
                    B.setProperty("Name", "B");
                    B.setProperty("Latitude", "59.98738");
                    B.setProperty("Longitude", "30.30546");

                    Relationship AtoB = A.createRelationshipTo(B, Ways.HaveWay);
                    Result result = graphDb.execute(
                            "WITH point({ x: " + A.getProperty("Latitude") + ", y:" + A.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p1," +
                                    " point({ x: " + B.getProperty("Latitude") + ", y:" + B.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p2 RETURN distance(p1,p2) AS dist");
                    // System.out.println(results);
                    while (result.hasNext()) {
                        Map<String, Object> row = result.next();
                        for (String key : result.columns()) {
                            AtoB.setProperty("Length", row.get(key));
                        }
                    }

                    Node E = graphDb.createNode(Vertex.Node);
                    E.setProperty("Name", "E");
                    E.setProperty("Latitude", "59.98625");
                    E.setProperty("Longitude", "30.30715");

                    Relationship BtoE = B.createRelationshipTo(E, Ways.HaveWay);
                    result = graphDb.execute(
                            "WITH point({ x: " + B.getProperty("Latitude") + ", y:" + B.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p1," +
                                    " point({ x: " + E.getProperty("Latitude") + ", y:" + E.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p2 RETURN distance(p1,p2) AS dist");
                    // System.out.println(results);
                    while (result.hasNext()) {
                        Map<String, Object> row = result.next();
                        for (String key : result.columns()) {
                            BtoE.setProperty("Length", row.get(key));
                        }
                    }

                    Node F = graphDb.createNode(Vertex.Node);
                    F.setProperty("Name", "F");
                    F.setProperty("Latitude", "59.98548");
                    F.setProperty("Longitude", "30.30548");

                    Relationship EtoF = E.createRelationshipTo(F, Ways.HaveWay);
                    result = graphDb.execute(
                            "WITH point({ x: " + E.getProperty("Latitude") + ", y:" + E.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p1," +
                                    " point({ x: " + F.getProperty("Latitude") + ", y:" + F.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p2 RETURN distance(p1,p2) AS dist");
                    // System.out.println(results);
                    while (result.hasNext()) {
                        Map<String, Object> row = result.next();
                        for (String key : result.columns()) {
                            EtoF.setProperty("Length", row.get(key));
                        }
                    }

                    Relationship FtoA = F.createRelationshipTo(A, Ways.HaveWay);
                    result = graphDb.execute(
                            "WITH point({ x: " + F.getProperty("Latitude") + ", y:" + F.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p1," +
                                    " point({ x: " + A.getProperty("Latitude") + ", y:" + A.getProperty("Longitude") + ", crs: 'WGS-84' }) AS p2 RETURN distance(p1,p2) AS dist");
                    // System.out.println(results);
                    while (result.hasNext()) {
                        Map<String, Object> row = result.next();
                        for (String key : result.columns()) {
                            FtoA.setProperty("Length", row.get(key));
                        }
                    }

                    tx.success();
                }
                System.out.println("Done successfully");
            }
            */
    }


}

/*
Coordinate domitry = new Coordinate(59.991298, 30.319191);
Coordinate university = new Coordinate(59.972128, 30.323983);
Transaction tx = graphDb.beginTx();
            Result result = graphDb.execute(
                    "WITH point({ x: "+domitry.getCoordinate().get(0)+", y:"+domitry.getCoordinate().get(0)+", crs: 'WGS-84' }) AS p1," +
                    " point({ x: "+university.getCoordinate().get(0)+", y:"+university.getCoordinate().get(0)+", crs: 'WGS-84' }) AS p2 RETURN distance(p1,p2) AS dist");
           // System.out.println(results);
            while ( result.hasNext() )
            {
                Map<String, Object> row = result.next();
                for ( String key : result.columns() )
                {
                    System.out.println("Dist (WGS-84) in metres " + row.get( key ) );
                }
            }
 */