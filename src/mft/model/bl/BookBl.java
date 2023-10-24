package mft.model.bl;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.bcel.internal.generic.DRETURN;
import mft.controller.BookController;
import mft.model.da.BookDa;
import mft.model.entity.Book;

import java.util.List;

public class BookBl {
    public static Book save(Book book) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            if (bookDa.findByName(book.getName())==null){
                return bookDa.save(book);
            }else return null;
        }
    }

    public static Book edit(Book book) throws Exception{
        try(BookDa bookDa = new BookDa()) {
            return bookDa.edit(book);
        }
    }

    public static Book remove(int id) throws Exception{
        try(BookDa bookDa = new BookDa()) {
            Book book = bookDa.findById(id);
            bookDa.remove(id);
            return book;
        }
    }

    public static List<Book> findAll() throws Exception{
        try(BookDa bookDa = new BookDa()) {
            return bookDa.findAll();
        }
    }

    public static Book findById(int id) throws Exception{
        try(BookDa bookDa = new BookDa()) {
            return bookDa.findById(id);
        }
    }
    public static Book findByName(String name) throws Exception{
        try(BookDa bookDa = new BookDa()) {
            return bookDa.findByName(name);
        }
    }
    public static List<Book> findByAuthor(String author) throws Exception{
        try(BookDa bookDa = new BookDa()) {
            return bookDa.findByAuthor(author);
        }
    }
}
