package mft.controller;

import mft.model.bl.BookBl;
import mft.model.bl.Logger;
import mft.model.entity.Book;

import java.util.List;
import java.util.regex.Pattern;

public class BookController {
    public static String save(String name, String author) throws Exception {
        String message;
        try {
            if (Pattern.matches("[\\w]{2,30}", name) && Pattern.matches("[\\w]{2,30}", author)) {
                Book book = Book
                        .builder()
                        .name(name)
                        .author(author)
                        .build();
                BookBl.save(book);
                message = book + "Saved";
                Logger.info("SAVE BOOK", book.toString(), 1);
            } else {
                message = "Invalid data";
                Logger.info("SAVE ERROR", message, 1);
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.info("SAVE ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String edit(String name, String author) throws Exception {
        String message;
        try {
            if (Pattern.matches("[\\w]{2,30}", name) && Pattern.matches("[\\w]{2,30}", author)) {
                Book book = Book
                        .builder()
                        .name(name)
                        .author(author)
                        .build();
                BookBl.edit(book);
                message = book + "Edited";
                Logger.info("EDIT BOOK", book.toString(), 1);
            } else {
                message="Invalid data";
                Logger.error("EDIT BOOK", message, 1);
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("EDIT BOOK", e.getMessage(), 1);
        }
        return message;
    }

    public static String remove(int id) throws Exception {
        String message;
        try {
            Book book = BookBl.findById(id);
            BookBl.remove(id);
            message = book + "Removed";
            Logger.info("REMOVE BOOK", book.toString(), 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("REMOVE BOOK", e.getMessage(), 1);
        }
        return message;
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
            Book book = BookBl.findById(id);
            Logger.info("FIND BOOK", "ID", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book findByName(String name) throws Exception {
        try {
            Book book = BookBl.findByName(name);
            Logger.info("FIND BOOK", "NAME", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book findByAuthor(String author) throws Exception {
        try {
            Book book = BookBl.findByAuthor(author);
            Logger.info("FIND BOOK", "AUTHOR", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }
}
