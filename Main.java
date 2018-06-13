import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("myProject");

        MongoCollection<Document> collection = mongoDatabase.getCollection("users");

        for (int i = 0; i < 10 ; i++) {
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj swoje imie:");
            String fisrtName1 = input.next();
            System.out.println("Podaj swoje nazwisko:");
            String lastName1 = input.next();
            System.out.println("Podaj wiek:");
            String age1 = input.next();

            Document doc = new Document("name", fisrtName1)
                    .append("surname", lastName1)
                    .append("age", age1);

            collection.insertOne(doc);
        }

        for (Document d : collection.find()) {
            System.out.println(d.toJson());
        }

        for (Document myDoc:collection.find(gt("wiek", 15))) {
            System.out.println(myDoc.toJson());
        }

    }
}
