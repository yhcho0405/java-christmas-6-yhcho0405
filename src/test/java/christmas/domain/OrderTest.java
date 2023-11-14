package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void 유효한_방문_날짜_설정() {
        order.setVisitDate("3");
        assertEquals(Calendar.DAY_3, order.getVisitDate());
    }

    @Test
    void 유효하지_않은_방문_날짜_설정() {
        assertThatThrownBy(() -> order.setVisitDate("32"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다.");
    }

    @Test
    void 유효한_메뉴_주문_설정() {
        order.setMenuOrder("티본스테이크-1,바비큐립-2");
        Map<MenuBoard, Integer> expectedOrders = new HashMap<>();
        expectedOrders.put(MenuBoard.T_BONE_STEAK, 1);
        expectedOrders.put(MenuBoard.BBQ_RIBS, 2);
        assertThat(order.getMenus().getOrders()).isEqualTo(expectedOrders);
    }

    @Test
    void 유효하지_않은_메뉴_주문_설정() {
        assertThatThrownBy(() -> order.setMenuOrder("피자-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }

    @Test
    void 혜택_계산() {
        order.setVisitDate("3");
        order.setMenuOrder("티본스테이크-1,바비큐립-2");
        order.calculateBenefit();
        assertThat(order.getBenefit()).isNotNull();
    }
}
