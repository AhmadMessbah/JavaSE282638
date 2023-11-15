package mft.controller;

import mft.model.bl.BookBl;
import mft.model.bl.BorrowBl;
import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Borrow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class BorrowController {

    public static Borrow save(int memberId, int bookId) {
        try {
            Borrow borrow =
                    Borrow
                            .builder()
                            .member(MemberBl.findById(memberId))
                            .book(BookBl.findById(bookId))
                            .build();
            BorrowBl.save(borrow);
            Logger.info("SAVE-BORROW", borrow.toString(), 1);
            return borrow;
        } catch (Exception e) {
            Logger.error("SAVE-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static Borrow edit(int borrowId, int memberId, int bookId, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) {
        try {
            Borrow borrow =
                    Borrow
                            .builder()
                            .id(borrowId)
                            .member(MemberBl.findById(memberId))
                            .book(BookBl.findById(bookId))
                            .borrowTimeStamp(borrowTimeStamp)
                            .returnTimeStamp(returnTimeStamp)
                            .build();

            BorrowBl.edit(borrow);
            Logger.info("EDIT BORROW", borrow.toString(), 1);
            return borrow;
        } catch (Exception e) {
            Logger.error("EDIT-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static Borrow remove(int id) {
        try {
            Borrow borrow = BorrowBl.findById(id);
            BorrowBl.remove(id);
            Logger.info("REMOVE-BORROW", borrow.toString(), 1);
            return borrow;
        } catch (Exception e) {
            Logger.error("REMOVE-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findAll() {
        try {
            List<Borrow> borrowList = BorrowBl.findAll();
            Logger.info("FIND-BORROW", "ALL", 1);
            return borrowList;
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static Borrow findById(int id) {
        try {
            Borrow borrow = BorrowBl.findById(id);
            Logger.info("FIND BORROW BY ID", borrow.toString(), 1);
            return borrow;
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findByMemberId(int memberId) {
        String message;
        try {
            MemberBl.findById(memberId);
            List<Borrow> borrowList = BorrowBl.findByMemberId(memberId);
            Logger.info("FIND-BORROW", "BY MEMBER ID", 1);
            return borrowList;
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findByBookId(int bookId) {
        try {
            BookBl.findById(bookId);
            List<Borrow> borrowList = BorrowBl.findByBookId(bookId);
            Logger.info("FIND-BORROW", "BY BOOK ID", 1);
            return borrowList;
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) {
        String message;
        try {
            List<Borrow> borrowList = BorrowBl.findByBorrowTimeStampRange(startTimeStamp, endTimeStamp);
            Logger.info("FIND-BORROW", "BY TIMESTAMP RANGE", 1);
            return borrowList;
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findByReturnStatus(boolean returnStatus) {
        try {
            List<Borrow> borrowList = BorrowBl.findByReturnStatus(returnStatus);
            Logger.info("FIND-BORROW", "BY RETURN STATUS", 1);
            return borrowList;
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findByBookName(String bookName) {
        try {
            if (Pattern.matches("[\\w]{2,30}", bookName)) {
                BookBl.findByName(bookName);
                List<Borrow> borrowList = BorrowBl.findByBookName(bookName);
                Logger.info("FIND-BORROW", "BY BOOK'S NAME", 1);
                return borrowList;
            } else {
                Logger.error("FIND-ERROR", "Invalid Data !", 1);
                return null;
            }
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Borrow> findByMemberNameAndFamily(String name, String family) {
        try {
            if (Pattern.matches("[a-zA-z\\s]{2,30}", name) && Pattern.matches("[a-zA-z\\s]{2,30}", family)) {
                MemberBl.findByNameAndFamily(name, family);
                List<Borrow> borrowList = BorrowBl.findByMemberNameAndFamily(name, family);
                Logger.info("FIND-BORROW", "BY MEMBER'S NAME AND FAMILY", 1);
                return borrowList;
            } else {
                Logger.error("FIND-ERROR", "Invali Data !", 1);
                return null;
            }
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), 1);
            return null;
        }
    }
}
