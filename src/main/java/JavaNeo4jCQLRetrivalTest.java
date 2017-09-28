import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 * Created by Stanislav on 28.09.2017.
 */
public class JavaNeo4jCQLRetrivalTest {
    public static void main(String[] args) {
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(new File("data/cars"));

        Result result = graphDb.execute("MATCH (Vertex:Node) RETURN Vertex");
        String results = result.resultAsString();
        System.out.println(results);
    }
}
