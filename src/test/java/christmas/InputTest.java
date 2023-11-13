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

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
