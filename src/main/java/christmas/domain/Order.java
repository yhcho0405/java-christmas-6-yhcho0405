package christmas.domain;

import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
    private Calendar visitDate;
    private Benefit benefit;
    private final Menu menus;

    public Order() {
        menus = new Menu();
    }

    public void setVisitDate(String expectedVisitDate) {
        validateExpectedVisitDate(expectedVisitDate);
        this.visitDate = Calendar.getByDay(Integer.parseInt(expectedVisitDate));
    }

    public void setMenuOrder(String menuOrder) {
        validateMenuOrder(menuOrder);

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


    private void validateExpectedVisitDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuOrder(String menuOrder) {
        if (menuOrder == null || menuOrder.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        Set<String> menuNames = new HashSet<>();
        String[] menuOrders = menuOrder.split(",");
        for (String order : menuOrders) {
            String menuName = validateIndividualOrder(order.trim());
            if (!menuNames.add(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private String validateIndividualOrder(String order) {
        String[] parts = order.split("-");

        if (parts.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        String menuName = parts[0].trim();
        String quantity = parts[1].trim();

        validateMenuName(menuName);
        validateMenuQuantity(quantity);

        return menuName;
    }

    private void validateMenuName(String menuName) {
        try {
            MenuBoard.getByName(menuName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuQuantity(String quantity) {
        try {
            if (Integer.parseInt(quantity) < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
