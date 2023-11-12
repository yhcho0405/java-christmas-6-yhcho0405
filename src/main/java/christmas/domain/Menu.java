package christmas.domain;

import christmas.domain.constants.MenuBoard;
import java.util.Map;

public class Menu {
    private Map<MenuBoard, Integer> orders;

    public Menu() {
    }

    public void setMenuOrder(Map<MenuBoard, Integer> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    public int getCountByCategory(MenuBoard.Category category) {
        int count = 0;
        for (Map.Entry<MenuBoard, Integer> entry : orders.entrySet()) {
            if (entry.getKey().getCategory() == category) {
                count += entry.getValue();
            }
        }
        return count;
    }

    public int calculateTotalOrderAmount() {
        int totalAmount = 0;

        for (Map.Entry<MenuBoard, Integer> orderEntry : orders.entrySet()) {
            MenuBoard menu = orderEntry.getKey();
            Integer quantity = orderEntry.getValue();

            totalAmount += menu.getPrice() * quantity;
        }

        return totalAmount;
    }

    public Map<MenuBoard, Integer> getOrders() {
        return orders;
    }

    private void validateOrders(Map<MenuBoard, Integer> orders) {
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        validateOrderQuantity(orders);
        validateBeverageOnlyOrder(orders);
    }

    private void validateOrderQuantity(Map<MenuBoard, Integer> orders) {
        int totalQuantity = 0;
        for (Integer quantity : orders.values()) {
            if (quantity < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            totalQuantity += quantity;
        }

        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateBeverageOnlyOrder(Map<MenuBoard, Integer> orders) {
        boolean nonBeverageOrderExists = orders.keySet().stream()
                .anyMatch(menu -> menu.getCategory() != MenuBoard.Category.BEVERAGE);

        if (!nonBeverageOrderExists) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
