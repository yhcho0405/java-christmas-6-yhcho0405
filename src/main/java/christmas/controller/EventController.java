package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class EventController {
    private final Order order;

    public EventController() {
        order = new Order();
    }

    public void start() {
        printEventInformation();
        askExpectedVisitDate();
        askMenuOrder();
        printResult();
    }

    private void printEventInformation() {
        OutputView.printInformation();
    }

    private void askExpectedVisitDate() {
        OutputView.printAskExpectedVisitDate();

        errorHandler(() -> {
            String expectedVisitDate = InputView.readLine();
            order.setVisitDate(expectedVisitDate);
            return null;
        });
    }

    private void askMenuOrder() {
        OutputView.printAskMenuOrder();

        errorHandler(() -> {
            String menuOrder = InputView.readLine();
            order.setMenuOrder(menuOrder);
            return null;
        });
    }

    private void printResult() {
        order.calculateBenefit();
        OutputView.printResult(order.getVisitDate(), order.getMenus(), order.getBenefit());
    }

    private <T> T errorHandler(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
