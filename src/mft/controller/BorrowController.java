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

    public static Borrow save(int memberId, int bookId, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) throws Exception{
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
            Logger.info("SAVE BORROW",borrow.toString(),1);
            return borrow;
        }catch (Exception e){
            Logger.error("SAVE BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static Borrow edit(int borrowId, int memberId, int bookId, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) throws Exception{
        try {
            Borrow borrow = Borrow.builder().id(borrowId).member(MemberBl.findById(memberId)).book(BookBl.findById(bookId)).borrowTimeStamp(borrowTimeStamp).returnTimeStamp(returnTimeStamp).build();
            BorrowBl.edit(borrow);
            Logger.info("EDIT BORROW",borrow.toString(),1);
            return borrow;
        }catch (Exception e){
            Logger.error("EDIT BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static Borrow remove(int id) throws Exception{
        try {
            Borrow borrow = BorrowBl.findById(id);
            BorrowBl.remove(id);
            Logger.info("REMOVE BORROW",borrow.toString(),1);
            return borrow;
        }catch (Exception e){
            Logger.error("REMOVE BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findAll() throws Exception{
        try {
            Logger.info("FIND BORROW","ALL",1);
            return BorrowBl.findAll();
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static Borrow findById(int id) throws Exception{
        try {
            Logger.info("FIND BORROW BY ID",BorrowBl.findById(id).toString(),1);
            return BorrowBl.findById(id);
        }catch (Exception e){
            Logger.error("FIND BORROW BY ID",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findByMemberId(int memberId) throws Exception{
        try {
            Logger.info("FIND BORROW","BY MEMBER ID",1);
            return BorrowBl.findByMemberId(memberId);
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findByBookId(int bookId) throws Exception {
        try {
            Logger.info("FIND BORROW","BY BOOK ID",1);
            return BorrowBl.findByBookId(bookId);
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findByBorrowTimeStampRange(LocalDateTime startTimeStamp, LocalDateTime endTimeStamp) throws Exception{
        try {
            Logger.info("FIND BORROW","BY TIMESTAMP RANGE",1);
            return BorrowBl.findByBorrowTimeStampRange(startTimeStamp,endTimeStamp);
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findByReturnStatus(boolean returnStatus) throws Exception{
        try {
            Logger.info("FIND BORROW","BY RETURN STATUS",1);
            return BorrowBl.findByReturnStatus(returnStatus);
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findByBookName(String bookName) throws Exception{
        try {
            if (Pattern.matches("[\\w]{2,30}",bookName)){
                Logger.info("FIND BORROW","BY BOOK NAME",1);
                return BorrowBl.findByBookName(bookName);
            }else {
                Logger.error("FIND BORROW","INVALID DATA",1);
                return null;
            }
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }

    public static List<Borrow> findByMemberNameFamily(String name, String family) throws Exception{
        try {
            if (Pattern.matches("[\\w]{2,30}",name) && Pattern.matches("[\\w]{2,30}",family)){
                Logger.info("FIND BORROW","BY MEMBER'S NAME AND FAMILY",1);
                return BorrowBl.findByMemberNameFamily(name,family);
            }else {
                Logger.error("FIND BORROW","INVALID DATA",1);
                return null;
            }
        }catch (Exception e){
            Logger.error("FIND BORROW",e.getMessage(),1);
            return null;
        }
    }
}
