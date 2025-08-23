package BookMarket.com.market.common;

public enum ErrorCode {

    INVALID_MENU_OPTION("1부터 9까지의 숫자를 입력하세요."),
    INVALID_APPROACH("잘못된 접근입니다."),
    EMPTY_CARTITEM("장바구니에 항목이 없습니다."),
    INVALID_ADMIN_INFO("관리자 정보가 일치하지 않습니다.");

    private String msg;

    ErrorCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
