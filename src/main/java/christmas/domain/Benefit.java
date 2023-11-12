package christmas.domain;

import christmas.domain.constants.Badge;
import christmas.domain.constants.Calendar;
import christmas.domain.constants.MenuBoard;

public class Benefit {
    private static final int WEEKDAY_DISCOUNT_PER_ITEM = 2_023;
    private static final int WEEKEND_DISCOUNT_PER_ITEM = 2_023;

    private final Calendar visitDate;
    private final Menu menus;

    private int dDayDiscount = 0;
    private int weekdayDiscount = 0;
    private int weekendDiscount = 0;
    private int specialDiscount = 0;
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
        if (menus.calculateTotalOrderAmount() < 10000) {
            resetDiscount();
        }
        if (menus.calculateTotalOrderAmount() >= 120000) {
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
        dDayDiscount = 0;
        weekdayDiscount = 0;
        weekendDiscount = 0;
        specialDiscount = 0;
        giftEvent = false;
    }

    private void calculateDDayDiscount() {
        if (visitDate.getDay() <= 25) {
            dDayDiscount = 1000 + (visitDate.getDay() - 1) * 100;
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
            specialDiscount = 1000;
        }
    }


}
