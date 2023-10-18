package mft.test;

import mft.model.da.BookDa;
import mft.model.entity.Book;

public class BookTest {
    public static void main(String[] args) throws Exception {
        // test
        Book book = Book.builder().id(1).name("java ----").author("oracle ----").build();

        BookDa bookDa = new BookDa();
//        System.out.println(bookDa.save(book));
//        System.out.println(bookDa.edit(book));
        bookDa.remove(1);
    }
}
