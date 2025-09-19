package BookMarket.com.market.exception;

import BookMarket.com.market.common.ErrorCode;

// 장바구니 예외 관리
public class CartException extends RuntimeException {
    private final ErrorCode error;

    public CartException(ErrorCode error) {
        super(error.getMsg());
        this.error = error;
    }
}
