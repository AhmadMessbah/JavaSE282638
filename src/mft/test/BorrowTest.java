package mft.test;

import mft.controller.BorrowController;
import mft.model.bl.BorrowBl;
import mft.model.da.BookDa;
import mft.model.da.BorrowDa;
import mft.model.da.MemberDa;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Member;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;

public class BorrowTest {
    public static void main(String[] args) throws Exception {

        MemberDa memberDa = new MemberDa();
        Member member1 = memberDa.findById(2);

        BookDa bookDa = new BookDa();
        Book book1 = bookDa.findById(3);


//        Borrow borrow = Borrow.builder()
//                .id(5)
//                .member(member1)
//                .book(book1)
//                .borrowTimeStamp(LocalDateTime.of(2023,10,22,11,00))
//                .returnTimeStamp(null)
//                .build();


//        BorrowDa borrowDa = new BorrowDa();
//        System.out.println(borrowDa.findAll());
//        System.out.println(borrowDa.findById(1));
//        System.out.println(borrowDa.findByBookId(2));
//        System.out.println(borrowDa.findByMemberId(1));
//        System.out.println(borrowDa.findByReturnStatus(true));
//        System.out.println(borrowDa.findByBorrowTimeStampRange(LocalDateTime.of(2023,10,15,10,30),LocalDateTime.now()));
//        System.out.println(borrowDa.edit(borrow));
//        borrowDa.remove(4);
//        System.out.println(borrowDa.edit(borrow));
//        System.out.println(borrowDa.save(borrow));
//        System.out.println(borrowDa.memberNotReturnedBooks(2));
//        System.out.println(borrowDa.findByBookName("javaSE"));
//        System.out.println(borrowDa.findByMemberNameFamily("ali","alipour"));

//        System.out.println(BorrowBl.findAll());
//        System.out.println(BorrowBl.findByBookId(2));
//        System.out.println(BorrowBl.findByMemberId(3));
//        System.out.println(BorrowBl.findByBorrowTimeStampRange(LocalDateTime.of(2023,9,15,14,30),LocalDateTime.now()));
//        System.out.println(BorrowBl.findById(4));
//        System.out.println(BorrowBl.findByReturnStatus(false));
//        System.out.println(BorrowBl.save(borrow));
//        System.out.println(BorrowBl.edit(borrow));
//        System.out.println(BorrowBl.remove(5));
//        System.out.println(BorrowBl.findByBookName("python"));
//        System.out.println(BorrowBl.findByMemberNameFamily("reza","rezaii"));


//        BorrowController.save(2,3,LocalDateTime.of(2023,10,22,11,05),null);
//        BorrowController.edit(9,3,3,LocalDateTime.of(2023,8,18,18,18),null);
//        BorrowController.remove(8);
//        BorrowController.findAll();
//        BorrowController.findById(6);
//        BorrowController.findByMemberId(2);
//        BorrowController.findByBookId(3);
//        BorrowController.findByBorrowTimeStampRange(LocalDateTime.of(2023,9,15,14,30),LocalDateTime.now());
//        BorrowController.findByReturnStatus(false);
//        BorrowController.findByBookName("python");
//        BorrowController.findByMemberNameFamily("mohsen","akbari");




    }
}
