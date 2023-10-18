package mft.test;

import mft.model.da.BorrowDa;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Member;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;

public class BorrowTest {
    public static void main(String[] args) throws Exception {

        Member member1 = Member.builder().id(1).name("ali").family("alipour").build();
        Book book1 = Book.builder().id(1).name("javaSE").author("se").build();


        Borrow borrow = Borrow.builder()
                .member(member1)
                .book(book1)
                .borrowTimeStamp(LocalDateTime.of(2023,10,17,10,30))
                .returnTimeStamp(null)
                .build();


        BorrowDa borrowDa = new BorrowDa();
        //System.out.println(borrowDa.findAll());
//        System.out.println(borrowDa.findById(1));
//        System.out.println(borrowDa.findByBookId(2));
//        System.out.println(borrowDa.findByMemberId(1));
//        System.out.println(borrowDa.findByReturnStatus(true));
//        System.out.println(borrowDa.findByBorrowTimeStampRange(LocalDateTime.of(2023,10,15,10,30),LocalDateTime.now()));
//        System.out.println(borrowDa.edit(borrow));
//        borrowDa.remove(4);
//        System.out.println(borrowDa.edit(borrow));
        System.out.println(borrowDa.save(borrow));



    }
}
