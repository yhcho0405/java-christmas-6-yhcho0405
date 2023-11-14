package christmas.domain.constants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    void 산타_배지_부여() {
        Badge badge = Badge.getBadgeByAmount(25_000);
        assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @Test
    void 트리_배지_부여() {
        Badge badge = Badge.getBadgeByAmount(15_000);
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @Test
    void 별_배지_부여() {
        Badge badge = Badge.getBadgeByAmount(7_000);
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @Test
    void 배지_부여_안됨() {
        Badge badge = Badge.getBadgeByAmount(3_000);
        assertThat(badge).isNull();
    }
}
