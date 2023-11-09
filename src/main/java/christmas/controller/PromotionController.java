package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {
    public PromotionController() {

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
        InputView.readDate();
    }

    private void askMenuOrder() {
    }

    private void printResult() {
    }
}
