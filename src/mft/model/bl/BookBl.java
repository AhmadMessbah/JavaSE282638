package mft.model.bl;

import mft.model.da.BookDa;
import mft.model.entity.Book;

import java.util.List;

public class BookBl {
    public static Book save(Book book) throws Exception{
        try(BookDa bookDa = new BookDa()) {
            return bookDa.save(book);
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
}
