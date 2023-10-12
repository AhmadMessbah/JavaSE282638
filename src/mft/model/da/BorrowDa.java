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
        Jdbc jdbc = new Jdbc();
        connection = jdbc.getConnection();
    }

    public void save(Borrow borrow) throws Exception {
        statement = connection.prepareStatement(
                "INSERT INTO BORROW_TBL(ID, MEMBER_ID, BOOK_ID, BORROW_TIMESTAMP, RETURN_TIMESTAMP) VALUES (?,?,?,?,?)"
        );
        statement.setInt(1,borrow.getId());
        statement.setInt(2,borrow.getMember().getId());
        statement.setInt(3,borrow.getBook().getId());
        statement.setTimestamp(4,Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        statement.setTimestamp(5,Timestamp.valueOf(borrow.getReturnTimeStamp()));
        statement.execute();
        close();
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

            if (resultSet.getTimestamp("RETURN_TIMESTAMP")!= null){
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

            if (resultSet.getTimestamp("RETURN_TIMESTAMP")!= null){
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
                "SELECT * FROM BORROW_REPORT WHERE BR_ID = "+id
        );
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
        if (resultSet.getTimestamp("RETURN_TIMESTAMP")!=null){
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

    public List<Borrow> findByMemberId(int m_id) throws Exception{
        statement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE M_ID = "+ m_id
        );
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()){
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
            if (resultSet.getTimestamp("RETURN_TIMESTAMP")!=null){
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

    public List<Borrow> findByBookId(int b_id) throws Exception{
        statement = connection.prepareStatement(
                "SELECT * from BORROW_REPORT where B_ID = " + b_id
        );
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()){
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
            if (resultSet.getTimestamp("RETURN_TIMESTAMP")!=null){
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

    public List<Borrow> findByReturnStatus(boolean returnStatus) throws Exception{
        String queryAddition ="is null";
        LocalDateTime returnDateTime = null;
        if (returnStatus){
            queryAddition = "is not null";
        }
        statement = connection.prepareStatement(
                "select * from BORROW_REPORT where RETURN_TIMESTAMP " + queryAddition
        );
        ResultSet resultSet = statement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()){
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

            if (resultSet.getTimestamp("RETURN_TIMESTAMP")!=null){
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

    public Borrow edit(int br_id,LocalDateTime borrowTimeStamp,LocalDateTime returnTimeStamp) throws Exception{
        statement = connection.prepareStatement(
                "update BORROW_TBL set BORROW_TIMESTAMP = ? ,RETURN_TIMESTAMP = ? where ID = "+br_id+" and RETURN_TIMESTAMP is not null "
        );
        statement.setTimestamp(1, Timestamp.valueOf(borrowTimeStamp));
        statement.setTimestamp(2, Timestamp.valueOf(returnTimeStamp));
        statement.execute();

        statement = connection.prepareStatement(
                "select * from BORROW_REPORT where BR_ID = "+br_id
        );
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

        Borrow borrow = Borrow.builder()
                .id(resultSet.getInt("BR_ID"))
                .member(member)
                .book(book)
                .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                .returnTimeStamp(resultSet.getTimestamp("RETURN_TIMESTAMP").toLocalDateTime())
                .build();

        close();
        return borrow;
    }

    public Borrow edit(int br_id,LocalDateTime borrowTimeStamp) throws Exception{
        statement = connection.prepareStatement(
                "update BORROW_TBL set BORROW_TIMESTAMP = ? where ID = "+br_id+ " and RETURN_TIMESTAMP is null "
        );
        statement.setTimestamp(1,Timestamp.valueOf(borrowTimeStamp));
        statement.execute();

        statement = connection.prepareStatement(
                "select * from BORROW_REPORT where BR_ID = "+br_id
        );
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

        Borrow borrow = Borrow.builder()
                .id(resultSet.getInt("BR_ID"))
                .member(member)
                .book(book)
                .borrowTimeStamp(resultSet.getTimestamp("BORROW_TIMESTAMP").toLocalDateTime())
                .returnTimeStamp(null)
                .build();

        close();
        return borrow;
    }

    public void close() throws Exception{
        statement.close();
        connection.close();
    }
}
