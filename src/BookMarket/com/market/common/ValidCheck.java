package BookMarket.com.market.common;

import BookMarket.com.market.cart.Cart;
import BookMarket.com.market.exception.CartException;
import BookMarket.com.market.exception.MainException;

public class ValidCheck {
    private static final String MAIN_MENU_NUM = "^[1-9]$";
    private static final String CHECK_NUMBER = "^[1-9]+$";

    // 메뉴 유효 검사
    public void isMenuValid(String menuNum) {
        if (!(menuNum.matches(MAIN_MENU_NUM))) {
            throw new MainException(ErrorCode.INVALID_MENU_OPTION);
        }
    }

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

}
