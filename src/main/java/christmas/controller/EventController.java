package christmas.controller;

import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class EventController {
    private final OrderService orderService;

    public EventController() {
        orderService = new OrderService();
    }

    public void start() {
        // 안내 메세지 출력
        printEventInformation();
        // 예상 방문 날짜 질문
        askExpectedVisitDate();
        // 주문할 메뉴 질문
        askMenuOrder();
        // 결과 출력
        printResult();
    }

    private void printEventInformation() {
        OutputView.printInformation();
    }

    private void askExpectedVisitDate() {
        OutputView.printAskExpectedVisitDate();

        errorHandler(() -> {
            String expectedVisitDate = InputView.readLine();
            orderService.setVisitDate(expectedVisitDate);
            return null;
        });
    }

    private void askMenuOrder() {
        OutputView.printAskMenuOrder();

        errorHandler(() -> {
            String menuOrder = InputView.readLine();
            orderService.setMenuOrder(menuOrder);
            return null;
        });
    }

    private void printResult() {
        orderService.calculateResult();
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
