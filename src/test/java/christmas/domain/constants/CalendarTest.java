package christmas.domain.constants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CalendarTest {

    @Test
    void 주말_판별() {
        assertThat(Calendar.DAY_1.isWeekend()).isTrue();
        assertThat(Calendar.DAY_4.isWeekend()).isFalse();
    }

    @Test
    void 특별_할인_날짜_판별() {
        assertThat(Calendar.DAY_3.isSpecialDiscountDay()).isTrue();
        assertThat(Calendar.DAY_4.isSpecialDiscountDay()).isFalse();
    }

    @Test
    void 유효한_날짜로_달력_가져오기() {
        assertThat(Calendar.getByDay(3)).isEqualTo(Calendar.DAY_3);
    }

    @Test
    void 유효하지_않은_날짜로_달력_가져오기_예외() {
        assertThatThrownBy(() -> Calendar.getByDay(32))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
