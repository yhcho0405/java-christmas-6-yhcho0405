package christmas.view;

public class OutputView {
    private static final String INFORMATION_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EXPECTED_VISIT_DATE_ASK_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_ORDER_ASK_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";

    private OutputView() {
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printInformation() {
        System.out.println(INFORMATION_MESSAGE);
    }

    public static void printAskExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE_ASK_MESSAGE);
    }

    public static void printAskMenuOrder() {
        System.out.println(MENU_ORDER_ASK_MESSAGE);
    }

    public static void printMenu() {
        System.out.println(ORDERED_MENU_MESSAGE);
    }
}