package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;

public class InputView {
    private static final String EXPECTED_VISIT_DATE_ASK_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    private InputView() {
    }

    public static int readExpectedVisitDate() {
        System.out.println(EXPECTED_VISIT_DATE_ASK_MESSAGE);
        String input = Console.readLine();
        validateReadDate(input);

        return Integer.parseInt(input);
    }

    public static void validateReadDate(String input) {
        try {
            int date = Integer.parseInt(input);

            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR]");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR]");
        }
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