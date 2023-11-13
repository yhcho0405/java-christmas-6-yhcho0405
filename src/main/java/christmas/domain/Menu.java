package christmas.domain;

import christmas.domain.constants.MenuBoard;
import christmas.domain.util.Validator;
import java.util.Map;

public class Menu {
    private Map<MenuBoard, Integer> orders;

    public Menu() {
    }

    public void setMenuOrder(Map<MenuBoard, Integer> orders) {
        Validator.validateOrders(orders);
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


}
