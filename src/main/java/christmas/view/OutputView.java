package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.Menu;
import christmas.domain.constants.Badge;
import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;
import java.util.Map;

public class OutputView {
    private static final String INFORMATION_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EXPECTED_VISIT_DATE_ASK_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_ORDER_ASK_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String RESULT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"
            + "\n"
            + "<주문 메뉴>\n"
            + "%s"
            + "\n"
            + "<할인 전 총주문 금액>\n"
            + "%s원\n"
            + "\n"
            + "<증정 메뉴>\n"
            + "%s\n"
            + "\n"
            + "<혜택 내역>\n"
            + "%s"
            + "\n"
            + "<총혜택 금액>\n"
            + "%s원\n"
            + "\n"
            + "<할인 후 예상 결제 금액>\n"
            + "%s원\n"
            + "\n"
            + "<12월 이벤트 배지>\n"
            + "%s";

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

    // 날짜, 주문 메뉴, 총 주문 금액, 증정 메뉴, 혜택 내역, 총 혜택 금액, 예상 결제 금액, 이벤트 배지
    public static void printResult(Calendar visitDate, Menu menus, Benefit benefit) {
        System.out.printf(RESULT_MESSAGE,
                visitDate.getDay(),
                createMenuOrderMessage(menus),
                String.format("%,d", menus.calculateTotalOrderAmount()),
                createGiftMenuMessage(benefit),
                createBenefitSummaryMessage(benefit),
                String.format("%,d", -benefit.getTotalBenefitAmount()),
                String.format("%,d", menus.calculateTotalOrderAmount() - benefit.getTotalDiscount()),
                createBadgeMessage(benefit.getBadge())
        );
    }

    private static String createMenuOrderMessage(Menu menus) {
        StringBuilder menuOrderString = new StringBuilder();
        Map<MenuBoard, Integer> orders = menus.getOrders();

        for (Map.Entry<MenuBoard, Integer> orderEntry : orders.entrySet()) {
            MenuBoard menuBoard = orderEntry.getKey();
            Integer quantity = orderEntry.getValue();

            menuOrderString.append(menuBoard.getName())
                    .append(" ")
                    .append(quantity)
                    .append("개\n");
        }

        return menuOrderString.toString();
    }

    private static String createGiftMenuMessage(Benefit benefit) {
        if (benefit.isGiftEvent()) {
            return "샴페인 1개";
        } else {
            return "없음";
        }
    }

    public static String createBenefitSummaryMessage(Benefit benefit) {
        StringBuilder summary = new StringBuilder();

        addDiscountLine(summary, "크리스마스 디데이 할인: -", benefit.getDDayDiscount());
        addDiscountLine(summary, "평일 할인: -", benefit.getWeekdayDiscount());
        addDiscountLine(summary, "주말 할인: -", benefit.getWeekendDiscount());
        addDiscountLine(summary, "특별 할인: -", benefit.getSpecialDiscount());

        if (benefit.isGiftEvent()) {
            addDiscountLine(summary, "증정 이벤트: -", MenuBoard.CHAMPAGNE.getPrice());
        }

        return !summary.isEmpty() ? summary.toString() : "없음\n";
    }

    private static void addDiscountLine(StringBuilder summary, String label, int amount) {
        if (amount > 0) {
            summary.append(label)
                    .append(String.format("%,d", amount))
                    .append("원\n");
        }
    }

    private static String createBadgeMessage(Badge badge) {
        if (badge == null) {
            return "없음";
        }
        return badge.getName();
    }

}