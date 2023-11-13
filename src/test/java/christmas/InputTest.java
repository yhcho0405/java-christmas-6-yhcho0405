package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class InputTest extends NsTest {

    @Test
    void 유효한_날짜_입력() {
        assertSimpleTest(() -> {
            run("15", "티본스테이크-1,바비큐립-1");
            assertThat(output()).doesNotContain("[ERROR]");
        });
    }

    @Test
    void 유효하지_않은_날짜_입력() {
        assertSimpleTest(() -> {
            runException("32");
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
    void 메뉴판에_없는_메뉴_입력() {
        assertSimpleTest(() -> {
            runException("3", "피자-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 부적절한_수량_입력() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-0,바비큐립-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 잘못된_형식_입력() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크/1,바비큐립 1");
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
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 음료만_주문() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3,레드와인-2");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
