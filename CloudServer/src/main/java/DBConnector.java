import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gen.SensorResource;
import org.bson.Document;

public class DBConnector {
    private final MongoDatabase database;
    private final String collectionName = "SmartHome";

    public DBConnector() throws Exception {
        MongoClient mClient = new MongoClient("mongo", 27017 );
        database = mClient.getDatabase("SmartHome");
    }

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
            System.out.println("[DBServer] Failed to persist Sensordata from: " + resource.location);
            return false;
        }
        System.out.println("[DBServer] Sensordata from " + resource.location + " has been successfully persisted!");
        return true;
    }

}
