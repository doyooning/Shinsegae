package BookMarket.com.market.common;

import BookMarket.com.market.cart.Cart;
import BookMarket.com.market.exception.CartException;
import BookMarket.com.market.exception.MainException;
import BookMarket.com.market.member.Admin;

public class ValidCheck {
    private static final String MAIN_MENU_NUM = "^[1-9]$";
    private static final String CHECK_NUMBER = "^[1-9]+$";
    private static final String SELECT_YES_NO = "^[YyNn]$";

    // 메뉴 유효 검사
    public void isMenuValid(String menuNum) {
        if (!(menuNum.matches(MAIN_MENU_NUM))) {
            throw new MainException(ErrorCode.INVALID_MENU_OPTION);
        }
    }

    // 도서 목록 유효 검사
    public void isBookListValid(int totalBook) {
        if (totalBook < 0) {
            throw new MainException(ErrorCode.CRITICAL_ERROR);
        }
    }

    // Y/N 선택 메뉴 유효 검사
    public void isYesOrNo(String str) {
        if (!(str.matches(SELECT_YES_NO))) {
            throw new MainException(ErrorCode.INVALID_SELECT_YN);
        }
    }

    // 장바구니가 비어있는 경우
    public void isCartEmpty(Cart mCart) {
        if (mCart.mCartCount == 0) {
            throw new CartException(ErrorCode.EMPTY_CARTITEM);
        }
    }

    // 변경할 수량 입력시 입력값이 숫자가 아닌 경우
    public void isValidQuantity(String quantity) {
        if (quantity.isEmpty()) {
            throw new MainException(ErrorCode.INVALID_EMPTY);
        }

        if (!(quantity.matches(CHECK_NUMBER))) {
            throw new MainException(ErrorCode.INVALID_QUANTITY);
        }
    }

    // 관리자 로그인 실패
    public void isCorrectAccount(String id, String pw, Admin admin) {
        if (!(id.equals(admin.getId()) && pw.equals(admin.getPassword()))) {
            throw new MainException(ErrorCode.INVALID_ADMIN_INFO);
        }
    }

    // 해당하는 도서 ID를 찾을 수 없을 때
    public void isPresentBookId(boolean flag) {
        if (!flag) {
            throw new MainException(ErrorCode.INVALID_BOOK_ID);
        }
    }
}
