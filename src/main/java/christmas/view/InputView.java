package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String EXPECTED_VISIT_DATE_ASK_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    private InputView() {
    }

    public static int readDate() {
        System.out.println(EXPECTED_VISIT_DATE_ASK_MESSAGE);
        String input = Console.readLine();
        // ...
        return 0;
    }
    // ...
}