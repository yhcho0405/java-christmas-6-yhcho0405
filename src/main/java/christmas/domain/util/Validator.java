package christmas.domain.util;

import christmas.domain.constants.ErrorMessage;
import christmas.domain.constants.MenuBoard;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Validator {
    private Validator() {
    }

    public static void validateExpectedVisitDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date <= 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public static void validateMenuOrder(String menuOrder) {
        if (menuOrder == null || menuOrder.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        Set<String> menuNames = new HashSet<>();
        String[] menuOrders = menuOrder.split(",");
        for (String order : menuOrders) {
            String menuName = validateIndividualOrder(order.trim());
            if (!menuNames.add(menuName)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
        }
    }

    public static String validateIndividualOrder(String order) {
        String[] parts = order.split("-");

        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        String menuName = parts[0].trim();
        String quantity = parts[1].trim();

        validateMenuName(menuName);
        validateMenuQuantity(quantity);

        return menuName;
    }

    public static void validateMenuName(String menuName) {
        try {
            MenuBoard.getByName(menuName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public static void validateMenuQuantity(String quantity) {
        try {
            if (Integer.parseInt(quantity) < 1) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public static void validateOrders(Map<MenuBoard, Integer> orders) {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        validateOrderQuantity(orders);
        validateBeverageOnlyOrder(orders);
    }

    public static void validateOrderQuantity(Map<MenuBoard, Integer> orders) {
        int totalQuantity = 0;
        for (Integer quantity : orders.values()) {
            if (quantity < 1) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
            totalQuantity += quantity;
        }

        if (totalQuantity > 20) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public static void validateBeverageOnlyOrder(Map<MenuBoard, Integer> orders) {
        boolean nonBeverageOrderExists = orders.keySet().stream()
                .anyMatch(menu -> menu.getCategory() != MenuBoard.Category.BEVERAGE);

        if (!nonBeverageOrderExists) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
