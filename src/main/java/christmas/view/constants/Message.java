package christmas.view.constants;

public enum Message {
    INFORMATION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_EXPECTED_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_MENU_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    RESULT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"
            + "\n"
            + "<주문 메뉴>\n"
            + "%s"
            + "\n"
            + "<할인 전 총주문 금액>\n"
            + "%s원\n"
            + "\n"
            + "<증정 메뉴>\n"
            + "%s\n"
            + "\n"
            + "<혜택 내역>\n"
            + "%s"
            + "\n"
            + "<총혜택 금액>\n"
            + "%s원\n"
            + "\n"
            + "<할인 후 예상 결제 금액>\n"
            + "%s원\n"
            + "\n"
            + "<12월 이벤트 배지>\n"
            + "%s"),

    D_DAY_DISCOUNT_LABEL("크리스마스 디데이 할인: -"),
    WEEKDAY_DISCOUNT_LABEL("평일 할인: -"),
    WEEKEND_DISCOUNT_LABEL("주말 할인: -"),
    SPECIAL_DISCOUNT_LABEL("특별 할인: -"),
    GIFT_EVENT_LABEL("증정 이벤트: -"),
    GIFT("샴페인 1개"),
    ITEM_QUANTITY("개"),
    NO_ITEM("없음"),
    WON("원"),
    NUMBER_FORMAT("%,d"),
    SPACE(" "),
    NEW_LINE("\n");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}