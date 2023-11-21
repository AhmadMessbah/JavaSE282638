package mft.model.da;

import mft.model.entity.Book;
import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class BookDa implements AutoCloseable {
    private Connection connection;
    private PreparedStatement statement;

    public BookDa() throws Exception {
        connection = Jdbc.getConnection();
    }

    public Book save(Book book) throws Exception {
        book.setId(Jdbc.nextId("BOOK_SEQ"));
        statement = connection.prepareStatement(
                "INSERT INTO BOOK_TBL(ID, NAME, AUTHOR, PAGES, PUBLISHER, LANGUAGE, GENRE, ISBN, DESCRIPTION, DELETED) VALUES (?,?,?,?,?,?,?,?,?,?)"
        );
        statement.setInt(1, book.getId());
        statement.setString(2, book.getName());
        statement.setString(3, book.getAuthor());
        statement.setInt(4,book.getPages());
        statement.setString(5,book.getPublisher());
        statement.setString(6,book.getLanguage());
        statement.setString(7,book.getGenre());
        statement.setString(8,book.getIsbn());
        statement.setString(9,book.getDescription());
        statement.setBoolean(10,false);
        statement.execute();
        return book;
    }

    public Book edit(Book book) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE BOOK_TBL SET NAME=?, AUTHOR=?, PAGES=?, PUBLISHER=?, LANGUAGE=?, GENRE=?, ISBN=?, DESCRIPTION=?, DELETED=? WHERE ID=?"
        );
        statement.setString(1, book.getName());
        statement.setString(2, book.getAuthor());
        statement.setInt(3,book.getPages());
        statement.setString(4,book.getPublisher());
        statement.setString(5,book.getLanguage());
        statement.setString(6,book.getGenre());
        statement.setString(7,book.getIsbn());
        statement.setString(8,book.getDescription());
        statement.setBoolean(9,false);
        statement.setInt(10, book.getId());
        statement.execute();
        return book;
    }

//    public Book remove(int id) throws Exception {
//        statement = connection.prepareStatement(
//                "DELETE FROM BOOK_TBL WHERE ID=?"
//        );
//        statement.setInt(1, id);
//        statement.execute();
//        return null;
//    }

    public Book remove(int id) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE BOOK_TBL SET DELETED=1 WHERE ID=?"
        );
        statement.setInt(1, id);
        statement.execute();
        return null;
    }

    public List<Book> findAll() throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL ORDER BY BOOK_TBL.ID"
        );
        ResultSet resultSet = statement.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book = Book.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .author(resultSet.getString("AUTHOR"))
                    .pages(resultSet.getInt("PAGES"))
                    .publisher(resultSet.getString("PUBLISHER"))
                    .language(resultSet.getString("LANGUAGE"))
                    .genre(resultSet.getString("GENRE"))
                    .isbn(resultSet.getString("ISBN"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
            bookList.add(book);
        }
        return bookList;
    }

    public Book findById(int id) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE ID=?"
        );
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Book book = null;
        while (resultSet.next()) {
            book = Book.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .author(resultSet.getString("AUTHOR"))
                    .pages(resultSet.getInt("PAGES"))
                    .publisher(resultSet.getString("PUBLISHER"))
                    .language(resultSet.getString("LANGUAGE"))
                    .genre(resultSet.getString("GENRE"))
                    .isbn(resultSet.getString("ISBN"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        return book;
    }

    public Book findByName(String name) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE NAME LIKE ?"
        );
        statement.setString(1,name);
        ResultSet resultSet = statement.executeQuery();
        Book book = null;
        while (resultSet.next()) {
            book = Book.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .author(resultSet.getString("AUTHOR"))
                    .pages(resultSet.getInt("PAGES"))
                    .publisher(resultSet.getString("PUBLISHER"))
                    .language(resultSet.getString("LANGUAGE"))
                    .genre(resultSet.getString("GENRE"))
                    .isbn(resultSet.getString("ISBN"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        return book;
    }

    public Book findByAuthor(String author) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE AUTHOR LIKE ?"
        );
        statement.setString(1, author);
        ResultSet resultSet = statement.executeQuery();
        Book book = null;
        while (resultSet.next()) {
            book = Book.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .author(resultSet.getString("AUTHOR"))
                    .pages(resultSet.getInt("PAGES"))
                    .publisher(resultSet.getString("PUBLISHER"))
                    .language(resultSet.getString("LANGUAGE"))
                    .genre(resultSet.getString("GENRE"))
                    .isbn(resultSet.getString("ISBN"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .deleted(resultSet.getBoolean("DELETED"))
                    .build();
        }
        return book;
    }

    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
