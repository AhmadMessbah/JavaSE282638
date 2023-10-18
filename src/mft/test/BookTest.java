package mft.test;

import mft.controller.BookController;

public class BookTest {
    public static void main(String[] args) throws Exception {
        // test
        BookController.save("Ja#$%#%va", "Oracle");
        BookController.findAll();
    }
}
