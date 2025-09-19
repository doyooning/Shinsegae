package BookMarket.com.market.cart;

import BookMarket.com.market.bookitem.Book;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItem {

	private Book itemBook;
	private String bookId;
	private int quantity;
	private int totalPrice;
	
	public CartItem() {}

	public CartItem(Book booklist) {
		this.itemBook = booklist;
		this.bookId = booklist.getBookId();
		this.quantity = 1;
		updateTotalPrice();
	}

	public void updateTotalPrice() {
		totalPrice = this.itemBook.getUnitPrice() * this.quantity;
	}	
}