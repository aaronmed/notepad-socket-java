package server;

import java.util.ArrayList;
import java.util.List;
import model.Note;

public class NoteController {
    
    List<Note> notes = new ArrayList<>();

    public NoteController() {
        createBasicNotes();
    }
   
    private void createBasicNotes() {
        notes.add(new Note("Comprar pan", "Pepe", "Comida"));
        notes.add(new Note("Llevar el coche a la ITV", "Juan", "Coche"));
    }
    
    public List<Note> getNotes(){
        return notes;
    }
    
    public void addNote(Note n){
        notes.add(n);
    }
    
    public List<Note> getNotesByCategory(String category){
        List<Note> notesByCategory = new ArrayList<>();
        for(Note n: notes){
            if (n.getCategory().equals(category)){
                notesByCategory.add(n);
            }
        }
        return notesByCategory;
    }
}
