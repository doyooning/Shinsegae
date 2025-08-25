package BookMarket.com.market.common;

public enum ErrorCode {

    INVALID_MENU_OPTION("1부터 9까지의 숫자를 입력하세요."),
    INVALID_APPROACH("잘못된 접근입니다."),
    INVALID_SELECT_YN("Y / N 중에서 입력해 주십시오."),
    INVALID_BOOK_ID("해당하는 ID의 도서를 찾을 수 없습니다."),
    INVALID_EMPTY("수량을 입력해 주십시오."),
    INVALID_QUANTITY("유효한 숫자를 입력해 주십시오."),

    INVALID_ADMIN_INFO("관리자 정보가 일치하지 않습니다."),

    EMPTY_CARTITEM("장바구니에 항목이 없습니다."),

    FILE_NOT_FOUND("도서 목록을 불러오는 중 오류가 발생하였습니다."),
    FILE_IO_ERROR("도서 목록 입출력 중 오류가 발생하였습니다."),

    CRITICAL_ERROR("프로그램을 다시 실행하여 주십시오.");

    private String msg;

    ErrorCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
