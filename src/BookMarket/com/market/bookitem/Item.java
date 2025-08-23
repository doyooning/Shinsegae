package BookMarket.com.market.bookitem;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
    String bookId;
    String name;
    int unitPrice;

    public Item(String bookId, String name, int unitPrice) {
        this.bookId = bookId;
        this.name = name;
        this.unitPrice = unitPrice;
    }
}
