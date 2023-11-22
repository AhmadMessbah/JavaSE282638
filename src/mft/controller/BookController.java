package mft.controller;

import mft.model.bl.BookBl;
import mft.model.bl.Logger;
import mft.model.entity.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController {
    public static Map<String, String> save(String name, String author, int pages, String publisher, String language, String genre, String isbn, String description) {
        Map<String, String> result = new HashMap<>();
        try {
            if (Validator.checkName(name, 30) && Validator.checkEnglishText(author, 50) && Validator.checkEnglishText(publisher, 30)
                    && Validator.checkName(language, 30) && Validator.checkName(genre, 50) && Validator.checkEnglishText(description, 30)) {
                Book book = Book
                        .builder()
                        .name(name)
                        .author(author)
                        .pages(pages)
                        .publisher(publisher)
                        .language(language)
                        .genre(genre)
                        .isbn(isbn)
                        .description(description)
                        .build();
                BookBl.save(book);
                Logger.info("SAVE BOOK", book.toString(), 0);
                result.put("status", "true");
                result.put("message", book.toString() + "Saved");
            } else {
                Logger.error("SAVE BOOK", "INVALID DATA", 0);
                result.put("status", "false");
                result.put("message", "Invalid Data");
            }
        } catch (Exception e) {
            Logger.error("SAVE BOOK", e.getMessage(), 0);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static Map<String, String> edit(int id, String name, String author, int pages, String publisher, String language, String genre, String isbn, String description) {
        Map<String, String> result = new HashMap<>();
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(author, 50) && Validator.checkName(publisher, 30)
                    && Validator.checkName(language, 30) && Validator.checkName(genre, 50) && Validator.checkName(description, 30)) {
                Book book = Book
                        .builder()
                        .id(id)
                        .name(name)
                        .author(author)
                        .pages(pages)
                        .publisher(publisher)
                        .language(language)
                        .genre(genre)
                        .isbn(isbn)
                        .description(description)
                        .build();
                BookBl.edit(book);
                Logger.info("EDIT BOOK", book.toString(), 1);
                result.put("status", "true");
                result.put("message", book.toString() + " Edited");
            } else {
                Logger.error("EDIT BOOK", "INVALID DATA", 0);
                result.put("status", "false");
                result.put("message", "Invalid Data");
            }
        } catch (Exception e) {
            Logger.error("EDIT BOOK", e.getMessage(), 0);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static Map<String, String> remove(int id) {
        Map<String, String> result = new HashMap<>();
        try {
            Book book = BookBl.findById(id);
            BookBl.remove(id);
            Logger.info("REMOVE BOOK", book.toString(), 1);
            result.put("status", "true");
            result.put("message", book.toString() + " Removed");
        } catch (Exception e) {
            Logger.error("REMOVE BOOK", e.getMessage(), 1);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static List<Book> findAll() {
        try {
            List<Book> bookList = BookBl.findAll();
            Logger.info("FIND BOOK", "ALL", 1);
            return bookList;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book findById(int id) {
        try {
            Book book = BookBl.findById(id);
            Logger.info("FIND BOOK", "ID", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book findByName(String name) {
        try {
            Book book = BookBl.findByName(name);
            Logger.info("FIND BOOK", "NAME", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static Book findByAuthor(String author) {
        try {
            Book book = BookBl.findByAuthor(author);
            Logger.info("FIND BOOK", "AUTHOR", 1);
            return book;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Book> findByPublisher(String publisher) {
        try {
            List<Book> bookList = BookBl.findByPublisher(publisher);
            Logger.info("FIND BOOK", "PUBLISHER", BaseController.user.getId());
            return bookList;
        } catch (Exception e) {
            Logger.error("FIND BOOK", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }
}
