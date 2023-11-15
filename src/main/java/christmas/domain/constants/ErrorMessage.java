package christmas.domain.constants;

public enum ErrorMessage {
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."),
    OVER_MAX_ORDER("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String MESSAGE_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return MESSAGE_PREFIX + message;
    }
}