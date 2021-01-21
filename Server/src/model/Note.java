package model;

import java.io.Serializable;

public class Note implements Serializable {
    
    private String text;
    private String author;
    private String category;
    
    public Note(String text, String author, String category){
        this.text = text;
        this.author = author;
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }
}