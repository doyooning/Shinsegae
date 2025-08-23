package BookMarket.com.market.main;

import BookMarket.com.market.bookitem.Book;
import BookMarket.com.market.cart.Cart;
import BookMarket.com.market.exception.CartException;
import BookMarket.com.market.member.Admin;
import BookMarket.com.market.member.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Welcome {
    // statics
    static Scanner sc = new Scanner(System.in);
	static Cart mCart = new Cart();
	static User mUser;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
    static final String MAIN_MENU_NUM = "[1-9]";
    static boolean flag = false;
    static int mTotalBook = 0;
    static ArrayList<Book> mBookList = new ArrayList<Book>();

    // main
	public static void main(String[] args) {
        bookListOnSale(mBookList);
        mUser = guestInfoInit();
        run();
	}

    // methods
    // 실행 전 데이터 세팅(객체 생성)
    public static User guestInfoInit() {
        System.out.print("당신의 이름을 입력하세요 : ");
        String userName = sc.nextLine();

        System.out.print("연락처를 입력하세요 : ");
        String userMobile = sc.nextLine();

        return new User(userName, userMobile);
    }

    public static void run() {
        while (!flag) {
            menuIntroduction();
            try {
                System.out.print("메뉴 번호를 선택해주세요 ");
                String selectNum = sc.nextLine();
                if (selectNum.matches(MAIN_MENU_NUM)) {
                    switch (selectNum) {
                        case "1" -> {
                            menuGuestInfo(mUser.getName(), mUser.getPhone());
                        }
                        case "2" -> {
                            menuCartItemList();
                        }
                        case "3" -> {
                            menuCartClear();
                        }
                        case "4" -> {
                            mTotalBook = totalFileToBookList();
                            menuCartAddItem(mBookList);
                        }
                        case "5" -> {
                            menuCartRemoveItemCount();
                        }
                        case "6" -> {
                            menuCartRemoveItem();
                        }
                        case "7" -> {
                            menuCartBill();
                        }
                        case "8" -> {
                            menuExit();
                        }
                        case "9" -> {
                            menuAdminLogin();
                        }
                    }
                } else {
                    System.out.println("1부터 9까지의 숫자를 입력하세요.");
                }
            } catch (CartException e) {
                System.out.println(e.getMessage());
                flag = true;
            } catch (Exception e) {
                System.out.println("올바르지 않은 메뉴 선택으로 종료합니다.");
                flag = true;
            }
        }

    }

	public static void menuIntroduction() {
        String header = """
                \t\t\t\tWelcome to Shopping Mall
                \t\t\t\tWelcome to Book Market!""";

        System.out.println("*".repeat(60));
        System.out.println(header);
        System.out.println("*".repeat(60));
        System.out.println("""
                1. 고객 정보 확인하기       \t6. 장바구니의 항목 삭제하기
                2. 장바구니 상품 목록 보기   \t7. 영수증 표시하기
                3. 장바구니 비우기          \t8. 종료
                4. 장바구니에 항목 추가하기   \t9. 관리자 로그인
                5. 장바구니 항목 수량 변경""");
        System.out.println("*".repeat(60));
    }

    // 고객 정보 확인하기
	public static void menuGuestInfo(String name, String mobile) {
		System.out.println("현재 고객 정보 : ");
		System.out.println("이름 " + mUser.getName() + "   연락처 " + mUser.getPhone());
	}

    // 장바구니 상품 목록 보기
	public static void menuCartItemList() {
		if (mCart.mCartCount > 0) {
			mCart.printCart();
		}

        if (mCart.mCartCount == 0) {
            System.out.println("장바구니에 항목이 없습니다.");
        }
	}

	// 장바구니 비우기
	public static void menuCartClear() throws CartException{
		if (mCart.mCartCount == 0) {
			System.out.println("장바구니에 항목이 없습니다.");
		} else {
			System.out.println("장바구니의 모든 항목을 삭제하겠습니까? Y | N ");
			String str = sc.nextLine();

			if (str.toUpperCase().equals("Y")) {
				System.out.println("장바구니의 모든 항목을 삭제했습니다.");
				mCart.clearBook();
			}
		}
	}

    // 장바구니 항목 추가
	public static void menuCartAddItem(ArrayList<Book> booklist) {
		mCart.printBookList(booklist);
		boolean quit = false;

		while (!quit) {
			System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");
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

			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N ");
				str = sc.nextLine();
				if (str.toUpperCase().equals("Y")) {
					System.out.println(booklist.get(numId).getBookId() + " 도서가 장바구니에 추가되었습니다.");
					// 장바구니에 넣기
                    if (!isCartInBook((booklist.get(numId).getBookId()))) {
                        mCart.insertBook(booklist.get(numId));
                    }
				}
				quit = true;
			}
			else
				System.out.println("다시 입력해 주세요");
		}
	}

    // 장바구니 항목 수량 변경
	public static void menuCartRemoveItemCount() throws CartException {

        if (mCart.mCartCount == 0) {
            throw new CartException("장비구니에 항목이 없습니다.");
        } else {
            menuCartItemList();
            boolean quit = false;
            while (!quit) {
                System.out.print("수량을 수정할 도서의 ID를 입력하세요 : ");
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
                if (flag) {
                    System.out.print("변경할 수량을 입력하세요(최소 1권) : ");
                    int quantity = Integer.parseInt(sc.nextLine());
                    mCart.mCartItem.get(numId).setQuantity(quantity);
                    System.out.println("수량 변경이 완료되었습니다.");
                    quit = true;
                } else System.out.println("다시 입력해 주세요");
            }
        }
	}

    // 장바구니의 항목 삭제하기
	public static void menuCartRemoveItem() throws CartException{
		if (mCart.mCartCount == 0) {
			throw new CartException("장비구니에 항목이 없습니다.");
		} else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				System.out.print("장바구니에서 삭제할 도서의 ID를 입력하세요 : ");
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
				if (flag) {
					System.out.println("장바구니의 항목을 삭제하겠습니까? Y | N ");
					str = sc.nextLine();
					if (str.toUpperCase().equals("Y")) {
						System.out.println(mCart.mCartItem.get(numId).getBookId() + " 장바구니에서 도서가 삭제되었습니다.");
						mCart.removeCart(numId);
					}
					quit = true;
				}
				else System.out.println("다시 입력해 주세요");
			}

		}
	}

    // 영수증 표시하기
	public static void menuCartBill() throws CartException{
		if (mCart.mCartCount == 0) {
			throw new CartException("장바구니에 항목이 없습니다.");
		} else {
			System.out.println("배송받을 분은 고객 정보와 같습니까? Y | N");
			String str = sc.nextLine();
			if (str.toUpperCase().equals("Y")) {
				System.out.println("배송지를 입력해주세요. ");
				String address = sc.nextLine();
				printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);
			} else {
				System.out.println("배송받을 고객명을 입력하세요. ");
				String name = sc.nextLine();
				System.out.println("배송받을 고객의 연락처를 입력하세요. ");
				String phone = sc.nextLine();
				System.out.println("배송받을 고객의 배송지를 입력하세요. ");
				String address = sc.nextLine();
				printBill(name, phone, address);
			}
		}
	}

    // 영수증 출력 로직
	public static void printBill(String name, String phone, String address) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("---------------배송받을 고객 정보---------------");
		System.out.println("고객명 : " + name + "  \t\t연락처 : " + phone);
		System.out.println("배송지 : " + address + "\t\t발송일 : " + strDate);

		mCart.printCart();

		int sum = 0;
		for (int i = 0; i < mCart.mCartCount; i++) {
			sum += mCart.mCartItem.get(i).getTotalPrice();
		}
        System.out.printf("""
                ---------------------------------------------
                \\t\\t\\t주문 총금액 : " + %d + "원\\n
                ---------------------------------------------
                \n""", sum);
	}

    // 종료
	public static void menuExit() {
		System.out.println("""
                \n##################
                    프로그램 종료
                ##################""");
        flag = true;
	}

    // 판매중인 책 목록
	public static void bookListOnSale(ArrayList<Book> booklist) {
		setFileToBookList(booklist);
	}

    // 장바구니에 이미 담았는지 확인
	public static boolean isCartInBook(String bookId) {
		return mCart.isCartInBook(bookId);
	}

    // 관리자 로그인
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");

		System.out.print("아이디 : ");
		String adminId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String adminPW = sc.nextLine();

		Admin admin = new Admin(mUser.getName(), mUser.getPhone());
		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			String[] writeBook = new String[7];
			System.out.println("도서 정보를 추가하겠습니까? Y | N ");
			String str = sc.nextLine();

			if (str.toUpperCase().equals("Y")) {
				Date date = new Date();
				String strDate = sdf.format(date);
				writeBook[0] = "ISBN" + strDate;
				System.out.println("도서ID : " + writeBook[0]);
				String st1 = sc.nextLine();
				System.out.println("도서명 : ");
				writeBook[1] = sc.nextLine();
				System.out.println("가격 : ");
				writeBook[2] = sc.nextLine();
				System.out.println("저자 : ");
				writeBook[3] = sc.nextLine();
				System.out.println("설명 : ");
				writeBook[4] = sc.nextLine();
				System.out.println("분야 : ");
				writeBook[5] = sc.nextLine();
				System.out.println("출판일 : ");
				writeBook[6] = sc.nextLine();

				try {
					FileWriter fw = new FileWriter("src\\BookMarket\\com\\market\\main\\book.txt", true);

					for (int i = 0; i < 7; i++) {
						fw.write(writeBook[i] + "\n");
						fw.close();
						System.out.println("새 도서 정보가 저장되었습니다.");
					}
				} catch (Exception e) {
					System.out.println(e);
				}

			} else {
				System.out.println("이름 " + admin.getName() + "   연락처 " + admin.getPhone());
				System.out.println("아이디 " + admin.getId() + "   비밀번호 " + admin.getPassword());
			}
		} else
			System.out.println("관리자 정보가 일치하지 않습니다.");
	}

    // 도서 목록 불러오는 메서드
	public static int totalFileToBookList() {
		try {
			FileReader fr = new FileReader("src\\BookMarket\\com\\market\\main\\book.txt");
			BufferedReader br = new BufferedReader(fr);

			String str;
			int num = 0;
			while ((str = br.readLine()) != null) {
				if(str.contains("ISBN")) {
					num++;
				}
			}

			br.close();
			fr.close();
			return num;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

    // 도서 목록 저장
	public static void setFileToBookList(ArrayList<Book> booklist) {
		try {
			FileReader fr = new FileReader("src\\BookMarket\\com\\market\\main\\book.txt");
			BufferedReader br = new BufferedReader(fr);

			String str2;
			String[] readBook = new String[7];

			while ((str2 = br.readLine()) != null) {
				if(str2.contains("ISBN")) {
					readBook[0] = str2;
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
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}