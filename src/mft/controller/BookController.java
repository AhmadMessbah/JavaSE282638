package mft.controller;

import mft.model.bl.BookBl;
import mft.model.entity.Book;

import java.util.regex.Pattern;

public class BookController {
    public static Book save(String name, String author) throws Exception{
        try{
            if(Pattern.matches("[\\w]{2,30}",name) && Pattern.matches("[\\w]{2,30}",author)){
                Book book = Book.builder().name(name).author(author).build();
                return BookBl.save(book);
            }else{
                System.out.println("Invalid Data");
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
