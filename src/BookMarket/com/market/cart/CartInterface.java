package BookMarket.com.market.cart;

import BookMarket.com.market.bookitem.Book;

import java.util.ArrayList;

public interface CartInterface {
    void printBookList(ArrayList<Book> p);
    boolean isCartInBook(String Id);
    void insertBook(Book p);
    void removeCart(int numId);
    void clearBook();
}
