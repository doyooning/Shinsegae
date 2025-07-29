package day5;

import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userName;
        String userMobile;

        System.out.print("당신의 이름을 입력하세요 : ");
        userName = sc.nextLine();

        System.out.print("연락처를 입력하세요 : ");
        userMobile = sc.nextLine();

        Outer:
        while (true) {
            System.out.println("*****************************************************************");
            System.out.println("                Welcome to Shopping Mall");
            System.out.println("                Welcome to Book Market!");
            System.out.println("*****************************************************************");
            System.out.println("1. 고객 정보 확인하기       4. 바구니에 항목 추가하기");
            System.out.println("2. 장바구니 상품 목록 보기   5. 장바구니의 항목 수량 줄이기 ");
            System.out.println("3. 장바구니 비우기          6. 장바구니의 항목 삭제하기");
            System.out.println("7. 영수증 표시하기          8. 종료");
            System.out.println("*****************************************************************");
            System.out.print("메뉴 번호를 선택해주세요 ");
            int menuNumber = sc.nextInt();

            switch (menuNumber) {
                case 1:
                    System.out.println("현재 고객 정보 : ");
                    System.out.printf("이름 %s   연락처 %s\n", userName, userMobile);
                    break;
                case 2:
                    System.out.println("장바구니 상품 목록 보기");
                    break;
                case 3:
                    System.out.println("장바구니 비우기");
                    break;
                case 4:
                    System.out.println("바구니 항목 추가하기");
                    break;
                case 5:
                    System.out.println("장바구니의 항목 수량 줄이기");
                    break;
                case 6:
                    System.out.println("장바구니의 항목 삭제하기");
                    break;
                case 7:
                    System.out.println("영수증 표시하기");
                    break;
                case 8:
                    System.out.println("프로그램이 종료됩니다. ");
                    break Outer;
            }
        }
    }
}
