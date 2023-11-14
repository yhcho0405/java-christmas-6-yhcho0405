package christmas.domain.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constants.MenuBoard;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void 유효하지_않은_방문_날짜_검증() {
        assertThatThrownBy(() -> Validator.validateExpectedVisitDate("32"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 잘못된_메뉴_주문_형식_검증() {
        assertThatThrownBy(() -> Validator.validateMenuOrder("피자-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 부적절한_메뉴_수량_검증() {
        assertThatThrownBy(() -> Validator.validateMenuQuantity("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 총_주문_수량_초과_검증() {
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.T_BONE_STEAK, 15);
        orders.put(MenuBoard.BBQ_RIBS, 6);

        assertThatThrownBy(() -> Validator.validateOrders(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음료만_주문_검증() {
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.ZERO_COLA, 3);
        orders.put(MenuBoard.RED_WINE, 2);

        assertThatThrownBy(() -> Validator.validateOrders(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_내역이_빈_경우() {
        Map<MenuBoard, Integer> orders = new HashMap<>();

        assertThatThrownBy(() -> Validator.validateOrders(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
