package mft.model.bl;

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
            throw new NotReturnedBookException();
        }
    }

    public static Borrow edit(Borrow borrow) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.edit(borrow);
        }
    }

    public static Borrow remove(int id) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            Borrow borrow = borrowDa.findById(id);
            borrowDa.remove(id);
            return borrow;
        }
    }

    public static List<Borrow> findAll() throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findAll();
        }
    }

    public static Borrow findById(int id) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findById(id);
        }
    }

    public static List<Borrow> findByMemberId(int memberId) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findByMemberId(memberId);
        }
    }

    public static List<Borrow> findByBookId(int bookId) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findByBookId(bookId);
        }
    }

    public static List<Borrow> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findByBorrowTimeStampRange(startTimeStamp, endTimeStamp);
        }
    }

    public static List<Borrow> findByReturnStatus(boolean returnStatus) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findByReturnStatus(returnStatus);
        }
    }

    public static List<Borrow> findByBookName(String bookName) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findByBookName(bookName);
        }
    }

    public static List<Borrow> findByMemberNameFamily(String name, String family) throws Exception {
        try (BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findByMemberNameFamily(name, family);
        }
    }

}
