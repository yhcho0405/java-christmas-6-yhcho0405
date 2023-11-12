package christmas.service;

import christmas.domain.Order;

public class OrderService {
    private final Order order;

    public OrderService() {
        order = new Order();
    }

    public void setVisitDate(String expectedVisitDate) {
        order.setVisitDate(expectedVisitDate);
    }

    public void setMenuOrder(String menuOrder) {
        order.setMenuOrder(menuOrder);
    }

    public void calculateResult() {
        order.calculateResult();
    }
}
