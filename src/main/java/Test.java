/**
 * Created by Stanislav on 21.09.2017.
 */
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.spatial.Coordinate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
                new File("data/cars"));

        Coordinate domitry = new Coordinate(59.991298, 30.319191);
        Coordinate university = new Coordinate(59.972128, 30.323983);

        try {
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
           // yards -> metres coef = 0.9144d);
            //result is 0.027110473990695872 in cartesian
            //result is 2386.143460362139 in WGS-84 ~ as google maps mb it's yards
            //check https://www.mapsdirections.info/ru/
        } catch (Exception e) {
            System.err.println("Trouble with execute: " + e.getMessage());
        }
    }
}
