package mft.model.bl;

import mft.controller.exception.DuplicateBookNameException;
import mft.controller.exception.NoContentException;
import mft.model.da.BookDa;
import mft.model.entity.Book;

import java.util.List;

public class BookBl {
    public static Book save(Book book) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            if (bookDa.findByName(book.getName()) == null) {
                return bookDa.save(book);
            }
            throw new DuplicateBookNameException("Duplicate book name");
        }
    }

    public static Book edit(Book book) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            return bookDa.edit(book);
        }
    }

    public static Book remove(int id) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            Book book = bookDa.findById(id);
            if (book != null) {
                bookDa.remove(id);
                return book;
            }throw new NoContentException("No book");
        }
    }

    public static List<Book> findAll() throws Exception {
        try (BookDa bookDa = new BookDa()) {
            List<Book> bookList=bookDa.findAll();
            if (bookList.size()>0) {
                return bookList;
            }
        }throw new NoContentException("There is no book !");
    }

    public static Book findById(int id) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            Book book=bookDa.findById(id);
            if (book != null) {
                return book;
            }
            throw new NoContentException("No book !");
        }
    }

    public static Book findByName(String name) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            Book book=bookDa.findByName(name);
            if (book != null) {
                return book;
            }
            throw new NoContentException("No book !");
        }
    }

    public static Book findByAuthor(String author) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            Book book=bookDa.findByAuthor(author);
            if (book != null) {
                return book;
            }
            throw new NoContentException("No book !");
        }
    }

    public static List<Book> findByPublisher(String publisher) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            List<Book> bookList=bookDa.findByPublisher(publisher);
            if (bookList.size()>0) {
                return bookList;
            }
        }throw new NoContentException("There is no book !");
    }
}
