package DAO;

import Server.NotesListDto;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NotesDAO {

    private static NotesDAO instance;

    private NotesDAO(){

    }

    public void addNote(String note) {
        MongoDatabase db = DatabaseConnection.mongoClient.getDatabase("MyDatabase");
        MongoCollection<Document> notesCollection = db.getCollection("Notes");
        Document newNote = new Document("note", note);
        notesCollection.insertOne(newNote);
    }

    public NotesListDto getAllNotes() {
        MongoDatabase db = DatabaseConnection.mongoClient.getDatabase("MyDatabase");
        MongoCollection<Document> notesCollection = db.getCollection("Notes");
        List<String> notes = notesCollection.find().into(new ArrayList<>())
                            .stream()
                            .map(document -> {
                                return document.getString("note");
                            })
                            .collect(Collectors.toList());
        return new NotesListDto(notes);
    }

    public static NotesDAO getInstance() {
        if(instance == null) {
            instance = new NotesDAO();
        }
        return instance;
    }
}
