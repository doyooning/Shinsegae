package BookMarket.com.market.common;

public enum Text {

    ENTER_USERNAME("당신의 이름을 입력해주세요. : "),
    ENTER_PHONE("연락처를 입력해주세요. : "),
    MENU_SELECT("메뉴 번호를 선택해주세요. : "),
    MAIN_MENU("""
                1. 고객 정보 확인하기       \t6. 장바구니의 항목 삭제하기
                2. 장바구니 상품 목록 보기   \t7. 영수증 표시하기
                3. 장바구니 비우기          \t8. 종료
                4. 장바구니에 항목 추가하기   \t9. 관리자 로그인
                5. 장바구니 항목 수량 변경"""),
    HEADER("""
                \t\t\t\tWelcome to Shopping Mall
                \t\t\t\tWelcome to Book Market!"""),
    BORDER_LINE_STAR("*".repeat(60)),
    BORDER_LINE_DASH("-".repeat(40)),

    CURRENT_USER_INFO("현재 고객 정보 : "),

    CART_CLEAR_CONFIRM("장바구니의 모든 항목을 삭제하겠습니까? Y | N "),
    CART_CLEAR_COMPLETE("장바구니의 모든 항목을 삭제했습니다."),

    ENTER_BOOK_ID_ADD("장바구니에 추가할 도서의 ID를 입력하세요 : "),
    ITEM_ADD_CONFIRM("장바구니에 추가하겠습니까? Y | N "),
    ITEM_ADD_COMPLETE(" 도서가 장바구니에 추가되었습니다."),

    ENTER_BOOK_ID_UPDATE("수량을 수정할 도서의 ID를 입력하세요 : "),
    UPDATE_BOOK_AMOUNT("변경할 수량을 입력하세요(최소 1권) : "),
    UPDATE_BOOK_AMOUNT_COMPLETE("수량 변경이 완료되었습니다."),

    ENTER_BOOK_ID_REMOVE("장바구니에서 삭제할 도서의 ID를 입력하세요 : "),
    ITEM_REMOVE_CONFIRM("장바구니의 항목을 삭제하겠습니까? Y | N "),
    ITEM_REMOVE_COMPLETE(" 도서가 장바구니에서 삭제되었습니다."),

    RECEIVER_EQUAL_CONFIRM("배송받을 분은 고객 정보와 같습니까? Y | N"),
    ENTER_ADDRESS("배송지를 입력해주세요. "),
    ENTER_RECEIVER("배송받을 고객명을 입력하세요. "),

    RECEIPT_USERINFO("------------배송받을 고객 정보-------------"),

    TOTAL_PRICE("주문 총금액 : "),

    EXIT_PROGRAM("""
                \n##################
                    프로그램 종료
                ##################"""),

    ENTER_ADMIN("관리자 정보를 입력하세요"),
    ID("아이디 : "),
    PW("비밀번호 : "),
    ADD_BOOK_DATA_CONFIRM("도서 정보를 추가하겠습니까? Y | N "),

    BOOK_ISBN("ISBN"),
    BOOK_ID("도서ID : "),
    BOOK_TITLE("도서명 : "),
    BOOK_PRICE("가격 : "),
    BOOK_AUTHOR("저자 : "),
    BOOK_DESCRIPTION("설명 : "),
    BOOK_CATEGORY("분야 : "),
    BOOK_RELEASE_DATE("출판일(yyyy/mm/dd) : "),

    BOOK_FILE_PATH("src\\BookMarket\\com\\market\\bookitem\\book.txt"),
    BOOK_SAVED("새 도서 정보가 저장되었습니다."),

    USERNAME("이름 "),
    PHONE("연락처 "),
    ADDRESS("배송지 "),
    DATE("발송일 ");


    private final String text;

    Text(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
