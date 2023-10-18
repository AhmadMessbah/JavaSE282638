package mft.model.da;

import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Member;
import mft.model.utils.Jdbc;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BorrowDa {
    private Connection connection;
    private PreparedStatement statement;

    public BorrowDa() throws Exception {
        connection = Jdbc.getConnection();
    }

    public Borrow save(Borrow borrow) throws Exception {
        borrow.setId(Jdbc.nextId("BORROW_SEQ"));
        statement = connection.prepareStatement(
                "INSERT INTO BORROW_TBL(ID, MEMBER_ID, BOOK_ID, BORROW_TIMESTAMP, RETURN_TIMESTAMP) VALUES (?,?,?,?,?)"
        );
        statement.setInt(1, borrow.getId());
        statement.setInt(2, borrow.getMember().getId());
        statement.setInt(3, borrow.getBook().getId());
        statement.setTimestamp(4, Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        statement.setTimestamp(5,(borrow.getReturnTimeStamp()==null)?null: Timestamp.valueOf(borrow.getReturnTimeStamp()));
        statement.execute();
        close();
        return borrow;
    }

    public Borrow edit(Borrow borrow) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE BORROW_TBL SET MEMBER_ID=?, BOOK_ID=?, BORROW_TIMESTAMP=?, RETURN_TIMESTAMP=? WHERE ID=?"
        );
        statement.setInt(1, borrow.getMember().getId());
        statement.setInt(2, borrow.getBook().getId());
        statement.setTimestamp(3, Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        statement.setTimestamp(4,(borrow.getReturnTimeStamp()==null)?null: Timestamp.valueOf(borrow.getReturnTimeStamp()));
        statement.setInt(5, borrow.getId());
        statement.execute();
        close();
        return borrow;
    }

    public void remove(int id) throws Exception {
        //Borrow borrow = findById(id);
        statement = connection.prepareStatement(
                "DELETE FROM BORROW_TBL WHERE ID=?"
        );
        statement.setInt(1, id);
        statement.execute();
        close();
        //return borrow;
    }

    public List<Borrow> findAll() throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT ORDER BY BORROW_TIMESTAMP"
        );

        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()) {
            Member member =
                    Member
                            .builder()
                            .id(resultSet.getInt("M_ID"))
                            .name(resultSet.getString("M_NAME"))
                            .family(resultSet.getString("M_FAMILY"))
                            .build();

            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("B_ID"))
                            .name(resultSet.getString("B_NAME"))
                            .author(resultSet.getString("B_AUTHOR"))
                            .build();

            LocalDateTime returnDateTime = null;

            if (resultSet.getTimestamp("RETURN_TIMESTAMP") != null) {
                returnDateTime = resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime();
            }

            Borrow borrow =
                    Borrow
                            .builder()
                            .id(resultSet.getInt("BR_ID"))
                            .member(member)
                            .book(book)
                            .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                            .returnTimeStamp(returnDateTime)
                            .build();

            borrowList.add(borrow);
        }
        close();
        return borrowList;
    }

    public Borrow findById(int id) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE BR_ID=?"
        );
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        Member member = Member.builder()
                .id(resultSet.getInt("M_ID"))
                .name(resultSet.getString("M_NAME"))
                .family(resultSet.getString("M_FAMILY"))
                .build();

        Book book = Book.builder()
                .id(resultSet.getInt("B_ID"))
                .name(resultSet.getString("B_NAME"))
                .author(resultSet.getString("B_AUTHOR"))
                .build();

        LocalDateTime returnDateTime = null;
        if (resultSet.getTimestamp("RETURN_TIMESTAMP") != null) {
            returnDateTime = resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime();
        }

        Borrow borrow = Borrow.builder()
                .id(resultSet.getInt("BR_ID"))
                .member(member)
                .book(book)
                .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                .returnTimeStamp(returnDateTime)
                .build();

        close();
        return borrow;
    }

    public List<Borrow> findByMemberId(int memberId) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE M_ID=?"
        );
        statement.setInt(1, memberId);
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("M_ID"))
                    .name(resultSet.getString("M_NAME"))
                    .family(resultSet.getString("M_FAMILY"))
                    .build();

            Book book = Book.builder()
                    .id(resultSet.getInt("B_ID"))
                    .name(resultSet.getString("B_NAME"))
                    .author(resultSet.getString("B_AUTHOR"))
                    .build();

            LocalDateTime returnDateTime = null;
            if (resultSet.getTimestamp("RETURN_TIMESTAMP") != null) {
                returnDateTime = resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime();
            }

            Borrow borrow = Borrow.builder()
                    .id(resultSet.getInt("BR_ID"))
                    .member(member)
                    .book(book)
                    .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                    .returnTimeStamp(returnDateTime)
                    .build();

            borrowList.add(borrow);

        }
        close();
        return borrowList;
    }

    public List<Borrow> findByBookId(int bookId) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * from BORROW_REPORT where B_ID=?"
        );
        statement.setInt(1,bookId);
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("M_ID"))
                    .name(resultSet.getString("M_NAME"))
                    .family(resultSet.getString("M_FAMILY"))
                    .build();

            Book book = Book.builder()
                    .id(resultSet.getInt("B_ID"))
                    .name(resultSet.getString("B_NAME"))
                    .author(resultSet.getString("B_AUTHOR"))
                    .build();

            LocalDateTime returnDateTime = null;
            if (resultSet.getTimestamp("RETURN_TIMESTAMP") != null) {
                returnDateTime = resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime();
            }

            Borrow borrow = Borrow.builder()
                    .id(resultSet.getInt("BR_ID"))
                    .member(member)
                    .book(book)
                    .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                    .returnTimeStamp(returnDateTime)
                    .build();

            borrowList.add(borrow);
        }
        close();
        return borrowList;
    }


    public List<Borrow> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE BORROW_TIMESTAMP BETWEEN ? AND ?"
        );
        statement.setTimestamp(1, Timestamp.valueOf(startTimeStamp));
        statement.setTimestamp(2, Timestamp.valueOf(endTimeStamp));
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()) {
            Member member =
                    Member
                            .builder()
                            .id(resultSet.getInt("M_ID"))
                            .name(resultSet.getString("M_NAME"))
                            .family(resultSet.getString("M_FAMILY"))
                            .build();

            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("B_ID"))
                            .name(resultSet.getString("B_NAME"))
                            .author(resultSet.getString("B_AUTHOR"))
                            .build();

            LocalDateTime returnDateTime = null;

            if (resultSet.getTimestamp("RETURN_TIMESTAMP") != null) {
                returnDateTime = resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime();
            }

            Borrow borrow =
                    Borrow
                            .builder()
                            .id(resultSet.getInt("BR_ID"))
                            .member(member)
                            .book(book)
                            .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                            .returnTimeStamp(returnDateTime)
                            .build();

            borrowList.add(borrow);
        }
        close();
        return borrowList;
    }

    public List<Borrow> findByReturnStatus(boolean returnStatus) throws Exception {
        String sqlCommand;
        if (returnStatus) {
            sqlCommand = "SELECT * FROM BORROW_REPORT WHERE RETURN_TIMESTAMP IS NOT NULL";
        } else{
            sqlCommand = "SELECT * FROM BORROW_REPORT WHERE RETURN_TIMESTAMP IS NULL";
        }

        statement = connection.prepareStatement(sqlCommand);
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("M_ID"))
                    .name(resultSet.getString("M_NAME"))
                    .family(resultSet.getString("M_FAMILY"))
                    .build();

            Book book = Book.builder()
                    .id(resultSet.getInt("B_ID"))
                    .name(resultSet.getString("B_NAME"))
                    .author(resultSet.getString("B_AUTHOR"))
                    .build();

            LocalDateTime returnDateTime = null;
            if (resultSet.getTimestamp("RETURN_TIMESTAMP") != null) {
                returnDateTime = resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime();
            }

            Borrow borrow = Borrow.builder()
                    .id(resultSet.getInt("BR_ID"))
                    .member(member)
                    .book(book)
                    .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                    .returnTimeStamp(returnDateTime)
                    .build();

            borrowList.add(borrow);
        }
        close();
        return borrowList;
    }

    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
