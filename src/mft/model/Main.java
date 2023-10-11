package mft.model;

import mft.model.da.BorrowDa;
import mft.model.entity.Borrow;

public class Main {
    public static void main(String[] args) throws Exception {
        BorrowDa borrowDa = new BorrowDa();
        for (Borrow borrow : borrowDa.findAll()) {
            System.out.println("Member : " + borrow.getMember());
            System.out.println("Book : " + borrow.getBook());
            System.out.println("Borrow Time : " + borrow.getBorrowTimeStamp());
            if(borrow.getReturnTimeStamp() == null){
                System.out.println("Not Returned");
            }else{
                System.out.println("Is Returned at " + borrow.getReturnTimeStamp());
            }
            System.out.println("------------------------------");
        }
    }
}
