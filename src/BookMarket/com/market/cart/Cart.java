package BookMarket.com.market.cart;

import BookMarket.com.market.bookitem.Book;

import java.util.ArrayList;

public class Cart implements CartInterface {
    // 장바구니 항목 리스트
    public ArrayList<CartItem> mCartItem = new ArrayList<CartItem>();

    // mCartCount는 장바구니에 담긴 종류가 다른 도서의 개수
    public static int mCartCount = 0;

    public Cart() {}

    // 도서 목록 출력
    public void printBookList(ArrayList<Book> booklist) {
        for (int i = 0; i < booklist.size(); i++) {
            Book bookitem = booklist.get(i);
            System.out.printf("%s | %s | %d | %s | %s | %s | %s\n",
                    bookitem.getBookId(), bookitem.getName(), bookitem.getUnitPrice(),
                    bookitem.getAuthor(), bookitem.getDescription(), bookitem.getCategory(),
                    bookitem.getReleaseDate());
        }
    }

    // 장바구니에 해당 도서가 담겨있는지 확인
    public boolean isCartInBook(String bookId) {
        boolean flag = false;
        for (int i = 0; i < mCartItem.size(); i++) {
            if (bookId.equals(mCartItem.get(i).getBookId())) {
                mCartItem.get(i).setQuantity(mCartItem.get(i).getQuantity() + 1);
                flag = true;
            }
        }
        return flag;
    }

    // 해당 도서를 장바구니 항목에 추가
    public void insertBook(Book book) {
        CartItem bookitem = new CartItem(book);
        mCartItem.add(bookitem);
        mCartCount = mCartItem.size();
    }

    // 해당 도서를 지움
    public void clearBook() {
        mCartItem.clear();
        mCartCount = 0;
    }

    public void printCart() {
        System.out.println("""
                장바구니 상품 목록 :
                ---------------------------------------
                    도서ID \t|     수량 \t|      합계
                ---------------------------------------""");
        for (int i = 0; i < mCartItem.size(); i++) {
            CartItem cartItem = mCartItem.get(i);
            System.out.printf("  %s  |     %d     |     %d\n",
                    cartItem.getBookId(), cartItem.getQuantity(), cartItem.getTotalPrice());
        }
    }

    public void removeCart(int numId) {
        mCartItem.remove(numId);
        mCartCount = mCartItem.size();
    }
}
