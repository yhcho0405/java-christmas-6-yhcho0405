package christmas.domain.constants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MenuBoardTest {

    @Test
    void 메뉴_이름으로_메뉴_가져오기() {
        assertThat(MenuBoard.getByName("티본스테이크")).isEqualTo(MenuBoard.T_BONE_STEAK);
    }

    @Test
    void 존재하지_않는_메뉴_이름_검증() {
        assertThatThrownBy(() -> MenuBoard.getByName("피자"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴_속성_검증() {
        MenuBoard menu = MenuBoard.T_BONE_STEAK;
        assertThat(menu.getName()).isEqualTo("티본스테이크");
        assertThat(menu.getPrice()).isEqualTo(55_000);
        assertThat(menu.getCategory()).isEqualTo(MenuBoard.Category.MAIN);
    }
}
