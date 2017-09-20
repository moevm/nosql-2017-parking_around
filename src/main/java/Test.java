/**
 * Created by Stanislav on 21.09.2017.
 */
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.spatial.Coordinate;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
                new File("data/cars"));
        String resultString;
        Coordinate domitry = new Coordinate(59.991298, 30.319191);
        Coordinate university = new Coordinate(59.972128, 30.323983);
        try {
            Transaction tx = graphDb.beginTx();
            resultString = graphDb.execute(
                    "WITH point({ x: "+domitry.getCoordinate().get(0)+", y:"+domitry.getCoordinate().get(0)+", crs: 'cartesian' }) AS p1," +
                    " point({ x: "+university.getCoordinate().get(0)+", y:"+university.getCoordinate().get(0)+", crs: 'cartesian' }) AS p2 RETURN distance(p1,p2) AS dist").resultAsString();
            System.out.println(resultString);
        } catch (Exception e) {
            System.err.println("Trouble with execute: " + e.getMessage());
        }
    }
}
