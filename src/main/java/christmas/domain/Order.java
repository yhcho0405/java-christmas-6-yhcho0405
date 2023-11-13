package christmas.domain;

import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;
import christmas.domain.util.Validator;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Calendar visitDate;
    private Benefit benefit;
    private final Menu menus;

    public Order() {
        menus = new Menu();
    }

    public void setVisitDate(String expectedVisitDate) {
        Validator.validateExpectedVisitDate(expectedVisitDate.trim());
        this.visitDate = Calendar.getByDay(
                Integer.parseInt(expectedVisitDate.trim())
        );
    }

    public void setMenuOrder(String menuOrder) {
        Validator.validateMenuOrder(menuOrder);

        Map<MenuBoard, Integer> orders = new HashMap<>();
        String[] menuOrders = menuOrder.split(",");
        for (String order : menuOrders) {
            String[] parts = order.split("-");
            String menuName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            MenuBoard menu = MenuBoard.getByName(menuName);
            orders.put(menu, orders.getOrDefault(menu, 0) + quantity);
        }
        menus.setMenuOrder(orders);
    }

    public void calculateBenefit() {
        benefit = new Benefit(visitDate, menus);
        benefit.calculateBenefit();
    }

    public Calendar getVisitDate() {
        return visitDate;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public Menu getMenus() {
        return menus;
    }
}
