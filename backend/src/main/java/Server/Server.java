package Server;

import DAO.NotesDAO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Server {
    private static List<String> notes = new ArrayList<>();

    public static void main(String[] args){
        Gson gson = new Gson(); // remember to make new gson object
        port(4000);
        path("/api", () -> {
            // get request data
            // data is all in the url
            // /api/users?abc=efg // anyone can see
            get("/users", (req, res) -> {
                // put more stuff here
                return "Test";
            });
            // post
            // data is in the body
            // don't see the data in the url
            post("/addNote", (req, res) -> {
                String bodyString = req.body();
                AddNoteDto noteDto = gson.fromJson(bodyString,
                        AddNoteDto.class);
                // add it to the list
                // notes.add(noteDto.note);
                NotesDAO notesDAO = NotesDAO.getInstance();
                notesDAO.addNote(noteDto.note);
                System.out.println(bodyString);
                System.out.println(notes.size());
                return "OK";
            });
            get("/getAllNotes", (req, res) -> {
                NotesDAO notesDAO = NotesDAO.getInstance();
                // NotesListDto list = new NotesListDto(notes);
                NotesListDto list = notesDAO.getAllNotes();
                return gson.toJson(list);
            });
            // if https then the body is encrypted
        });
    }
}
