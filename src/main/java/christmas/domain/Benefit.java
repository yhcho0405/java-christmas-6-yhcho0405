package christmas.domain;

import christmas.domain.constants.Badge;
import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;

public class Benefit {
    private static final int WEEKDAY_DISCOUNT_PER_ITEM = 2_023;
    private static final int WEEKEND_DISCOUNT_PER_ITEM = 2_023;
    private static final int D_DAY_DISCOUNT_START = 1_000;
    private static final int D_DAY_DISCOUNT_INCREMENT = 100;
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int MIN_ORDER_AMOUNT_FOR_DISCOUNT = 10_000;
    private static final int MIN_ORDER_AMOUNT_FOR_GIFT = 120_000;
    private static final int D_DAY_DISCOUNT_END_DAY = 25;
    private static final int DEFAULT_DISCOUNT = 0;

    private final Calendar visitDate;
    private final Menu menus;

    private int dDayDiscount = DEFAULT_DISCOUNT;
    private int weekdayDiscount = DEFAULT_DISCOUNT;
    private int weekendDiscount = DEFAULT_DISCOUNT;
    private int specialDiscount = DEFAULT_DISCOUNT;
    private Badge badge = null;
    private Boolean giftEvent = false;

    public Benefit(Calendar visitDate, Menu menus) {
        this.visitDate = visitDate;
        this.menus = menus;
    }

    public void calculateBenefit() {
        calculateDDayDiscount();
        calculateWeekdayDiscount();
        calculateWeekendDiscount();
        calculateSpecialDiscount();
        if (menus.calculateTotalOrderAmount() < MIN_ORDER_AMOUNT_FOR_DISCOUNT) {
            resetDiscount();
        }
        if (menus.calculateTotalOrderAmount() >= MIN_ORDER_AMOUNT_FOR_GIFT) {
            giftEvent = true;
        }
        badge = Badge.getBadgeByAmount(getTotalBenefitAmount());
    }

    public int getDDayDiscount() {
        return dDayDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public boolean isGiftEvent() {
        return giftEvent;
    }

    public Badge getBadge() {
        return badge;
    }

    public int getTotalBenefitAmount() {
        int totalBenefitAmount = dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        if (giftEvent) {
            totalBenefitAmount += MenuBoard.CHAMPAGNE.getPrice();
        }
        return totalBenefitAmount;
    }

    public int getTotalDiscount() {
        return dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
    }

    private void resetDiscount() {
        dDayDiscount = DEFAULT_DISCOUNT;
        weekdayDiscount = DEFAULT_DISCOUNT;
        weekendDiscount = DEFAULT_DISCOUNT;
        specialDiscount = DEFAULT_DISCOUNT;
        giftEvent = false;
    }

    private void calculateDDayDiscount() {
        if (visitDate.getDay() <= D_DAY_DISCOUNT_END_DAY) {
            dDayDiscount = D_DAY_DISCOUNT_START + (visitDate.getDay() - 1) * D_DAY_DISCOUNT_INCREMENT;
        }
    }

    private void calculateWeekdayDiscount() {
        if (!visitDate.isWeekend()) {
            int dessertCount = menus.getCountByCategory(MenuBoard.Category.DESSERT);
            weekdayDiscount = dessertCount * WEEKDAY_DISCOUNT_PER_ITEM;
        }
    }

    private void calculateWeekendDiscount() {
        if (visitDate.isWeekend()) {
            int mainCourseCount = menus.getCountByCategory(MenuBoard.Category.MAIN);
            weekendDiscount = mainCourseCount * WEEKEND_DISCOUNT_PER_ITEM;
        }
    }

    private void calculateSpecialDiscount() {
        if (visitDate.isSpecialDiscountDay()) {
            specialDiscount = SPECIAL_DISCOUNT;
        }
    }


}
