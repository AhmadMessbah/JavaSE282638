package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Book {
    private int id;
    private String name;
    private String author;
    private int pages;
    private String publisher;
    private String language;
    private String genre;
    private String isbn;
    private String description;
    private boolean deleted;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
