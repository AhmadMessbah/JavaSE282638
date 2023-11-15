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
                "INSERT INTO BOOK_TBL(ID,NAME, AUTHOR) VALUES (?,?,?)"
        );
        statement.setInt(1, book.getId());
        statement.setString(2, book.getName());
        statement.setString(3, book.getAuthor());
        statement.execute();
        return book;
    }

    public Book edit(Book book) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE BOOK_TBL SET NAME=?, AUTHOR=? WHERE ID=?"
        );
        statement.setString(1, book.getName());
        statement.setString(2, book.getAuthor());
        statement.setInt(3, book.getId());
        statement.execute();
        return book;
    }

    public Book remove(int id) throws Exception {
        statement = connection.prepareStatement(
                "DELETE FROM BOOK_TBL WHERE ID=?"
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
                    .build();
        }
        return book;
    }

    public Book findByName(String name) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE NAME LIKE ?"
        );
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        Book book = null;
        while (resultSet.next()) {
            book = Book.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .author(resultSet.getString("AUTHOR"))
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
