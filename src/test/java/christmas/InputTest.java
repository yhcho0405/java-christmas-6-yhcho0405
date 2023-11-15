package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class InputTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 유효한_날짜_입력() {
        assertSimpleTest(() -> {
            run("15", "티본스테이크-1,바비큐립-1");
            assertThat(output()).doesNotContain("[ERROR]");
        });
    }

    @Test
    void 유효하지_않은_날짜_입력_1() {
        assertSimpleTest(() -> {
            runException("32");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 유효하지_않은_날짜_입력_2() {
        assertSimpleTest(() -> {
            runException("30.1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 유효하지_않은_날짜_입력_3() {
        assertSimpleTest(() -> {
            runException("-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 유효하지_않은_날짜_입력_4() {
        assertSimpleTest(() -> {
            runException("hello");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 유효하지_않은_날짜_입력_5() {
        assertSimpleTest(() -> {
            runException("\n");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 유효한_메뉴_및_수량_입력() {
        assertSimpleTest(() -> {
            run("10", "해산물파스타-2,레드와인-1");
            assertThat(output()).doesNotContain("[ERROR]");
        });
    }

    @Test
    void 메뉴판에_없는_메뉴_입력_1() {
        assertSimpleTest(() -> {
            runException("3", "피자-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴판에_없는_메뉴_입력_2() {
        assertSimpleTest(() -> {
            runException("3", "\n");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴판에_없는_메뉴_입력_3() {
        assertSimpleTest(() -> {
            runException("3", "-");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴판에_없는_메뉴_입력_4() {
        assertSimpleTest(() -> {
            runException("3", "1-피자");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 부적절한_수량_입력_1() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-0,바비큐립-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 부적절한_수량_입력_2() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크--1,바비큐립-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 부적절한_수량_입력_3() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-1.1,바비큐립-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 부적절한_수량_입력_4() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-asdf,바비큐립-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }


    @Test
    void 잘못된_형식_입력_1() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크/1,바비큐립 1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 잘못된_형식_입력_2() {
        assertSimpleTest(() -> {
            runException("3", "티본 스테이크 - 1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 잘못된_형식_입력_3() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크 -- 1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 중복_메뉴_입력() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-1,티본스테이크-1,바비큐립-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 총_주문_수량_초과() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-10,바비큐립-11");
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    void 음료만_주문() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3,레드와인-2");
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    void 크리스마스_디데이_할인_적용() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1");
            assertThat(output()).contains("크리스마스 디데이 할인: -1,200원");
        });
    }

    @Test
    void 평일_및_주말_할인_적용() {
        assertSimpleTest(() -> {
            run("4", "초코케이크-2");
            assertThat(output()).contains("평일 할인: -4,046원");
            run("8", "티본스테이크-1");
            assertThat(output()).contains("주말 할인: -2,023원");
        });
    }

    @Test
    void 특별_할인_적용() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1");
            assertThat(output()).contains("특별 할인: -1,000원");
        });
    }

    @Test
    void 증정_이벤트_적용() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-3");
            assertThat(output()).contains("증정 이벤트: -25,000원");
        });
    }

    @Test
    void 배지_부여() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-3");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
        });

        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
        });

        assertSimpleTest(() -> {
            run("4", "초코케이크-3");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "별");
        });

        assertSimpleTest(() -> {
            run("4", "초코케이크-7");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "트리");
        });
    }

    @Test
    void 주문_내역_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-2");
            assertThat(output()).contains("티본스테이크 1개", "바비큐립 2개");
        });
    }

    @Test
    void 할인_내역_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1");
            assertThat(output()).contains("크리스마스 디데이 할인: -1,200원");
        });
    }

    @Test
    void 총혜택_금액_출력() {
        assertSimpleTest(() -> {
            run("25", "양송이수프-1,타파스-1,시저샐러드-1,티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1,초코케이크-1,아이스크림-1,제로콜라-1,레드와인-1,샴페인-1");
            assertThat(output()).contains("<총혜택 금액>" + LINE_SEPARATOR + "-33,446원");
        });
    }

    @Test
    void 할인_후_예상_결제_금액_출력() {
        assertSimpleTest(() -> {
            run("25", "양송이수프-1,타파스-1,시저샐러드-1,티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1,초코케이크-1,아이스크림-1,제로콜라-1,레드와인-1,샴페인-1");
            assertThat(output()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "288,054원");
        });
    }

    @Test
    void 이벤트_배지_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-2");
            assertThat(output()).containsPattern("<12월 이벤트 배지>\\n(없음|별|트리|산타)");
        });
    }

    @Test
    void 혜택_없음_시_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
