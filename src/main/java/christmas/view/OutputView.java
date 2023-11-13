package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.Menu;
import christmas.domain.constants.Badge;
import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;
import christmas.view.constants.Message;
import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printInformation() {
        System.out.println(Message.INFORMATION.getMessage());
    }

    public static void printAskExpectedVisitDate() {
        System.out.println(Message.REQUEST_EXPECTED_VISIT_DATE.getMessage());
    }

    public static void printAskMenuOrder() {
        System.out.println(Message.REQUEST_MENU_ORDER.getMessage());
    }

    public static void printResult(Calendar visitDate, Menu menus, Benefit benefit) {
        System.out.printf(Message.RESULT.getMessage(),
                visitDate.getDay(),
                createMenuOrderMessage(menus),
                String.format(Message.NUMBER_FORMAT.getMessage(), menus.calculateTotalOrderAmount()),
                createGiftMenuMessage(benefit),
                createBenefitSummaryMessage(benefit),
                String.format(Message.NUMBER_FORMAT.getMessage(), -benefit.getTotalBenefitAmount()),
                String.format(Message.NUMBER_FORMAT.getMessage(),
                        menus.calculateTotalOrderAmount() - benefit.getTotalDiscount()),
                createBadgeMessage(benefit.getBadge())
        );
    }

    private static String createMenuOrderMessage(Menu menus) {
        StringBuilder menuOrderString = new StringBuilder();
        Map<MenuBoard, Integer> orders = menus.getOrders();

        for (Map.Entry<MenuBoard, Integer> orderEntry : orders.entrySet()) {
            MenuBoard menuBoard = orderEntry.getKey();
            int quantity = orderEntry.getValue();
            menuOrderString.append(menuBoard.getName())
                    .append(Message.SPACE.getMessage())
                    .append(quantity)
                    .append(Message.ITEM_QUANTITY.getMessage())
                    .append(Message.NEW_LINE.getMessage());
        }
        return menuOrderString.toString();
    }

    private static String createGiftMenuMessage(Benefit benefit) {
        if (benefit.isGiftEvent()) {
            return Message.GIFT.getMessage();
        } else {
            return Message.NO_ITEM.getMessage();
        }
    }

    private static String createBenefitSummaryMessage(Benefit benefit) {
        StringBuilder summary = new StringBuilder();
        addDiscountLine(summary, Message.D_DAY_DISCOUNT_LABEL.getMessage(), benefit.getDDayDiscount());
        addDiscountLine(summary, Message.WEEKDAY_DISCOUNT_LABEL.getMessage(), benefit.getWeekdayDiscount());
        addDiscountLine(summary, Message.WEEKEND_DISCOUNT_LABEL.getMessage(), benefit.getWeekendDiscount());
        addDiscountLine(summary, Message.SPECIAL_DISCOUNT_LABEL.getMessage(), benefit.getSpecialDiscount());

        if (benefit.isGiftEvent()) {
            addDiscountLine(summary, Message.GIFT_EVENT_LABEL.getMessage(), MenuBoard.CHAMPAGNE.getPrice());
        }
        if (!summary.isEmpty()) {
            return summary.toString();
        }
        return Message.NO_ITEM.getMessage() + Message.NEW_LINE.getMessage();
    }

    private static void addDiscountLine(StringBuilder summary, String label, int amount) {
        if (amount > 0) {
            summary.append(label)
                    .append(String.format(Message.NUMBER_FORMAT.getMessage(), amount))
                    .append(Message.WON.getMessage())
                    .append(Message.NEW_LINE.getMessage());
        }
    }

    private static String createBadgeMessage(Badge badge) {
        if (badge == null) {
            return Message.NO_ITEM.getMessage();
        }
        return badge.getName();
    }

}