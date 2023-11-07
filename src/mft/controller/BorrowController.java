package mft.controller;

import mft.model.bl.BookBl;
import mft.model.bl.BorrowBl;
import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Borrow;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class BorrowController {

    public static String save(int memberId, int bookId, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) {
        String message;
        try {
            Borrow borrow =
                    Borrow
                            .builder()
                            .member(MemberBl.findById(memberId))
                            .book(BookBl.findById(bookId))
                            .borrowTimeStamp(borrowTimeStamp)
                            .returnTimeStamp(returnTimeStamp)
                            .build();
            BorrowBl.save(borrow);
            message = borrow + " Saved";
            Logger.info("SAVE-BORROW", borrow.toString(), 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("SAVE-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String edit(int borrowId, int memberId, int bookId, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) {
        String message;
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
            message = borrow + "Edited";
            Logger.info("EDIT BORROW", borrow.toString(), 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("EDIT-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String remove(int id) {
        String message;
        try {
            Borrow borrow = BorrowBl.findById(id);
            BorrowBl.remove(id);
            message = borrow + "Removed";
            Logger.info("REMOVE-BORROW", borrow.toString(), 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("REMOVE-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findAll() {
        String message;
        try {
            message = BorrowBl.findAll().toString();
            Logger.info("FIND-BORROW", "ALL", 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findById(int id) {
        String message;
        try {
            message = BorrowBl.findById(id).toString();
            Logger.info("FIND BORROW BY ID", message, 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findByMemberId(int memberId) {
        String message;
        try {
            message = BorrowBl.findByMemberId(memberId).toString();
            Logger.info("FIND-BORROW", "BY MEMBER ID", 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findByBookId(int bookId) {
        String message;
        try {
            message = BorrowBl.findByBookId(bookId).toString();
            Logger.info("FIND-BORROW", "BY BOOK ID", 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) {
        String message;
        try {
            message = BorrowBl.findByBorrowTimeStampRange(startTimeStamp, endTimeStamp).toString();
            Logger.info("FIND-BORROW", "BY TIMESTAMP RANGE", 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findByReturnStatus(boolean returnStatus) {
        String message;
        try {
            message = BorrowBl.findByReturnStatus(returnStatus).toString();
            Logger.info("FIND-BORROW", "BY RETURN STATUS", 1);
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findByBookName(String bookName) {
        String message;
        try {
            if (Pattern.matches("[\\w]{2,30}", bookName)) {
                message = BorrowBl.findByBookName(bookName).toString();
                Logger.info("FIND-BORROW", "BY BOOK'S NAME", 1);
            } else {
                message = "Invalid Data !";
                Logger.error("FIND-ERROR", message, 1);
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }

    public static String findByMemberNameAndFamily(String name, String family) {
        String message;
        try {
            if (Pattern.matches("[a-zA-z\\s]{2,30}", name) && Pattern.matches("[a-zA-z\\s]{2,30}", family)) {
                message = BorrowBl.findByMemberNameAndFamily(name, family).toString();
                Logger.info("FIND-BORROW", "BY MEMBER'S NAME AND FAMILY", 1);
            } else {
                message = "Invali Data !";
                Logger.error("FIND-ERROR", message, 1);
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("FIND-ERROR", e.getMessage(), 1);
        }
        return message;
    }
}
