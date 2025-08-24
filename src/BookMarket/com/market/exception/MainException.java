package BookMarket.com.market.exception;

import BookMarket.com.market.common.ErrorCode;

// 사용자 정의 예외 관리
public class MainException extends RuntimeException {
    private final ErrorCode error;

    public MainException(ErrorCode error) {
        super(error.getMsg());
        this.error = error;
    }
}
