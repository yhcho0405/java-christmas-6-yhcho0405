package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constants.Badge;
import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BenefitTest {
    private Benefit benefit;
    private Menu menus;

    @BeforeEach
    void setUp() {
        menus = new Menu();
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.CHOCO_CAKE, 2);
        orders.put(MenuBoard.T_BONE_STEAK, 1);
        menus.setMenuOrder(orders);
    }

    @Test
    void 크리스마스_디데이_할인_적용() {
        benefit = new Benefit(Calendar.DAY_3, menus);
        benefit.calculateBenefit();
        assertThat(benefit.getDDayDiscount()).isEqualTo(1_200);
    }

    @Test
    void 평일_할인_적용() {
        benefit = new Benefit(Calendar.DAY_4, menus);
        benefit.calculateBenefit();
        assertThat(benefit.getWeekdayDiscount()).isEqualTo(4_046);
    }

    @Test
    void 주말_할인_적용() {
        benefit = new Benefit(Calendar.DAY_8, menus);
        benefit.calculateBenefit();
        assertThat(benefit.getWeekendDiscount()).isEqualTo(2_023);
    }

    @Test
    void 특별_할인_적용() {
        benefit = new Benefit(Calendar.DAY_3, menus);
        benefit.calculateBenefit();
        assertThat(benefit.getSpecialDiscount()).isEqualTo(1_000);
    }

    @Test
    void 증정_이벤트_및_배지_적용() {
        menus.setMenuOrder(new HashMap<>() {{
            put(MenuBoard.T_BONE_STEAK, 4);
        }});
        benefit = new Benefit(Calendar.DAY_3, menus);
        benefit.calculateBenefit();
        assertThat(benefit.isGiftEvent()).isTrue();
        assertThat(benefit.getBadge()).isEqualTo(Badge.SANTA);
    }

    @Test
    void 총혜택_금액_계산() {
        benefit = new Benefit(Calendar.DAY_3, menus);
        benefit.calculateBenefit();
        int expectedTotal = 1_200 + 4_046 + 1_000;
        assertThat(benefit.getTotalBenefitAmount()).isEqualTo(expectedTotal);
    }
}
