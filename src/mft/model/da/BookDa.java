package mft.model.da;

import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Member;
import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookDa {
    private Connection connection;
    private PreparedStatement statement;

    public BookDa() throws Exception {
        connection = Jdbc.getConnection();
    }
    public Book save(Book book) throws Exception {
        book.setId(Jdbc.nextId("BOOK_SEQ"));
        statement = connection.prepareStatement(
                "INSERT INTO BOOK_TBL(ID,NAME,AUTHOR) VALUES (?,?,?)"
        );
        statement.setInt(1, book.getId());
        statement.setString(2,book.getName());
        statement.setString(3,book.getAuthor());
        statement.execute();
        close();
        return book;
    }
    public Book edit(Book book) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE BOOK_TBL SET NAME=?, AUTHOR=? WHERE ID=?"
        );
        statement.setString(1,book.getName());
        statement.setString(2,book.getAuthor());
        statement.setInt(3, book.getId());
        statement.execute();
        return book;
    }
    public void remove(int id) throws Exception {
        //Book book = findById(id);
        statement = connection.prepareStatement(
                "DELETE FROM BOOK_TBL WHERE ID=?"
        );
        statement.setInt(1, id);
        statement.execute();
        //return book;
    }
    public List<Book>  findAll() throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL ORDER BY BOOK_TBL.ID"
        );
        ResultSet resultSet=statement.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book = Book.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .author(resultSet.getString("AUTHOR"))
                    .build();
            bookList.add(book);
        }
        close();
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
        close();
        return book;
    }
    public Book findByName(String name) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE NAME=?"
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
        close();
        return book;
    }
    public Book findByAuthor(String author) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE AUTHOR=?"
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
        close();
        return book;
    }
    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
