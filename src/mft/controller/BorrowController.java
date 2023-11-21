package mft.controller;

import mft.model.bl.BookBl;
import mft.model.bl.BorrowBl;
import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Borrow;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowController {

    public static Map<String, String> save(int memberId, int bookId, String description) {
        Map<String, String> result = new HashMap<>();
        try {
            Borrow borrow =
                    Borrow
                            .builder()
                            .member(MemberBl.findById(memberId))
                            .book(BookBl.findById(bookId))
                            .description(description)
                            .build();
            BorrowBl.save(borrow);
            Logger.info("SAVE-BORROW", borrow.toString(), BaseController.user.getId());
            result.put("status", "true");
            result.put("message", borrow.toString()+" Saved");
        } catch (Exception e) {
            Logger.error("SAVE-ERROR", e.getMessage(), BaseController.user.getId());
            result.put("status", "false");
            result.put("message",e.getMessage());
        }
        return result;
    }

    public static Map<String, String> edit(int borrowId, int memberId, int bookId, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp, String description, boolean deleted) {
        Map<String, String> result = new HashMap<>();
        try {
            Borrow borrow =
                    Borrow
                            .builder()
                            .id(borrowId)
                            .member(MemberBl.findById(memberId))
                            .book(BookBl.findById(bookId))
                            .borrowTimeStamp(borrowTimeStamp)
                            .returnTimeStamp(returnTimeStamp)
                            .description(description)
                            .deleted(deleted)
                            .build();
            BorrowBl.edit(borrow);
            Logger.info("EDIT BORROW", borrow.toString(), BaseController.user.getId());
            result.put("status", "true");
            result.put("message", borrow.toString()+" Edited");
        } catch (Exception e) {
            Logger.error("EDIT-ERROR", e.getMessage(), BaseController.user.getId());
            result.put("status", "false");
            result.put("message",e.getMessage());
        }
        return result;
    }

    public static Map<String, String> returnBook(int id){
        Map<String, String> result = new HashMap<>();
        try {
            BorrowBl.returnBook(id);
            Logger.info("RETURN-BOOK", "BORROW ID : "+id, BaseController.user.getId());
            result.put("status", "true");
            result.put("message", "BORROW ID ("+id+") RETURNED");
        } catch (Exception e) {
            Logger.error("RETURN BOOK-ERROR", e.getMessage(), BaseController.user.getId());
            result.put("status", "false");
            result.put("message",e.getMessage());
        }
        return result;
    }

    public static Map<String, String> remove(int id) {
        Map<String, String> result = new HashMap<>();
        try {
            Borrow borrow = BorrowBl.findById(id);
            BorrowBl.remove(id);
            Logger.info("REMOVE-BORROW", borrow.toString(), BaseController.user.getId());
            result.put("status", "true");
            result.put("message", borrow.toString()+" Removed");
        } catch (Exception e) {
            Logger.error("REMOVE-ERROR", e.getMessage(), BaseController.user.getId());
            result.put("status", "false");
            result.put("message",e.getMessage());
        }
        return result;
    }

    public static Map<String, List> findAll(){
        Map<String, List> result = new HashMap<>();
        try {
            List<Borrow> borrowList = BorrowBl.findAll();
            Logger.info("FIND-BORROW", "ALL", BaseController.user.getId());
            result.put("ok", borrowList);
        }catch (Exception e){
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

//    public static List<Borrow> findAll() {
//        try {
//            List<Borrow> borrowList = BorrowBl.findAll();
//            Logger.info("FIND-BORROW", "ALL", 1);
//            return borrowList;
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), 1);
//            return null;
//        }
//    }

    public static Map<String, Borrow> findById(int id) {
        Map<String, Borrow> result = new HashMap<>();
        try {
            Borrow borrow = BorrowBl.findById(id);
            Logger.info("FIND BORROW BY ID", borrow.toString(), BaseController.user.getId());
            result.put("ok", borrow);
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

    public static Map<String, List> findByMemberId(int memberId) {
        Map<String, List> result = new HashMap<>();
        try {
            MemberBl.findById(memberId);
            List<Borrow> borrowList = BorrowBl.findByMemberId(memberId);
            Logger.info("FIND-BORROW", "BY MEMBER ID", BaseController.user.getId());
            result.put("ok", borrowList);
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }
//    public static List<Borrow> findByMemberId(int memberId) {
//        try {
//            MemberBl.findById(memberId);
//            List<Borrow> borrowList = BorrowBl.findByMemberId(memberId);
//            Logger.info("FIND-BORROW", "BY MEMBER ID", 1);
//            return borrowList;
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), 1);
//            return null;
//        }
//    }

    public static Map<String, List> findByBookId(int bookId) {
        Map<String, List> result = new HashMap<>();
        try {
            BookBl.findById(bookId);
            List<Borrow> borrowList = BorrowBl.findByBookId(bookId);
            Logger.info("FIND-BORROW", "BY BOOK ID", BaseController.user.getId());
            result.put("ok", borrowList);
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

//    public static List<Borrow> findByBookId(int bookId) {
//        try {
//            BookBl.findById(bookId);
//            List<Borrow> borrowList = BorrowBl.findByBookId(bookId);
//            Logger.info("FIND-BORROW", "BY BOOK ID", 1);
//            return borrowList;
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), 1);
//            return null;
//        }
//    }

    public static Map<String, List> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) {
        Map<String, List> result = new HashMap<>();
        try {
            List<Borrow> borrowList = BorrowBl.findByBorrowTimeStampRange(startTimeStamp, endTimeStamp);
            Logger.info("FIND-BORROW", "BY TIMESTAMP RANGE", BaseController.user.getId());
            result.put("ok", borrowList);
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

//    public static List<Borrow> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) {
//        String message;
//        try {
//            List<Borrow> borrowList = BorrowBl.findByBorrowTimeStampRange(startTimeStamp, endTimeStamp);
//            Logger.info("FIND-BORROW", "BY TIMESTAMP RANGE", 1);
//            return borrowList;
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), 1);
//            return null;
//        }
//    }

    public static Map<String, List> findByReturnStatus(boolean returnStatus) {
        Map<String, List> result = new HashMap<>();
        try {
            List<Borrow> borrowList = BorrowBl.findByReturnStatus(returnStatus);
            Logger.info("FIND-BORROW", "BY RETURN STATUS", BaseController.user.getId());
            result.put("ok", borrowList);
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

//    public static List<Borrow> findByReturnStatus(boolean returnStatus) {
//        try {
//            List<Borrow> borrowList = BorrowBl.findByReturnStatus(returnStatus);
//            Logger.info("FIND-BORROW", "BY RETURN STATUS", BaseController.user.getId());
//            return borrowList;
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
//            return null;
//        }
//    }

    public static Map<String, List> findByBookName(String bookName) {
        Map<String, List> result = new HashMap<>();
        try {
            if (Validator.checkName(bookName, 30)) {
                BookBl.findByName(bookName);
                List<Borrow> borrowList = BorrowBl.findByBookName(bookName);
                Logger.info("FIND-BORROW", "BY BOOK'S NAME", BaseController.user.getId());
                result.put("ok", borrowList);
            } else {
                Logger.error("FIND-ERROR", "Invalid Data !", BaseController.user.getId());
                result.put("Invalid Data !", null);
            }
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

//    public static List<Borrow> findByBookName(String bookName) {
//        try {
//            if (Validator.checkName(bookName, 30)) {
//                BookBl.findByName(bookName);
//                List<Borrow> borrowList = BorrowBl.findByBookName(bookName);
//                Logger.info("FIND-BORROW", "BY BOOK'S NAME", BaseController.user.getId());
//                return borrowList;
//            } else {
//                Logger.error("FIND-ERROR", "Invalid Data !", BaseController.user.getId());
//                return null;
//            }
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
//            return null;
//        }
//    }

    public static Map<String, List> findByMemberNameAndFamily(String name, String family) {
        Map<String, List> result = new HashMap<>();
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                MemberBl.findByNameAndFamily(name, family);
                List<Borrow> borrowList = BorrowBl.findByMemberNameAndFamily(name, family);
                Logger.info("FIND-BORROW", "BY MEMBER'S NAME AND FAMILY", BaseController.user.getId());
                result.put("ok", borrowList);
            } else {
                Logger.error("FIND-ERROR", "Invalid Data !", BaseController.user.getId());
                result.put("Invalid Data !", null);
            }
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }

//    public static List<Borrow> findByMemberNameAndFamily(String name, String family) {
//        try {
//            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
//                MemberBl.findByNameAndFamily(name, family);
//                List<Borrow> borrowList = BorrowBl.findByMemberNameAndFamily(name, family);
//                Logger.info("FIND-BORROW", "BY MEMBER'S NAME AND FAMILY", BaseController.user.getId());
//                return borrowList;
//            } else {
//                Logger.error("FIND-ERROR", "Invalid Data !", BaseController.user.getId());
//                return null;
//            }
//        } catch (Exception e) {
//            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
//            return null;
//        }
//    }

    public static Map<String, List> findByDeletedStatus(boolean deleted) {
        Map<String, List> result = new HashMap<>();
        try {
            List<Borrow> borrowList = BorrowBl.findByDeletedStatus(deleted);
            Logger.info("FIND-BORROW", "BY DELETED STATUS", BaseController.user.getId());
            result.put("ok", borrowList);
        } catch (Exception e) {
            Logger.error("FIND-ERROR", e.getMessage(), BaseController.user.getId());
            result.put(e.getMessage(), null);
        }
        return result;
    }
}
