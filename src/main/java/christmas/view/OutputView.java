package christmas.view;

public class OutputView {
    private static final String INFORMATION_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";

    private OutputView() {
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printInformation() {
        System.out.println(INFORMATION_MESSAGE);
        // ...
    }

    public static void printMenu() {
        System.out.println(ORDERED_MENU_MESSAGE);
        // ...
    }
    // ...
}