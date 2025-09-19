package BookMarket.com.market.main;

import BookMarket.com.market.bookitem.Book;
import BookMarket.com.market.cart.Cart;
import BookMarket.com.market.common.ErrorCode;
import BookMarket.com.market.common.Text;
import BookMarket.com.market.common.ValidCheck;
import BookMarket.com.market.exception.CartException;
import BookMarket.com.market.exception.MainException;
import BookMarket.com.market.member.Admin;
import BookMarket.com.market.member.User;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Welcome {
    // statics
    private static final ValidCheck validCheck = new ValidCheck();

    static Scanner sc = new Scanner(System.in);
    static Cart mCart = new Cart();
    static User mUser;
    static SimpleDateFormat sdfFull = new SimpleDateFormat("yyMMddhhmmss");
    static SimpleDateFormat sdfShort = new SimpleDateFormat("MM/dd/yyyy");

    static boolean flag = false;
    static int mTotalBook = 0;
    static ArrayList<Book> mBookList = new ArrayList<Book>();

    // main
	public static void main(String[] args) {
        mUser = guestInfoInit();
        run();
	}

    // methods
    // 실행 전 데이터 세팅(객체 생성)
    public static User guestInfoInit() {
        System.out.print(Text.ENTER_USERNAME.getText());
        String userName = sc.nextLine();

        System.out.print(Text.ENTER_PHONE.getText());
        String userMobile = sc.nextLine();

        return new User(userName, userMobile);
    }

    public static void run() {
        while (!flag) {
            try {
                bookListOnSale(mBookList);
                validCheck.isBookListValid(mTotalBook);
                menuIntroduction();
                System.out.print(Text.MENU_SELECT.getText());
                String selectNum = sc.nextLine();
                validCheck.isMenuValid(selectNum);
                switch (selectNum) {
                    case "1" -> menuGuestInfo(mUser.getName(), mUser.getPhone());
                    case "2" -> menuCartItemList();
                    case "3" -> menuCartClear();
                    case "4" -> menuCartAddItem(mBookList);
                    case "5" -> menuCartUpdateItemCount();
                    case "6" -> menuCartRemoveItem();
                    case "7" -> menuCartBill();
                    case "8" -> menuExit();
                    case "9" -> menuAdminLogin();
                }
            } catch (MainException | CartException e) {
                System.out.println(e.getMessage());
                run();
            } catch (Exception e) {
                run();
            }
        }
    }

	public static void menuIntroduction() {
        System.out.println(Text.BORDER_LINE_STAR.getText());
        System.out.println(Text.HEADER.getText());
        System.out.println(Text.BORDER_LINE_STAR.getText());
        System.out.println(Text.MAIN_MENU.getText());
        System.out.println(Text.BORDER_LINE_STAR.getText());
    }

    // 고객 정보 확인하기
	public static void menuGuestInfo(String name, String mobile) {
		System.out.println(Text.CURRENT_USER_INFO.getText());
		System.out.println(Text.USERNAME.getText() + name + "   " + Text.PHONE.getText() + mobile);
	}

    // 장바구니 상품 목록 보기
	public static void menuCartItemList() {
        validCheck.isCartEmpty(mCart);
        mCart.printCart();
	}

	// 장바구니 비우기
	public static void menuCartClear() throws CartException{
		validCheck.isCartEmpty(mCart);
        System.out.println(Text.CART_CLEAR_CONFIRM.getText());
        String str = sc.nextLine();
        validCheck.isYesOrNo(str);
        switch (str.toUpperCase()) {
            case "Y" -> {
                System.out.println(Text.CART_CLEAR_COMPLETE.getText());
                mCart.clearBook();
            }
        }
	}

    // 장바구니 항목 추가
	public static void menuCartAddItem(ArrayList<Book> booklist) {
        mTotalBook = totalFileToBookList();
        mCart.printBookList(booklist);
        System.out.print(Text.ENTER_BOOK_ID_ADD.getText());
        String str = sc.nextLine();

        boolean flag = false;
        int numId = -1;

        for (int i = 0; i < booklist.size(); i++) {
            if (str.equals(booklist.get(i).getBookId())) {
                numId = i;
                flag = true;
                break;
            }
        }
        validCheck.isPresentBookId(flag);
        System.out.println(Text.ITEM_ADD_CONFIRM.getText());
        str = sc.nextLine();
        validCheck.isYesOrNo(str);
        switch (str.toUpperCase()) {
            case "Y" -> {
                System.out.println(booklist.get(numId).getBookId() + Text.ITEM_ADD_COMPLETE.getText());
                // 장바구니에 넣기
                if (!isCartInBook((booklist.get(numId).getBookId()))) {
                    mCart.insertBook(booklist.get(numId));
                }
            }
		}
	}

    // 장바구니 항목 수량 변경
	public static void menuCartUpdateItemCount() {
        validCheck.isCartEmpty(mCart);
        menuCartItemList();

        System.out.print(Text.ENTER_BOOK_ID_UPDATE.getText());
        String str = sc.nextLine();
        boolean flag = false;
        int numId = -1;

        for (int i = 0; i < mCart.mCartCount; i++) {
            if (str.equals(mCart.mCartItem.get(i).getBookId())) {
                numId = i;
                flag = true;
                break;
            }
        }
        validCheck.isPresentBookId(flag);
        System.out.print(Text.UPDATE_BOOK_AMOUNT.getText());
        String quantity = sc.nextLine();

        validCheck.isValidQuantity(quantity);
        mCart.mCartItem.get(numId).setQuantity(Integer.parseInt(quantity));
        mCart.mCartItem.get(numId).updateTotalPrice();
        System.out.println(Text.UPDATE_BOOK_AMOUNT_COMPLETE.getText());
	}

    // 장바구니의 항목 삭제하기
	public static void menuCartRemoveItem() throws CartException{
		validCheck.isCartEmpty(mCart);
        menuCartItemList();

        System.out.print(Text.ENTER_BOOK_ID_REMOVE.getText());
        String str = sc.nextLine();
        boolean flag = false;
        int numId = -1;

        for (int i = 0; i < mCart.mCartCount; i++) {
            if (str.equals(mCart.mCartItem.get(i).getBookId())) {
                numId = i;
                flag = true;
                break;
            }
        }
        validCheck.isPresentBookId(flag);
        System.out.println(Text.ITEM_REMOVE_CONFIRM.getText());
        str = sc.nextLine();
        validCheck.isYesOrNo(str);
        switch(str.toUpperCase()) {
            case "Y" -> {
                System.out.println(mCart.mCartItem.get(numId).getBookId() + Text.ITEM_REMOVE_COMPLETE.getText());
                mCart.removeCart(numId);
            }
        }
	}

    // 영수증 표시하기
	public static void menuCartBill() throws CartException{
		validCheck.isCartEmpty(mCart);

        System.out.println(Text.RECEIVER_EQUAL_CONFIRM.getText());
        String str = sc.nextLine();
        validCheck.isYesOrNo(str);
        switch (str.toUpperCase()) {
            case "Y" -> {
                System.out.println(Text.ENTER_ADDRESS.getText());
                String address = sc.nextLine();
                printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);
            }
            case "N" -> {
                System.out.println(Text.ENTER_RECEIVER.getText());
                String name = sc.nextLine();
                System.out.println(Text.ENTER_PHONE.getText());
                String phone = sc.nextLine();
                System.out.println(Text.ENTER_ADDRESS.getText());
                String address = sc.nextLine();
                printBill(name, phone, address);
            }
        }
	}

    // 영수증 출력 로직
	public static void printBill(String name, String phone, String address) {
		String strDate = sdfShort.format(new Date());
		System.out.println();
		System.out.println(Text.RECEIPT_USERINFO.getText());
		System.out.println(Text.USERNAME.getText() + name + "  \t\t" + Text.PHONE.getText() + phone);
		System.out.println(Text.ADDRESS.getText() + address + "\t\t" + Text.DATE.getText() + strDate);

		mCart.printCart();

		int totalPrice = 0;
		for (int i = 0; i < mCart.mCartCount; i++) {
			totalPrice += mCart.mCartItem.get(i).getTotalPrice();
		}

        System.out.println(Text.BORDER_LINE_DASH.getText());
        System.out.printf("\t\t%s %d  원\n", Text.TOTAL_PRICE.getText(), totalPrice);
        System.out.println(Text.BORDER_LINE_DASH.getText());
	}

    // 종료
	public static void menuExit() {
		System.out.println(Text.EXIT_PROGRAM.getText());
        flag = true;
	}

    // 판매중인 책 목록
	public static void bookListOnSale(ArrayList<Book> booklist) {
        mBookList.clear();
		setFileToBookList(booklist);
	}

    // 장바구니에 이미 담았는지 확인
	public static boolean isCartInBook(String bookId) {
		return mCart.isCartInBook(bookId);
	}

    // 관리자 로그인
	public static void menuAdminLogin() {
        System.out.println(Text.ENTER_ADMIN.getText());

        System.out.print(Text.ID.getText());
        String adminId = sc.nextLine();

        System.out.print(Text.PW.getText());
        String adminPW = sc.nextLine();

        Admin admin = new Admin(mUser.getName(), mUser.getPhone());
        validCheck.isCorrectAccount(adminId, adminPW, admin);

        String[] writeBook = new String[7];
        System.out.println(Text.ADD_BOOK_DATA_CONFIRM.getText());
        String str = sc.nextLine();

        validCheck.isYesOrNo(str);
        switch (str.toUpperCase()) {
            case "Y" -> {
                // ISBN 생성 : ISBN0000(그냥 날짜 제일 뒤 4자리로 임의 생성)
                String strDate = sdfFull.format(new Date()).substring(8, 12);
                writeBook[0] = Text.BOOK_ISBN.getText() + strDate;
                System.out.println(Text.BOOK_ID.getText() + writeBook[0]);
                System.out.print(Text.BOOK_TITLE.getText());
                writeBook[1] = sc.nextLine();
                System.out.print(Text.BOOK_PRICE.getText());
                writeBook[2] = sc.nextLine();
                System.out.print(Text.BOOK_AUTHOR.getText());
                writeBook[3] = sc.nextLine();
                System.out.print(Text.BOOK_DESCRIPTION.getText());
                writeBook[4] = sc.nextLine();
                System.out.print(Text.BOOK_CATEGORY.getText());
                writeBook[5] = sc.nextLine();
                System.out.print(Text.BOOK_RELEASE_DATE.getText());
                writeBook[6] = sc.nextLine();

                try {
                    FileWriter fw = new FileWriter("src\\BookMarket\\com\\market\\bookitem\\book.txt", true);

                    for (int i = 0; i < 7; i++) {
                        fw.write(writeBook[i] + "\n");
                    }
                    fw.close();
                    System.out.println(Text.BOOK_SAVED.getText());
                } catch (FileNotFoundException e) {
                    System.out.println(ErrorCode.FILE_NOT_FOUND);
                } catch (Exception e) {
                    System.out.println(ErrorCode.FILE_IO_ERROR);
                    System.out.println(e);
                }
            }
            case "N" -> {
                System.out.println(Text.USERNAME.getText() + admin.getName() + "   " + Text.PHONE.getText() + admin.getPhone());
                System.out.println(Text.ID.getText() + admin.getId() + "   " + Text.PW.getText() + admin.getPassword());
            }
        }
    }

    // 도서 목록의 총 도서 개수
	public static int totalFileToBookList() {
		try {
			FileReader fr = new FileReader("src\\BookMarket\\com\\market\\bookitem\\book.txt");
			BufferedReader br = new BufferedReader(fr);

			String str;
			int num = 0;
			while ((str = br.readLine()) != null) {
				if(str.contains(Text.BOOK_ISBN.getText())) {
					num++;
				}
			}
			br.close();
			fr.close();
			return num;
		} catch (FileNotFoundException e) {
            System.out.println(ErrorCode.FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ErrorCode.FILE_IO_ERROR);
            System.out.println(e);
        }
		return -1;
	}

    // 도서 목록 저장
	public static void setFileToBookList(ArrayList<Book> booklist) {
        try {
            FileReader fr = new FileReader("src\\BookMarket\\com\\market\\bookitem\\book.txt");
            BufferedReader br = new BufferedReader(fr);

            String str;
            String[] readBook = new String[7];

            while ((str = br.readLine()) != null) {
                if (str.contains(Text.BOOK_ISBN.getText())) {
                    readBook[0] = str;
                    readBook[1] = br.readLine();
                    readBook[2] = br.readLine();
                    readBook[3] = br.readLine();
                    readBook[4] = br.readLine();
                    readBook[5] = br.readLine();
                    readBook[6] = br.readLine();
                }
                Book bookitem = new Book(readBook[0], readBook[1],
                        Integer.parseInt(readBook[2]), readBook[3],
                        readBook[4], readBook[5], readBook[6]);
                booklist.add(bookitem);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(ErrorCode.FILE_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(ErrorCode.FILE_IO_ERROR);
            System.out.println(e);
        }
	}
}