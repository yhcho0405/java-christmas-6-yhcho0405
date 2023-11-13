package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        });
    }

    private void askMenuOrder() {
        OutputView.printAskMenuOrder();

        errorHandler(() -> {
            String menuOrder = InputView.readLine();
            order.setMenuOrder(menuOrder);
        });
    }

    private void printResult() {
        order.calculateBenefit();
        OutputView.printResult(order.getVisitDate(), order.getMenus(), order.getBenefit());
    }

    private void errorHandler(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
