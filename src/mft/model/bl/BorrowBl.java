package mft.model.bl;

import mft.exception.NoContentException;
import mft.exception.NotReturnedBookException;
import mft.model.da.BorrowDa;
import mft.model.entity.Borrow;

import java.time.LocalDateTime;
import java.util.List;

public class BorrowBl {

    public static Borrow save(Borrow borrow) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            if (borrowDa.memberNotReturnedBooks(borrow.getMember().getId()) == 0) {
                return borrowDa.save(borrow);
            }
            throw new NotReturnedBookException("Previous Borrowed Book not returned !");
        }
    }

    public static Borrow edit(Borrow borrow) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            if (borrowDa.findById(borrow.getId()) != null) {
                return borrowDa.edit(borrow);
            }
            throw new NoContentException("Invalid borrow ID !");
        }
    }

    public static Borrow remove(int id) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            Borrow borrow = borrowDa.findById(id);
            if (borrow != null) {
                borrowDa.remove(id);
                return borrow;
            }
            throw new NoContentException("No Borrow !");
        }
    }

    public static List<Borrow> findAll() throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            List<Borrow> borrowList = borrowDa.findAll();
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record !");
        }
    }

    public static Borrow findById(int id) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            Borrow borrow = borrowDa.findById(id);
            if (borrow != null) {
                return borrow;
            }
            throw new NoContentException("Invalid borrow ID !");
        }
    }

    public static List<Borrow> findByMemberId(int memberId) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            MemberBl.findById(memberId);
            List<Borrow> borrowList = borrowDa.findByMemberId(memberId);
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record for this member !");
        }
    }

    public static List<Borrow> findByBookId(int bookId) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            BookBl.findById(bookId);
            List<Borrow> borrowList = borrowDa.findByBookId(bookId);
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record for this book !");
        }
    }

    public static List<Borrow> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            List<Borrow> borrowList = borrowDa.findByBorrowTimeStampRange(startTimeStamp, endTimeStamp);
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record in this term !");
        }
    }

    public static List<Borrow> findByReturnStatus(boolean returnStatus) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            List<Borrow> borrowList = borrowDa.findByReturnStatus(returnStatus);
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record !");
        }
    }

    public static List<Borrow> findByBookName(String bookName) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            BookBl.findByName(bookName);
            List<Borrow> borrowList = borrowDa.findByBookName(bookName);
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record for this book !");
        }
    }

    public static List<Borrow> findByMemberNameAndFamily(String name, String family) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            MemberBl.findByNameAndFamily(name, family);
            List<Borrow> borrowList = borrowDa.findByMemberNameAndFamily(name, family);
            if (borrowList.size() > 0) {
                return borrowList;
            }
            throw new NoContentException("There is no borrow record for this member !");
        }
    }

}
