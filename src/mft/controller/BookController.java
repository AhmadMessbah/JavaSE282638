package mft.controller;

import lombok.ToString;
import mft.model.bl.BookBl;
import mft.model.bl.Logger;
import mft.model.da.BookDa;
import mft.model.entity.Book;

import java.util.List;
import java.util.regex.Pattern;

public class BookController {
    public static Book save(String name, String author) throws Exception {
        try {
            if (Pattern.matches("[\\w]{2,30}", name) && Pattern.matches("[\\w]{2,30}", author)) {
                Book book = Book.builder().name(name).author(author).build();
                BookBl.save(book);
                Logger.info("SAVE BOOK", book.toString(), 1);
                return book;
            } else {
                Logger.error("SAVE BOOK", "INVALID DATA", 1);
                return null;
            }
        } catch (Exception e) {
            Logger.error("SAVE BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book edit(String name, String author) throws Exception {
        try {
            if (Pattern.matches("[\\w]{2,30}", name) && Pattern.matches("[\\w]{2,30}", author)) {
                Book book = Book.builder().name(name).author(author).build();
                BookBl.edit(book);
                Logger.info("EDIT BOOK", book.toString(), 1);
                return book;
            } else {
                Logger.error("EDIT BOOK", "INVALID DATA", 1);
                return null;
            }
        } catch (Exception e) {
            Logger.error("EDIT BOOK", e.getMessage(), 1);
            return null;
        }
    }
    public static Book remove(int id) throws Exception {
        try {
            Book book = BookBl.findById(id);
            BookBl.remove(id);
            Logger.info("REMOVE BOOK", book.toString(), 1);
            return book;
        } catch (Exception e) {
            Logger.error("REMOVE BOOK", e.getMessage(), 1);
            return null;
        }
    }

        public static List<Book> findAll() throws Exception {
        try {
            List<Book> bookList = BookBl.findAll();
            Logger.info("FIND BOOK", "ALL", 1);
            return bookList;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book findById(int id) throws Exception {
        try {
            Book book =BookBl.findById(id);
            Logger.info("FIND BOOK", "ID", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }
    public static Book findByName(String name) throws Exception {
        try {
            Book book =BookBl.findByName(name);
            Logger.info("FIND BOOK", "NAME", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }
    public static List<Book> findByAuthor(String author) throws Exception {
        try {
            List<Book> bookList = BookBl.findByAuthor(author);
            Logger.info("FIND BOOK", "AUTHOR", 1);
            return bookList;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }
}
