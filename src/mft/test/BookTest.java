package mft.test;

import mft.controller.BookController;
import mft.model.bl.BookBl;
import mft.model.da.BookDa;
import mft.model.entity.Book;

public class BookTest {
    public static void main(String[] args) throws Exception {
        // test
        System.out.println(BookController.save("Java", "Oracle"));
    }
}
