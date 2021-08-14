import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gen.SensorResource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;

/**
 * DBConnector handles the database connection to MongoDB server instance
 */
public class DBConnector {
    private final MongoDatabase database;
    private final String collectionName = "SmartHome";
    private static String cloudServerName;
    private static int cloudServerPort;
    private Logger logger;

    /**
     * The DBConnector constructor initializes the DB Connection via env-variables or default hardcoded values for IDE-testing
     * A database instance is being created and can be referenced by the following methods.
     */
    public DBConnector() {

        if ( //start in Docker
                (System.getenv("DB_SERVER_NAME") != null) || (System.getenv("DB_SERVER_PORT") != null)) {
            cloudServerName = System.getenv("DB_SERVER_NAME");
            cloudServerPort = Integer.parseInt(System.getenv("DB_SERVER_PORT"));
        }
        else{ // start in IDE
            cloudServerName = "localhost";
            cloudServerPort = 27017;
        }
        logger = Logger.getLogger(DBConnector.class);
        BasicConfigurator.configure();
        MongoClient mClient = new MongoClient(cloudServerName, cloudServerPort);
        database = mClient.getDatabase("SmartHome");
    }

    /**
     * Persists a SensorResource object as a new document into the created mongo collection
     * @param resource object being transmitted via RPC-Thrift
     * @return whether persisting the resource has been successful or not
     */
    public Boolean persist(SensorResource resource) {
        /*
        Choosing WriteConcern.MAJORITY to indicate, that write operations have been propagated to other mongo db instances
        This will be relevant in P5!
         */
        MongoCollection<Document> mCollection = database.getCollection(collectionName).withWriteConcern(WriteConcern.MAJORITY);

        Document document = new Document(
                "sensorlocation", resource.location).append(
                "timestamp", resource.timestamp).append(
                "humidity", resource.humidity).append(
                "temperature", resource.temp).append(
                "brightness", resource.brightness).append(
                "volume", resource.volume);
        try {
            mCollection.insertOne(document);
        } catch (MongoWriteException mwe) {
            mwe.printStackTrace();
            logger.error("Failed to persist Sensordata from: [" + resource.location + "]");
            return false;
        }
        logger.info("Sensordata from [" + resource.location + "] has been successfully persisted!");
        return true;
    }

}
