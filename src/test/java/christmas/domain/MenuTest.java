package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constants.MenuBoard;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void 유효한_메뉴_주문_설정() {
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.CHOCO_CAKE, 2);
        orders.put(MenuBoard.T_BONE_STEAK, 1);

        menu.setMenuOrder(orders);
        assertThat(menu.getOrders()).isEqualTo(orders);
    }

    @Test
    void 부적절한_메뉴_주문_설정() {
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.CHOCO_CAKE, -1);

        assertThatThrownBy(() -> menu.setMenuOrder(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 카테고리별_수량_계산() {
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.CHOCO_CAKE, 2);
        orders.put(MenuBoard.T_BONE_STEAK, 1);

        menu.setMenuOrder(orders);
        assertThat(menu.getCountByCategory(MenuBoard.Category.DESSERT)).isEqualTo(2);
        assertThat(menu.getCountByCategory(MenuBoard.Category.MAIN)).isEqualTo(1);
    }

    @Test
    void 총_주문_금액_계산() {
        Map<MenuBoard, Integer> orders = new HashMap<>();
        orders.put(MenuBoard.CHOCO_CAKE, 2);
        orders.put(MenuBoard.T_BONE_STEAK, 1);

        menu.setMenuOrder(orders);
        assertThat(menu.calculateTotalOrderAmount()).isEqualTo(85000);
    }
}
